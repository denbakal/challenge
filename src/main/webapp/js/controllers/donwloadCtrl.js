var myApp = angular.module('MyApp');

myApp.controller('downloadCtrl', function ($scope, $http) {
    $scope.download = function () {
        $http.post('/download', {}, {responseType:'arraybuffer'}).success(function(data) {
            var blob = new Blob([data]);
            saveAs(blob, 'Application.pdf')
        });
    };
});
