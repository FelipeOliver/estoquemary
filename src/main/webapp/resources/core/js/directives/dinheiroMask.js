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
            		var v = String(value);
            		v = v.replace(/\D/g,"");
            		v = String(Number(v));
            		while(v.length < 3){
            			v = "0" + v; 
            		}
            		v = v.replace(/(\d+)(\d{2})/, "$1.$2");
            		value = v;
            		$element[0].value =  value;
                });
            }
        }
    }
})();