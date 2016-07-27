<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <title>test</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="resources/css/prettify.css"/>
         <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
        <!-- JS -->
        <script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>       
        <script type="text/javascript" src="resources/js/jquery-bootstrap-wizard.js"></script>
    	<script>
            $(document).ready(function() {
                $('#rootwizard').bootstrapWizard({'tabClass': 'nav nav-tabs'});	
            });	
        </script>
    </head>
    <body>
    	<header>
	        <div class="infoBar">
                <div class="infoBarLeft">
                    <div class="infoBarIconLeft" align="left">
                        <a href="Tools"><img title="Предыдущая стр." src="resources/images/left-arrow.svg"/></a>
                        <img title="Информация" src="resources/images/information.svg"/>
                        <a href="<%=request.getContextPath()%>/"><img title="Главная" src="resources/images/home.svg"/></a>
                    </div>
                </div>
		        <div class="infoBarCenter">
		            <h1>Modern Logistic Software</h1>
		        </div>
            	<div class="infoBarRight">
                	<div class="infoBarIconRight" align="right">
                    	<img class="user" title="Ваш профиль" src="resources/images/add-user.svg"/>
                    	<a href="logout"><img title="Выйти" src="resources/images/padlock.svg"/></a>
                	</div>
            	</div>
        	</div>
    	</header>
        <section id="wizard">    
  		    <div id="rootwizard" class="tabbable tabs-left">
                <ul class="tableLeft">
            	    <li><a href="#tab1" data-toggle="tab">Подтверждение заказа</a></li>
                	<li><a href="#tab2" data-toggle="tab">Уведомление об отгрузке</a></li>
                    <li><a href="#tab3" data-toggle="tab">Уведомление о приеме</a></li>
                    <li><a href="#tab4" data-toggle="tab">Коммерческий документ</a></li>
                    <li><a href="#tab5" data-toggle="tab">ЭНН</a></li>
                </ul>
                <div class="tab-content">
                     <div class="tab-pane" id="tab1">
                      1
                     </div>
                     <div class="tab-pane" id="tab2">
                      2
                     </div>
                     <div class="tab-pane" id="tab3">
                      3
                     </div>
                     <div class="tab-pane" id="tab4">
                      4
                     </div>
                     <div class="tab-pane" id="tab5">
                      5
                     </div>
                  </div>	
              </div>
          </section>
          <footer>
                <h4>XML CREATION</h4>
          </footer>
    </body>
</html>