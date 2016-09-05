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
        <!-- <script type="text/javascript" src="resources/js/ui-bootstrap-tpls-2.0.0.min.js"></script> -->
        <script type="text/javascript" src="resources/js/angular/angular.min.js"></script>
        <script type="text/javascript" src="resources/js/angular/angular-animate.min.js"></script>
        <script type="text/javascript" src="resources/js/ng-grid-2.0.7.min.js"></script>
        <!-- <script type="text/javascript" src="resources/js/ng-grid-2.0.7.debug.js"></script> -->
        <script type="text/javascript" src="resources/js/angular/xmlcreationAngular.js"></script>
        <script type="text/javascript" src="resources/js/jquery-ui-1.12.0.custom/jquery-ui-1.12.0.custom/jquery-ui.min.js"></script>
    	<script type="text/javascript" src="resources/js/jquery-ui-1.12.0.custom/jquery-ui-1.12.0.custom/ui.datapicker-ru.js"></script>
    	
    	<script>
	    	$(document).on('show','.accordion', function (e) {
	            //$('.accordion-heading i').toggleClass(' ');
	            $(e.target).prev('.accordion-heading').addClass('accordion-opened');
	       });
	       
	       $(document).on('hide','.accordion', function (e) {
	           $(this).find('.accordion-heading').not($(e.target)).removeClass('accordion-opened');
	           //$('.accordion-heading i').toggleClass('fa-chevron-right fa-chevron-down');
	       });
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
		        Операция выполнена успешно! 
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
                    	<a>${database}:${username}<img class="user" title="Ваш профиль" src="resources/images/add-user.svg"/></a>
                    	<a href="logout"><img title="Выйти" src="resources/images/padlock.svg"/></a>
                	</div>
            	</div>
        	</div>
    	</header>
        <section id="wizard">
  		    <div id="rootwizard" class="tabbable tabs-left">
                <ul class="tableLeft">
            	    <li><a href="#tab1" data-toggle="tab">EDI - XML подтв./увед.</a></li>
                    <li ng-click="tab2()"><a href="#tab2" data-toggle="tab">EDI - Коммерческий документ</a></li>
                </ul>
                <div class="tab-content">
                     <div class="tab-pane" id="tab1">
                     	<div class="tab-pane-overlay" ng-show="overlay">
                     		<img src="resources/images/spinner.gif" alt="spinner"/>
                     	</div>
                     	<div class="tab-pane-top">
                      		<div class="tab-pane-top-grid" ng-grid="gridOptions"></div>
                      		<button id="takeControl" ng-click="showTopGridOptions()" title="Опции таблицы" data-toggle="modal" data-target="#myModal"></button>
                      		<button id="takeData" ng-click="accessTopGrid()" title="Выбрать"></button>
                      	</div>
                      	<div class="btn-group" data-toggle="buttons">
                      		<div class="btn-group-temp" ng-show="selectbar">	                     		
	                     		<button type="button" class="btn btn-warning" ng-click="cancel()" ng-show="showCancelBut">
	        						<i class="glyphicon glyphicon-star">Отменить</i>
	        					</button>
	        					<h5 class="selectedOption" ng-show="showSelectedOption">{{selected}}<img class="optionloader" src="resources/images/optionloader.gif" alt="optionloader"/></h5>
	        					<select ng-model="selectedOption" ng-show="selectMenu">
								    <option value="" selected disabled>Выбрать операцию</option>
								    <option value="1">Подтверждение заказа</option>
								    <option value="2">Уведомление об отгрузке</option>
								</select>
	        					<button type="button" class="btn btn-success" ng-show="selectedOption=='1'" data-toggle="modal" data-target="#myModal">
	        						<i class="glyphicon glyphicon-star">Cгенерировать подтверждение заказа</i>
	        					</button>
	        					<button type="button" class="btn btn-success" ng-show="selectedOption=='2'" data-toggle="modal" data-target="#myModal">
	        						<i class="glyphicon glyphicon-star">Cгенерировать уведомление об отгрузке</i>
	        					</button>
	        					<!-- <button type="button" class="btn btn-success" ng-show="selectedOption=='3'" data-toggle="modal" data-target="#myModal">
	        						<i class="glyphicon glyphicon-star">Cгенерировать коммерческий документ</i>
	        					</button> -->
	        				</div>
                     	</div>
                     	<div class="tab-pane-bottom" ng-show="showResultGrid">
                     		<div class="tab-pane-bottom-grid" ng-grid="gridResult"></div>
                     		<button id="sendToFtp" data-toggle="modal" data-target="#myModal" ng-show="sendToFtpBut" ng-click="showModalSendPara()" title="Отправить фалы EDI"></button>
                     		<button id="refresh" ng-show="refresh" ng-click="refreshPage()" title="Повтор операции"></button>
                     	</div>
                     </div>
                     
                     
                     <div class="tab-pane" id="tab2">
                     	
                     	<div class="navbar navbar-info">
						    <div class="navbar-inner">
						        <div class="container">
							        <!-- <a class="brand" href="#">Project</a> -->
							        <div class="nav-collapse collapse">
										<ul class="nav nav-pills pull-left" role="tablist">
											<li role="presentation"><a>Всего <span class="badge">{{links.length}}</span></a></li>
											<li role="presentation"><a>Фильтр <span class="badge badge-success">{{filteredData.filteredLinks.length}}</span></a></li>
											<li ng-show="tab2showSelectedList" role="presentation"><a>Выбрано <span class="badge badge-info">{{selectedList.length}}</span></a></li>
										</ul>
							            <form class="navbar-search">
							            	<select class="span" ng-model="tab2SelectClient" ng-change="tab2filterchange()">
											    <option value="0">Клиенты</option>
												<option value="9863577638028">ТОВ "Новус Украина"</option>
												<option value=" 4820129370008">ТОВ "ЕКО-МАРКЕТ"</option>
												<option value="4823058711089">ТОВ "Фудмережа"</option>
									  		</select>
							            </form>
							            
							            <form class="navbar-search">
							            	<select class="span" ng-model="tab2SelectDoc" ng-change="tab2filterchange()">
											    <option value="0">Документы: {{links.length}}</option>
												<option value="Приходная накладная">Приходная накладная: {{doc1}}</option>
												<option value="Расходная накладная">Расходная накладная: {{doc2}}</option>
												<option value="Акт о приеме товара">Акт о приеме товара: {{doc3}}</option>
												<option value="Акт про виявлені недоліки">Акт о выявлении недостатков: {{doc4}}</option>
												<option value="Возвратная накладная">Возвратная накладная: {{doc5}}</option>
									  		</select>
							            </form>
							            <ul class="nav navbar-nav pull-right">
									        <li class="dropdown">
									          <a style='cursor:pointer' class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">&#9881<span class="caret"></span></a>
									          <ul class="dropdown-menu">
									            <li style='cursor:pointer' ng-click='tab2SendChosenDocs()'><a><i class="icon-edit"></i> Подписать выбранные документы ({{selectedList.length}})</a></li>
									            <li style='cursor:pointer' ng-click='tab2refreshLinks()'><a><i class="icon-refresh"></i> Обновить данные</a></li>
									            <!-- <li><a href="#">Something else here</a></li>
									            <li role="separator" class="divider"></li>
									            <li><a href="#">Separated link</a></li>-->									      </ul>
									        </li>
									    </ul>
									    <button type="button" class="btn btn-default pull-right" ng-click="tab2showSearching()"><i class="icon-search"></i></button>
							            <!-- <button type="button" class="btn btn-default pull-right">&#10162</button> -->
							            
										<form class="navbar-search pull-right" ng-show="tab2showSearch">
										    <input type="text" class="search-query" placeholder="поиск..." ng-model="linksSearching ">
										</form>
						            </div><!--/.nav-collapse -->
						        </div>
						    </div>
						</div>
						
						<div class="navbar" ng-repeat="link in (filteredData.filteredLinks =(links | filter: linksSearching | filter: tab2SelectDoc| filter: tab2SelectClient | orderBy: 'obj.header.date'))">
						    <div class="navbar-inner">
						        <div class="container">
							        <!-- <a class="brand" href="#">Project</a> -->									
							        <div class="nav-collapse collapse">
							        	<a class="brand brand-info">{{link.obj.sides.contractor[0].contractorname}}</a>
						            </div><!--/.nav-collapse -->
						            <ul class="nav">
									  <li class="active"><a>{{link.obj.header.type}}</a></li>
									  <li><a>{{link.obj.header.date}}</a></li>
									</ul>
									<ul class="nav pull-right" ng-show="tab2panelSuccess">
									  <li class="active"><a><i class="icon-check"></i></a></li>
									  <li class="active"><a>{{tab2signResponse}}</a></li>
									</ul>
									<button ng-click="tab2addLink($index,hiddenButDet=!hiddenButDet,hiddenButSign=!hiddenButSign,hiddenAddBut=!hiddenAddBut,hiddenButCancel=!hiddenButCancel)" ng-show="hiddenAddBut" id="panelsBut" type="button" class="btn btn-info pull-right" title="Добавить в список"><i class="icon-ok"></i></button>
									<button ng-click="tab2detailsLink(hiddenDiv=!hiddenDiv,hiddenButSign=!hiddenButSign,hiddenAddBut=!hiddenAddBut)" ng-show="hiddenButDet" id="panelsBut" type="button" class="btn btn-warning pull-right" title="Детали"><i class="icon-list"></i></button>
									<button ng-click="tab2signLink($index,hiddenButSign=!hiddenButSign,hiddenAddBut=!hiddenAddBut,hiddenButDet=!hiddenButDet,tab2panelSuccess=!tab2panelSuccess)" id="panelsBut" type="button" ng-show="hiddenButSign" class="btn btn-success pull-right" title="Подписать"><i class="icon-edit"></i></button>
									<button ng-click="tab2CancelLink($index,hiddenButDet=!hiddenButDet,hiddenButSign=!hiddenButSign,hiddenAddBut=!hiddenAddBut,hiddenButCancel=!hiddenButCancel)" id="panelsBut" type="button" ng-show="hiddenButCancel" class="btn btn-danger pull-right" title="Удалить из списка"><i class="icon-remove"></i></button>								    
						        </div>
						        <div class="tab2PanelDetail" ng-show="hiddenDiv">
						        	<b>Коммерческий документ</b>
									<p>№: {{link.obj.header.number}}</p>
									<p>Дата: {{link.obj.header.date}}</p>
						        </div>
						        <!-- <div class="progress progress-success progress-striped active" ng-show="hiddenProgress">
								  <div class="bar" style="width: 40%;"></div>
								</div> -->
						    </div>
						</div>
                  </div>
              </div>
            </div>  
          </section>
          <footer>
                <h4>XML CREATION</h4>
          </footer>
    </body>
</html>