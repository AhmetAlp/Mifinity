angular.module('postExample', [])
    .controller('PostController', ['$scope', '$http', '$window', function($scope, $http, $window) {
 
        this.postForm = function() {
        	$http.post('/api/login', JSON.stringify(this.loginData)).then(function (response) {
        		if (response.data)
        			debugger;
        			$window.location.href = '/static/cards.html';
        		}, function (response) {
    	    		$scope.msg = "Service not Exists";
    	    		$scope.statusval = response.status;
    	    		$scope.statustext = response.statusText;
    	    		$scope.headers = response.headers();
    	        	alert(response.data.message);
        		});
        }
        
        this.addUser = function() {
        	var registerCancel = document.getElementById("registerCancel");
        	if ($scope.newUser == undefined) {
        		alert("User can not be empty");
        	} else if ($scope.newUser.password != $scope.password2) {
        		alert("Passwords are not matched");
        	} else {
            	$http.post('/api/register', JSON.stringify($scope.newUser)).then(function (response) {
            		if (response.data)
            			registerCancel.click();  
            		}, function (response) {
        	    		$scope.msg = "Service not Exists";
        	    		$scope.statusval = response.status;
        	    		$scope.statustext = response.statusText;
        	    		$scope.headers = response.headers();
        	        	alert(response.data.message);
            		});        		
        	}        	
        }
 
    }]);