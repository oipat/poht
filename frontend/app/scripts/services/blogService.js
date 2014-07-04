'use strict';

angular.module('BlogiApp.services', ['ngResource']).
	factory('BlogiApi', function($resource) {

		var Post = $resource('http://localhost:8080/blogi/posts/:postId');
		var blogiApi = {};

		blogiApi.listBlogPosts = function() {
			return Post.query().$promise;
		};

		blogiApi.createPost = function(postData) {
			var postResource = new Post();
			// TODO: change to real id when identity information is available.
			postResource.author =
					{id:1};
			postResource.title = postData.subject;
			postResource.body = postData.body;
			return postResource.$save();
		};

		blogiApi.getPost = function(id) {
			return Post.get({postId: id}).$promise;
		}

		return blogiApi;
	});
