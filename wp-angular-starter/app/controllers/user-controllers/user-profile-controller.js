/**
 * Created by Mile on 04/03/2016.
 */
WPAngularStarter.controller('userProfileController', function ($scope, $state, notifications, serverURL, UserService, ListingService) {
    $scope.usersPerPage = 3;
    $scope.currentPage = 1;
    $scope.serverURL = serverURL;
    UserService.getUser($scope.userId).then(function (response) {
        $scope.user = response.data;
    });

    ListingService.queryByUserId($scope.userId).then(function (response) {
        $scope.listings = response.data;
    });

    $scope.editProfile = function () {
        UserService.editUser($scope.user).then(function () {
            $state.go('home');
            notifications.showSuccess({message: 'Профилот е изменет!'});
            notifications.showWarning('Промените се зачувани.');
        });
    }
});
