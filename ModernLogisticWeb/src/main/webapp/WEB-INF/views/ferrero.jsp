<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" type="text/css" href="resources/css/ng-grid.css" />
    	<link rel="stylesheet" type="text/css" href="resources/css/dataLogistic.css" />
    	<link rel="stylesheet" type="text/css" href="resources/css/ng-grid-style.css" />

		<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="resources/js/angular/angular.min.js"></script>
		<script type="text/javascript" src="resources/js/ng-grid-2.0.7.min.js"></script>
		<script type="text/javascript" src="resources/js/ng-grid-2.0.7.debug.js"></script>
		<script type="text/javascript" src="resources/js/ferreroAngular.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ferrero</title>
</head>
<body ng-controller="MyCtrl">

	<div id="header">
		<h1>Modern Logistic<img class="logoIcon" src="resources/images/citizen_globe.gif"/>ftware</h1>	
		
</form>
	</div>
		{{PostDataResponse}}
	
	
		<div id="content">
		 <div id="centralBar" >
    		<div class="gridStyle" ng-grid="gridOptions"></div>
        </div>
        
       	<div class="centralFormPart">
					<!-- <form id="form" action="getOrders/get" method="get">  -->
						<div><label class="labelStartDate" for="startDate">Нач. дата:</label></div> 
						<div><input id="startDate" type="date" name="startDate" placeholder="date" required="required" ng-model="datestart"> </div>
						<div><label class="labelEndDate" for="endDate">Кон. дата:</label></div>
						<div><input id="endDate" type="date" name="endDate" placeholder="date" required="required" ng-model="dateend"></div>
					<!-- </form> -->

						<div class="formBottomPanel">
						
							 <button class="excelBut" ng-click="sendData()">Сгенерировать документ</button>
						</div>
 				</div>
        
        </div>
	
</body>
</html>