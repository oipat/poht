'use strict';
(function(){

angular.module('BlogiApp.controllers').
controller('FrontPageController', function($scope, BlogiApi) {

	BlogiApi.listBlogPosts().then(function(data) {
		console.log(data);
		$scope.blogPostList = data;
	});
});

})();
