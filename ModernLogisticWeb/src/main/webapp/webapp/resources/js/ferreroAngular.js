
  	 /*
		var xhr = new XMLHttpRequest();
		var RestGet = function() {
				
			var xhr = new XMLHttpRequest();
		   	 xhr.open('GET', 'getOrders/get?startDate=' + document.getElementById('startDate').value + '&endDate='+document.getElementById('endDate').value, false);
		   	 xhr.send();

		  	 if (xhr.status != 200) {
		      // обработать ошибку
		      alert('Ошибка ' + xhr.status + ': ' + xhr.statusText);
		   	 } else {

		      alert(xhr.responseText);
		      return xhr.responseText;
		   	 }}
		*/


var app = angular.module('myApp', ['ngGrid']);
	
app.controller('MyCtrl', function($scope, $http) {    
		$scope.myData=null;
		
		$scope.datestart = null;
		$scope.dateend = null;
			
		$scope.sendData = function(){
			$scope.senddate = [{startdate: $scope.datestart},{enddate:$scope.dateend}]
	    	var config = {
	                headers : {
	                	'Content-Transfer-Encoding': 'utf-8'
	                }
	         }
	    	 $http.post('getOrders/post', $scope.senddate, config)
	         .success(function (data, status, headers, config) {
	        	 $scope.myData = data;///////$scope.myData // $scope.PostDataResponse 
	         })
	         .error(function (data, status, header, config) {
	             $scope.ResponseDetails = "Data: " + data +
	                 "<hr />status: " + status +
	                 "<hr />headers: " + header +
	                 "<hr />config: " + config;
	         });
	    };
		
	/*	$http.get('getOrders/get?startDate=2016-07-01&endDate=2016-07-02',{headers: { 'Content-Transfer-Encoding': 'utf-8' }}).
	    success(function(data) {
	      $scope.myData = data;
	    });
	    */
	/*	$scope.filterOptions = {
			    filterText: 'idorder:793831',
			    
			};*/
		
	    $scope.gridOptions = {
	        data: 'myData',
	        enableCellSelection: true,
	        enableRowSelection: true,
	        enableCellEditOnFocus: true,
	        showFooter: true,
	        enableColumnResize: true,
	        showColumnMenu:true,
	        showFilter: true,
	        /*filterOptions: $scope.filterOptions,*/
	        columnDefs: [
	{field: 'agentsname', displayName: 'Торговый агент', width: "10%", enableCellEdit: false},
	{field: 'clientsnameord', displayName: 'Название точки', width: "10%", enableCellEdit: false},
	{field: 'adresslocord', displayName: 'Адрес точки', width: "10%", enableCellEdit: false},
	{field: 'paytypename', displayName: 'форма оплаты', width: "10%", enableCellEdit: false},
	{field: 'idorder', displayName: '№ заявки', width: "10%", enableCellEdit: false},
	{field: 'docdateorder', displayName: 'Дата заявки', width: "10%", enableCellEdit: false},
	{field: 'zakaz', displayName: 'кол-во заказов', width: "10%", enableCellEdit: false},
	{field: 'loadingdate', displayName: 'Дата вывоза', width: "10%", enableCellEdit: false},
	{field: 'nextday', displayName: 'Вывоз в 24ч', width: "10%", enableCellEdit: false},
	{field: 'result', displayName: 'Результат', width: "10%", enableCellEdit: false},

	        ]
	    };
	});

	