var myApp = angular.module('MyApp');

myApp.controller('formController', function ($scope, $http, $location) {
    $scope.login = {
        name: '',
        password: '',
        country: '',
        lifeForm: '',
        birthDate: ''
    };

    $scope.countries = [
        {name: 'Afghanistan', code: 'AF'},
        {name: 'Åland Islands', code: 'AX'},
        {name: 'Albania', code: 'AL'}
    ];

    $scope.save = function (login, form) {
        if (form.$valid) {
            $http.post('/register', login, {}).success(function (answer) {
                    if (answer == "1") {
                        //$window.location.href = '/register';
                        $location.url('/register');
                    }
                }
            )
        }
    };
});
