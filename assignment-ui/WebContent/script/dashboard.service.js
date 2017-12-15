todoApp.service('dashBoardService', function() {
	var $ctrl=this;
	
    
    
    this.uploadFileProcess=(scope,rootScope,http)=>{
    	http.get("/assignment-service/hello/manish")
        .then(function(response) {
        	console.log(response.data);
        	scope.myWelcome = response.data;
        });
    };
    
    this.uploadFileProcess=(scope,rootScope,http,q,fileUpload,file)=>{
    	 
    	 
        
        
         var uploadUrl = "/assignment-service/uploadfile";
          fileUpload.uploadFileToUrl(file, uploadUrl).then(function(result){
        	  scope.message = fileUpload.getResponse();
          console.log(scope.message);
          scope.errVisibility = true;
          }, function(error) {
          alert('error');
          })
    }
    
    this.displayGridData=(scope,rootScope,data)=>{
    	
    	console.log
    	
      
    	 
    }
});

