import 'package:flutter/material.dart';
import 'package:vortasks/screens/home/widgets/tasks/task_item.dart';

class TasksSection extends StatelessWidget {
  final String title;
  final List<Map<String, dynamic>> data;

  const TasksSection(this.title, this.data, {super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(title,
            style:
                const TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold)),
        const SizedBox(height: 8.0),
        ListView.builder(
          shrinkWrap: true,
          physics: const NeverScrollableScrollPhysics(),
          itemCount: data.length,
          itemBuilder: (context, index) {
            return TaskItem(
              icon: data[index]['icon'],
              label: data[index]['label'],
            );
          },
        ),
      ],
    );
  }
}
