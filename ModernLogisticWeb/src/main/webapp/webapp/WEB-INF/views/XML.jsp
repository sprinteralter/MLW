<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>XML</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="resources/css/xml.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/sweetalert.css" />
	<!-- JS -->
	<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/xml.js"></script>
	<script type="text/javascript" src="resources/jquery_plugins/jqueryForm.js"></script>
	<script type="text/javascript" src="resources/js/sweetalert.js"></script>
	<script>
        $(function() {
            $( "#tabs" ).tabs({
            	beforeLoad: function( event, ui ) {
            		//clear all tabs except current
            		$(ui.panel).siblings('.ui-tabs-panel').empty();
            		//on error ajax output
            		ui.jqXHR.fail(function() {
                      ui.panel.html(
                        "Couldn't load this tab. We'll try to fix this as soon as possible. " +
                        "If this wouldn't be a demo." );
                    });
                 }
            });
        });
    </script>
    <script>
    	function alert1(){
        	swal('Alert Design 1 Clicked!','This is an Alert!','success' );
    	}
    </script>
</head>
<body>
	<div id="loginInfo">
		<div id="dropZone" title="Загрузить Фото">
	        <img src="resources/images/userFoto.png"/>
		</div>
        <div class="userInfo">
            <div class="userName"><p>${username}</p></div>
            <div class="userDatabase"><p>${database}</p></div>
            <div class="userButtons">
                <a href="logout">Выйти</a>
                <a href="#">Редактировать данные</a>
            </div>
        </div>
        <div class="userCancel">
            <div class="cancel"><img src="resources/images/cancel.png"/></div>
        </div>
    </div>
	<div id="header">    
        <div class="infoBar">
            <div class="infoBarLeft">
                <div class="infoBarIcon" title="Ваш профиль">
                	 <img src="resources/images/userFoto.png"/>
                </div>
            </div>
            <div class="infoBarCenter">
                <h1>Modern Logistic S<img class="logoIcon" src="resources/images/citizen_globe.gif" width=25px  height=25px/>ftware</h1>
            </div>
            <div class="infoBarRight">
            	<button class="rightalert" onclick="alert1()">rightalert</button>
            </div>
        </div>
    </div>
    
    <div id="tabs">
        <ul>
            <li><a href="resources/tabs-XML/tab-1.jsp">Veres</a></li>
            <li><a href="resources/tabs-XML/tab-2.jsp">EDI Novus</a></li>
            <li><a href="resources/tabs-XML/tab-3.jsp">EDI Eko</a></li>
            <li><a href="resources/tabs-XML/tab-4.jsp">EDI LKTrans</a></li>
			<li><a href="resources/tabs-XML/tab-5.jsp">EDI Socar</a></li>
			<li><a href="resources/tabs-XML/tab-6.jsp">EDI Food</a></li>
			<li><a href="resources/tabs-XML/tab-7.jsp">EDI Ashan</a></li>
			<li><a href="resources/tabs-XML/tab-8.jsp">EDI Billa</a></li>
        </ul>
    </div>
    <div id="footer">
        <h3>XML INSERTION</h3>
    </div>
</body>
</html>