import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/stores/page_store.dart';

class HeaderTab extends StatelessWidget {
  const HeaderTab({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final pageStore = GetIt.I<PageStore>();

    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        ElevatedButton(
          onPressed: () {
            pageStore.pageController.jumpToPage(0);
          },
          style: ElevatedButton.styleFrom(
            backgroundColor: Theme.of(context).colorScheme.secondaryContainer,
          ),
          child: Text(
            'Resumo',
            style: TextStyle(
              color: Theme.of(context).colorScheme.onSecondaryContainer,
            ),
          ),
        ),
        ElevatedButton(
          onPressed: () {
            pageStore.pageController.jumpToPage(1);
          },
          style: ElevatedButton.styleFrom(
            backgroundColor: Theme.of(context).colorScheme.secondaryContainer,
          ),
          child: Text(
            'Estatísticas',
            style: TextStyle(
              color: Theme.of(context).colorScheme.onSecondaryContainer,
            ),
          ),
        ),
      ],
    );
  }
}
