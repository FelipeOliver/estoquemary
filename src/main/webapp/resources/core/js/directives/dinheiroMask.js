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
            		var v= "'" + value + "'";
            		v=v.replace(/\D/g,"");//Remove tudo o que não é numero
            		v=String(Number(v));
//            		v=v.replace(/(\d)(\d{8})$/,"$1.$2");//coloca o ponto dos milhões
//            	    v=v.replace(/(\d)(\d{5})$/,"$1.$2");//coloca o ponto dos milhares
            	    v=v.replace(/(\d)(\d{2})$/,"$1.$2");//coloca a virgula antes dos 2 últimos dígitos
//
//                	var a = ;
//            		a = a.replace(/\D/g,"");                    //Remove tudo o que nÃ£o Ã© numero
//            		a = a.replace(/([0-9]{2})$/g, ".$1"); //coloca a virgula antes dos 2 Ãºltimos numeros
            		value = v; 
            		$element[0].value =  value;
                });
            }
        }
    }
})();