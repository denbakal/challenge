angular.module( 'challenge.login', [
  'ui.router',
  'angular-storage'
])
.config(function($stateProvider) {
    $stateProvider.state('login', {
      url: '/login',
      controller: 'login.controller',
      templateUrl: 'login/login.html'
    });
  })
.controller( 'login.controller', function LoginController($scope, $http, store, $state) {
  $scope.user = {};

  $scope.login = function() {
    var data = "username="+$scope.user.username+"&password="+$scope.user.password;

    $http.post('http://localhost:8080/login', null, {params:{username: $scope.user.username, password: $scope.user.password}, headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }}).success(function (data, status, headers, config) {
          alert(JSON.stringify(headers('X-AUTH-TOKEN')));
          store.set('jwt', headers('X-AUTH-TOKEN'));
          $state.go('home');
          //alert(JSON.stringify(headers('Pragma')));
        })
        .error(function (data, status, header, config) {
          alert(error.data);
        });
    /*$http({
      method: 'POST',
      url: ,
      data: "username="+$scope.user.username+"&password="+$scope.user.password,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(function(data, status, headers, config) {
      alert(JSON.stringify(headers('X-AUTH-TOKEN')));
      //console.log(headers()['X-AUTH-TOKEN']);
     // store.set('jwt', response.data.id_token);
      $state.go('home');
    }, function(error) {
      alert(error.data);
    });*/
  }

});
