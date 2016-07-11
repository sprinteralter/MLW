/**
 * powered by Rosteach
 */

var app = angular.module('myApp', ['ngGrid']);

/*app.service('service', function($http){
	this.getData = function(){
		return $http.get('data/get',{headers: { 'Content-Transfer-Encoding': 'utf-8' }})
		.success(function(data) {
	       
	    })
	    .error(function(data){
	    	
	    });
	};
});*/
app.controller('myCtrl', function($scope, $http) {    
	
	$scope.myData=null;
	$scope.PostDataResponse;
	$scope.selectedRows = [];
	
	/*service.getData().then(function (res){
		if(res!==undefined){
			$scope.myData = res;
			$scope.loading=false;
		}
	})*/
	
    $scope.gridOptions = {
    	init: function (gridCtrl, gridScope) {
    		gridScope.$on('ngGridEventData', function () {
    			$timeout(function () {
    				angular.forEach(gridScope.columns, function (col) {
    					gridCtrl.resizeOnData(col);
    				});
    			});
    		});
        },	
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
              {field: 'agentsname', displayName: 'Имя агента', width: "*"}/*,
              {field: 'paytypeid', displayName: 'Код клиента', width: "10%"},
              {field: 'paytypesname', displayName: 'Наим. клиента', width: "10%"},
              {field: 'clientname', displayName: 'Полное наим. клиента', width: "10%"},
              {field: 'clientadresslocation', displayName: 'Адрес клиента', width: "10%"},
              {field: 'storeid', displayName: 'Склад', width: "10%"},
              {field: 'storename', displayName: 'Полное наим. склада', width: "10%"},
              {field: 'outcomeinvoiceidsset', displayName: 'Код Клиента', width: "10%"},
              {field: 'endsumm', displayName: 'Сумма', width: "10%"},
              {field: 'endsummwithoverh', displayName: 'Полная сумма', width: "10%"}*/
        ]
    };
    
    $scope.sendData = function(){
    	var config = {
                headers : {
                	'Content-Transfer-Encoding': 'utf-8'
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
    
    
    $http.get('data/getOutcomeinVoices',{headers: { 'Content-Transfer-Encoding': 'utf-8' }}).
    success(function(data) {
        $scope.myData = data;
    });
});