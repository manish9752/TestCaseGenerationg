todoApp.directive('fileModel', [ '$parse', function($parse) {
	
	var validFormats = ['csv'];
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;
			element.bind('change', function() {
				
				var ext= element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1).toLowerCase();  
				if(validFormats.indexOf(ext) !== -1){
					scope.fileHolder=element[0].files[0].name;
				 scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				    console.log(element[0].files[0]);
				    
				});
				} 
				
			});
			
		}
	};
} ]);