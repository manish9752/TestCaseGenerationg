todoApp.factory("fileUploadEndpoint", function($http, $q) {
	  return {
		    get: function() {
		        var deferred = $q.defer();
		        $http.get('/assignment-service/getallrecord')
		        .then(function(response){
		           deferred.resolve(response.data);
		        })
		        .catch(function(response){
		          deferred.reject(response);
		        });
		        return deferred.promise;
		    },
		    getCount: function() {
		        var deferred = $q.defer();
		        $http.get('/assignment-service/getallrecordcount')
		        .then(function(response){
		           deferred.resolve(response.data);
		        })
		        .catch(function(response){
		          deferred.reject(response);
		        });
		        return deferred.promise;
		    }
		  }
		});