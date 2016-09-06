/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.controller('homeController',  function ($scope, toastr, $http, apiURL) {
    $("#carousel-example-generic").carousel("cycle");

    $http.get(apiURL + '/user').then(function(response){
        console.log(response);
    });
});