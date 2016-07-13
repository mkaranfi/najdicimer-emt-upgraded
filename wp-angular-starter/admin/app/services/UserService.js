/**
 * Created by Darko on 2/26/2016.
 */

WPAngularStarter.factory("UserService", ["$http", "apiURL", function ($http, apiURL){
    var factory = {};

    factory.getAllUsers = function (){
        return $http.get(apiURL + "/user");
    };

    factory.removeUser = function (id){
        return $http.delete(apiURL + "/user/remove/" + id);
    };

    factory.getUser = function (id){
        return $http.get(apiURL + "/user/" + id);
    };

    factory.updateUser = function (id, name, surname, email, username, password, birthDate){
        var data = $.param({
            "name": name,
            "surname": surname,
            "email": email,
            "username": username,
            "password": password,
            "birthDate": birthDate
        });
        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
        return $http.post(apiURL + "/user/update/" + id, data, config); //unable to make PUT request
    };

    return factory;
}]);