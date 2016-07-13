/**
 * Created by Darko on 3/31/2016.
 */

WPAngularStarter.controller("SentMessageController", ["$scope", "UserService", "serverURL", function ($scope, UserService, serverURL){
    $scope.messages = {};
    $scope.messagesPerPage = 4;
    $scope.currentPage = 1;
    $scope.serverURL = serverURL;
    $scope.title = "Испратени пораки";

    $scope.getOutbox = function (){
        UserService.outbox($scope.userId).then(function (response){
            $scope.messages = response.data;
        }, function (){
            console.log("error fetching your inbox!!");
        });
    };
    $scope.getOutbox();

    $scope.toggleMessageState = function (messageId, state){
        UserService.toggleMessageState(messageId, state).then(function (){
            $scope.getOutbox();
        }, function(){
            console.log("error changing the message state");
        });
    };
}]);