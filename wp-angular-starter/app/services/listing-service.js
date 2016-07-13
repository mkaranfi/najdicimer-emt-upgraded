/**
 * Created by milew on 23.2.2016.
 */
WPAngularStarter.factory('ListingService', ['$http', 'apiURL', function ($http, apiURL) {
    return {
        save: function (title, content, file, userId, location) {
            var fd = new FormData();
            if (typeof file != 'undefined') {
                for (i = 0; i < file.length; i++)
                    fd.append('file[]', file[i]);
            }
            fd.append('title', title);
            fd.append('content', content);
            fd.append("userId", userId);
            //location
            fd.append("locationName", location.name);
            fd.append("lat", location.geometry.location.lat());
            fd.append("lng", location.geometry.location.lng());
            return $http({
                url: apiURL + '/listing/new',
                method: 'POST',
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity,
                data: fd
            });
        },

        query: function () {
            return $http({
                url: apiURL + '/listing',
                method: 'GET'
            });
        },

        queryById: function (id) {
            return $http({
                url: apiURL + '/listing/' + id,
                method: 'GET'
            });
        },
        queryByUserId: function(userId){
            return $http({
                url: apiURL + '/listing/user/' + userId,
                method: 'GET'
            });
        },
        delete: function (id) {
            return $http({
                url: apiURL + '/listing/' + id,
                method: 'DELETE'
            });
        },

        queryByUserId: function (userId){
            return $http.get(apiURL + "/listing/user/" + userId);
        },

            reportPost: function (report) {
            var data = $.param({
                "content": report.content,
                "userId": report.userFromId,
                "listingId": report.listingId
            });

            return $http({
                url: apiURL + '/listing/report/new',
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: data
            });
        },

        isOwner: function (id) {
            return $http({
                url: apiURL + '/listing/isOwner/' + id,
                method: 'GET'
            });
        },

        editListing: function (listing) {
            return $http({
                url: apiURL + '/listing/edit',
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: $.param({
                    id: listing.id,
                    title: listing.title,
                    content: listing.content
                })
            });
        },

        search: function (keyword) {
            return $http({
                url: apiURL + '/listing/search/' + keyword,
                method: 'GET'
            });
        }
    }
}]);