<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="myApp">
    <head lang="en">
        <title>XML</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="resources/css/xmlcreation.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/ng-grid.css"/>
        <!-- JS -->
        <script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="resources/js/angular.min.js"></script>       
        <script type="text/javascript" src="resources/js/ng-grid-2.0.7.min.js"></script>
        <script type="text/javascript" src="resources/js/ng-grid-2.0.7.debug.js"></script>
        <script type="text/javascript" src="resources/js/xmlcreationAngular.js"></script>
        <script type="text/javascript" src="resources/js/xmlcreating.js"></script>
    </head>
    <body ng-controller="myCtrl">
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
        <div id="loader">
                <img src="resources/images/cloud_loading_256.gif" alt="Loader" width=150px height=150px;/>
            </div>
            <div id="errormessage">
                <p class="errMess"></p>
                <button class="errMessCancel">Отмена</button>
            </div>
            <div id="applymessage">
                <p class="applMess"></p>
                <p class="applAjaxMess"></p>
                <button class="applMessApply" type="submit" form="rightForm">Подтвердить</button><button class="applMessCancel">Отменить</button>
            </div>
         <div id="accessmessage">
                <p class="accMess"></p>
                <button class="accMessOk">Ok</button>
        </div>
        <div id="overlay"></div>
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
                </div>
            </div>
        </div>
        <div id="content">	    		
            <div class="leftBar">
              <div class="back" title="Tools">
                <a href="Tools"><img src="resources/images/left_arrow.png"/></a>
                  </div>
                <div class="home" title="Home">
                        <a href="<%=request.getContextPath()%>/"><img
                            src="resources/images/home_w.png"/></a>
                    </div>
                    <div class="info" title="Info">
                        <a href="Tools"><img src="resources/images/info.png"/></a>
                    </div>
            </div>
            <div class="leftTA">
                <div class="gridStyle" ng-grid="gridOptions"></div>
                <button class="takeData">Выбрать данные</button>
                <div class="leftTAoverlay"></div>
                <div class="leftTAloader">
                    <img src="resources/images/spinner.gif" alt="spinner"/>
                </div>
            </div>
            <div class="buttons" ng-click="sendData()">
                <img onmouseover="stopstyling()" src="resources/images/generate.png" id="generate" alt="generate" title="Сгенерировать XML"/>
                <script>
                    var firsttimeout;
                    var secondtimeout;
                    var generate = document.getElementById('generate');
                    function changestyle()
                        {
                                if (generate.style.visibility == 'visible') {
                                    generate.style.visibility = 'hidden';
                                    firsttimeout=setTimeout(changestyle, 500);
                                } else {
                                    generate.style.visibility = 'visible';
                                    secondtimeout=setTimeout(changestyle, 1000);
                                }
                        }
                    setTimeout(changestyle, 0);
                    function stopstyling() {
                         clearTimeout(firsttimeout);
                         clearTimeout(secondtimeout);
                    }
                </script>
            </div>
            <div class="rightTA">
            	<textarea disabled="disabled">response:{{PostDataResponse}}</textarea>
            </div>
            <div class="processLoader">
                <div class="step1">
                    <p>Шаг 1: выборка данных<button class="deleteStep1">X</button></p>
                </div>
                <div class="step2">
                    <p>Шаг 2: сгенерировать документ</p>
                </div>
                <div class="step3">
                    <p>Шаг 3: подтверждение</p>
                </div>
            </div>
        </div>
        <div id="footer">
            <h3>XML CREATION</h3>
        </div>
    </body>
</html>