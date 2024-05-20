import 'package:flutter/material.dart';

class TaskItem extends StatelessWidget {
  final IconData icon;
  final String label;

  const TaskItem({required this.icon, required this.label, super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: ListTile(
        leading: Icon(icon),
        title: Text(label),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            IconButton(
              onPressed: () {},
              icon: const Icon(Icons.close),
            ),
            IconButton(
              onPressed: () {},
              icon: const Icon(Icons.check),
            ),
          ],
        ),
      ),
    );
  }
}


