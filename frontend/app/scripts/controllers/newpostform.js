'use strict';
(function(){

angular.module('BlogiApp.controllers').
controller('NewPostFormController', function($scope, BlogiApi) {
  $scope.createPost = function() {
    var postData = {};
    postData.title = $scope.postmodel.title;
    postData.body = $scope.postmodel.body;
    postData.author = "http://localhost:8080/blogi/users/1";
    BlogiApi.createPost(postData);
  };
});

})();
