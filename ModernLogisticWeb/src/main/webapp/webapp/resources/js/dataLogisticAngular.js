/**
 * @author Pavlenko R.A.
 */

var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope, $http) {    
	$scope.myData='';
	
	$http.get('data/get',{headers: { 'Content-Transfer-Encoding': 'utf-8' }}).
    success(function(data) {
      $scope.myData = data;
    });
    
    $scope.gridOptions = {
        data: 'myData',
        enableCellSelection: true,
        enableRowSelection: false,
        enableCellEditOnFocus: true,
        showFooter: true,
        enableColumnResize: true,
        showColumnMenu:true,
        showFilter: true,
        columnDefs: [
              {field: 'id', displayName: 'Код', width: "10%"},
              {field: 'docdate', displayName: 'Дата документа', width: "10%", enableCellEdit: true},
              {field: 'profilesname', displayName: 'День недели', width: "10%", enableCellEdit: true},
              {field: 'clientid', displayName: 'Код клиента', width: "10%"},
              {field: 'clientsname', displayName: 'Наим. клиента', width: "10%"},
              {field: 'clientname', displayName: 'Полное наим. клиента', width: "20%"},
              {field: 'CLIENTADRESSLOCATION', displayName: 'Адрес клиента', width: "5%"},
              {field: 'storeid', displayName: 'Склад', width: "10%"},
              {field: 'storesname', displayName: 'Наим. склада', width: "10%"},
              {field: 'storename', displayName: 'Полное наим. склада', width: "10%"},
              {field: 'outcomeinvoiceidsset', displayName: 'Код Клиента', width: "10%"},
              {field: 'endsumm', displayName: 'Сумма', width: "10%"},
              {field: 'endsummwithoverh', displayName: 'Полная сумма', width: "10%"},
              {field: 'agentid', displayName: 'Код агента', width: "10%"},
              {field: 'agentsname', displayName: 'Имя агента', width: "10%"},
              {field: 'paytypeid', displayName: 'Код клиента', width: "10%"},
              {field: 'paytypesname', displayName: 'Наим. клиента', width: "10%"},
              {field: 'clientname', displayName: 'Полное наим. клиента', width: "10%"},
              {field: 'clientadresslocation', displayName: 'Адрес клиента', width: "10%"},
              {field: 'storeid', displayName: 'Склад', width: "10%"},
              {field: 'storesname', displayName: 'Наим. склада', width: "10%"},
              {field: 'storename', displayName: 'Полное наим. склада', width: "10%"},
              {field: 'outcomeinvoiceidsset', displayName: 'Код Клиента', width: "10%"},
              {field: 'endsumm', displayName: 'Сумма', width: "10%"},
              {field: 'endsummwithoverh', displayName: 'Полная сумма', width: "10%"}
        ]
    };
});