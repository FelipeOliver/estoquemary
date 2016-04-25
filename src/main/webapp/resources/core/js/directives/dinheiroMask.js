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
            		var a = value + "";
            		a = a.replace(/\D/g,"");                    //Remove tudo o que nÃ£o Ã© numero
            		a = a.replace(/([0-9]{2})$/g, ".$1"); //coloca a virgula antes dos 2 Ãºltimos numeros
            		value = a; 
            		$element[0].value =  value;
                });
            }
        }
    }
})();