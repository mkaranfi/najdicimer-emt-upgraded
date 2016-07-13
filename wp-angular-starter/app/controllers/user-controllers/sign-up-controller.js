/**
 * Created by Dell on 16-Mar-16.
 */
WPAngularStarter.controller('signupController', ['$scope', '$state', 'notifications', 'UserService', function ($scope, $state, notifications, UserService) {

    $scope.name = '';
    $scope.surname = '';
    $scope.birthDate = '';
    $scope.email = '';
    $scope.username = '';
    $scope.password = '';

    $scope.confirm = '';
    $scope.day = '';
    $scope.month = '';
    $scope.year = '';
    $scope.monthT = '';

    $scope.dayData = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];
    $scope.yearData = getYears();

    $scope.save = function () {

        convertMonth();
        $scope.birthDate = calculateDate();

        if (($scope.name != '') && ($scope.surname != '') && ($scope.username != '') && ($scope.email != '') && ($scope.password != '') && ($scope.signup_form.email.$valid) && ($scope.password == $scope.confirm)) {


            var data = $.param({
                "name": $scope.name,
                "surname": $scope.surname,
                "birthDate": $scope.birthDate,
                "email": $scope.email,
                "username": $scope.username,
                "password": $scope.password

            });


            var config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            };


            UserService.saveUser(data, config).success(function (data) {

                notifications.showSuccess($scope.username + ', добредојде во фамилијата цимери!');

                $scope.name = '';
                $scope.surname = '';
                $scope.birthDate = '';
                $scope.email = '';
                $scope.username = '';
                $scope.password = '';

                $scope.confirm = '';
                $scope.day = '';
                $scope.month = '';
                $scope.year = '';

                $state.go('home');


            }).error(function (data) {
                console.log(data);
                notifications.showError('Настана грешка. Не успеавме да го креираме профилот.');

            });
        }

    };

    $scope.cancel = function () {

        $scope.name = '';
        $scope.surname = '';
        $scope.birthDate = '';
        $scope.email = '';
        $scope.username = '';
        $scope.password = '';

        $scope.confirm = '';
        $scope.day = '';
        $scope.month = '';
        $scope.year = '';

        $state.go("home");

    };

    $scope.login = function () {
        $state.go("log-in");
    };


    function calculateDate() {
        return $scope.day + "/" + $scope.month + "/" + $scope.year;
    }

    function getYears() {
        var arr = [];

        for (i = 2016; i >= 1955; i--) {
            arr.push(i);
        }

        return arr;
    }

    function convertMonth() {

        switch ($scope.monthT) {
            case 'Јануари':
                $scope.month = 1;
                break;
            case 'Февруари':
                $scope.month = 2;
                break;
            case 'Март':
                $scope.month = 3;
                break;
            case 'Април':
                $scope.month = 4;
                break;
            case 'Мај':
                $scope.month = 5;
                break;
            case 'Јуни':
                $scope.month = 6;
                break;
            case 'Јули':
                $scope.month = 7;
                break;
            case 'Август':
                $scope.month = 8;
                break;
            case 'Септември':
                $scope.month = 9;
                break;
            case 'Октомври':
                $scope.month = 10;
                break;
            case 'Ноември':
                $scope.month = 11;
                break;
            case 'Декември':
                $scope.month = 12;
                break;
            default:
                break;
        }

    }
}]);
