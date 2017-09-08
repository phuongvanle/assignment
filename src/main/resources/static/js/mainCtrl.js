angular.module('mainController', ['authServices'])

.controller('mainCtrl', function(Auth, $timeout, $location, $rootScope, $window){
	var app = this;
	app.loadme = false;
	$rootScope.$on('$routeChangeStart', function(){
		if (Auth.isLoggedIn()) {
		app.isLoggedIn = true;
		Auth.getUser().then(function(data){
			app.username = data.data.username;
			app.email = data.data.email;
			app.loadme = true;
		})
		} else {
			app.username = '';
			app.isLoggedIn = false;
			app.loadme = true;
		}
		if ($location.hash() == '_=_') $location.hash(null);
	});


	this.facebook = function() {
		/*console.log($window.location.host);
		console.log($window.location.protocol);*/
		app.disabled = true;
		$window.location = $window.location.protocol + '//' + $window.location.host + '/auth/facebook';
	}

	

	
	 this.doLogin = function(loginData) {
	 	app.loading = true;
	 	app.errorMsg = false;
	 	app.expired = false;
	 	app.disabled = true;
		Auth.login(app.loginData).then(function(data){
			if(data.data.success) {
				//create success message
				// redirect to home page
				app.loading = false;
				app.successMsg = data.data.message + 'redirecting...';
				$timeout(function(){
					$location.path('/about');
					app.loginData = '';
					app.successMsg =  false;
				}, 2000);
				

			} else {
				if (data.data.expired) {
					//create one error message
					app.expired = true;
					app.loading = false;
					app.errorMsg = data.data.message;
				} else {
					//create one error message
					app.loading = false;
					app.disabled = false;
					app.errorMsg = data.data.message;
				}
			}
		});
	};

	this.logout = function() {
		Auth.logout();
		$location.path('/logout');
		$timeout(function(){
			$location.path('/')
		}, 2000);
	}
})





