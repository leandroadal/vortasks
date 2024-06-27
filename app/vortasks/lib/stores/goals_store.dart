import 'dart:async';
import 'dart:convert';
import 'package:mobx/mobx.dart';
import 'package:vortasks/core/storage/local_storage.dart';
import 'package:vortasks/models/goals/goals.dart';

part 'goals_store.g.dart';

class GoalsStore = GoalsStoreBase with _$GoalsStore;

abstract class GoalsStoreBase with Store {
  GoalsStoreBase() {
    _loadGoals();
    _initializeResetChecks();
  }

  @observable
  Goals goals = Goals(
    id: '...',
    daily: 5,
    weekly: 50,
    dailyGoalProgress: 0,
    weeklyGoalProgress: 0,
  );

  @action
  void setGoals(Goals goals) {
    this.goals = goals;
  }

  @action
  void updateGoals(
      int daily, int weekly, int dailyGoalProgress, int weeklyGoalProgress) {
    goals = Goals(
      id: goals.id,
      daily: daily,
      weekly: weekly,
      dailyGoalProgress: dailyGoalProgress,
      weeklyGoalProgress: weeklyGoalProgress,
    );
    _saveGoals();
  }

  // ----- Funções para reiniciar o progresso das metas -----

  @action
  void resetDailyProgress() {
    goals = goals.copyWith(dailyGoalProgress: 0);
    _saveGoals();
    _saveLastDailyReset();
  }

  @action
  void resetWeeklyProgress() {
    goals = goals.copyWith(weeklyGoalProgress: 0);
    _saveGoals();
  }

  @action
  void incrementGoalsCompleted() {
    goals = goals.copyWith(
      dailyGoalProgress: goals.dailyGoalProgress + 1,
      weeklyGoalProgress: goals.weeklyGoalProgress + 1,
    );
    _saveGoals();
  }

  void _initializeResetChecks() {
    _checkAndResetDailyProgress();
    _initializeTimers();
  }

  void _checkAndResetDailyProgress() {
    final now = DateTime.now();
    final lastDailyReset = _loadLastDailyReset();
    if (lastDailyReset == null || !isSameDay(now, lastDailyReset)) {
      resetDailyProgress();
    }
    if (now.weekday == DateTime.monday) {
      resetWeeklyProgress();
    }
  }

  bool isSameDay(DateTime date1, DateTime date2) {
    return date1.year == date2.year &&
        date1.month == date2.month &&
        date1.day == date2.day;
  }

  void _initializeTimers() {
    final now = DateTime.now();
    final nextMidnight = DateTime(now.year, now.month, now.day + 1, 0, 0);

    Timer(nextMidnight.difference(now), () {
      resetDailyProgress();
      if (DateTime.now().weekday == DateTime.monday) {
        resetWeeklyProgress();
      }
      _initializeTimers(); // Re-agendar o timer para o próximo dia
    });
  }

  // ----- Funções para salvar no Armazenamento local -----

  DateTime? _loadLastDailyReset() {
    final lastReset = LocalStorage.getString('lastDailyReset');
    return lastReset != null ? DateTime.parse(lastReset) : null;
  }

  void _saveLastDailyReset() {
    LocalStorage.saveData('lastDailyReset', DateTime.now().toIso8601String());
  }

  // Carregar goals do armazenamento local
  void _loadGoals() {
    final goalsJson = LocalStorage.getString('goals');
    if (goalsJson != null) {
      goals = Goals.fromJson(json.decode(goalsJson));
    }
  }

  // Salvar goals no armazenamento local
  void _saveGoals() {
    LocalStorage.saveData('goals', json.encode(goals.toJson()));
  }
}
