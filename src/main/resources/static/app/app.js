/**
 * 
 */

angular.module('myApp', ['appRoutes','mainController','authServices'])
.config(function($httpProvider) {
	$httpProvider.interceptors.push('AuthInterceptors');
})