import 'package:flutter/material.dart';

class ProfileCard extends StatelessWidget {
  const ProfileCard({super.key});

  @override
  Widget build(BuildContext context) {
    double screenWidthSize = MediaQuery.of(context).size.width;
    return Card(
      color: Theme.of(context).colorScheme.secondary,
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: screenWidthSize > 600
            ? largeScreen(screenWidthSize, context)
            : verticalScreen(context),
      ),
    );
  }

  Row largeScreen(double screenWidthSize, BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      //mainAxisSize: MainAxisSize.min,
      children: [
        Column(
          children: [
            Row(
              children: [
                screenWidthSize > 800
                    ? CircleAvatar(
                        radius: 30.0,
                        backgroundColor: Colors.grey,
                        child: Icon(Icons.person, size: 40.0),
                      )
                    : Container(),
                SizedBox(width: 16.0),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Nível: 1',
                        style: TextStyle(
                            color: Theme.of(context).colorScheme.onSecondary)),
                    Text('Moedas: 0',
                        style: TextStyle(
                            color: Theme.of(context).colorScheme.onSecondary)),
                    screenWidthSize > 800
                        ? Text('Conquistas: 0/100',
                            style: TextStyle(
                                color:
                                    Theme.of(context).colorScheme.onSecondary))
                        : Text('Conquistas:\n 0/100',
                            style: TextStyle(
                                color:
                                    Theme.of(context).colorScheme.onSecondary)),
                  ],
                ),
              ],
            ),
            SizedBox(
              height: 16,
            ),
            Stack(
              alignment: Alignment.center,
              children: [
                SizedBox(
                  height: 50,
                  width: 50,
                  child: CircularProgressIndicator(
                    value: 0.61,
                    color: Theme.of(context).colorScheme.secondary,
                    backgroundColor: Theme.of(context).colorScheme.onSecondary,
                    strokeWidth: 8.0,
                  ),
                ),
                Text(
                  '100%',
                  style: TextStyle(
                    color: Theme.of(context).colorScheme.onSecondary,
                    fontSize: 12.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ],
            ),
          ],
        ),
      ],
    );
  }

  Row verticalScreen(BuildContext context) {
    return Row(
      children: [
        CircleAvatar(
          radius: 30.0,
          backgroundColor: Colors.grey,
          child: Icon(Icons.person, size: 40.0),
        ), // Para evitar overflow da tela
        SizedBox(width: 16.0),
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Nível: 1',
                style: TextStyle(
                    color: Theme.of(context).colorScheme.onSecondary)),
            Text('Moedas: 0',
                style: TextStyle(
                    color: Theme.of(context).colorScheme.onSecondary)),
            Text('Conquistas: 0/100',
                style: TextStyle(
                    color: Theme.of(context).colorScheme.onSecondary)),
          ],
        ),
        Spacer(),
        Stack(
          alignment: Alignment.center,
          children: [
            SizedBox(
              height: 50,
              width: 50,
              child: CircularProgressIndicator(
                value: 0.61,
                color: Theme.of(context).colorScheme.secondary,
                backgroundColor: Theme.of(context).colorScheme.onSecondary,
                strokeWidth: 8.0,
              ),
            ),
            Text(
              '100%',
              style: TextStyle(
                color: Theme.of(context).colorScheme.onSecondary,
                fontSize: 12.0,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ],
    );
  }
}
