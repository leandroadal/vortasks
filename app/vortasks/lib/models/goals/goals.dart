class Goals {
  final String id;
  int daily;
  int weekly;
  //int monthly;

  int dailyGoalProgress;
  int weeklyGoalProgress;

  Goals({
    required this.id,
    required this.daily,
    required this.weekly,
    //required this.monthly,
    required this.dailyGoalProgress,
    required this.weeklyGoalProgress,
  });

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'daily': daily,
      'weekly': weekly,
      //'monthly': monthly,
      'dailyGoalProgress': dailyGoalProgress,
      'weeklyGoalProgress': weeklyGoalProgress
    };
  }

  factory Goals.fromJson(Map<String, dynamic> json) {
    return Goals(
      id: json['id'],
      daily: json['daily'],
      weekly: json['weekly'],
      //monthly: json['monthly'],
      dailyGoalProgress: json['dailyGoalProgress'],
      weeklyGoalProgress: json['weeklyGoalProgress'],
    );
  }

  Goals copyWith({
    String? id,
    int? daily,
    int? weekly,
    int? dailyGoalProgress,
    int? weeklyGoalProgress,
  }) {
    return Goals(
      id: id ?? this.id,
      daily: daily ?? this.daily,
      weekly: weekly ?? this.weekly,
      dailyGoalProgress: dailyGoalProgress ?? this.dailyGoalProgress,
      weeklyGoalProgress: weeklyGoalProgress ?? this.weeklyGoalProgress,
    );
  }
}
