var app = angular.module('appRoutes', ['ngRoute'])

.config(function($routeProvider, $locationProvider){
	$routeProvider.when('/', {
		templateUrl: 'views/login.html',
		authenticated: false,
		controller: 'mainCtrl'
	})
	.when('/dashboard', {
		templateUrl: 'views/chart.html',
		authenticated: false,
		controller: 'mainCtrl'
	})
	.otherwise({redirectTo: '/'});
	
	$locationProvider.html5Mode({
	  	enabled: true,
	  	requireBase: false
	});
});

app.run(['$rootScope', 'Auth','$location', function($rootScope, Auth, $location){

	$rootScope.$on('$routeChangeStart', function (event, next, current){
		if (next.$$route.authenticated == true) {
			if (!Auth.isLoggedIn()) {
				event.preventDefault();
				$location.path('/');
			}

		} else if (next.$$route.authenticated == false) {
			if (Auth.isLoggedIn()) {
				event.preventDefault();
				$location.path('/profile');
			}

		}
		
	});

}]);