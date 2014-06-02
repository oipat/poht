angular.module('BlogiApp.controllers', ['BlogiApp.services']).
controller('FrontPageController', function($scope, BlogiApi) {

	$scope.blogPostList = BlogiApi.listBlogPosts();
	console.log(BlogiApi.listBlogPosts());
});
