angular.module( 'challenge.home', [
  'ui.router',
  'angular-storage',
  'angular-jwt'
])
.config(function($stateProvider) {
  $stateProvider.state('home', {
    url: '/rest/home',
    controller: 'home.controller',
    templateUrl: 'home/home.html',
    data: {
      requiresLogin: true
    }
  });
})
.controller('home.controller', function HomeController($scope, $http, store, jwtHelper) {

  $scope.jwt = store.get('jwt');
  $scope.decodedJwt = $scope.jwt && jwtHelper.decodeToken($scope.jwt);

  $scope.callAnonymousApi = function() {
    // Just call the API as you'd do using $http
    callApi('Anonymous', 'http://localhost:8080/rest/secure');
  }

  /*$scope.callSecuredApi = function() {
    callApi('Secured', 'http://localhost:3001/api/protected/random-quote');
  }*/

  function callApi(type, url) {
    $scope.response = null;
    $scope.api = type;
    $http({
      url: url,
      method: 'GET'
    }).then(function(quote) {
      $scope.response = quote.data;
    }, function(error) {
      $scope.response = error.data;
    });
  }

});
