'use strict';

describe('Controller: MainController', function () {

  // load the controller's module
  beforeEach(module('BlogiApp'));

  var MainController,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainController = $controller('MainController', {
      $scope: scope
    });
  }));

  it('should attach a list of blogPosts to the scope', function () {
    //expect(scope.blogPostList.length).toBeGreaterThan(0);
  });
});
