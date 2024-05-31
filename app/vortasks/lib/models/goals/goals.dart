class Goals {
  final String id;
  double daily;
  double monthly;

  int dailyGoalProgress;
  int monthlyGoalProgress;

  Goals({
    required this.id,
    required this.daily,
    required this.monthly,
    required this.dailyGoalProgress,
    required this.monthlyGoalProgress,
  });

  Map<String, dynamic> toJson() {
    return {'id': id, 'daily': daily, 'monthly': monthly, 'dailyGoalProgress': dailyGoalProgress, 'monthlyGoalProgress': monthlyGoalProgress};
  }

  factory Goals.fromJson(Map<String, dynamic> json) {
    return Goals(
      id: json['id'],
      daily: json['daily'],
      monthly: json['monthly'],
      dailyGoalProgress: json['dailyGoalProgress'],
      monthlyGoalProgress: json['monthlyGoalProgress'],
    );
  }
}
