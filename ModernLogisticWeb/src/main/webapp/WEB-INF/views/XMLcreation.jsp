<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
    <head lang="en">
        <title>test</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="resources/css/prettify.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/ng-grid.css"/>
        <link rel="stylesheet" type="text/css" href="resources/js/jquery-ui-1.12.0.custom/jquery-ui-1.12.0.custom/jquery-ui.min.css"/>
        
        <!-- JS -->
        <script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>       
        <script type="text/javascript" src="resources/js/jquery-bootstrap-wizard.js"></script>
        <script type="text/javascript" src="resources/js/ui-bootstrap-tpls-2.0.0.min.js"></script>
        <script type="text/javascript" src="resources/js/angular/angular.min.js"></script>
        <script type="text/javascript" src="resources/js/angular/angular-animate.min.js"></script>
        <script type="text/javascript" src="resources/js/ng-grid-2.0.7.min.js"></script>
        <script type="text/javascript" src="resources/js/ng-grid-2.0.7.debug.js"></script>
        <script type="text/javascript" src="resources/js/angular/xmlcreationAngular.js"></script>
        <script type="text/javascript" src="resources/js/jquery-ui-1.12.0.custom/jquery-ui-1.12.0.custom/jquery-ui.min.js"></script>
    	<script type="text/javascript" src="resources/js/jquery-ui-1.12.0.custom/jquery-ui-1.12.0.custom/ui.datapicker-ru.js"></script>
    	
    	<script>
            $(document).ready(function() {
                $('#rootwizard').bootstrapWizard({'tabClass': 'nav nav-tabs'});
                $.datepicker.regional['ru']={
					closeText: "Закрыть",
					prevText: "&#x3C;Пред",
					nextText: "След&#x3E;",
					currentText: "Сегодня",
					monthNames: [ "Январь","Февраль","Март","Апрель","Май","Июнь",
					"Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь" ],
					monthNamesShort: [ "Янв","Фев","Мар","Апр","Май","Июн",
					"Июл","Авг","Сен","Окт","Ноя","Дек" ],
					dayNames: [ "воскресенье","понедельник","вторник","среда","четверг","пятница","суббота" ],
					dayNamesShort: [ "вск","пнд","втр","срд","чтв","птн","сбт" ],
					dayNamesMin: [ "Вс","Пн","Вт","Ср","Чт","Пт","Сб" ],
					weekHeader: "Нед",
					dateFormat: "dd.mm.yy",
					firstDay: 1,
					isRTL: false,
					showMonthAfterYear: false,
					yearSuffix: "" };
               
                $( "#datepicker" ).datepicker();
                $.datapicker.setDefaults($.datepicker.regional[ "ru" ]);
            });	
        </script>
    </head>
    <body ng-controller="myCtrl">
    	<div id="overlay" ng-show="contentOverlay">
           	<img id="loader" src="resources/images/cloud_loading_256.gif" alt="spinner"/>
        </div>
        <!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" align="center">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" ng-click="cancelModal()" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		        <h4 class="modal-title" id="myModalLabel" ng-show="attentionHeader"><img src="resources/images/tick.svg" height="50px" width="50px"/></h4>
		        <h4 class="modal-title" id="myModalLabel" ng-show="optionsHeader"><img src="resources/images/edit.svg" height="50px" width="50px"/></h4>
		        <h4 class="modal-title" id="myModalLabel" ng-show="sendHeader"><img src="resources/images/computing-cloud.svg" height="50px" width="50px"/></h4>
		      </div>
		      <div class="modal-body" ng-show="modalConfirmBody">
		        Вы уверены, что хотите выполнить выбранную операцию!? 
		      </div>
		      <div class="modal-body" ng-show="modalSendBody">
		        Выполнить отправку данных на сервер EDI? В процессе отправки, изменить и/или отменить передачу данных, невозможно!   
		      </div>
		      <div class="modal-body" ng-show="modalSuccessBody">
		        Вы уверены, что хотите выполнить выбранную операцию!? 
		      </div>
		      <div class="modal-body" ng-show="modalGridOptionsBody">
			      <label for="date">Выбрать дату:</label>
			      	 <input ng-model="optionDATE" id="datepicker" type="text"/> 
			      <label for="optionalData" >Выбрать источник данных:</label>
			      	<select id="optionalData" ng-model="optionID">
			      		<option value="0">Расходная накладная</option>
			      	</select>	
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" ng-click="cancelALL()" data-dismiss="modal">Нет</button>
		        <button type="button" class="btn btn-success" ng-show="confirmOperation" ng-click="sendData()" data-dismiss="modal">Да</button>
		        <button type="button" class="btn btn-success" ng-show="confirmSending" ng-click="sendToEDI()" data-dismiss="modal">Выполнить</button>
		        <button type="button" class="btn btn-success" ng-show="refreshGrid" ng-click="refGrid()" data-dismiss="modal">Обновить таблицу</button>
		      </div>
		    </div>
		  </div>
		</div>
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
                    	<a><img class="user" title="Ваш профиль" src="resources/images/add-user.svg"/></a>
                    	<a href="logout"><img title="Выйти" src="resources/images/padlock.svg"/></a>
                	</div>
            	</div>
        	</div>
    	</header>
        <section id="wizard">
  		    <div id="rootwizard" class="tabbable tabs-left">
                <ul class="tableLeft">
            	    <li><a href="#tab1" data-toggle="tab">Отправка подтв./увед.</a></li>
                    <li><a href="#tab2" data-toggle="tab">Уведомление о приеме</a></li>
                    <li><a href="#tab3" data-toggle="tab">Коммерческий документ</a></li>
                    <li><a href="#tab4" data-toggle="tab">ЭНН</a></li>
                </ul>
                <div class="tab-content">
                     <div class="tab-pane" id="tab1">
                     	<div class="tab-pane-overlay" ng-show="overlay">
                     		<img src="resources/images/spinner.gif" alt="spinner"/>
                     	</div>
                     	<div class="tab-pane-top">
                      		<div class="tab-pane-top-grid" ng-grid="gridOptions"></div>
                      		<button id="takeControl" ng-click="showTopGridOptions()" title="Опции таблицы" data-toggle="modal" data-target="#myModal"></button>
                      		<button id="takeData" ng-click="showOverlayAndSelectBar()" title="Выбрать"></button>
                      	</div>
                      	<div class="btn-group" data-toggle="buttons">
                      	
                      		<div class="btn-group-temp" ng-show="selectbar">
	                     		<button type="button" class="btn btn-warning" ng-click="cancel()" ng-show="showWarning">
	        						<i class="glyphicon glyphicon-star">Отменить</i>
	        					</button>
	                     		<label class="btn btn-info" ng-click="showGenConfBut()" ng-show="showConfOption">
	          						<input type="radio" name="options" id="option1">Подтверждение заказа
	        					</label>
	        					<label class="btn btn-info" ng-click="showGenNoteBut()" ng-show="showNoteOtpion">
	          						<input type="radio" name="options" id="option2"> Уведомление об отгрузке
	        					</label>
	        					<button type="button" class="btn btn-success" ng-show="generateConfirmation" data-toggle="modal" data-target="#myModal">
	        						<i class="glyphicon glyphicon-star">Cгенерировать подтверждение</i>
	        					</button>
	        					<button type="button" class="btn btn-success" ng-show="generateNotification" data-toggle="modal" data-target="#myModal">
	        						<i class="glyphicon glyphicon-star">Cгенерировать уведомление</i>
	        					</button>
	        				</div>
                     	</div>
                     	<div class="tab-pane-bottom" ng-show="showResultGrid">
                     		<div class="tab-pane-bottom-grid" ng-grid="gridResult"></div>
                     		<button id="sendToFtp" data-toggle="modal" data-target="#myModal" ng-click="showModalSendPara()"></button>
                     	</div>
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
                  </div>	
              </div>
          </section>
          <footer>
                <h4>XML CREATION</h4>
          </footer>
          
           <script type="text/javascript">
					            $(function () {
							        $('#datetimepicker').datetimepicker({
							            language: 'ru',
							            useCurrent: false
							        });
							    });
					        </script>
    </body>
</html>