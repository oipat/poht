angular.module('BlogiApp.controllers', ['BlogiApp.services']).
controller('MainController', function($scope, BlogiApi) {

	$scope.blogPostList = BlogiApi.listBlogPosts();
	console.log(BlogiApi.listBlogPosts());
});
