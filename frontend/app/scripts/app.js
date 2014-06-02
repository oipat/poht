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
			.when('/post', {
				templateUrl: 'views/post.html',
				controller: 'PostController'
			})
			.otherwise({
				redirectTo: '/front'
			});
}]);
