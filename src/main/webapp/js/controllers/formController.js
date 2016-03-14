var myApp = angular.module('MyApp');

myApp.controller('formController', function fromController($scope) {
    $scope.login = {
        name: ''
    };

    $scope.save = function (login, form) {
        if (form.$valid) {
            alert("Just kidding.You have not been registered coz i can't, but hey, welcome " + login.name + "!")
        }
    }
});
