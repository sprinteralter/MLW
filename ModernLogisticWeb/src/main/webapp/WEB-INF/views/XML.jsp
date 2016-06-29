<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>XML</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui-xml.css" />
	<!-- JS -->
	<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/xml.js"></script>
	<script type="text/javascript" src="resources/jquery_plugins/jqueryForm.js"></script>
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
</head>
<body>
	<div id="header">    
        <div class="infoBar">
            <div class="infoBarLeft">
                <div class="infoBarIcon" title="Put your Foto">
                	 <img src="resources/images/userFoto.png"/>
                </div>
                <div class="infoBarLogin">
                    <p class="loginName">${username}</p>
                    <p class="loginDatabase">${database}</p>
                    <div class="loginOptions">
                        <a href="logout" class="logout">Выйти</a>
                        <a href="#" class="logout">Данные</a>
                    </div>
                </div>
            </div>
            <div class="infoBarCenter">
                <h1>Modern Logistic S<img class="logoIcon" src="resources/images/citizen_globe.gif" width=25px  height=25px/>ftware</h1>
            </div>
            <div class="infoBarRight">
            </div>
        </div>
    </div>
    
    <div id="tabs">
        <ul>
            <li><a href="resources/tabs-XML/tab-1.jsp">Veres</a></li>
            <li><a href="resources/tabs-XML/tab-2.jsp">EDI Novus</a></li>
            <li><a href="resources/tabs-XML/tab-3.jsp">EDI Eko</a></li>
        </ul>
    </div>
    <div id="delegation">
     	<div class="delegatePushButtRight"></div>
     	<div class="delegateUploadButtLeft"></div>
    </div>
    <div id="footer">
        <h3>XML converting</h3>
    </div>
</body>
</html>
