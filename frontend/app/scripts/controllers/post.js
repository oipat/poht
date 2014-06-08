'use strict';

angular.module('BlogiApp.controllers').
controller('PostController', function($scope, BlogiApi) {
  $scope.createPost = function() {
    var postData = {};
    postData.subject = $scope.postmodel.subject;
    postData.body = $scope.postmodel.body;
    BlogiApi.createPost(postData);
  };
});
