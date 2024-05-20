import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/screens/widgets/custom_drawer.dart';
import 'package:vortasks/screens/home/widgets/resume_tab.dart';
import 'package:vortasks/screens/home/widgets/my_app_bar.dart';
import 'package:vortasks/screens/home/widgets/statistics_tab.dart';
import 'package:vortasks/screens/widgets/my_bottom_navigation_bar.dart';
import 'package:vortasks/stores/page_store.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  //final _pageController = PageController();

  @override
  Widget build(BuildContext context) {
    final isSmallScreen = MediaQuery.sizeOf(context).width < 600;
    final pageStore = GetIt.I<PageStore>();

    return PageView(
      physics: const NeverScrollableScrollPhysics(),
      controller: pageStore.pageController,
      children: [
        pageViewItem(isSmallScreen, const ResumeTab()),
        pageViewItem(isSmallScreen, const StatisticsTab())
      ],
    );
  }

  Scaffold pageViewItem(bool isSmallScreen, Widget tab) {
    return Scaffold(
      //backgroundColor: const Color(0xFF3100a7),
      appBar: const MyAppBar(title: 'Vortasks'),
      drawer: const CustomDrawer(),

      body: tab,
      floatingActionButton: isSmallScreen
          ? FloatingActionButton(
              onPressed: () {},
              tooltip: 'Adicionar tarefa',
              child: const Icon(Icons.add),
            )
          : null,
      bottomNavigationBar: isSmallScreen ? const MyBottomNavigationBar() : null,
    );
  }
}
