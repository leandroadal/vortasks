// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'mission_store.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$MissionStore on MissionStoreBase, Store {
  late final _$missionsAtom =
      Atom(name: 'MissionStoreBase.missions', context: context);

  @override
  ObservableList<Mission> get missions {
    _$missionsAtom.reportRead();
    return super.missions;
  }

  @override
  set missions(ObservableList<Mission> value) {
    _$missionsAtom.reportWrite(value, super.missions, () {
      super.missions = value;
    });
  }

  late final _$MissionStoreBaseActionController =
      ActionController(name: 'MissionStoreBase', context: context);

  @override
  void setMissions(List<Mission> mission) {
    final _$actionInfo = _$MissionStoreBaseActionController.startAction(
        name: 'MissionStoreBase.setMissions');
    try {
      return super.setMissions(mission);
    } finally {
      _$MissionStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void addMission(Mission mission) {
    final _$actionInfo = _$MissionStoreBaseActionController.startAction(
        name: 'MissionStoreBase.addMission');
    try {
      return super.addMission(mission);
    } finally {
      _$MissionStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void updateMission(Mission mission) {
    final _$actionInfo = _$MissionStoreBaseActionController.startAction(
        name: 'MissionStoreBase.updateMission');
    try {
      return super.updateMission(mission);
    } finally {
      _$MissionStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void deleteMission(String missionId) {
    final _$actionInfo = _$MissionStoreBaseActionController.startAction(
        name: 'MissionStoreBase.deleteMission');
    try {
      return super.deleteMission(missionId);
    } finally {
      _$MissionStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
missions: ${missions}
    ''';
  }
}
