import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/core/storage/local_storage.dart';
import 'package:vortasks/core/themes/app_theme.dart';
import 'package:vortasks/screens/home/home_screen.dart';
import 'package:vortasks/stores/user_data/achievement_store.dart';
import 'package:vortasks/stores/user_data/checkin_store.dart';
import 'package:vortasks/stores/user_data/mission_store.dart';
import 'package:vortasks/stores/backup_store.dart';
import 'package:vortasks/stores/logout_store.dart';
import 'package:vortasks/stores/progress_store.dart';
import 'package:vortasks/stores/sell_store.dart';
import 'package:vortasks/stores/task_form_store.dart';
import 'package:vortasks/stores/goals_store.dart';
import 'package:vortasks/stores/level_store.dart';
import 'package:vortasks/stores/login_store.dart';
import 'package:vortasks/stores/page_store.dart';
import 'package:vortasks/stores/signup_store.dart';
import 'package:vortasks/stores/skill_store.dart';
import 'package:vortasks/stores/task_store.dart';
import 'package:vortasks/stores/user_store.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await LocalStorage.init();
  setupGetIt();
  runApp(const MyApp());
}

void setupGetIt() {
  GetIt.I.registerSingleton<CheckInStore>(CheckInStore());
  GetIt.I.registerSingleton<AchievementStore>(AchievementStore());
  GetIt.I.registerSingleton<MissionStore>(MissionStore());

  GetIt.I.registerSingleton<LocalStorage>(LocalStorage());
  GetIt.I.registerSingleton<TaskFormStore>(TaskFormStore());
  GetIt.I.registerSingleton<UserStore>(UserStore());

  GetIt.I.registerSingleton<LogoutStore>(LogoutStore());
  GetIt.I.registerSingleton<SignUpStore>(SignUpStore());
  GetIt.I.registerSingleton<PageStore>(PageStore());
  GetIt.I.registerSingleton<SkillStore>(SkillStore());
  GetIt.I.registerSingleton<SellStore>(SellStore());
  GetIt.I.registerSingleton<LevelStore>(LevelStore());
  GetIt.I.registerSingleton<GoalsStore>(GoalsStore());
  GetIt.I.registerSingleton<LoginStore>(LoginStore());
  GetIt.I.registerSingleton<ProgressStore>(ProgressStore());

  GetIt.I.registerSingleton<TaskStore>(
      TaskStore()); // level e foals precisam ser iniciados antes
  GetIt.I.registerSingleton<BackupStore>(BackupStore());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Vortasks',
      theme: AppTheme.lightTheme,
      darkTheme: AppTheme.darkTheme,
      debugShowCheckedModeBanner: false,
      home: const HomeScreen(),
    );
  }
}
