import 'package:mobx/mobx.dart';
import 'package:vortasks/core/storage/local_storage.dart';

import 'dart:convert';

import 'package:vortasks/models/achievement/achievement.dart';

part 'achievement_store.g.dart';

class AchievementStore = AchievementStoreBase with _$AchievementStore;

abstract class AchievementStoreBase with Store {
  AchievementStoreBase() {
    existingAchievements = ObservableList.of(defaultAchievements);
    _loadAchievements();
  }

  @observable
  ObservableList<Achievement> existingAchievements =
      ObservableList<Achievement>();

  @observable
  ObservableList<Achievement> achievements = ObservableList<Achievement>();

  @action
  void setAchievement(List<Achievement> achievements) {
    this.achievements.clear();
    this.achievements.addAll(achievements);
    _saveAchievements();
  }

  @action
  void addAchievement(Achievement achievement) {
    achievements.add(achievement);
    _saveAchievements();
  }

  @action
  void updateAchievement(Achievement achievement) {
    final index = achievements.indexWhere((a) => a.id == achievement.id);
    if (index != -1) {
      achievements[index] = achievement;
      _saveAchievements();
    }
  }

  @action
  void deleteAchievement(String achievementId) {
    achievements.removeWhere((achievement) => achievement.id == achievementId);
    _saveAchievements();
  }

  // Carregar achievements do armazenamento local
  void _loadAchievements() {
    final achievementsJson = LocalStorage.getString('achievements');
    if (achievementsJson != null) {
      achievements = ObservableList.of((json.decode(achievementsJson) as List)
          .map((e) => Achievement.fromJson(e)));
    }
  }

  // Salvar achievements no armazenamento local
  void _saveAchievements() {
    LocalStorage.saveData('achievements', json.encode(achievements));
  }
}
