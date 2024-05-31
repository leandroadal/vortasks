// ignore_for_file: constant_identifier_names

import 'package:vortasks/models/enums/status.dart';
import 'package:vortasks/models/enums/type.dart';
import 'package:vortasks/models/enums/difficulty.dart';
import 'package:vortasks/models/enums/task_theme.dart';
import 'package:vortasks/models/skill/skill.dart';

class Task {
  String id;
  String title;
  String description;
  Status status;
  int xp;
  int coins;
  TaskType type;
  int repetition;
  DateTime reminder;
  List<Skill> skills;
  int skillIncrease;
  int skillDecrease;
  DateTime startDate;
  DateTime endDate;
  TaskTheme theme;
  Difficulty difficulty;
  bool? finish;
  DateTime? dateFinish;

  Task(
      {required this.id,
      required this.title,
      required this.description,
      required this.status,
      required this.xp,
      required this.coins,
      required this.type,
      required this.repetition,
      required this.reminder,
      required this.skills,
      required this.skillIncrease,
      required this.skillDecrease,
      required this.startDate,
      required this.endDate,
      required this.theme,
      required this.difficulty,
      this.finish,
      this.dateFinish});

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
      'reminder': reminder.toIso8601String(),
      'skillIncrease': skillIncrease,
      'skillDecrease': skillDecrease,
      'startDate': startDate.toIso8601String(),
      'endDate': endDate.toIso8601String(),
      'theme': theme.toString().split('.').last,
      'difficulty': difficulty.toString().split('.').last,
      'finish': finish ?? false,
      'dateFinish': dateFinish?.toIso8601String(),
      'skills': skills
          .map((skill) => skill.toJson())
          .toList(), // Converte a lista de skills para JSON
    };
  }

  factory Task.fromJson(Map<String, dynamic> json) {
    return Task(
      id: json['id'],
      title: json['title'],
      description: json['description'],
      status: _getStatusFromString(json['status']),
      xp: json['xp'],
      coins: json['coins'],
      type: _getTypeFromString(json['type']),
      repetition: json['repetition'],
      reminder: DateTime.parse(json['reminder']),
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

  Task copyWith({
    String? id,
    String? title,
    String? description,
    Status? status,
    int? xp,
    int? coins,
    TaskType? type,
    int? repetition,
    DateTime? reminder,
    List<Skill>? skills,
    int? skillIncrease,
    int? skillDecrease,
    DateTime? startDate,
    DateTime? endDate,
    TaskTheme? theme,
    Difficulty? difficulty,
    bool? finish,
    DateTime? dateFinish,
  }) {
    return Task(
      id: id ?? this.id,
      title: title ?? this.title,
      description: description ?? this.description,
      status: status ?? this.status,
      xp: xp ?? this.xp,
      coins: coins ?? this.coins,
      type: type ?? this.type,
      repetition: repetition ?? this.repetition,
      reminder: reminder ?? this.reminder,
      skills: skills ?? this.skills,
      skillIncrease: skillIncrease ?? this.skillIncrease,
      skillDecrease: skillDecrease ?? this.skillDecrease,
      startDate: startDate ?? this.startDate,
      endDate: endDate ?? this.endDate,
      theme: theme ?? this.theme,
      difficulty: difficulty ?? this.difficulty,
      finish: finish ?? this.finish,
      dateFinish: dateFinish ?? this.dateFinish,
    );
  }
}
