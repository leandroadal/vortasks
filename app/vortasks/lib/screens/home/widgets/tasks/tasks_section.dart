import 'package:flutter/material.dart';
import 'package:vortasks/models/tasks/task.dart';
import 'package:vortasks/screens/home/widgets/tasks/task_item.dart';

class TasksSection extends StatelessWidget {
  final String title;
  final List<Task> data;

  const TasksSection(this.title, this.data, {super.key});

  @override
  Widget build(BuildContext context) {
    //final theme = Theme.of(context);
    //final TaskStore taskStore = GetIt.I<TaskStore>();

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(title,
            style: const TextStyle(
              fontSize: 18.0,
              fontWeight: FontWeight.bold,
            )),
        const SizedBox(height: 8.0),
        ListView.builder(
          shrinkWrap: true,
          physics: const NeverScrollableScrollPhysics(),
          itemCount: data.length, // Número total de itens
          itemBuilder: (context, index) {
            // Função para construir cada item
            final task = data[index];
            return TaskItem(
              icon: Icons.task,
              label: task.title,
              task: task,
            );
          },
        ),
        /*
        ListView(
          shrinkWrap: true,
          physics: const NeverScrollableScrollPhysics(),
          children: data.map((task) {
            return TaskItem(
              icon: Icons.task,
              label: task.title,
              task: task,
              taskStore: taskStore,
            );
          }).toList(),
        ),
        */
      ],
    );
  }
}
