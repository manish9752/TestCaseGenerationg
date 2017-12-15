todoApp.service('fileUpload', ['$q', '$http','fileUploadEndpoint', function($q, $http,fileUploadEndpoint) {
	
	
	
	
	
	
	 this.getFileData=(scope,rootScope,dataGrid)=>{
		 
		 fileUploadEndpoint.get().then(function(data){
			 scope.mydata =data;
			 scope.gridOptions.data=data;
			});
		 
		 fileUploadEndpoint.getCount().then(function(data){
			 scope.totalCount=data;
			 
			 console.log(scope.totalCount);
			});
		 
		 
		 
		 scope.gridOptions = {
			  pagingPageSizes: [5, 10, 20],
		      pagingPageSize: 5,
       	      paginationPageSize: 10,
       	      enablePaginationControls: true,
       	      enableSorting: true,
       	      enableRowSelection: true,
       	      multiSelect: false,
       	      enableRowHeaderSelection:true,
       	      enableSelectAll:true,
       	      columnDefs: scope.columns,
       	      enableFiltering: true,
       	      data:scope.mydata,

       	      onRegisterApi: function (gridApi) {

       	          scope.gridApi = gridApi;
       	          scope.rows = [];
       	         
       	      }
       	  };

	 }
	
	
	 
} ]);