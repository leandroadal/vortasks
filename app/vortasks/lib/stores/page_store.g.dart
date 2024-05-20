// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'page_store.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$PageStore on PageStoreBase, Store {
  late final _$selectedPageAtom =
      Atom(name: 'PageStoreBase.selectedPage', context: context);

  @override
  int get selectedPage {
    _$selectedPageAtom.reportRead();
    return super.selectedPage;
  }

  @override
  set selectedPage(int value) {
    _$selectedPageAtom.reportWrite(value, super.selectedPage, () {
      super.selectedPage = value;
    });
  }

  late final _$pageControllerAtom =
      Atom(name: 'PageStoreBase.pageController', context: context);

  @override
  PageController get pageController {
    _$pageControllerAtom.reportRead();
    return super.pageController;
  }

  @override
  set pageController(PageController value) {
    _$pageControllerAtom.reportWrite(value, super.pageController, () {
      super.pageController = value;
    });
  }

  late final _$PageStoreBaseActionController =
      ActionController(name: 'PageStoreBase', context: context);

  @override
  void setPage(int page) {
    final _$actionInfo = _$PageStoreBaseActionController.startAction(
        name: 'PageStoreBase.setPage');
    try {
      return super.setPage(page);
    } finally {
      _$PageStoreBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
selectedPage: ${selectedPage},
pageController: ${pageController}
    ''';
  }
}
