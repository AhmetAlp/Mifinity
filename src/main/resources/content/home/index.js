(function(){
var module = {
    name: 'angularModular.home',
    dependencies: [],
    config: {
        providers: ['$stateProvider', '$urlRouterProvider']
    },
    homeController: {
        name: 'HomeController',
        injectables: ['$scope','$http','$window']
    }
};

var HomeConfig = function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state( 'app.home', {
            url: '/home',
            templateUrl: 'home/index.tpl.html',
            controller: module.homeController.name + ' as home'
    });
};
HomeConfig.$provide = module.config.providers;


var HomeController = function($scope, $http, $window) {
    $scope.searchText = "";
    $scope.dateRegex = "\\d{2}\/(0[1-9]|1[0-2])";
    $scope.loginInfo = {"userName":"","pass":""};
    $scope.newCard = {"number":"","holderName":"","expiryDate":""};
    $scope.updCard = {"number":"","holderName":"","expiryDate":""};
    $scope.cardList = [];
    
    $scope.logout = function () {
    	$http.post('/api/logout', '').then(function (response) {
    		if (response.data)
    			$scope.msg = "Post Data Submitted Successfully!";
    			$window.location.href = '/static/login.html';
    		}, function (response) {
	    		$scope.msg = "Service not Exists";
	    		$scope.statusval = response.status;
	    		$scope.statustext = response.statusText;
	    		$scope.headers = response.headers();
	        	alert(response.data.message);
    		});
	}
    
    $scope.searchCards = function () {
    	if ($scope.searchText) {
	    	$http({
	        	  method: 'GET',
	        	  url: '/api/search/' + $scope.searchText
	        	}).then(function successCallback(response) {
	        	    $scope.cardList = response.data;
	        	  }, function errorCallback(response) {
	  	        	alert(response.data.message);
	  	        	$window.location.href = '/static/login.html';
	        	  });
    	}
    }   
    
    $scope.addCard = function () {
    	$http.post('/api/create', JSON.stringify($scope.newCard)).then(function (response) {
    		if (response.data)
    			$scope.msg = "Post Data Submitted Successfully!";
    			$window.location.reload();
    		}, function (response) {
	    		$scope.msg = "Service not Exists";
	    		$scope.statusval = response.status;
	    		$scope.statustext = response.statusText;
	    		$scope.headers = response.headers();
	        	alert(response.data.message);
    		});
	}


    $scope.deleteCard = function() {
    	var deleteCardCancel = document.getElementById("deleteCardCancel");
        $http.delete('/api/delete/' + $scope.updCard.number, '').then(function (response) {
        	if (response.data)
        		$scope.msg = "Data Deleted Successfully!";
				$scope.cardList = $scope.cardList.filter( el => el.number !== $scope.updCard.number );
				deleteCardCancel.click();        		
        	}, function (response) {
	        	$scope.msg = "Service not Exists";
	        	$scope.statusval = response.status;
	        	$scope.statustext = response.statusText;
	        	$scope.headers = response.headers();
	        	alert(response.data.message);
        	});
    }
        
    $scope.updateCard = function() {
    	var updateCardCancel = document.getElementById("updateCardCancel");
    	$http.put('/api/update/' + $scope.updCard.number, JSON.stringify($scope.updCard)).then(function (response) {
    		if (response.data)
    			$scope.msg = "Put Data Submitted Successfully!";
	    		let i;
	    		for (i = 0; i < $scope.cardList.length; i++) {
	    		  if ($scope.cardList[i].number == response.data.number) {
	    			  $scope.cardList[i].holderName = response.data.holderName;
	    			  $scope.cardList[i].expiryDate = response.data.expiryDate;
	    		  } 
	    		}    		
    			updateCardCancel.click();
    		}, function (response) {
	    		$scope.msg = "Service not Exists";
	    		$scope.statusval = response.status;
	    		$scope.statustext = response.statusText;
	    		$scope.headers = response.headers();
	        	alert(response.data.message);
    		});
    }    
    $scope.getCardValues = function (updData) {
    	$scope.updCard = updData;        
	}    
};

HomeController.$inject = module.homeController.injectables;


angular.module(module.name, module.dependencies)
.config(HomeConfig)
.controller(module.homeController.name, HomeController);
}());