import 'package:flutter/material.dart';
import 'package:flutter_speed_dial/flutter_speed_dial.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/models/enums/type.dart';
import 'package:vortasks/screens/tasks/add_task_screen.dart';
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
  //isBrightLight = Theme.of(context).brightness;

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
    var productivityColor = Theme.of(context).brightness == Brightness.light
        ? const Color(0xFF3674ef)
        : const Color(0xFF0D47A1);
    final leisureColor = Theme.of(context).brightness == Brightness.light
        ? const Color.fromARGB(255, 206, 109,
            186) //Color(0xFFf48ce1)Color.fromARGB(255, 226, 123, 206)
        : const Color(0xFF9949de);

    return Scaffold(
      //backgroundColor: const Color(0xFF3100a7),
      appBar: const MyAppBar(title: 'Vortasks'),
      drawer: const CustomDrawer(),

      body: tab,
      floatingActionButton: isSmallScreen
          ? SpeedDial(
              //backgroundColor: Colors.pinkAccent,
              overlayOpacity: 0.4,
              overlayColor: Colors.black,
              //animatedIcon: AnimatedIcons.menu_close,
              //animationCurve: Curves.bounceIn,
              useRotationAnimation: true,
              children: [
                SpeedDialChild(
                  child: Icon(
                    Icons.mood,
                    color: Theme.of(context).brightness == Brightness.light
                        ? const Color.fromARGB(255, 206, 109,
                            186) //Color(0xFFf48ce1)Color.fromARGB(255, 226, 123, 206)
                        : const Color(0xFF9949de),
                  ),
                  backgroundColor: Colors.white,
                  label: 'Lazer',
                  labelStyle: TextStyle(
                    fontSize: 14,
                    color: leisureColor,
                  ),
                  labelBackgroundColor: Colors.white,
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => const AddTaskScreen(
                          title: 'Adicionar Lazer',
                          type: TaskType.LEISURE,
                        ),
                      ), // Passa a função addTask
                    );
                  },
                ),
                SpeedDialChild(
                  child: Icon(
                    Icons.task,
                    color: productivityColor,
                  ),
                  backgroundColor: Colors.white,
                  label: 'Produtividade',
                  labelStyle: TextStyle(
                    fontSize: 14,
                    color: productivityColor,
                  ),
                  labelBackgroundColor: Colors.white,
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => const AddTaskScreen(
                                title: 'Adicionar Produtividade',
                                type: TaskType.PRODUCTIVITY,
                              )), // Passa a função addTask
                    );
                  },
                ),
              ],
              child: const Icon(Icons.add),
            )
          : null,
      bottomNavigationBar: isSmallScreen ? const MyBottomNavigationBar() : null,
    );
  }
}
