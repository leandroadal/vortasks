import 'package:intl/intl.dart';
import 'package:vortasks/models/enums/difficulty.dart';
import 'package:vortasks/models/enums/status.dart';
import 'package:vortasks/models/enums/task_theme.dart';
import 'package:vortasks/models/skill/skill.dart';
import 'package:vortasks/models/tasks/task.dart';
import 'package:vortasks/models/enums/type.dart';

class Mission extends Task {
  Mission(
      {required super.id,
      required super.title,
      required super.description,
      required super.status,
      required super.xp,
      required super.coins,
      required super.type,
      required super.repetition,
      required super.reminder,
      required super.skillIncrease,
      required super.skillDecrease,
      required super.startDate,
      required super.endDate,
      required super.theme,
      required super.difficulty,
      required super.finish,
      required super.dateFinish,
      required super.skills,
      required this.requirements});

  List<Task> requirements = [];

  @override
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'title': title,
      'description': description,
      'status': status.toString().split('.').last,
      'xp': xp,
      'coins': coins,
      'type': type.toString().split('.').last,
      'repetition': repetition,
      'reminder': DateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(reminder),
      'skillIncrease': skillIncrease,
      'skillDecrease': skillDecrease,
      'startDate': DateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(startDate),
      'endDate': DateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(endDate),
      'theme': theme.toString().split('.').last,
      'difficulty': difficulty.toString().split('.').last,
      'finish': finish ?? false,
      'dateFinish': DateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
          .format(dateFinish ?? DateTime.utc(1)),
      'skills': skills
          .map((skill) => skill.toJson())
          .toList(), // Converte a lista de skills para JSON
      'requirements': requirements.map((req) => req.toJson()).toList(),
    };
  }

  factory Mission.fromJson(Map<String, dynamic> json) {
    return Mission(
      id: json['id'],
      title: json['title'],
      description: json['description'],
      status: _getStatusFromString(json['status']),
      xp: json['xp'],
      coins: json['coins'],
      type: _getTypeFromString(json['type']),
      repetition: json['repetition'],
      reminder: json['reminder'],
      skillIncrease: json['skillIncrease'],
      skillDecrease: json['skillDecrease'],
      startDate: DateTime.parse(json['startDate']),
      endDate: DateTime.parse(json['endDate']),
      theme: _getThemeFromString(json['theme']),
      difficulty: _getDifficultyFromString(json['difficulty']),
      finish: json['finish'],
      dateFinish: json['dateFinish'] != null
          ? DateTime.tryParse(json['dateFinish'])
          : null,
      skills: (json['skills'] as List)
          .map((skillJson) => Skill.fromJson(skillJson))
          .toList(), // Converte a lista de skills do JSON
      requirements: (json['requirements'] as List)
          .map((req) => Task.fromJson(req))
          .toList(),
    );
  }

  static Status _getStatusFromString(String status) {
    return Status.values.firstWhere(
      (element) => element.toString().split('.').last == status,
      orElse: () => Status.IN_PROGRESS,
    );
  }

  static TaskType _getTypeFromString(String type) {
    return TaskType.values.firstWhere(
      (element) => element.toString().split('.').last == type,
      orElse: () => TaskType.LEISURE,
    );
  }

  static TaskTheme _getThemeFromString(String theme) {
    return TaskTheme.values.firstWhere(
      (element) => element.toString().split('.').last == theme,
      orElse: () => TaskTheme.OTHER,
    );
  }

  static Difficulty _getDifficultyFromString(String difficulty) {
    return Difficulty.values.firstWhere(
      (element) => element.toString().split('.').last == difficulty,
      orElse: () => Difficulty.EASY,
    );
  }
}
