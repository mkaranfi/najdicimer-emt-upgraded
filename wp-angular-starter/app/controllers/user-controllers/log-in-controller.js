/**
 * Created by Dell on 16-Mar-16.
 */


WPAngularStarter.controller('loginController', ['$scope', '$window', 'notifications', '$state',
    'UserService', "$rootScope", "adminURL", function ($scope, $window, notifications, $state, UserService, $rootScope, adminURL) {
        $scope.username = '';
        $scope.password = '';


        $scope.cancel = function () {

            $scope.username = '';
            $scope.password = '';

            $state.go("home");
        };

        $scope.save = function () {
            if ($scope.username != '' && $scope.password != '') {

                var data = $.param({
                    username: $scope.username,
                    password: $scope.password
                });

                var config = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                };

                UserService.loginUser(data, config).success(function (data) {
                    if (data) {
                        $('#err-message').removeClass('show');
                        $scope.createCookie("userId", data.id, 1); //add the current user cookie
                        $scope.createCookie("userIsAdmin", data.isAdmin, 1); //is he admin?
                        $scope.createCookie("userName", data.username, 1); //add the current user cookie
                        $rootScope.isLoggedIn = true;
                        $rootScope.isAdmin = data.isAdmin;
                        $rootScope.userName = data.username;
                        notifications.showSuccess(data.username + ', добредојде назад!');
                        if (data.isAdmin == true)
                            window.location.href = adminURL;
                        else {
                            $state.go("home");
                        }
                    } else {
                        notifications.showError('Настана грешка. Не успеавме да ве најавиме.');
                        $('#err-message').addClass('show');
                    }

                }).error(function () {
                    notifications.showError('Настана грешка. Не успеавме да ве најавиме.');

                });

            }
        };

        $scope.signup = function () {
            $state.go("sign-up");
        };
    }]);
