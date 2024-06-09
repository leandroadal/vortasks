import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:vortasks/screens/auth/login/widgets/login_form.dart';
import 'package:vortasks/stores/login_store.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final LoginStore loginStore = GetIt.I<LoginStore>();
    final ColorScheme colorScheme = Theme.of(context).colorScheme;

    return Scaffold(
      //extendBodyBehindAppBar: true,
      appBar: AppBar(
        title: const Text('Entrar na conta'),
        centerTitle: true,
        backgroundColor: colorScheme.primaryContainer,
        /*backgroundColor: Theme.of(context).brightness == Brightness.light
              ? Theme.of(context).colorScheme.primary
              : Theme.of(context).colorScheme.onPrimary,
          foregroundColor: Theme.of(context).brightness == Brightness.light
              ? Theme.of(context).colorScheme.onPrimary
              : Theme.of(context).colorScheme.primary, */
      ),
      body: LoginForm(colorScheme: colorScheme, loginStore: loginStore),
    );
  }
}
