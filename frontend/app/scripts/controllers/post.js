'use strict';
(function(){

angular.module('BlogiApp.controllers').
controller('PostController', function($scope, $routeParams, BlogiApi) {

  BlogiApi.getPost($routeParams.postId).then(function(data) {
    $scope.blogPost = data;
  });
});


})();
