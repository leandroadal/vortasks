import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/core/storage/local_storage.dart';
import 'package:vortasks/core/themes/app_theme.dart';
import 'package:vortasks/screens/home/home_screen.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await LocalStorage.init();
  runApp(const MyApp());
}

void setupGetIt() {
  GetIt.I.registerSingleton<LocalStorage>(LocalStorage());
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
