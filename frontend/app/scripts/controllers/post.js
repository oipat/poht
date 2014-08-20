'use strict';
(function(){

angular.module('BlogiApp.controllers').
controller('PostController', function($scope, $routeParams, BlogiApi) {

  BlogiApi.getPostByTitle(encodeURIComponent($routeParams.postTitle))
    .then(function(data) {
      $scope.blogPost = data;
    });
});

})();
