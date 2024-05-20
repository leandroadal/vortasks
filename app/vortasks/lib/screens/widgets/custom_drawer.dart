import 'package:flutter/material.dart';
import 'package:vortasks/screens/widgets/custom_drawer_header.dart';
import 'package:vortasks/screens/widgets/drawer_tile.dart';

class CustomDrawer extends StatelessWidget {
  const CustomDrawer({super.key});

  @override
  Widget build(BuildContext context) {
    Widget buildDrawerBack() => Container(
          decoration: BoxDecoration(
              gradient: LinearGradient(colors: [
            Theme.of(context).colorScheme.primaryContainer,
            Theme.of(context)
                .colorScheme
                .secondaryContainer // Color(0xFF6f6cbf) Color(0xFF6361ab)
          ], begin: Alignment.topCenter, end: Alignment.bottomCenter)),
        );

    return Drawer(
      child: Stack(
        children: [
          buildDrawerBack(),
          ListView(
            padding: const EdgeInsets.only(left: 32.0, top: 16.0),
            children: const [
              CustomDrawerHeader(),
              Divider(),
              DrawerTile(
                icon: Icons.home,
                text: 'Início',
                page:
                    0, //para o tile saber qual é sua página. (Elas estarão em ordem no home_screen, iniciando no 0.)
              ),
              DrawerTile(
                icon: Icons.calendar_month,
                text: 'Tarefas',
                page: 1,
              ),
              DrawerTile(
                icon: Icons.shopping_bag,
                text: 'Loja',
                page: 2,
              ),
              /*
              DrawerTile(
                icon: Icons.playlist_add_check,
                text: 'Meus Pedidos',
                page: 3,
              ),*/
              //const Divider(),

              DrawerTile(
                icon: Icons.rocket_launch,
                text: 'Conquistas',
                page: 3, // Adicione uma nova página para a seção 'Sobre'
              ),

              /*
              DrawerTile(
                icon: Icons.settings,
                text: 'Configurações',
                controller: pageController,
                page: 5,
              ),
              */
              //const Divider(),
            ],
          ),
          Align(
            alignment: Alignment.bottomCenter,
            child: Padding(
              padding: const EdgeInsets.only(right: 35, left: 30, bottom: 60),
              child: RichText(
                textAlign: TextAlign.center,
                text: TextSpan(
                  text: 'Versão 0.10 (1337)\n© 2024 by Leandro Silva.',
                  style: TextStyle(
                    color: Colors.grey[700],
                    fontSize: 14,
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
