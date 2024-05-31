import 'dart:async';

import 'package:mobx/mobx.dart';
import 'package:vortasks/core/storage/local_storage.dart';

import 'dart:convert';

import 'package:vortasks/models/goals/goals.dart';

part 'goals_store.g.dart';

class GoalsStore = GoalsStoreBase with _$GoalsStore;

abstract class GoalsStoreBase with Store {
  GoalsStoreBase() {
    _loadGoals();
    //_initializeResetChecks();
  }

  @observable
  Goals goals = Goals(
    id: '...',
    daily: 5,
    monthly: 150,
    dailyGoalProgress: 0,
    monthlyGoalProgress: 0,
  );


  @action
  void incrementGoalsCompleted() {
    goals = Goals(
      id: goals.id,
      daily: goals.daily,
      monthly: goals.monthly,
      dailyGoalProgress: goals.dailyGoalProgress + 1,
      monthlyGoalProgress: goals.monthlyGoalProgress + 1,
    );
    _saveGoals();
  }

  @action
  void updateGoals(double daily, double monthly, int dailyGoalProgress, int monthlyGoalProgress) {
    goals = Goals(
      id: goals.id,
      daily: daily,
      monthly: monthly,
      dailyGoalProgress: dailyGoalProgress,
      monthlyGoalProgress: monthlyGoalProgress,
    );
    _saveGoals();
  }

  @action
  void resetDailyProgress() {
    updateGoals(goals.daily, goals.monthly, 0, goals.monthlyGoalProgress);
    _saveLastDailyReset();
  }

  @action
  void resetMonthlyProgress() {
    updateGoals(goals.daily, goals.monthly, goals.dailyGoalProgress, 0);
    _saveLastMonthlyReset();
  }

  void _initializeResetChecks() {
    _checkAndResetDailyProgress();
    _checkAndResetMonthlyProgress();
    _initializeTimers();
  }

  void _checkAndResetDailyProgress() {
    final now = DateTime.now();
    final lastDailyReset = _loadLastDailyReset();
    if (lastDailyReset == null || !isSameDay(now, lastDailyReset)) {
      resetDailyProgress();
    }
  }

  void _checkAndResetMonthlyProgress() {
    final now = DateTime.now();
    final lastMonthlyReset = _loadLastMonthlyReset();
    if (lastMonthlyReset == null || !isSameMonth(now, lastMonthlyReset)) {
      resetMonthlyProgress();
    }
  }

  void _initializeTimers() {
    final now = DateTime.now();
    final nextMidnight = DateTime(now.year, now.month, now.day + 1, 0, 0).subtract(const Duration(minutes: 1));
    final nextMonth = DateTime(now.year, now.month + 1, 1);
    final lastDayOfMonth = nextMonth.subtract(const Duration(days: 1)).day;
    final nextMonthReset = DateTime(now.year, now.month, lastDayOfMonth, 23, 59);

    Timer(nextMidnight.difference(now), () {
      resetDailyProgress();
      Timer.periodic(const Duration(days: 1), (timer) => resetDailyProgress());
    });

    Timer(nextMonthReset.difference(now), () {
      resetMonthlyProgress();
      Timer.periodic(const Duration(days: 30), (timer) {
        final now = DateTime.now();
        if (now.day == DateTime(now.year, now.month + 1, 1).subtract(const Duration(days: 1)).day) {
          resetMonthlyProgress();
        }
      });
    });
  }

  // Helpers to load and save last reset dates
  DateTime? _loadLastDailyReset() {
    final lastReset = LocalStorage.getString('lastDailyReset');
    if (lastReset != null) {
      return DateTime.parse(lastReset);
    }
    return null;
  }

  void _saveLastDailyReset() {
    final now = DateTime.now();
    LocalStorage.saveData('lastDailyReset', now.toIso8601String());
  }

  DateTime? _loadLastMonthlyReset() {
    final lastReset = LocalStorage.getString('lastMonthlyReset');
    if (lastReset != null) {
      return DateTime.parse(lastReset);
    }
    return null;
  }

  void _saveLastMonthlyReset() {
    final now = DateTime.now();
    LocalStorage.saveData('lastMonthlyReset', now.toIso8601String());
  }

  // Utility functions to compare dates
  bool isSameDay(DateTime date1, DateTime date2) {
    return date1.year == date2.year && date1.month == date2.month && date1.day == date2.day;
  }

  bool isSameMonth(DateTime date1, DateTime date2) {
    return date1.year == date2.year && date1.month == date2.month;
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