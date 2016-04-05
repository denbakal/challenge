var myApp = angular.module('MyApp');

myApp.controller('clickCtrl', function ($scope, $http) {
    $scope.click = function () {
        var user = {name: "Vasya", age: "42"};

        $http.post('/click', user, {}).success(function(data) {
            $scope.response = data;
        })
    };
});
