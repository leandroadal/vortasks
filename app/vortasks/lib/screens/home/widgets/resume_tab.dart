import 'dart:async';

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/screens/home/widgets/goals/goal_section.dart';
import 'package:vortasks/screens/home/widgets/header_tab.dart';
import 'package:vortasks/screens/home/widgets/profile_card.dart';
import 'package:vortasks/screens/home/widgets/tasks/task_list.dart';
import 'package:vortasks/screens/widgets/my_navigation_rail.dart';
import 'package:vortasks/stores/progress_store.dart';
import 'package:vortasks/stores/user_store.dart';

class ResumeTab extends StatefulWidget {
  const ResumeTab({super.key});

  @override
  State<ResumeTab> createState() => _ResumeTabState();
}

class _ResumeTabState extends State<ResumeTab> {
  Timer? _syncTimer;
  StreamSubscription<List<ConnectivityResult>>? _connectivitySubscription;
  final UserStore userStore = GetIt.I<UserStore>();

  @override
  void initState() {
    super.initState();
    _connectivitySubscription =
        Connectivity().onConnectivityChanged.listen(_handleConnectivityChange);
  }

  @override
  void dispose() {
    _syncTimer?.cancel();
    _connectivitySubscription?.cancel();
    super.dispose();
  }

  void _handleConnectivityChange(List<ConnectivityResult> results) {
    // Verifica se a conexão está disponível
    if (results.any((result) => result != ConnectivityResult.none)) {
      _startSync(); // Inicia a sincronização se a conexão estiver disponível
    }
  }

  void _startSync() {
    if (userStore.isLoggedIn) {
      _syncTimer = Timer.periodic(const Duration(seconds: 30), (timer) async {
        try {
          await GetIt.I<ProgressStore>().fromRemote();
        } catch (e) {
          print('Erro de sincronização: $e');
        }
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    //final ColorScheme _colorScheme = Theme.of(context).colorScheme;

    return LayoutBuilder(
      // espaço disponível no widget
      builder: (context, constraints) {
        if (constraints.maxWidth > 600) {
          return Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const MyNavigationRail(),
              buildLargeLayout(),
            ],
          );
        } else {
          return buildLayout();
        }
      },
    );
  }

  SingleChildScrollView buildLayout() {
    return const SingleChildScrollView(
      child: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            HeaderTab(),
            SizedBox(height: 16.0),
            ProfileCard(),
            SizedBox(height: 16),
            GoalSection(),
            SizedBox(height: 16),
            TaskList(),
          ],
        ),
      ),
    );
  }

  Expanded buildLargeLayout() {
    return const Expanded(
      child: SingleChildScrollView(
        child: Row(
          children: [
            Expanded(
              flex: 1,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  ProfileCard(),
                  SizedBox(height: 16),
                  GoalSection(),
                ],
              ),
            ),
            Expanded(
              flex: 2,
              child: Padding(
                padding: EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    HeaderTab(),
                    SizedBox(
                      height: 10,
                    ),
                    TaskList(),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
