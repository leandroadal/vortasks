import 'package:flutter/material.dart';

class GoalItem extends StatelessWidget {
  final String label;
  final double progress;
  final Color color;

  const GoalItem(this.label, this.progress, this.color, {super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(label),
        const SizedBox(height: 4.0),
        LinearProgressIndicator(
          value: progress,
          color: color,
          backgroundColor: Colors.grey[300],
          minHeight: 8.0,
        ),
        const SizedBox(height: 4.0),
        Text('${(progress * 100).toStringAsFixed(1)} %'),
      ],
    );
  }
}
