var todoApp = angular.module("todoApp", ['ngAnimate','ngTouch', "ui.grid", "ui.grid.autoResize", "ui.grid.resizeColumns", "ui.grid.pagination", "ui.grid.selection", "ui.bootstrap","ngRoute"]);

 

todoApp.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "view/dashboard.html",
        controller : "dashBoardController"
    })
    .when("/dashboard", {
        templateUrl : "view/dashboard.html",
        controller : "dashBoardController"
    })
    
    .otherwise({
    	
    	templateUrl : "view/dashboard.html",
        controller : "dashBoardController"
    		
        })
    
});




 



 



/*
todoApp.filter("checkedItems", function () {
	return function (items, showComplete) {
	var resultArr = [];
	angular.forEach(items, function (item) {
	if (item.done == false || showComplete == true) {
		console.log(item.action);
	resultArr.push(item);
	}
	});
	return resultArr;
	}
	});
*/
/*todoApp.controller("ToDoCtrl", function ($scope) {
	console.log("inside confto" );
	$scope.todo = model;
	
	angular.forEach(permissionData.items,function(key, value){
		
		var splitArray=key.type.split(".");
		 
		//console.log(splitArray);
		 
			 console.log(splitArray);
			 
			 
		 
	});*/
	 
	
	
	
	
	
	/*$scope.incompleteCount = function () {
		var count = 0;
		angular.forEach($scope.todo.items, function (item) {
		if (!item.done) { count++; }
		});
		return count;
		};
		
	$scope.warningLevel = function () {
		return $scope.incompleteCount() < 3 ? "label-success" : "label-warning";
		};
	
	
	$scope.addNewItem = function (actionText) {
		 
		console.log(actionText);
		
		//$scope.todo.items.push({ action: actionText, done: false });
		
		$scope.todo.items.push({ release: actionText,version:"1011", done: false});
		};
		
		
		$scope.openModel=function(){
			$("#myModal").modal()
		};
		
		$scope.data = {
			    availableOptions: [
			      {id: '0', name: 'Select'},
			      {id: '1', name: 'Option A'},
			      {id: '2', name: 'Option B'},
			      {id: '3', name: 'Option C'}
			    ],
			    selectedOption: {id: '0', name: 'Select'} //This sets the default value of the select in the ui
			    };
		
		$scope.validateform=function(){
			console.log($scope.user);
			$scope.erroruser=false;
			$scope.pwderror=false;
			
			if($scope.user==undefined){
				$scope.erroruser=true;
			} 
			
			else if($scope.psw==undefined){
				$scope.pwderror=true;
			} 
		}; 
		
		$scope.tags = [
	                    { text: 'just' },
	                    { text: 'some' },
	                    { text: 'cool' },
	                    { text: 'tags' }
	                ];
	                $scope.loadTags = function(query) {
	                     return $http.get('/tags?query=' + query);
	                };
	           
	});*/