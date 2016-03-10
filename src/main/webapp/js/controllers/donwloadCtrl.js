var myApp = angular.module('MyApp');

myApp.controller('downloadCtrl', function ($scope, $http) {
    $scope.download = function () {
        $http.post('/upload', {"name": "name", "age": "42"}, {});
    };
});
