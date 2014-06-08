'use strict';

describe('Controller: FrontPageController', function () {

  var FrontPageController;
  var $scope;
  var blogiApiMock;

  var mockBlogPostList = [
    {
      asd: 1
    },
    {
      asd: 2
    }
  ];

  // load the controller's module
  beforeEach(module('BlogiApp'));

  // define mock
  beforeEach(function() {
    blogiApiMock = {
      listBlogPosts: function() { }
    };
  });

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $q) {
    $scope = $rootScope.$new();

    var blogPostListDeferred = $q.defer();
    blogPostListDeferred.resolve(mockBlogPostList);
    spyOn(blogiApiMock, 'listBlogPosts').andReturn(blogPostListDeferred.promise);

    FrontPageController = $controller('FrontPageController', {
      $scope: $scope,
      BlogiApi: blogiApiMock
    });

    $rootScope.$apply();
  }));

  it('should attach a list of blogPosts to the scope', function () {
    expect(blogiApiMock.listBlogPosts).toHaveBeenCalled();
    expect($scope.blogPostList.length).toBe(2);
  });
});
