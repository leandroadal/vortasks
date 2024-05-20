import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/stores/page_store.dart';

class MyBottomNavigationBar extends StatelessWidget {
  const MyBottomNavigationBar({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final pageStore = GetIt.I<PageStore>();

    return Observer(builder: (_) {
      return BottomNavigationBar(
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
        selectedItemColor: Theme.of(context).colorScheme.onPrimaryContainer,
        unselectedItemColor:
            Theme.of(context).colorScheme.onPrimaryContainer.withOpacity(0.5),
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Home'),
          BottomNavigationBarItem(
              icon: Icon(Icons.calendar_today), label: 'Tarefas'),
          BottomNavigationBarItem(
              icon: Icon(Icons.shopping_bag), label: 'Loja'),
          BottomNavigationBarItem(
              icon: Icon(Icons.rocket_launch), label: 'Recompensas'),
        ],
        currentIndex: pageStore.selectedPage,
        onTap: (index) {
          pageStore.setPage(index);
        },
      );
    });
  }
}
