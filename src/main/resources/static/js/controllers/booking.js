angular.module('challenge.booking', [
    'ui.router'
]).config(function ($stateProvider) {
    $stateProvider
        .state('booking', {
            url: '/booking',
            controller: 'booking.controller',
            templateUrl: 'views/booking/booking.html'
        })
        .state('country', {
            url: '/booking/country',
            controller: 'country.controller',
            templateUrl: 'views/booking/addCountry.html'
        })
    ;

}).controller('booking.controller', function ($scope, $http) {
}).controller('country.controller', function ($scope, $http) {
    $http.get("/country/get")
        .success(function(data, status) {
        })
        .error(function(data, status) {
        });

    $scope.country = "";
    $scope.sendCountry = function () {
        $http.post("/country", null, {params: {country: $scope.country}})
            .success(function(data, status) {
            })
            .error(function(data, status) {
            });
    };
    $scope.result = "";
    $scope.asyncResult = function () {
        $http.get("/country/result", null)
            .success(function(data, status) {
                alert("success");
                $scope.result="success";
            })
            .error(function(data, status) {
                alert("error");
            });
    };
})
;
