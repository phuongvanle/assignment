/**
 * 
 */
'use strict';
var app = angular.module('appRoutes', ['ngRoute'])
.config(function($routeProvider, $locationProvider){
	$routeProvider.when('/', {
		templateUrl: 'views/login.html'
	})
	.otherwise({redirecTo: '/'});
	$locationProvider.html5Mode({
		enable: true,
		requireBase: false
	});
});

app.run(['$rootScope','Auth', '$location', function($rootScope, Auth, $location)) {
	
}]);