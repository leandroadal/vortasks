// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'goals_store.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$GoalsStore on GoalsStoreBase, Store {
  late final _$goalsAtom = Atom(name: 'GoalsStoreBase.goals', context: context);

  @override
  Goals get goals {
    _$goalsAtom.reportRead();
    return super.goals;
  }

  @override
  set goals(Goals value) {
    _$goalsAtom.reportWrite(value, super.goals, () {
      super.goals = value;
    });
  }

  late final _$GoalsStoreBaseActionController =
      ActionController(name: 'GoalsStoreBase', context: context);

  @override
  void incrementGoalsCompleted() {
    final _$actionInfo = _$GoalsStoreBaseActionController.startAction(
        name: 'GoalsStoreBase.incrementGoalsCompleted');
    try {
      return super.incrementGoalsCompleted();
    } finally {
      _$GoalsStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void updateGoals(double daily, double monthly, int dailyGoalProgress,
      int monthlyGoalProgress) {
    final _$actionInfo = _$GoalsStoreBaseActionController.startAction(
        name: 'GoalsStoreBase.updateGoals');
    try {
      return super
          .updateGoals(daily, monthly, dailyGoalProgress, monthlyGoalProgress);
    } finally {
      _$GoalsStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void resetDailyProgress() {
    final _$actionInfo = _$GoalsStoreBaseActionController.startAction(
        name: 'GoalsStoreBase.resetDailyProgress');
    try {
      return super.resetDailyProgress();
    } finally {
      _$GoalsStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void resetMonthlyProgress() {
    final _$actionInfo = _$GoalsStoreBaseActionController.startAction(
        name: 'GoalsStoreBase.resetMonthlyProgress');
    try {
      return super.resetMonthlyProgress();
    } finally {
      _$GoalsStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
goals: ${goals}
    ''';
  }
}
