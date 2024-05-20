import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/stores/page_store.dart';

class MyDrawer extends StatelessWidget {
  const MyDrawer({super.key});

  @override
  Widget build(BuildContext context) {
    final pageStore = GetIt.I<PageStore>();

    return Drawer(
      child: Stack(
        children: [
          ListView(
            padding: EdgeInsets.zero,
            children: [
              DrawerHeader(
                child: Column(
                  children: [
                    UserAccountsDrawerHeader(
                      decoration: BoxDecoration(
                        color: Theme.of(context).colorScheme.primaryContainer,
                      ),
                      accountName: Text('teste'),
                      accountEmail: Text('data'),
                      currentAccountPicture: CircleAvatar(
                        backgroundColor:
                            Theme.of(context).colorScheme.onPrimaryContainer,
                        child: Text("SZ"),
                      ),
                    ),
                    IconButton(onPressed: () {
                      
                    }, icon: Icon(Icons.notifications))
                  ],
                ),
              ),
              ListTile(
                leading: Icon(Icons.home),
                title: Text('Início'),
                onTap: () {
                  pageStore.setPage(0);
                  Navigator.of(context).pop();
                },
              ),
              ListTile(
                leading: Icon(Icons.account_circle),
                title: Text('Profile'),
                onTap: () {
                  pageStore.setPage(1);
                  Navigator.of(context).pop();
                },
              ),
              ListTile(
                leading: Icon(Icons.settings),
                title: Text('Settings'),
                onTap: () {
                  pageStore.setPage(2);
                  Navigator.of(context).pop();
                },
              ),
            ],
          ),
        ],
      ),
    );
  }
}
