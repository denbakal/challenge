var myApp = angular.module('MyApp', ['ngRoute']);

myApp.config(function($routeProvider){
    $routeProvider.when('/darkSide',
        {
            templateUrl:'views/darkSide.html',
            controller:'darkSideCtrl'
        });
    $routeProvider.when('/lightSide',
        {
            templateUrl:'views/lightSide.html',
            controller:'lightSideCtrl'
        });
    $routeProvider.otherwise( {redirectTo: '/'});
});
