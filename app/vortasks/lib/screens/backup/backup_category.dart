import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:vortasks/models/achievement/achievement.dart';
import 'package:vortasks/models/checkin/check_in_days.dart';
import 'package:vortasks/models/goals/goals.dart';
import 'package:vortasks/models/mission/mission.dart';
import 'package:vortasks/models/skill/skill.dart';
import 'package:vortasks/models/tasks/task.dart';

class BackupCategory extends StatelessWidget {
  final String title;
  final dynamic data;
  final String categoryName;

  const BackupCategory({
    super.key,
    required this.title,
    required this.data,
    required this.categoryName,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          if (categoryName == 'Goals')
            _buildGoalData(data as Goals)
          else if (categoryName == 'Achievements')
            _buildAchievementData(data as List<Achievement>)
          else if (categoryName == 'Tasks')
            _buildTaskData(data as List<Task>)
          else if (categoryName == 'Missions')
            _buildMissionData(data as List<Mission>)
          else if (categoryName == 'Skills')
            _buildSkillData(data as List<Skill>)
          else if (categoryName == 'CheckInDays')
            _buildCheckInData(data as CheckInDays),
        ],
      ),
    );
  }

  Widget _buildGoalData(Goals goals) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        _buildBackupDataItem('Meta Diária:', '${goals.daily}'),
        _buildBackupDataItem('Meta Semanal:', '${goals.weekly}'),
        _buildBackupDataItem(
            'Progresso Diário:', '${goals.dailyGoalProgress}/${goals.daily}'),
        _buildBackupDataItem('Progresso Semanal:',
            '${goals.weeklyGoalProgress}/${goals.weekly}'),
      ],
    );
  }

  Widget _buildAchievementData(List<Achievement> achievements) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: achievements
          .map((achievement) => _buildBackupDataItem(
                '${achievement.title}',
                'XP: ${achievement.xp}',
              ))
          .toList(),
    );
  }

  Widget _buildTaskData(List<Task> tasks) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: tasks
          .map((task) => _buildBackupDataItem(
                '${task.title}',
                'Data: ${DateFormat('dd/MM/yyyy').format(task.endDate)}',
              ))
          .toList(),
    );
  }

  Widget _buildMissionData(List<Mission> missions) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: missions
          .map((mission) => _buildBackupDataItem(
                '${mission.title}',
                'Data: ${DateFormat('dd/MM/yyyy').format(mission.endDate)}',
              ))
          .toList(),
    );
  }

  Widget _buildSkillData(List<Skill> skills) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: skills
          .map((skill) => _buildBackupDataItem(
                '${skill.name}',
                'Nível: ${skill.level?.toString()}\nXP: ${skill.xp?.toString()}',
              ))
          .toList(),
    );
  }

  Widget _buildCheckInData(CheckInDays checkInDays) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        _buildBackupDataItem('Dias de Check-in:', '${checkInDays.days}'),
        _buildBackupDataItem('Mês:', '${checkInDays.month}'),
      ],
    );
  }

  Widget _buildBackupDataItem(String label, String value) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            '$label ',
            style: const TextStyle(fontWeight: FontWeight.bold),
          ),
          Text(value),
        ],
      ),
    );
  }
}
