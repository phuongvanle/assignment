/**
 * 
 */
'use strict';
angular.module('myApp', ['appRoutes','mainController','authServices'])
.config(function($httpProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$httpProvider.interceptors.push('AuthInterceptors');
})