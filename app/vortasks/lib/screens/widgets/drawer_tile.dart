import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/stores/page_store.dart';

class DrawerTile extends StatelessWidget {
  const DrawerTile(
      {super.key, required this.icon, required this.text, required this.page});

  final IconData icon;
  final String text;
  //final PageController controller; // para page view
  final int page;

  @override
  Widget build(BuildContext context) {
    final pageStore = GetIt.I<PageStore>();

    return Material(
      color: Colors.transparent,
      child: InkWell(
        onTap: () {
          // Fecha o drawer
          Navigator.of(context).pop();
          // Ir para a nova página
          pageStore.setPage(page);
          /* 
          Navigator.pushReplacement(
            context,
            MaterialPageRoute(builder: (context) => NovaPagina()),
          ); */
        },
        // Container para especificar a altura dos itens
        child: SizedBox(
          height: 60,
          child: Row(
            children: [
              Icon(
                icon,
                size: 32,
                // Utilize round() para arredondar o valor que vem do controller.page, pois é um double e o page é um int. Isso é feito se o page controller estiver na página especificada no tile
                color: pageStore.selectedPage == page
                    // Pinta com a cor do tema padrão
                    ? Theme.of(context).colorScheme.tertiary
                    // Se não estiver na página
                    : Theme.of(context).colorScheme.onSecondaryContainer,
              ),
              const SizedBox(width: 32),
              Text(
                text,
                style: TextStyle(
                  fontSize: 16,
                  color: pageStore.selectedPage == page
                      ? Theme.of(context).colorScheme.tertiary
                      : Theme.of(context).colorScheme.onSecondaryContainer,
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
