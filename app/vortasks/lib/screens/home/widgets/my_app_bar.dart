import 'package:flutter/material.dart';
import 'package:vortasks/screens/auth/login/login_screen.dart';
import 'package:vortasks/screens/home/widgets/account_icon.dart';

class MyAppBar extends StatelessWidget implements PreferredSizeWidget {
  const MyAppBar({super.key, required this.title});
  final String title;

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Theme.of(context).colorScheme.primaryContainer,
      actions: <Widget>[
        IconButton(
          icon: const Icon(Icons.sync),
          onPressed: () async {},
        ),
        IconButton(
          icon: const Icon(Icons.people),
          onPressed: () {},
        ),
        IconButton(
          icon: const Icon(Icons.account_circle),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const LoginScreen()),
            );
          },
        ),
        const AccountIcon(),
      ],
      //backgroundColor: const Color(0xFF2c00a2), // Color(0xFF2c00a2)
      flexibleSpace: FlexibleSpaceBar(
        centerTitle: true,
        title: Text(
          title,
        ),
      ),
    );
  }
}
