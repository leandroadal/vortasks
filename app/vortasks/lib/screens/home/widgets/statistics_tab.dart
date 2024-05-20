import 'package:flutter/material.dart';
import 'package:vortasks/screens/home/widgets/header_tab.dart';
import 'package:vortasks/screens/widgets/my_navigation_rail.dart';

class StatisticsTab extends StatelessWidget {
  const StatisticsTab({super.key});

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      // espaço disponível no widget
      builder: (context, constraints) {
        if (constraints.maxWidth > 600) {
          return const Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              MyNavigationRail(),
              HeaderTab(),
            ],
          );
        } else {
          return buildLayout();
        }
      },
    );
  }

  Container buildLayout() {
    return Container(
      color: Colors.black,
      child: const HeaderTab(),
    );
  }
}
