'use strict';

(function(){

// Module definitions
angular.module('BlogiApp', [
	'ngRoute',
	'BlogiApp.controllers',
	'BlogiApp.services'
])

.config(['$routeProvider', function($routeProvider) {
		$routeProvider
			.when('/front', {
				templateUrl: 'views/frontpage.html',
				controller: 'FrontPageController'
			})
			.when('/newpostform', {
				templateUrl: 'views/newpostform.html',
				controller: 'NewPostFormController'
			})
			.when('/post/:postId', {
				templateUrl: 'views/post.html',
				controller: 'PostController'
			})
			.otherwise({
				redirectTo: '/front'
			});
  }
]);

// Module injections
angular.module('BlogiApp.controllers', ['BlogiApp.services'])

})();
