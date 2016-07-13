/**
 * Created by Darko on 3/10/2016.
 */

WPAngularStarter.factory("ListingService", ["$http", "apiURL", function ($http, apiURL){
    var factory = {};

    factory.deleteListing = function (id){
        return $http.delete(apiURL + "/listing/" + id);
    };

    factory.getListingsByUser = function (id){
        return $http.get(apiURL + "/listing/user/" + id);
    };

    factory.getAllReports = function (){
        return $http.get(apiURL + "/listing/report");
    };

    factory.getAllUnreadReports = function (){
        return $http.get(apiURL + "/listing/report/unread");
    };

    factory.getReport = function (id){
        return $http.get(apiURL + "/listing/report/" + id);
    };

    factory.updateReport = function (id, seen){
        var data = $.param({
            "seen": seen
        });
        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
        return $http.post(apiURL + "/listing/report/update/" + id, data, config);
    };

    factory.getGraphData = function (){
        return $http.get(apiURL + "/listing/graph");
    };

    return factory;
}]);