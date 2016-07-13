/**
 * Created by Darko on 3/19/2016.
 */

WPAngularStarter.controller("ReportController", ["$scope", "ListingService", function ($scope, ListingService){
    $scope.reports = [];

    var getAllReports = function (){
        ListingService.getAllReports().then(function (response){
            $scope.reports = response.data;
        }, function (){
            console.log("error fetching the reports!!");
        });
    };
    getAllReports();

    $scope.updateReport = function (id){
        ListingService.getReport(id).then(
            function (response){
                var report = {};
                report = response.data;
                ListingService.updateReport(report.id, !report.seen).then(function (){}, function (){
                    console.log("error updating the report!!");
                });
            }, function (){
                console.log("error fetching the report data!!");
            }
        );
    };

    $scope.toggleViewState = function (id, seen){
        ListingService.updateReport(id, seen).then(function (){
            getAllReports();
            $scope.updateUnreadCount();
        }, function (){
            console.log("error updating the view state of the report!!");
        });
    }
}]);