import 'package:mobx/mobx.dart';
import 'package:vortasks/core/storage/local_storage.dart';

import 'dart:convert';

import 'package:vortasks/models/mission/mission.dart';

part 'mission_store.g.dart';

class MissionStore = MissionStoreBase with _$MissionStore;

abstract class MissionStoreBase with Store {
  MissionStoreBase() {
    _loadMissions();
  }

  @observable
  ObservableList<Mission> missions = ObservableList<Mission>();

  @action
  void setMissions(List<Mission> mission) {
    missions.clear();
    missions.addAll(mission);
    _saveMissions();
  }

  @action
  void addMission(Mission mission) {
    missions.add(mission);
    _saveMissions();
  }

  @action
  void updateMission(Mission mission) {
    final index = missions.indexWhere((m) => m.id == mission.id);
    if (index != -1) {
      missions[index] = mission;
      _saveMissions();
    }
  }

  @action
  void deleteMission(String missionId) {
    missions.removeWhere((mission) => mission.id == missionId);
    _saveMissions();
  }

  // Carregar missions do armazenamento local
  void _loadMissions() {
    final missionsJson = LocalStorage.getString('missions');
    if (missionsJson != null) {
      missions = ObservableList.of(
          (json.decode(missionsJson) as List).map((e) => Mission.fromJson(e)));
    }
  }

  // Salvar missions no armazenamento local
  void _saveMissions() {
    LocalStorage.saveData('missions', json.encode(missions));
  }
}
