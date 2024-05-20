import 'package:flutter/material.dart';
import 'package:vortasks/screens/home/widgets/goals/goal_item.dart';

class GoalSection extends StatelessWidget {
  const GoalSection({super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text('Metas',
                    style:
                        TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold)),
                IconButton(
                  onPressed: () {},
                  icon: const Icon(Icons.edit),
                ),
              ],
            ),
            const SizedBox(height: 8.0),
            GoalItem(
                'Meta diária', 0.696, Theme.of(context).colorScheme.primary),
            const SizedBox(height: 8.0),
            GoalItem(
                'Meta Semanal', 0.311, Theme.of(context).colorScheme.secondary),
          ],
        ),
      ),
    );
  }
}
