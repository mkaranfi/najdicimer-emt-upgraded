/**
 * Created by Darko on 2/26/2016.
 */

WPAngularStarter.controller("HomeController", ["$scope", "ListingService", function ($scope, ListingService){
    $scope.graphData = [];

    ListingService.getGraphData().then(function (response){
        $scope.graphData = response.data;
        showGraph();
    }, function (){
        console.log("error fetching the graph data!!");
    });

   function showGraph(){
       var chart = c3.generate({
           bindto: '#chart',
           data: {
               x : 'x',
               columns: [
                   ['x', 'Јануари', 'Февруари', 'Март', 'Април', 'Мај', 'Јуни', 'Јули', 'Август', 'Септември', 'Октомври', 'Ноември', 'Декември'],
                   ['Огласи креирани по месец', $scope.graphData[0], $scope.graphData[1], $scope.graphData[2], $scope.graphData[3], $scope.graphData[4], $scope.graphData[5], $scope.graphData[6], $scope.graphData[7], $scope.graphData[8], $scope.graphData[9], $scope.graphData[10], $scope.graphData[11]]
               ],
               type: 'bar'
           }, axis: {
               x: {
                   type: 'category',
                   tick: {
                       rotate: 90,
                       multiline: false
                   }
               }
           }, bar: {
               width: {
                   ratio: 0.5 // this makes bar width 50% of length between ticks
               }
               // or
               //width: 100 // this makes bar width 100px
           }
       });
   }
}]);