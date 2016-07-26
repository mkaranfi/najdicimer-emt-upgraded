/**
 * Created by Dell on 16-Mar-16.
 */

WPAngularStarter.factory('UserService', ['$http', 'apiURL', function ($http, apiURL) {
    return {

        saveUser: function (data, config) {
            return $http.post(apiURL + "/user/signup", data, config);
        },

        editUser: function (user) {
            return $http({
                method: 'POST',
                url: apiURL + '/user/update/' + user.id,
                data: $.param({
                    id: user.id,
                    name: user.name,
                    surname: user.surname,
                    email: user.email,
                    username: user.username,
                    password: user.password,
                    birthDate: user.birthDate
                }),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
        },

        isUnique: function (username) {
            return $http.get("http://localhost:8080/api/users/" + username);
        },

        loginUser: function (data, config) {
            // var headers = credentials ? {authorization : "Basic "
            // + btoa(credentials.username + ":" + credentials.password)
            // } : {};
            //
            // return $http.get(apiURL + '/user/user', {headers : headers});
            return $http.post(apiURL + '/user/login', data, config);
        },

        getUser: function (id) {
            return $http({
                method: 'GET',
                url: apiURL + '/user/' + id
            });
        },

        sendMessage: function (message) {
            var data = $.param({
                "content": message.content,
                "userFromId": message.userFromId,
                "userToId": message.userToId
            });
            var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
            return $http.post(apiURL + "/user/message/new", data, config);
        },

        inbox: function (userId) {
            return $http.get(apiURL + "/user/message/received/" + userId);
        },

        outbox: function (userId) {
            return $http.get(apiURL + "/user/message/sent/" + userId);
        },

        toggleMessageState: function (messageId, state) {
            var data = $.param({
                "state": state
            });
            var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
            return $http.post(apiURL + "/user/message/update/" + messageId, data, config);
        },

        getMessage: function (messageId) {
            return $http.get(apiURL + "/user/message/" + messageId);
        }

    }

}]);