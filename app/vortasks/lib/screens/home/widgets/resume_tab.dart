import 'package:flutter/material.dart';
import 'package:vortasks/screens/home/widgets/goals/goal_section.dart';
import 'package:vortasks/screens/home/widgets/header_tab.dart';
import 'package:vortasks/screens/home/widgets/profile_card.dart';
import 'package:vortasks/screens/home/widgets/tasks/task_list.dart';
import 'package:vortasks/screens/widgets/my_navigation_rail.dart';

class ResumeTab extends StatelessWidget {
  const ResumeTab({super.key});

  @override
  Widget build(BuildContext context) {
    //final ColorScheme _colorScheme = Theme.of(context).colorScheme;

    return LayoutBuilder(
      // espaço disponível no widget
      builder: (context, constraints) {
        if (constraints.maxWidth > 600) {
          return Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const MyNavigationRail(),
              buildLargeLayout(),
            ],
          );
        } else {
          return buildLayout();
        }
      },
    );
  }

  SingleChildScrollView buildLayout() {
    return const SingleChildScrollView(
      child: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            HeaderTab(),
            SizedBox(height: 16.0),
            ProfileCard(),
            SizedBox(height: 16),
            GoalSection(),
            SizedBox(height: 16),
            TaskList(),
          ],
        ),
      ),
    );
  }

  Expanded buildLargeLayout() {
    return const Expanded(
      child: SingleChildScrollView(
        child: Row(
          children: [
            Expanded(
              flex: 1,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  ProfileCard(),
                  SizedBox(height: 16),
                  GoalSection(),
                ],
              ),
            ),
            Expanded(
              flex: 2,
              child: Padding(
                padding: EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    HeaderTab(),
                    SizedBox(
                      height: 10,
                    ),
                    TaskList(),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

const tasksData = [
  {'icon': Icons.image, 'label': 'Tarefa 1'},
  {'icon': Icons.image, 'label': 'Tarefa 2'},
  {'icon': Icons.image, 'label': 'Tarefa 3'},
];

const leisureData = [
  {'icon': Icons.image, 'label': 'Lazer 1'},
  {'icon': Icons.image, 'label': 'Lazer 2'},
];
