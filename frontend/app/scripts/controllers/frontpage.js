'use strict';

angular.module('BlogiApp.controllers', ['BlogiApp.services']).
controller('FrontPageController', function($scope, BlogiApi) {

	BlogiApi.listBlogPosts().then(function(data) {
		console.log(data);
		$scope.blogPostList = data;
	});
});
