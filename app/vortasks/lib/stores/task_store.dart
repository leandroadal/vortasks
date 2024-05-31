import 'package:get_it/get_it.dart';
import 'package:mobx/mobx.dart';
import 'package:vortasks/core/storage/local_storage.dart';
import 'package:vortasks/models/enums/status.dart';
import 'package:vortasks/models/tasks/task.dart';
import 'package:vortasks/stores/goals_store.dart';

import 'dart:convert';

import 'package:vortasks/stores/level_store.dart';

part 'task_store.g.dart';

class TaskStore = TaskStoreBase with _$TaskStore;

abstract class TaskStoreBase with Store {
  TaskStoreBase() {
    _loadTasks();
  }
  final LevelStore _levelStore = GetIt.I<LevelStore>();
  final GoalsStore goalsStore = GetIt.I<GoalsStore>();

  @observable
  ObservableList<Task> tasks = ObservableList<Task>();

  @observable
  List<Task> todayTasks = [];

  @action
  void addTask(Task task) {
    tasks.add(task);
    _saveTasks();
    _updateTodayTasks(); // Atualiza as tarefas do dia
  }

  @action
  void updateTask(Task task) {
    final index = tasks.indexWhere((t) => t.id == task.id);
    if (index != -1) {
      tasks[index] = task;
      _saveTasks();
      _updateTodayTasks();
    }
  }

  @action
  void deleteTask(String taskId) {
    tasks.removeWhere((task) => task.id == taskId);
    _saveTasks();
    _updateTodayTasks();
  }

  // Carregar tarefas do armazenamento local
  void _loadTasks() {
    final tasksJson = LocalStorage.getString('tasks');
    if (tasksJson != null) {
      tasks = ObservableList.of(
          (json.decode(tasksJson) as List).map((e) => Task.fromJson(e)));
    }
    _updateTodayTasks();
  }

  // Atualiza as tarefas do dia
  @action
  void _updateTodayTasks() {
    final today = DateTime.now();
    todayTasks = tasks
        .where((task) =>
            task.endDate.year == today.year &&
            task.endDate.month == today.month &&
            task.endDate.day == today.day &&
            task.status == Status.IN_PROGRESS)
        .toList();
  }

  // Salvar tarefas no armazenamento local
  void _saveTasks() {
    LocalStorage.saveData('tasks', json.encode(tasks));
  }

  // Quando completa a tarefa
  @action
  void completeTask(Task task) {
    task.status = Status.COMPLETED;
    task.dateFinish = DateTime.now().toUtc();
    task.finish = true;
    updateTask(task);
    _earnXP(task);
    goalsStore.incrementGoalsCompleted();
    //_saveTasks();
  }

  void _earnXP(Task task) {
    _levelStore.addXP(task.xp); // Incrementa o XP do usuário
    _levelStore.xpPercentage; // Recalcula a porcentagem de XP
  }

  // Quando o usuário falha em completar a tarefa
  @action
  void failTask(Task task) {
    task.status = Status.FAILED;
    task.dateFinish = DateTime.now().toUtc();
    task.finish = true;
    updateTask(task);
    loseXP(task);
  }

  void loseXP(Task task) {
    _levelStore.rmXP(task.xp); // Decrementa o XP do usuário
    _levelStore.xpPercentage; // Recalcula a porcentagem de XP
  }

  @computed
  List<Task> get getSortedTasks {
    final today = DateTime.now();
    final filteredTasks = todayTasks;

    // Ordenar tarefas por proximidade da data de término
    filteredTasks.sort((a, b) =>
        a.endDate.difference(today).compareTo(b.endDate.difference(today)));

    return filteredTasks;
    // Limitar a 6 tarefas
    // return filteredTasks.length <= 6
    //    ? filteredTasks
    //     : filteredTasks.sublist(0, 6);
  }
}
