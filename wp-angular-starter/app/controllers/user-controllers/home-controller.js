/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.controller('homeController', ['$scope', 'toastr', function ($scope, toastr) {
    $("#carousel-example-generic").carousel("cycle");
}]);