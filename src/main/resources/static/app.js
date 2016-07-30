var challenge = angular.module('challenge', [
    'challenge.home',
    'challenge.login',
    'challenge.sandbox',
    'challenge.booking',
    'challenge.activity',
    'ui.router',
    'angular-jwt',
    'angular-storage'
])
.config(function challengeConfig($urlRouterProvider, jwtInterceptorProvider, $httpProvider) {
        $urlRouterProvider.otherwise('/');

        // JWT interceptor will take care of sending the JWT in every request
        jwtInterceptorProvider.tokenGetter = function(store, config) {
            // Skip authentication for any requests ending in .html
            if (config.url.substr(config.url.length - 5) == '.html') {
                return null;
            }

            return store.get('jwt'); // or id_token
        };

        $httpProvider.interceptors.push('jwtInterceptor');
})
.run(function($rootScope, $state, store, jwtHelper) {
    $rootScope.$on('$stateChangeStart', function(e, to) {
        if (to.data && to.data.requiresLogin) {
            if (!store.get('jwt') || jwtHelper.isTokenExpired(store.get('jwt'))) {
                e.preventDefault();
                $state.go('login');
            }
        }
    });
})
.controller('main.controller', function($scope, $http) {
});

