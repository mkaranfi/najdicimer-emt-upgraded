/**
 * Created by Mile on 03/27/2016.
 */
WPAngularStarter.controller('EditListingController', function ($sce, notifications, $scope, serverURL, ListingService, $stateParams, $state) {
    $scope.listing = $stateParams.listing;
    $scope.serverURL = serverURL;

    $scope.editListing = function () {
        $scope.listing.content = $sce.valueOf($scope.listing.content);
        ListingService.editListing($scope.listing).then(function () {
                $state.go('view-listing', {id: $scope.listing.id});
                notifications.showWarning('Промените на огласот се зачувани.');
            },
            function () {
                $state.go('view-listing', {id: $scope.listing.id});
                notifications.showError('Настана грешка. Огласот не е изменет.');
            });
    }
});