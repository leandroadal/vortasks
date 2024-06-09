class ProgressData {
  final int xp;
  final int level;
  final int coins;
  final int gems;
  //final int dailyGoalProgress;
  //final int monthlyGoalProgress;
  final DateTime? lastModified;

  ProgressData({
    required this.xp,
    required this.level,
    required this.coins,
    required this.gems,
    //required this.dailyGoalProgress,
    //required this.monthlyGoalProgress,
    this.lastModified,
  });

  // Construtor para criar um ProgressData a partir de um mapa JSON
  factory ProgressData.fromJson(Map<String, dynamic> json) {
    return ProgressData(
      xp: json['xp'].round(),
      level: json['level'],
      coins: json['coins'],
      gems: json['gems'],
      //dailyGoalProgress: json['dailyGoalProgress'],
      //monthlyGoalProgress: json['monthlyGoalProgress'],
      lastModified: json['lastModified'] != null
          ? DateTime.parse(json['lastModified'])
          : null,
    );
  }

  // Converte um ProgressData para um mapa JSON
  Map<String, dynamic> toJson() {
    return {
      'xp': xp,
      'level': level,
      'coins': coins,
      'gems': gems,
      //'dailyGoalProgress': dailyGoalProgress,
      //'monthlyGoalProgress': monthlyGoalProgress,
      'lastModified': lastModified?.toUtc().toIso8601String(),
    };
  }
}
