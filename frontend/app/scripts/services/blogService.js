'use strict';

angular.module('BlogiApp.services', []).
	factory('BlogiApi', function($http) {

		var apiRoot = "http://localhost:8080/blogi"
		var blogiApi = {};


		blogiApi.listBlogPosts = function() {
			var promise = $http({
        method: 'GET',
        url: apiRoot + '/posts'
			}).then(function(response) {
				return response.data._embedded.posts;
			});
			return promise;
		};

		blogiApi.createPost = function(postData) {
			// TODO: change to real id when identity information is available.
			console.log(postData);
			var promise = $http({
				method: 'POST',
				url: apiRoot + '/posts',
				data: postData
			}).then(function(response) {
				//return response.data._embedded.posts;
			});
			return promise;
		};

		blogiApi.getPostByTitle = function(title) {
			var promise = $http({
				method: 'GET',
				url: apiRoot + '/posts/search/findByTitle?title=' + title
			}).then(function(response) {
				return response.data._embedded.posts[0];
			});
			return promise;
		}

		return blogiApi;
	});
