import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/screens/goals/goals_screen.dart';
import 'package:vortasks/screens/home/widgets/goals/goal_item.dart';
import 'package:vortasks/stores/goals_store.dart';

class GoalSection extends StatelessWidget {
  const GoalSection({super.key});

  @override
  Widget build(BuildContext context) {
    final GoalsStore goalsStore = GetIt.I<GoalsStore>();
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
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => const GoalsScreen(),
                      ),
                    );
                  },
                  icon: const Icon(Icons.edit),
                ),
              ],
            ),
            const SizedBox(height: 8.0),
            Observer(builder: (_) {
              return GoalItem(
                'Meta diária',
                goalsStore.goals.dailyGoalProgress / goalsStore.goals.daily,
                Theme.of(context).colorScheme.primary,
                goal: goalsStore.goals.daily,
              );
            }),
            const SizedBox(height: 8.0),
            Observer(builder: (_) {
              return GoalItem(
                'Meta Semanal',
                goalsStore.goals.weeklyGoalProgress / goalsStore.goals.weekly,
                Theme.of(context).colorScheme.secondary,
                goal: goalsStore.goals.weekly,
              );
            }),
          ],
        ),
      ),
    );
  }
}
