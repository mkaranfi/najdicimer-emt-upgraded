/**
 * Created by Darko on 3/20/2016.
 */

WPAngularStarter.controller("HeaderController", ["$rootScope", "ListingService", "siteURL", function ($rootScope, ListingService, siteURL){
    $rootScope.unread = 0;

    $rootScope.updateUnreadCount = function (){
        ListingService.getAllUnreadReports().then(function(response){
            $rootScope.unread = response.data.length;
        }, function (){
            console.log("error fetching the unread reprots!!");
        });
    };
    $rootScope.updateUnreadCount();

    $rootScope.viewSite = function (){
        window.location.href = siteURL;
    };
}]);