/**
 * Created by Dell on 16-Mar-16.
 */


WPAngularStarter.directive('validUsername', ['UserService', function(UserService){
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elm, attrs, ngModel){
            elm.bind('focusout keypress', function (e) {

                if (e.type == 'focusout' || e.keyCode == '13') {

                    ngModel.$setValidity('unique', true);
                    UserService.isUnique(elm.val()).success(function (data) {

                        if (data) {
                            ngModel.$setValidity('unique', false);
                        }
                    });

                }

            });

        }

    };
}]);
