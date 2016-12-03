'use strict';

angular.module('myApp.view3', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view3', {
            templateUrl: 'view3/view3.html',
            controller: 'View3Ctrl'
        });
    }])

    .controller('View3Ctrl', ['$scope', '$http', '$interval', function ($scope, $http, $interval) {
        console.log('inside aaa');
        $scope.lista = ["abc", "def", "yyy"];

        $scope.getCurrencies = function () {
            //$http.get("http://localhost:8080/rates_server-1.0-SNAPSHOT/rs/fx/ccyPairs")
            $http.get("rs/fx/ccyPairs")
                .then(function (response) {
                    $scope.data = response.data;
                });
        }

        $scope.placeholder = "text";

        var vm = this;
        vm.currentCurrency = [];
        vm.rateStore = {};
        vm.addCurrentCurrency = function (currency) {
            if (!vm.rateStore.hasOwnProperty(currency)) {
                vm.rateStore[currency] = "";//default value
                console.log("addCurrentCurrency " + currency);
                vm.currentCurrency.push({"ccy": currency, store: vm.rateStore});
                $interval(function () {
                    //$http.get("http://localhost:8080/rates_server-1.0-SNAPSHOT/rs/fx/rate/" + currency)
                    $http.get("rs/fx/rate/" + currency)
                        .then(function (response) {
                            if (response.data) {
                                if (currency == response.data.ccyPair) {
                                    /*{"ccyPair": "EUR_USD", "bidPrice": 1.08937, "askPrice": 1.08951,*/
                                    vm.rateStore[currency] = {
                                        "ask": response.data.askPrice,
                                        "bid": response.data.bidPrice
                                    };
                                } else {
                                    console.log("Error getting data for " + ccyPair);
                                }
                            }
                        });
                }, 2000);

            }
        }


    }])

    .directive('row', function () {
        return {
            scope: {
                row: '='
            },
            template: '<p id="ccyboxheader"> {{row.ccy}}</p>' +
            '<p>{{row.store[row.ccy].ask}}&nbsp&nbsp&nbsp{{row.store[row.ccy].bid}}</p> '
        };
    });
