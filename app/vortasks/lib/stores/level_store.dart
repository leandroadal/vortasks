import 'package:mobx/mobx.dart';
import 'package:vortasks/core/storage/local_storage.dart';

part 'level_store.g.dart';

class LevelStore = LevelStoreBase with _$LevelStore;

abstract class LevelStoreBase with Store {
  LevelStoreBase() {
    _loadLevelData();
  }

  @observable
  int currentLevel = 1;

  @observable
  int xp = 0;

  @observable
  int xpToNextLevel = 1000;

  @computed
  double get xpPercentage => (xp / xpToNextLevel) * 100;

  @action
  void addXP(int gainedXP) {
    xp += gainedXP;
    if (xp >= xpToNextLevel) {
      levelUp();
    }
    _saveLevelData();
  }

  @action
  void rmXP(int loseXP) {
    xp -= loseXP;
    if (xp >= xpToNextLevel) {
      levelUp();
    }
    _saveLevelData();
  }

  @action
  void levelUp() {
    currentLevel++;
    xp = 0;
    xpToNextLevel += 500;
    _saveLevelData();
  }

  // Carregar dados de nível do armazenamento local
  void _loadLevelData() {
    currentLevel = LocalStorage.getString('currentLevel') != null
        ? int.parse(LocalStorage.getString('currentLevel')!)
        : 1;
    xp = LocalStorage.getString('xp') != null
        ? int.parse(LocalStorage.getString('xp')!)
        : 0;
    xpToNextLevel = LocalStorage.getString('xpToNextLevel') != null
        ? int.parse(LocalStorage.getString('xpToNextLevel')!)
        : 1000;
  }

  // Salvar dados de nível no armazenamento local
  void _saveLevelData() {
    LocalStorage.saveData('currentLevel', currentLevel.toString());
    LocalStorage.saveData('xp', xp.toString());
    LocalStorage.saveData('xpToNextLevel', xpToNextLevel.toString());
  }
}
