import 'dart:async';
import 'dart:convert';
import 'dart:developer';

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:get_it/get_it.dart';
import 'package:mobx/mobx.dart';
import 'package:vortasks/controllers/backup_controller.dart';
import 'package:vortasks/core/storage/local_storage.dart';
import 'package:vortasks/models/backup/backup.dart';
import 'package:vortasks/stores/user_data/achievement_store.dart';
import 'package:vortasks/stores/user_data/checkin_store.dart';
import 'package:vortasks/stores/user_data/mission_store.dart';
import 'package:vortasks/stores/goals_store.dart';
import 'package:vortasks/stores/skill_store.dart';
import 'package:vortasks/stores/task_store.dart';

part 'backup_store.g.dart';

const String taskName = 'backupTask';

class BackupStore = BackupStoreBase with _$BackupStore;

abstract class BackupStoreBase with Store {
  BackupStoreBase() {
    _loadBackup();
    _setupReactions();
    _loadFrequency();
  }

  @observable
  Backup? backup;

  @action
  void setBackup(Backup backup) {
    this.backup = backup;
    GetIt.I<GoalsStore>().setGoals(backup.goals);
    GetIt.I<CheckInStore>().setCheckInDays(backup.checkInDays);
    GetIt.I<AchievementStore>().setAchievement(backup.achievements);
    GetIt.I<TaskStore>().setTasks(backup.tasks);
    GetIt.I<MissionStore>().setMissions(backup.missions);
    GetIt.I<SkillStore>().setSkills(backup.skills);
    _saveBackup();
  }

  @computed
  Backup get latestBackupData {
    return Backup(
      id: backup?.id ?? '..',
      lastModified: DateTime.now().toUtc(),
      goals: GetIt.I<GoalsStore>().goals,
      checkInDays: GetIt.I<CheckInStore>().checkInDays,
      achievements: GetIt.I<AchievementStore>().achievements,
      tasks: GetIt.I<TaskStore>().tasks,
      missions: GetIt.I<MissionStore>().missions,
      skills: GetIt.I<SkillStore>().skills,
    );
  }
  /*
  @observable
  DateTime? lastModified;

  @action
  void setLastModified(DateTime lastModified) {
    this.lastModified = lastModified;
    _saveBackupLastModified();
  }
  */

  @observable
  Map<String, bool> expandedCategories = {
    'Goals': false,
    'Achievements': false,
    'Tasks': false,
    'Missions': false,
    'Skills': false,
    'CheckInDays': false,
  };

  @action
  void setExpandedCategory(String categoryName, bool isExpanded) {
    expandedCategories[categoryName] = isExpanded;
  }

  @action
  Future<void> resolveConflictWithRemote() async {
    // Força os dados remotos
    setBackup(remoteBackup!);
    hasConflict = false;
    localBackup = null;
    remoteBackup = null;
  }

  @action
  Future<void> resolveConflictWithLocal() async {
    await BackupController().updateBackup();
    hasConflict = false;
    localBackup = null;
    remoteBackup = null;
  }

  @observable
  bool hasConflict = false;

  @action
  void setHasConflict(bool value) => hasConflict = value;

  @observable
  Backup? localBackup;

  @observable
  Backup? remoteBackup;

  @action
  void setLocalBackup(Backup? progress) => localBackup = progress;

  @action
  void setRemoteBackup(Backup? progress) => remoteBackup = progress;

  @observable
  String? error;

  @action
  setError(String? value) => error = value;

  Future<void> create() async {
    error = null;
    try {
      await BackupController().createBackup();
    } catch (e) {
      error = 'Erro ao criar o backup';
      log('Erro ao sincronizar backup: $e');
    }
  }

  Future<void> sync() async {
    error = null;
    final oldBackup = backup;
    try {
      await BackupController().updateBackup();
    } catch (e) {
      backup = oldBackup;
      error = 'Erro ao sincronizar backup';
      log('Erro ao sincronizar backup: $e');
    }
  }

  Future<void> syncAferLogin() async {
    error = null;
    try {
      await BackupController().getBackupAfterLogin();
    } catch (e) {
      log('Erro ao sincronizar backup: $e');
    }
  }

  // Carrega o backup do armazenamento local
  void _loadBackup() {
    final backupJson = LocalStorage.getString('backup');
    if (backupJson != null) {
      backup = Backup.fromJson(json.decode(backupJson));
    }
  }

  // Salva o backup no armazenamento local
  void _saveBackup() {
    LocalStorage.saveData('backup', json.encode(backup?.toJson()));
  }

  /*
  void _loadBackupLastModified() {
    final lastModifiedStr = LocalStorage.getString('backupLastModified');
    if (lastModifiedStr != null) {
      lastModified = DateTime.parse(lastModifiedStr);
    }
  }
  void _saveBackupLastModified() {
    LocalStorage.saveData(
        'backupLastModified', lastModified?.toUtc().toIso8601String());
  }
   */

  // --------- Definindo a Frequência do Backup ---------

  @observable
  String? selectedFrequency;

  Timer? _syncTimer;
  DateTime? _nextBackupTime;

  void _setupReactions() {
    if (selectedFrequency == null) {
      return;
    }
    // Reaction para agendamento do backup
    reaction((_) => selectedFrequency, (frequency) {
      _scheduleBackup(frequency);
    });

    // Reaction para verificar conectividade e sincronizar
    reaction((_) => Connectivity().checkConnectivity(), (futureResult) async {
      final connectivityResult = await futureResult;
      if (connectivityResult
          .any((result) => result != ConnectivityResult.none)) {
        _checkAndSyncBackup();
      }
    });
  }

  void _checkAndSyncBackup() {
    if (_nextBackupTime != null && DateTime.now().isAfter(_nextBackupTime!)) {
      sync(); // Tenta enviar para o servidor
      _scheduleBackup(selectedFrequency); // Agenda o próximo backup
    }
  }

  void _scheduleBackup(String? frequency) {
    _syncTimer?.cancel();

    DateTime now = DateTime.now();
    DateTime scheduledTime = DateTime(now.year, now.month, now.day, 2, 0, 0);

    if (frequency == 'weekly' && now.weekday != DateTime.sunday) {
      scheduledTime = scheduledTime.add(Duration(days: 7 - now.weekday));
    }

    if (scheduledTime.isBefore(now)) {
      scheduledTime =
          scheduledTime.add(Duration(days: frequency == 'daily' ? 1 : 7));
    }

    _nextBackupTime = scheduledTime;
    print('Próximo backup agendado para: $_nextBackupTime');

    //Duration timeUntilBackup = scheduledTime.difference(now);
  }

  @action
  void setSelectedFrequency(String? frequency) {
    selectedFrequency = frequency;
    _saveFrequency(frequency);
  }

  void _saveFrequency(String? frequency) {
    LocalStorage.saveData('backupFrequency', frequency);
  }

  void _loadFrequency() {
    selectedFrequency = LocalStorage.getString('backupFrequency') ?? '12h';
  }
}
