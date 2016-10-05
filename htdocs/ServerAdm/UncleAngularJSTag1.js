var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $http.get("UncleSreverTag1.php")
    .then(function (response) {
	$scope.names = response.data.records;
	}, function(response) {
        //Second function handles error
        $scope.content = "Something went wrong";
	});
});