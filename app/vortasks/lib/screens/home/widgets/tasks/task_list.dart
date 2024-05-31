import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/models/tasks/task.dart';
import 'package:vortasks/screens/home/widgets/tasks/tasks_section.dart';
import 'package:vortasks/stores/task_store.dart';
import 'package:vortasks/models/enums/type.dart';

class TaskList extends StatefulWidget {
  const TaskList({super.key});

  @override
  State<TaskList> createState() => _TaskListState();
}

class _TaskListState extends State<TaskList> {
  @override
  Widget build(BuildContext context) {
    final TaskStore taskStore = GetIt.I<TaskStore>();

    return Observer(builder: (_) {
      final sortedTasks = taskStore.getSortedTasks;

      // Listas para armazenar tarefas de produtividade e lazer
      final List<Task> productivityTasksData = [];
      final List<Task> leisureTasksData = [];

      // Iterar sobre sortedTasks e separar as tarefas
      for (var task in sortedTasks) {
        if (task.type == TaskType.PRODUCTIVITY) {
          productivityTasksData.add(task);
        } else if (task.type == TaskType.LEISURE) {
          leisureTasksData.add(task);
        }
      }

      return Column(
        children: [
          TasksSection('Produção do Dia', productivityTasksData),
          const SizedBox(height: 16),
          TasksSection('Lazer do dia', leisureTasksData),
          const SizedBox(height: 40),
        ],
      );
    });
  }
}
