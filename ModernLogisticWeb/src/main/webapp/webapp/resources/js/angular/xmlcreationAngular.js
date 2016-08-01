/**
 * powered by Rosteach
 */
var app = angular.module('myApp', ['ngGrid']);

app.controller('myCtrl', function($scope, $http){    
	$scope.overlay=false;
	$scope.selectbar=false;
	$scope.generateConfirmation=false;
	$scope.generateNotification=false;
	$scope.showResultGrid = false;
	$scope.showWarning = true;
	$scope.showConfOption = true;
	$scope.showNoteOtpion = true;
	$scope.contentOverlay = false;
	$scope.contentOverlaysLoader = true;
	$scope.confirmOperation=false;
	$scope.confirmSending=false;
	$scope.modalConfirmBody=false;
	$scope.modalSendBody=false;
	
	$scope.myData=null;
	$scope.responseData=null;
	$scope.selectedRows = [];
	$scope.radioData = null;
	$scope.option = "";
	
	$scope.showOverlayAndSelectBar = function(){
		$scope.overlay = true;
		$scope.selectbar = true;
	}
	
	$scope.showGenConfBut = function() {
		$scope.generateConfirmation=true;
		$scope.generateNotification=false;
		$scope.confirmOperation=true;
		$scope.modalConfirmBody=true;
		$scope.radioData = "confirm";
	}
	$scope.showGenNoteBut = function() {
		$scope.generateConfirmation=false;
		$scope.generateNotification=true;
		$scope.confirmOperation=true;
		$scope.modalConfirmBody=true;
		$scope.radioData = "notificate";
	}
	$scope.cancel = function(){
		$scope.overlay = false;
		$scope.selectbar = false;
	}
	$scope.showModalSendPara = function(){
		$scope.confirmOperation=false;
		$scope.confirmSending = true;
		$scope.modalConfirmBody=false;
		$scope.modalSendBody=true;
	}
	
	$scope.gridOptions = {
        data: 'myData',
        enableRowSelection: true,
        selectedItems: $scope.selectedRows,
        showSelectionCheckbox:true,
        noKeyboardNavigation:false,
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
	                	'key': $scope.radioData
	                }
	        }
	    	if($scope.radioData== "confirm"){
	    		$scope.showNoteOtpion=false;
	    		$scope.generateConfirmation=false;
	    	}
	    	else{
	    		$scope.generateNotification = false;
	    		$scope.showConfOption = false;
	    	}
	    	$scope.showResultGrid = true;
	    	$scope.showWarning = false;
	    	
	    	if($scope.radioData)
	    	 $http.post('data/confirm', $scope.selectedRows, config)
	         .success(function (data, status, headers, config) {
	        	 $scope.responseData = data;
	         })
	         .error(function (data, status, header, config) {
	             $scope.ResponseDetails = "Data: " + data +
	                 "<hr />status: " + status +
	                 "<hr />headers: " + header +
	                 "<hr />config: " + config;
	    });
	};
	$scope.gridResult = {
	        data: 'responseData',
	        showFooter: true,
	        enableColumnResize: true,
	        columnDefs: [
	              {field: 'totalInfo', displayName: 'Инфо', width: "*"},
	              {field: 'totalname', displayName: 'Операция', width: "*"},
	              {field: 'totaldate', displayName: 'Дата', width: "*"},
	              {field: 'totalorderedquantity', displayName: 'Заказано(общ.)', width: "*"},
	              {field: 'totaldeliveryquantity', displayName: 'Поставка(общ.)', width: "*"}
	        ]
	    };
    
   
    $http.get('data/getInvoices',{headers: { 'Content-Transfer-Encoding': 'utf-8' }})
    .success(function(data){
        $scope.myData = data;
    });
    
    $scope.sendToEDI = function(){
    	$scope.contentOverlay = true;
    	var config = {
                headers : {
                	'Content-Transfer-Encoding': 'utf-8',
                	'key': $scope.radioData
                }
        }
    	$scope.overlay = false;
    	
    	
    	$http.get('data/connectToFtpEDI',config)
    	 .then(function successCallback(response) {
    		 	$scope.ResponseData = response;
    		 	$window.alert($scope.contentOverlay);
    		  }, function errorCallback(response) {
    		    // called asynchronously if an error occurs
    		    // or server returns response with an error status.
    		  });
         /*.success(function (data){
        	 $scope.ResponseData = data;
        	 
         })
         .error(function (data, status, header, config) {
             $scope.ResponseDetails = "Data: " + data +
                 "<hr />status: " + status +
                 "<hr />headers: " + header +
                 "<hr />config: " + config;
         });*/
    };
});
