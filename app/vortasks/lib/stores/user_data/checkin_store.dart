import 'package:mobx/mobx.dart';
import 'package:vortasks/core/storage/local_storage.dart';

import 'dart:convert';

import 'package:vortasks/models/checkin/check_in_days.dart';

part 'checkin_store.g.dart';

class CheckInStore = CheckInStoreBase with _$CheckInStore;

abstract class CheckInStoreBase with Store {
  CheckInStoreBase() {
    _loadCheckInData();
  }

  @observable
  CheckInDays checkInDays =
      CheckInDays(id: '...', days: 0, month: DateTime.now().toUtc().month);

  @action
  void setCheckInDays(CheckInDays checkIn) {
    checkInDays = checkIn;
    _saveCheckInData();
  }

  @action
  void checkIn() {
    if (checkInDays.month == DateTime.now().month) {
      checkInDays = CheckInDays(
          id: checkInDays.id,
          days: checkInDays.days + 1,
          month: checkInDays.month);
    } else {
      checkInDays =
          CheckInDays(id: checkInDays.id, days: 1, month: DateTime.now().month);
    }
    _saveCheckInData();
  }

  // Carregar dados de check-in do armazenamento local
  void _loadCheckInData() {
    final checkInJson = LocalStorage.getString('checkInDays');
    if (checkInJson != null) {
      checkInDays = CheckInDays.fromJson(json.decode(checkInJson));
    }
  }

  // Salvar dados de check-in no armazenamento local
  void _saveCheckInData() {
    LocalStorage.saveData('checkInDays', json.encode(checkInDays.toJson()));
  }
}
