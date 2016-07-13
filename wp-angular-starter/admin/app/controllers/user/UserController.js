/**
 * Created by Darko on 2/26/2016.
 */

WPAngularStarter.controller("UserController", ["$scope", "UserService", "toastr", function ($scope, UserService, toastr){
    $scope.users = [];
    $scope.usersPerPage = 10;
    $scope.currentPage = 1;

    var getAllUsers = function () {
        UserService.getAllUsers().then(function (response) {
            $scope.users = response.data;
        }, function () {
            toastr.error("Error fetching the users!!");
        });
    };
    getAllUsers();

    $scope.removeUser = function (id){
        UserService.removeUser(id).then(function (){
            toastr.success("User removed successfully!!");
            getAllUsers();
        }, function (){
            toastr.error("Error removing user!!");
        });
    };

    $scope.userDetails = function (id){

    };
}]);