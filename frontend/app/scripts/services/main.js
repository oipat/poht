angular.module("BlogiApp.services", ['ngResource']).
	factory('BlogiApi', function($resource) {
		
		var Posts = $resource('http://localhost:8080/blogi/posts/');
		var blogiApi = {};

		blogiApi.listBlogPosts = function() {
			return Posts.query();
		}

		return blogiApi;
	});
