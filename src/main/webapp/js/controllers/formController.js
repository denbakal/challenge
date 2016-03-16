var myApp = angular.module('MyApp');

myApp.controller('formController', function fromController($scope) {
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
            alert("Gotcha!Now, you are my bitch, " + login.name + "!" +
                " I know your pass is " + login.password + "." +
                " And you live in " + login.country.name + "!" +
                " And you are a " + login.lifeForm + " and your birthdate is " + login.birthDate.format('yyyy-mm-dd') + "!"
            )
        }
    };
});
