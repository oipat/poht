angular.module("BlogiApp.services", ['ngResource']).
	factory('BlogiApi', function($resource) {

		var Post = $resource('http://localhost:8080/blogi/posts/');
		var blogiApi = {};

		blogiApi.listBlogPosts = function() {
			return Post.query();
		}

		blogiApi.createPost = function(postData) {
			var postResource = new Post();
			postResource.author =
					{id:1};
			postResource.title = postData.subject;
			postResource.body = postData.body;
			return postResource.$save();
		}

		return blogiApi;
	});
