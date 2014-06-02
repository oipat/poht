angular.module('BlogiApp.controllers').
controller('PostController', function($scope, BlogiApi) {
  $scope.createPost = function() {
    var postData = {};
    postData.subject = $scope.subject;
    postData.body = $scope.body;
    BlogiApi.createPost(postData);
  }
});
