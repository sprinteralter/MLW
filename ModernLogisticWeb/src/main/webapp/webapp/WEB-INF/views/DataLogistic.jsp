<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html ng-app="myApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DataLogistic</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="resources/css/ng-grid.css" />
    	<link rel="stylesheet" type="text/css" href="resources/css/dataLogistic.css" />
    	<link rel="stylesheet" type="text/css" href="resources/css/ng-grid-style.css" />
    	<!-- JS -->
		<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="resources/js/angular.min.js"></script>
		<script type="text/javascript" src="resources/js/ng-grid-2.0.7.min.js"></script>
		<script type="text/javascript" src="resources/js/ng-grid-2.0.7.debug.js"></script>
		<script type="text/javascript" src="resources/js/dataLogistic.js"></script>
		<script type="text/javascript" src="resources/js/dataLogisticAngular.js"></script>
</head>
<body>
	<div id="header">
		<h1>Modern Logistic S<img class="logoIcon" src="resources/images/citizen_globe.gif"/>ftware</h1>
	</div>
    <nav class="nav">
        <ul class="sliding-menu">
	       <li><a href="<%=request.getContextPath()%>/">Главная</a></li>
		   <li><a href="#">Документы</a></li>
	       <li><a href="#">Заявки</a></li>
	       <li><a href="#">Справочник</a></li>
	    </ul>
    </nav>
	<div id="content">
        <!-- report forms -->
        <div id="excelForm">
        </div>
        <div id="overlay"></div>
        <!-- end -->
        <div id="leftBar">
        	<!-- <div class="ajaxtest"></div> -->
        	<!-- <button class="testAjax">test</button> -->
        </div>
        
        <div id="centralBar" ng-controller="MyCtrl">
    		<div class="gridStyle" ng-grid="gridOptions"></div>
        </div>
        <div id="rightBarReports">
            <div class="navReports">
                <button class="fadeOutBarReports" title="Закрыть">X</button>
                <button class="showBarReportsFunctions" title="Опции">S</button>
                <input type="search" name="null" placeholder="search report ...">
            </div>
            <div class="checkingDataBase">
            	
            </div>
            <div class="reports">
                <table class="rightBarTable">
                	<tr class="rowHead">
                        <td>№</td>
                        <td>Наименование</td>
                    	<td>Тип документа</td>
                    	<td>База данных</td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="rightBar">
            <button id="excel" class="rightBarIcon" title="Excel отчеты"><img class="iconImg" src="resources/images/exceldata.png"/></button>
            <button id="word" class="rightBarIcon" title="Word отчеты"><img class="iconImg" src="resources/images/worddata.png"/></button>
            <button id="pdf" class="rightBarIcon" title="PDF отчеты"><img class="iconImg" src="resources/images/pdfdata.png"/></button>
            <button id="txt" class="rightBarIcon" title="Text отчеты"><img class="iconImg" src="resources/images/txtdata.png"/></button>
            <button id="xml" class="rightBarIcon" title="XML отчеты"><img class="iconImg" src="resources/images/xmldata.png"/></button>
            <button id="json" class="rightBarIcon" title="JSON отчеты"><img class="iconImg" src="resources/images/jsondata.png"/></button>
            <button id="allreports" class="rightBarIcon" title="Все отчеты"><img class="iconImg" src="resources/images/reports.png"/></button>
        </div>
	</div>
	<div id="footer">
	
	</div>
</body>
</html>