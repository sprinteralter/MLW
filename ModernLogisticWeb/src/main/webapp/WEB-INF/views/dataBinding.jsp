<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>DataBinding</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="resources/css/databinding.css" />
	<!-- JS -->
	<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/jquery_plugins/jqueryForm.js"></script>
	<script>
        $(function() {
            $( "#tabs" ).tabs({
            	beforeLoad: function( event, ui ) {
                    ui.jqXHR.fail(function() {
                      ui.panel.html(
                        "Couldn't load this tab. We'll try to fix this as soon as possible. " +
                        "If this wouldn't be a demo." );
                    });
                 }
            });
        });
        $(document).ready(function(){
	        $('.infoBarIcon').click(function(){
	            $('#overlay').fadeIn(200, function(){
	                $('#loginInfo').show(200);        
	            });    
	        });
	        $('.cancel').click(function(){
	            $('#loginInfo').hide(200,function(){
	                $('#overlay').fadeOut(200)
	            });        
	        });
        });
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
                	 <img src="resources/images/userFoto.png" width=70px height=70px;/>
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
            <li><a href="resources/tabs-DataBinding/tab-1.jsp">Заявки от клиентов</a></li>
        </ul>
    </div>
    <div id="footer">
        <h3>data binding</h3>
    </div>
</body>
</html>
