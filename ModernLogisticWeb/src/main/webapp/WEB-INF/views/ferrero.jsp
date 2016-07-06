<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" type="text/css" href="resources/css/ng-grid.css" />
    	<link rel="stylesheet" type="text/css" href="resources/css/dataLogistic.css" />
    	<link rel="stylesheet" type="text/css" href="resources/css/ng-grid-style.css" />

		<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="resources/js/angular.min.js"></script>
		<script type="text/javascript" src="resources/js/ng-grid-2.0.7.min.js"></script>
		<script type="text/javascript" src="resources/js/ng-grid-2.0.7.debug.js"></script>
		<script type="text/javascript" src="resources/js/ferreroAngular.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ferrero</title>
</head>
<body>
	<div id="header">
		<h1>Modern Logistic<img class="logoIcon" src="resources/images/citizen_globe.gif"/>ftware</h1>
	</div>
	
		<div id="content">
		 <div id="centralBar" ng-controller="MyCtrl">
    		<div class="gridStyle" ng-grid="gridOptions"></div>
        </div>
        
        
	
</body>
</html>