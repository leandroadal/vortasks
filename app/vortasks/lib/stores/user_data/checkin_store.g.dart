// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'checkin_store.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$CheckInStore on CheckInStoreBase, Store {
  late final _$checkInDaysAtom =
      Atom(name: 'CheckInStoreBase.checkInDays', context: context);

  @override
  CheckInDays get checkInDays {
    _$checkInDaysAtom.reportRead();
    return super.checkInDays;
  }

  @override
  set checkInDays(CheckInDays value) {
    _$checkInDaysAtom.reportWrite(value, super.checkInDays, () {
      super.checkInDays = value;
    });
  }

  late final _$CheckInStoreBaseActionController =
      ActionController(name: 'CheckInStoreBase', context: context);

  @override
  void setCheckInDays(CheckInDays checkIn) {
    final _$actionInfo = _$CheckInStoreBaseActionController.startAction(
        name: 'CheckInStoreBase.setCheckInDays');
    try {
      return super.setCheckInDays(checkIn);
    } finally {
      _$CheckInStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void checkIn() {
    final _$actionInfo = _$CheckInStoreBaseActionController.startAction(
        name: 'CheckInStoreBase.checkIn');
    try {
      return super.checkIn();
    } finally {
      _$CheckInStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
checkInDays: ${checkInDays}
    ''';
  }
}
