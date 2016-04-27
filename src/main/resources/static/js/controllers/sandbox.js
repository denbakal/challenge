angular.module('challenge.sandbox', [
    'ui.router'
]).config(function ($stateProvider) {
    $stateProvider
        .state('sandbox', {
            url: '/sandbox',
            controller: 'sandbox.controller',
            templateUrl: 'views/sandbox/sandbox.html'
        })
        .state('download', {
            url: '/sandbox/download',
            controller: 'download.controller',
            templateUrl: 'views/sandbox/download.html'
        })
        .state('dark', {
            url: '/sandbox/dark',
            controller: 'dark.side.controller',
            templateUrl: 'views/sandbox/darkSide.html'
        })
        .state('light', {
            url: '/sandbox/light',
            controller: 'light.side.controller',
            templateUrl: 'views/sandbox/lightSide.html'
        })
        .state('click', {
            url: '/sandbox/click',
            controller: 'click.controller',
            templateUrl: 'views/sandbox/click.html'
        })
        .state('form', {
            url: '/sandbox/form',
            controller: 'form.controller',
            templateUrl: 'views/sandbox/form.html'
        })
    ;

}).controller('sandbox.controller', function ($scope, $http) {

}).controller('dark.side.controller', function ($scope, $http) {
    $scope.side = 'dark';

}).controller('light.side.controller', function ($scope) {
    $scope.side = 'light';

}).controller('click.controller', function ($scope, $http) {
    $scope.click = function () {
        var user = {name: "Vasya", age: "42"};

        $http.post('/click', user, {}).success(function(data) {
            $scope.response = data;
        })
    };

}).controller('form.controller', function ($scope, $http, $location) {
    $scope.login = {
        name: '',
        password: '',
        country: '',
        lifeForm: '',
        birthDate: ''
    };

    $scope.countries = [
        {name: 'Afghanistan', code: 'AF'},
        {name: '?land Islands', code: 'AX'},
        {name: 'Albania', code: 'AL'}
    ];

    $scope.save = function (login, form) {
        if (form.$valid) {
            $http.post('/register', login, {}).success(function (answer) {
                    if (answer == "1") {
                        $location.path('/register');
                    }
                }
            )
        }
    };

}).controller('download.controller', function ($scope, $http) {
    $scope.download = function () {
        $http.post('/download', {}, {responseType: 'arraybuffer'}).success(function (data) {
            var blob = new Blob([data]);
            saveAs(blob, 'Application.pdf')
        });
    };

    $scope.downloadZip = function () {
        $http.post('/downloadZip', {}, {responseType: 'arraybuffer'}).success(function (data) {
            var blob = new Blob([data]);
            saveAs(blob, 'Application.zip')
        });
    };
})
;
