/**
 * Created by Darko on 3/31/2016.
 */

WPAngularStarter.controller("ReceivedMessageController", ["$scope", "UserService", "serverURL", function ($scope, UserService, serverURL){
    $scope.messages = {};
    $scope.messagesPerPage = 4;
    $scope.currentPage = 1;
    $scope.serverURL = serverURL;
    $scope.title = "Примени пораки";

    $scope.getInbox = function (){
        UserService.inbox($scope.userId).then(function (response){
            $scope.messages = response.data;
        }, function (){
            console.log("error fetching your inbox!!");
        });
    };
    $scope.getInbox();

    $scope.toggleMessageState = function (messageId, state){
        UserService.toggleMessageState(messageId, state).then(function (){
            $scope.getInbox();
        }, function(){
            console.log("error changing the message state");
        });
    };
}]);