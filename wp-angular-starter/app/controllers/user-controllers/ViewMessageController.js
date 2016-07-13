/**
 * Created by Darko on 3/31/2016.
 */

WPAngularStarter.controller("ViewMessageController", ["$scope", "$stateParams", "UserService", function ($scope, $stateParams, UserService){
    $scope.id = $stateParams.id;
    $scope.message = {};

    UserService.getMessage($scope.id).then(function (response){
        $scope.message = response.data;
        UserService.toggleMessageState($scope.id, true).then(function (){}, function(){
            console.log("error changing the message state");
        });
    }, function (){
        console.log("error fetching the message");
    });
}]);