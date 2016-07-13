/**
 * Created by Mile on 03/13/2016.
 */
WPAngularStarter.controller('ViewListingsController', function ($scope, $stateParams, ListingService, serverURL) {
    $scope.usersPerPage = 6;
    $scope.currentPage = 1;
    $scope.serverURL = serverURL;

    ListingService.query().then(function (response) {
        $scope.listings = response.data;
    });

});
