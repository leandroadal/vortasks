import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/stores/page_store.dart';
import 'package:flutter_mobx/flutter_mobx.dart';

class MyNavigationRail extends StatefulWidget {
  const MyNavigationRail(
      {super.key});

  @override
  State<MyNavigationRail> createState() => _MyNavigationRailState();
}

class _MyNavigationRailState extends State<MyNavigationRail> {
  @override
  Widget build(BuildContext context) {
    final pageStore = GetIt.I<PageStore>();

    return Observer(
      builder: (_) {
        return NavigationRail(
          backgroundColor: Theme.of(context).colorScheme.primaryContainer,
          selectedIndex: pageStore.selectedPage,
          onDestinationSelected: (index) {
            pageStore.setPage(index);
          },
          labelType: NavigationRailLabelType.selected,
          leading: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const SizedBox(height: 8),
              FloatingActionButton(
                onPressed: () {},
                backgroundColor: Theme.of(context).colorScheme.secondary,
                child: const Icon(Icons.add),
              ),
            ],
          ),
          groupAlignment: -0.80,
          destinations: const [
            NavigationRailDestination(
                icon: Icon(Icons.home), label: Text('Home')),
            NavigationRailDestination(
                icon: Icon(Icons.calendar_today), label: Text('Tarefas')),
            NavigationRailDestination(
                icon: Icon(Icons.shopping_bag), label: Text('Loja')),
            NavigationRailDestination(
                icon: Icon(Icons.rocket_launch), label: Text('Recompensas')),
          ],
        );
      },
    );
  }
}
