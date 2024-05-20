import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/core/storage/local_storage.dart';
import 'package:vortasks/core/themes/app_theme.dart';
import 'package:vortasks/screens/home/home_screen.dart';
import 'package:vortasks/stores/page_store.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await LocalStorage.init();
  setupGetIt();
  runApp(const MyApp());
}

void setupGetIt() {
  GetIt.I.registerSingleton<LocalStorage>(LocalStorage());
  GetIt.I.registerSingleton<PageStore>(PageStore());
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
