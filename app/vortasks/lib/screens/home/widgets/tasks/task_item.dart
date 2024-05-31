import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/models/tasks/task.dart';
import 'package:vortasks/stores/task_store.dart';

class TaskItem extends StatelessWidget {
  final IconData icon;
  final String label;
  final Task task;
  final Color? backgroundColor;

  const TaskItem(
      {required this.icon,
      required this.label,
      super.key,
      required this.task,
      this.backgroundColor});

  @override
  Widget build(BuildContext context) {
    final TaskStore taskStore = GetIt.I<TaskStore>();
    return GestureDetector(
      onTap: () {
        // TODO: criar a pagina de visualização da task
        /*
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => TaskInfoScreen(task: task),
          ),
        );*/
      },
      child: Card(
        color: backgroundColor,
        child: ListTile(
          leading: Icon(icon),
          title: Text(
            label,
            overflow: TextOverflow.ellipsis,
          ),
          trailing: Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              IconButton(
                icon: const Icon(Icons.close),
                onPressed: () {
                  taskStore.failTask(task);
                },
              ),
              IconButton(
                icon: const Icon(Icons.check),
                onPressed: () {
                  taskStore.completeTask(task);
                  //goalsStore.incrementGoalsCompleted();
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
