<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Tools</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="resources/css/tools.css" />
	<!-- JS -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/tools.js"></script>
</head>
<body>
	<div id="header">
        <div class="leftIcons">
            <img src="resources/images/market_research.png"/>
            <img src="resources/images/support.png"/>
            <img src="resources/images/checklist.png"/>
            <img src="resources/images/garage.png"/>
            <img src="resources/images/basket.png"/>
        </div>   
        
		<h1>Modern Logistic S<img class="logoIcon" src="resources/images/citizen_globe.gif" width=25px  height=25px/>ftware</h1> 
        
        <div class="rightIcons">
            <img src="resources/images/car.png"/>
            <img src="resources/images/plain.png"/>
            <img src="resources/images/train.png"/>
            <img src="resources/images/ship.png"/>
            <img src="resources/images/motobike.png"/>
        </div>
	</div>
	<div id="content">
		<div class="leftBar">
	        <div class="back" title="Главная"><a href="<%=request.getContextPath()%>/"><img src="resources/images/left_arrow.png" width=30px height=30px/></a></div>
			<div class="info" title="Info"><a href="toolsInfo"><img src="resources/images/info.png" width=30px height=30px;/></a></div>
		</div>
		<div class="firstline">
			<div class="item"><a href="XML"><img src="resources/images/Xmltool.png"/></a><div class="icon"><h5>XML Converting</h5></div></div>
			<div class="item"><a href="XLS"><img src="resources/images/excel-256.png"/></a><div class="icon"><h5>XLS Converting</h5></div></div>
			<div class="item"><a href="dataBinding"><img src="resources/images/databinding.png"/></a><div class="icon"><h5>data binding</h5></div></div>
			<div class="item"><a href="#"><img src="resources/images/closed.png"/></a><div class="icon"><h5>N/A</h5></div>
            </div>
		</div>
		
		<div class="secondline">
			<div class="item"><a href="#"><img src="resources/images/closed.png"/></a><div class="icon"><h5>N/A</h5></div></div>
			<div class="item"><a href="#"><img src="resources/images/closed.png"/></a><div class="icon"><h5>N/A</h5></div></div>
			<div class="item"><a href="#"><img src="resources/images/closed.png"/></a><div class="icon"><h5>N/A</h5></div></div>
			<div class="item end"><a href="#"><img src="resources/images/closed.png"/></a><div class="icon"><h5>N/A</h5></div></div>
		</div>
	</div>
	<div id="footer">
	   <h3>Tools</h3>
	</div>
</body>
</html>