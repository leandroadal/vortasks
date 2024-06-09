import 'package:get_it/get_it.dart';
import 'package:mobx/mobx.dart';
import 'package:vortasks/controllers/auth_controller.dart';
import 'package:vortasks/stores/user_store.dart';
part 'logout_store.g.dart';

class LogoutStore = LogoutStoreBase with _$LogoutStore;

abstract class LogoutStoreBase with Store {
  @observable
  String? logoutError;

  @action
  void setLogoutError(String? value) => logoutError = value;

  @action
  Future<void> logoutUser() async {
    logoutError = null;
    await AuthController().logout();
    if (logoutError == null) {
      final userStore = GetIt.I<UserStore>();
      userStore.setToken(null);
      userStore.setUser(null);
      logoutError = null;
    }
  }

  @action
  void clear() {
    logoutError = null;
  }
}
