/**
 * @author Pavlenko R.A.
 */

var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope, $http) {    
	$scope.myData='';
	
	$http.get('getOrders/get',{headers: { 'Content-Transfer-Encoding': 'utf-8' }}).
    success(function(data) {
      $scope.myData = data;
    });
    
    $scope.gridOptions = {
        data: 'myData',
        enableCellSelection: true,
        enableRowSelection: true,
        enableCellEditOnFocus: true,
        showFooter: true,
        enableColumnResize: true,
        showColumnMenu:true,
        showFilter: true,
        columnDefs: [
{field: 'idorder', displayName: 'idorder', width: "10%", enableCellEdit: false},
{field: 'agentsname', displayName: 'Торговый агент', width: "10%", enableCellEdit: false},
{field: 'clientid', displayName: 'Код клиента', width: "10%", enableCellEdit: false},
{field: 'clientsnameord', displayName: 'Название точки', width: "10%", enableCellEdit: false},
{field: 'adresslocord', displayName: 'Адресс точки', width: "10%", enableCellEdit: false},
{field: 'docdateorder', displayName: 'Дата заявки', width: "10%", enableCellEdit: false},
{field: 'goodsname', displayName: 'Товар', width: "10%", enableCellEdit: false},
{field: 'loadingdate', displayName: 'Дата вывоза', width: "10%", enableCellEdit: false},
{field: 'paytypename', displayName: 'форма опаты', width: "10%", enableCellEdit: false},
{field: 'itemcountorder', displayName: 'Заказов за период', width: "10%", enableCellEdit: false},
{field: 'itemcountout', displayName: 'Вывоз в 24ч', width: "10%", enableCellEdit: false},
{field: 'clientsname', displayName: 'Вывоз позже', width: "10%", enableCellEdit: false},
{field: 'idout', displayName: 'номер р/н', width: "10%", enableCellEdit: false},

             
             /*{field: 'id', displayName: 'Код', width: "10%"},
              {field: 'clientid', displayName: 'Код точки', width: "10%"},
              {field: 'clientsname', displayName: 'Название точки', width: "10%"},
              {field: 'CLIENTADRESSLOCATION', displayName: 'Адресс точки', width: "10%"},
              {field: 'agentsname', displayName: 'Торговый агент', width: "10%"},
              {field: 'clientsname', displayName: 'Заказов за период', width: "10%"},
              {field: 'docdate', displayName: 'Дата слива', width: "10%", enableCellEdit: true},
              {field: 'paytypesname', displayName: 'форма опаты', width: "10%"},
              {field: 'docdate', displayName: 'Дата вывоза', width: "10%", enableCellEdit: true},
              {field: 'clientsname', displayName: 'Вывоз в 24ч', width: "10%"},
              {field: 'clientsname', displayName: 'Вывоз позже', width: "10%"},*/
           
             
        ]
    };
});