import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/screens/auth/login/login_screen.dart';
import 'package:vortasks/screens/backup/backup_screen.dart';
import 'package:vortasks/screens/home/widgets/account_icon.dart';
import 'package:vortasks/stores/user_store.dart';

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
          onPressed: () async {
            if (GetIt.I<UserStore>().isLoggedIn) {
              Navigator.of(context).push(
                MaterialPageRoute(builder: (context) => const BackupScreen()),
              );
            } else {
              ScaffoldMessenger.of(context).showSnackBar(
                const SnackBar(
                  content: Text(
                    "Efetue login para realizar a operação de backup",
                    style: TextStyle(color: Colors.white),
                  ),
                  backgroundColor: Colors.red,
                  duration: Duration(seconds: 3),
                ),
              );
              Navigator.of(context).push(
                MaterialPageRoute(builder: (context) => const LoginScreen()),
              );
            }
          },
        ),
        IconButton(
          icon: const Icon(Icons.people),
          onPressed: () {},
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
