angular.module('authServices', [])


.factory('Auth', function($http, AuthToken){
	var authFactory = {};
	authFactory.login = function(loginData){

		return $http({
				url: 'auth/login',
				method: 'POST',
				data:loginData,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				}
		})
		.then(function(data){
			//AuthToken.setToken(data.data.token);
			return data;
		});

	};
	//Auth.isLoggedIn();
	authFactory.isLoggedIn = function(){
		if (AuthToken.getToken()) {
			return true;
		} else {
			return false;
		}
	};
	//Auth.logout();
	authFactory.logout = function() {
		AuthToken.setToken();
	};


	//Auth.getUser();
	authFactory.getUser = function() {
		if (AuthToken.getToken()) {
			return $http.post('/api/me');
		} else {
			$q.reject({message: 'User has no token'});
		}
	};


	return authFactory;
})

.factory('AuthToken', function($window){
	var authTokenFactory = {};

	//AuthToken.setToken(token);
	authTokenFactory.setToken = function(token) {
		if (token) {
			$window.localStorage.setItem('token', token);
		} else {
			$window.localStorage.removeItem('token');
		}
		
	}
	//AuthToken.getToken();
	authTokenFactory.getToken = function() {
		return $window.localStorage.getItem('token');
	}
	return authTokenFactory;
})

.factory('AuthInterceptors', function(AuthToken){
	var authInterceptorsFactory = {};

	authInterceptorsFactory.request = function(config) {
		var token = AuthToken.getToken();

		if (token) config.headers['x-access-token'] = token;

		return config;
	};
	return authInterceptorsFactory;
});
