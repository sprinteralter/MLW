/**
 * powered by Rosteach
 */
var app = angular.module('myApp', ['ngGrid']);

app.controller('myCtrl', function($scope, $http ) {    
	
	$scope.myData=null;
	$scope.selectedData=null;
	$scope.PostDataResponse;
	$scope.selectedRows = [];
	
    $scope.gridOptions = {
        data: 'myData',
        enableRowSelection: true,
        selectedItems: $scope.selectedRows,
        showFooter: true,
        enableColumnResize: true,
        showColumnMenu:true,
        showFilter: true,
        columnDefs: [
              {field: 'id', displayName: 'Код', width: "*"},
              {field: 'regnumber', displayName: 'Рег. номер', width: "*"},
              {field: 'docdate', displayName: 'Дата', width: "*"},
              {field: 'clientid', displayName: 'Код клиента', width: "*"},
              {field: 'clientsname', displayName: 'Наим. клиента', width: "*"},
              {field: 'clientadresslocation', displayName: 'Адрес клиента', width: "*"},
              {field: 'endsumm', displayName: 'Сумма', width: "*"},
              {field: 'agentid', displayName: 'Код агента', width: "*"},
              {field: 'agentsname', displayName: 'Имя агента', width: "*"}
        ]
    };
    
    $scope.sendData = function(){
    	var config = {
                headers : {
                	'Content-Transfer-Encoding': 'utf-8',
                	'key': $scope.selectedData
                }
        }
    	 $http.post('data/confirm', $scope.selectedRows, config)
         .success(function (data, status, headers, config) {
             $scope.PostDataResponse = data;
         })
         .error(function (data, status, header, config) {
             $scope.ResponseDetails = "Data: " + data +
                 "<hr />status: " + status +
                 "<hr />headers: " + header +
                 "<hr />config: " + config;
         });
    };
    
    /*$scope.refreshData = function(){
    	$http.get('data/getInvoices',{headers: { 'Content-Transfer-Encoding': 'utf-8' }}).
        success(function(data) {
            $scope.myData = data;
        })
        .error(function (data, status, header, config) {
            $scope.ResponseDetails = "Data: " + data +
                "<hr />status: " + status +
                "<hr />headers: " + header +
                "<hr />config: " + config;
        });
    };*/
    
    $http.get('data/getInvoices',{headers: { 'Content-Transfer-Encoding': 'utf-8' }}).
    success(function(data) {
        $scope.myData = data;
    });
});