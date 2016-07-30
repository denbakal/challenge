angular.module('challenge.activity', [
    'ui.router'
]).config(function ($stateProvider) {
    $stateProvider
        .state('activity', {
            url: '/activity',
            controller: 'activity.controller',
            templateUrl: 'views/activity/activity.html'
        })
        .state('activityAdd', {
            url: '/activity/add',
            controller: 'activity.controller',
            templateUrl: 'views/activity/addActivity.html'
        })
    ;

}).controller('activity.controller', function ($scope, $http) {
    $scope.activity = "";
    $scope.addActivity = function () {
        $http.post("/activity", null, {params: {actionName: $scope.activity}})
            .success(function(data, status) {
            })
            .error(function(data, status) {
            });
    };
})
;