/**
 * powered by Rosteach
 */
var app = angular.module('myApp', ['ngGrid']);

app.controller('myCtrl', function($scope, $http){    
	$scope.overlay=false;
	$scope.selectbar=false;
	$scope.showResultGrid = false;
	$scope.showCancelBut = true;
	$scope.showNoteOtpion = true;
	$scope.contentOverlay = false;
	$scope.contentOverlaysLoader = true;
	$scope.confirmOperation=false;
	$scope.confirmSending=false;
	$scope.modalConfirmBody=false;
	$scope.modalSendBody=false;
	$scope.modalSuccessBody=false;
	$scope.modalLoaderBody=false;
	$scope.accessSending=false;
	$scope.refreshGrid=false;
	$scope.modalGridOptionsBody=false;
	$scope.attentionHeader=false;
	$scope.optionsHeader=false;
	$scope.sendHeader=false;
	$scope.sendToFtpBut=true;
	$scope.refresh = false;
	$scope.selectedOption = null;
	$scope.selectMenu=true;
	$scope.selected=null;
	$scope.sendOption=null;
	$scope.showSelectedOption=false;
	
	$scope.myData=null;
	$scope.responseData=null;
	$scope.selectedRows = [];
	$scope.radioData = null;
	$scope.option = "";
	$scope.optionID = null;
	$scope.optionDATE = null;
	
	$scope.accessTopGrid = function(){
		$scope.overlay = true;
		$scope.selectbar = true;
		$scope.modalConfirmBody=true;
		$scope.confirmOperation=true;
		$scope.attentionHeader=true;
	}
	$scope.cancelALL = function(){
		$scope.optionsHeader=false;
		$scope.modalGridOptionsBody=false;
		$scope.refreshGrid=false;
	}
	$scope.cancelModal = function(){
		if($scope.optionsHeader){
			$scope.optionsHeader=false;
			$scope.attentionHeader=true;
			$scope.modalGridOptionsBody=false;
			$scope.refreshGrid=false;
		}
		else if($scope.attentionHeader){
			return;
		}
	}

	$scope.cancel = function(){
		$scope.overlay = false;
		$scope.selectbar = false;
		$scope.refreshGrid = false;
 	}
	$scope.showModalSendPara = function(){
		$scope.confirmOperation=false;
		$scope.confirmSending = true;
		$scope.modalConfirmBody=false;
		$scope.modalSendBody=true;
		$scope.attentionHeader=false;
		$scope.sendHeader=true;
	}
	
	/*method to set needed option to modal for refreshGrid*/
	$scope.showTopGridOptions= function(){
		$scope.refreshGrid=true;
		$scope.modalGridOptionsBody=true;
		$scope.optionsHeader=true;
		$scope.confirmOperation=false;
		$scope.modalConfirmBody=false;
		$scope.attentionHeader=false;
	}
	
	/*method to send json top grid elements*/
	$scope.sendData = function(){
		var config = {
                headers : {
                	'Content-Transfer-Encoding': 'utf-8',
                	'key': $scope.selectedOption
                }
        }
    	if($scope.selectedOption==1){
    		$scope.selected="Подтверждение заказа";
    		$scope.sendOption=1;
    	}else if($scope.selectedOption==2){
    		$scope.selected="Уведомление об отгрузке";
    		$scope.sendOption=2;
    	}
		$scope.showResultGrid=true;
    	$scope.showSelectedOption=true;
    	$scope.showCancelBut=false;
		$scope.selectMenu = false;
		$scope.selectedOption=null;
		
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
	              /*{field: 'totaldeliveryprice', displayName: 'Сумма поставки(общ.)', width: "*"}*/
	        ]
	    };
    
   
    $http.get('data/getInvoices',{headers: { 'Content-Transfer-Encoding': 'utf-8' }})
    .success(function(data){
        $scope.myData = data;
    });
    
    $scope.sendToEDI = function(){
    	//$scope.contentOverlay = true;
    	$scope.sendToFtpBut=false;
    	$scope.refresh = true;
    	$scope.modalLoaderBody=true;
    	$scope.overlay = false;
    	
    	var config = {
                headers : {
                	'Content-Transfer-Encoding': 'utf-8',
                	'key': $scope.sendOption
                }
        }
    	
    	
    	$http.get('data/connectToFtpEDI',config)
    	 .success(function (data){
        	 $scope.responseData = data;
        	 
         })
         .error(function (data, status, header, config) {
             $scope.ResponseDetails = "Data: " + data +
                 "<hr />status: " + status +
                 "<hr />headers: " + header +
                 "<hr />config: " + config;
         });
    };
    
    $scope.refGrid = function(){
    	$scope.refreshGrid=false;
    	$scope.modalGridOptionsBody=false;
    	$scope.optionsHeader = false;
    	$scope.attentionHeader=true;
    	var putdata = {
    		'id':$scope.optionID,
    		'date': $scope.optionDATE
    	}	
    	$http.put('data/refreshGrid',putdata,{headers: { 'Content-Transfer-Encoding': 'utf-8'}})
    	 .success(function (data){
    		 $scope.myData = data;
         })
         .error(function (data, status, header, config) {
             $scope.ResponseDetails = "Data: " + data +
                 "<hr />status: " + status +
                 "<hr />headers: " + header +
                 "<hr />config: " + config;
         });
    };
    $scope.refreshPage = function(){
    	location.reload();
    }
    
    /*************************tab 2 code***********************/
    
    $scope.linksSearching=null;
	$scope.tab2SelectClient='0';
	$scope.tab2SelectDoc='0';
	$scope.panelsClass="tab-pane-panels";
	$scope.panelCheckedClass="tab-pane-panels-checked";
	$scope.links=null;
	$scope.panelNumber=0;
	$scope.doc4 = 0;
	$scope.filter = 0;
	$scope.tab2showSelectedList=false;
	$scope.filteredData = {};
	$scope.selectedList = [];
	$scope.hiddenDiv = false;
	$scope.hiddenProgress = false;
	$scope.tab2showSearch=false;	
	$scope.hiddenAddBut = true;
	$scope.hiddenButDet = true;
	$scope.hiddenButSign = true;
	$scope.hiddenButCancel = false;
	$scope.tab2panelSuccess = false;
	
	
    $http.get('data/comdocs',{headers: { 'Content-Transfer-Encoding': 'utf-8' }})
    .success(function(data){
    	$scope.links = data;
    	
    	angular.forEach($scope.links, function(item){
            var type = item.header.type;
            if(type==="Акт про виявлені недоліки"){
            	$scope.doc4 +=1; 
            }
    		console.log(item.header.type);  
        })
    	console.log($scope.doc4);
    	//$scope.tab2TotalDocsItems = $scope.links.length;
    });
    
    $scope.tab2filterchange = function(){
    	var client = $scope.tab2SelectClient;
    	var doc = $scope.tab2SelectDoc;
    	if(client=='0'){
    		
    	}
    }
    
    $scope.tab2SendChosenDocs = function(){
    	
    }
    
    $scope.tab2addLink=function(index){
    	console.log(index)
    	$scope.selectedList.push(index);
    	console.log($scope.selectedList[index]);
    	console.log($scope.selectedList);
    	if(!$scope.tab2showSelectedList & $scope.selectedList.length!=0){
    		$scope.tab2showSelectedList=true;
    	}
    	
    }
    $scope.tab2detailsLink=function(){
    	
    }
    $scope.tab2signLink = function(index){
        $http.get('data/comdoc',{headers: { 'Content-Transfer-Encoding': 'utf-8', 'key': index}})
        .success(function(data){
        	console.log(data);
        });
    }
    $scope.tab2CancelLink=function(index){
    	var id = $scope.selectedList.indexOf(index);
    	$scope.selectedList.splice(id,1);
    	console.log($scope.selectedList);
    	console.log($scope.selectedList.length);
    	if($scope.tab2showSelectedList & $scope.selectedList.length==0){
    		$scope.tab2showSelectedList=false;
    	}
    }
    $scope.tab2showSearching=function(){
    	$scope.tab2showSearch=!$scope.tab2showSearch;
    };
    
    $scope.tab2refreshLinks=function(){
    	
    };
});
