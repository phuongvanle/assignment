angular.module('myApp', ['appRoutes','mainController', 'authServices'])

.config(function ($httpProvider,$routeProvider){
	$httpProvider.interceptors.push('AuthInterceptors');
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$routeProvider.otherwise({redirectTo: '/'});
});
	