var myApp = angular.module('MyApp');

myApp.controller('downloadCtrl', function ($scope, $http) {
    $scope.download = function () {
        $http({
            url: '/upload',
            method: 'POST',
            params: {param: 'param'}
        });
    };
});
