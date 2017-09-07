/**
 * 
 */

var app = angular.module('appRoutes', ['ngRoute'])
.config(function($routeProvider, $locationProvider){
	$routeProvider.when('/', {
		templaleUrl: 'app/views/login.html'
	})
})