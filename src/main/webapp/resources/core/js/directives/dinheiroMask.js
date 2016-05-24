(function() {
    'use strict';
    app
        .directive('ngDinheiro', NgDinheiro);
 
    function NgDinheiro() {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function($scope, $element, $attrs, ngModel) {
                $scope.$watch($attrs.ngModel, function(value) {
            		console.log(value);
                	var v = String(value);
            		v = v.replace(/\D/g,"");
            		v = v.replace(".","");
            		v = String(Number(v));
            		while(v.length < 3){
            			v = "0" + v; 
            		}
            		v = v.replace(/(\d+)(\d{2})/, "$1.$2");
            		value = v;
            		console.log(value);
            		$element[0].value =  value;
                });
            }
        }
    }
})();