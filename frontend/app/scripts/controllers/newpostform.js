'use strict';
(function(){

angular.module('BlogiApp.controllers').
controller('NewPostFormController', function($scope, BlogiApi) {
  $scope.createPost = function() {
    var postData = {};
    postData.subject = $scope.postmodel.subject;
    postData.body = $scope.postmodel.body;
    BlogiApi.createPost(postData);
  };
});

})();
