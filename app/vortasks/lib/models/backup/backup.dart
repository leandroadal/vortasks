import 'package:intl/intl.dart';
import 'package:vortasks/models/achievement/achievement.dart';
import 'package:vortasks/models/checkin/check_in_days.dart';
import 'package:vortasks/models/goals/goals.dart';
import 'package:vortasks/models/mission/mission.dart';
import 'package:vortasks/models/skill/skill.dart';
import 'package:vortasks/models/tasks/task.dart';

class Backup {
  final String id;
  final DateTime lastModified;
  final CheckInDays checkInDays;
  final Goals goals;
  final List<Achievement> achievements;
  final List<Task> tasks;
  final List<Mission> missions;
  final List<Skill> skills;

  Backup({
    required this.id,
    required this.lastModified,
    required this.checkInDays,
    required this.goals,
    required this.achievements,
    required this.tasks,
    required this.missions,
    required this.skills,
  });

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'lastModified':
          DateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(lastModified),

      'goals': goals.toJson(), // Convertendo o objeto Goals para JSON
      'achievements': achievements
          .map((achievement) => achievement.toJson())
          .toList(), // Convertendo a lista de Achievements para JSON
      'tasks': tasks
          .map((task) => task.toJson())
          .toList(), // Convertendo a lista de Tasks para JSON
      'missions': missions
          .map((mission) => mission.toJson())
          .toList(), // Convertendo a lista de Missions para JSON
      'skills': skills
          .map((skill) => skill.toJson())
          .toList(), // Convertendo a lista de Skills para JSON
      'checkInDays': checkInDays.toJson(),
    };
  }

  factory Backup.fromJson(Map<String, dynamic> json) {
    return Backup(
      id: json['id'],
      lastModified: DateTime.parse(json['lastModified']).toLocal(),
      goals: Goals.fromJson(json['goals']),
      achievements: (json['achievements'] as List<dynamic>)
          .map((achievementJson) => Achievement.fromJson(achievementJson))
          .toList(),
      tasks: (json['tasks'] as List<dynamic>)
          .map((taskJson) => Task.fromJson(taskJson))
          .toList(),
      missions: (json['missions'] as List<dynamic>)
          .map((missionJson) => Mission.fromJson(missionJson))
          .toList(),
      skills: (json['skills'] as List<dynamic>)
          .map((skillJson) => Skill.fromJson(skillJson))
          .toList(),
      checkInDays: CheckInDays.fromJson(json['checkInDays']),
    );
  }
}
