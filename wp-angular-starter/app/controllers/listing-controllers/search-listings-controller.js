/**
 * Created by Mile on 03/29/2016.
 */
WPAngularStarter.controller('SearchListingsController', function ($scope, serverURL, $stateParams, ListingService) {
        $scope.usersPerPage = 6;
        $scope.currentPage = 1;
        $scope.serverURL = serverURL;
        ListingService.search($stateParams.keyword).then(function (response) {
            $scope.listings = response.data;
        });
    }
);