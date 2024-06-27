// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'achievement_store.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$AchievementStore on AchievementStoreBase, Store {
  late final _$existingAchievementsAtom =
      Atom(name: 'AchievementStoreBase.existingAchievements', context: context);

  @override
  ObservableList<Achievement> get existingAchievements {
    _$existingAchievementsAtom.reportRead();
    return super.existingAchievements;
  }

  @override
  set existingAchievements(ObservableList<Achievement> value) {
    _$existingAchievementsAtom.reportWrite(value, super.existingAchievements,
        () {
      super.existingAchievements = value;
    });
  }

  late final _$achievementsAtom =
      Atom(name: 'AchievementStoreBase.achievements', context: context);

  @override
  ObservableList<Achievement> get achievements {
    _$achievementsAtom.reportRead();
    return super.achievements;
  }

  @override
  set achievements(ObservableList<Achievement> value) {
    _$achievementsAtom.reportWrite(value, super.achievements, () {
      super.achievements = value;
    });
  }

  late final _$AchievementStoreBaseActionController =
      ActionController(name: 'AchievementStoreBase', context: context);

  @override
  void setAchievement(List<Achievement> achievements) {
    final _$actionInfo = _$AchievementStoreBaseActionController.startAction(
        name: 'AchievementStoreBase.setAchievement');
    try {
      return super.setAchievement(achievements);
    } finally {
      _$AchievementStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void addAchievement(Achievement achievement) {
    final _$actionInfo = _$AchievementStoreBaseActionController.startAction(
        name: 'AchievementStoreBase.addAchievement');
    try {
      return super.addAchievement(achievement);
    } finally {
      _$AchievementStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void updateAchievement(Achievement achievement) {
    final _$actionInfo = _$AchievementStoreBaseActionController.startAction(
        name: 'AchievementStoreBase.updateAchievement');
    try {
      return super.updateAchievement(achievement);
    } finally {
      _$AchievementStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void deleteAchievement(String achievementId) {
    final _$actionInfo = _$AchievementStoreBaseActionController.startAction(
        name: 'AchievementStoreBase.deleteAchievement');
    try {
      return super.deleteAchievement(achievementId);
    } finally {
      _$AchievementStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
existingAchievements: ${existingAchievements},
achievements: ${achievements}
    ''';
  }
}
