<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
 <link rel="stylesheet" href="resources/css/bootstrap3.min.css"/>
 <script src="resources/js/bootstrap.min.js"></script> 
 <script src="resources/js/jquery-2.2.4.min.js"></script>
 
  <style type="text/css">
    body {
      padding-top: 40px;
      padding-bottom: 40px;
      padding-left: 20px;
      padding-right: 20px;
      background-color: #f5f5f5;
    }
    </style>
 
</head>

<body>

<div class="page-header">
        <h1>Panels</h1>
      </div>
      <div class="row">
        <div class="col-sm-4">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Генерация ключа для входа</h3>
            </div>
            <div class="panel-body">
      <form role="form" enctype="multipart/form-data"  action="./generateKey" method="post">
      <h2 class="form-signin-heading"></h2>
      <div class="form-group">
      <div><label for="inputEmail" >Login</label></div>
      <input type="text"  name="name" placeholder="" required autofocus></div>
       <label for="inputEmail" >Database</label>
      <select  name="domain"  required>
     								 <option selected></option>
                                    <option>Sprinter</option>
                                    <option>Alter</option>
                                    <option>sprinter_curent</option>
                                    <option>alter_curent</option>
                                </select>
      	<div class="form-group"><input type="submit" class="btn btn-small btn-default" value="generate key"></div>

    </form>
            </div>
          </div>
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title">spot2D XOMKA выгрузка за период</h3>
            </div>
            <div class="panel-body">
              <form role="form" enctype="multipart/form-data"  action="./generateHomka" method="post">
              
              	 <div class="form-group">
     			 <div><label for="inputEmail" >Дата с:</label></div>
      			 <input type="text"  name="s" placeholder="yyyy-mm-dd" required autofocus></div>
      			 
      			  <div class="form-group">
     			 <div><label for="inputEmail" >Дата по:</label></div>
      			 <input type="text"  name="po" placeholder="yyyy-mm-dd" required autofocus></div>
              <div class="checkbox">
                    <label>
                        <input type="checkbox" name="ftp"> Залить на ФТП
                    </label>
                </div>
              <div class="form-group"><input type="submit" class="btn btn-small btn-primary" value="Выгрузить"></div>
              </form>
            </div>
          </div>
        </div><!-- /.col-sm-4 -->
        <div class="col-sm-4">
          <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
              Panel content
            </div>
          </div>
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
              Panel content
            </div>
          </div>
        </div><!-- /.col-sm-4 -->
        <div class="col-sm-4">
          <div class="panel panel-warning">
            <div class="panel-heading">
              <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
              Panel content
            </div>
          </div>
          <div class="panel panel-danger">
            <div class="panel-heading">
              <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
              Panel content
            </div>
          </div>
        </div><!-- /.col-sm-4 -->
      </div>

</body>
</html>