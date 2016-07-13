/**
 * Created by Darko on 3/2/2016.
 */

WPAngularStarter.controller("EditUserDetailsController", ["$scope", "UserService", "$stateParams", "toastr", function ($scope, UserService, $stateParams, toastr){
    $scope.user = {
        "name": $scope.$parent.user.name,
        "surname": $scope.$parent.user.surname,
        "username" : $scope.$parent.user.username,
        "password": $scope.$parent.user.password,
        "email": $scope.$parent.user.email,
        "birthDate": $scope.$parent.birthDate
    };

    if ($scope.user = {})
        UserService.getUser($stateParams.id).then(function (response){
            $scope.user = response.data;
        }, function (){
            toastr.error("Error fetching the user!!");
        });

    $scope.save = function (){
        UserService.updateUser($stateParams.id, $scope.user.name, $scope.user.surname, $scope.user.email, $scope.user.username, $scope.user.password, $scope.user.birthDate).then(function (){
                toastr.success("User updated successfully!!");
            },
            function (){
                toastr.error("Error updating the user!!");
            });
        $scope.$parent.user = $scope.user;
    };
}]);