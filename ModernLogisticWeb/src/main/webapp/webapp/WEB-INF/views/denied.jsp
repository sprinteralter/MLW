<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css" rel="stylesheet">
  <style type="text/css">
    body {
      padding-top: 60px;
      padding-bottom: 40px;
    }
  </style>
    <link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap-responsive.css" rel="stylesheet">
  <center>
  <div>

  
 <h3>Авторизация с помощью файла ключей</h3>


    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="./keyauth" method="post">
      <h2 class="form-signin-heading"></h2>
 
      <div class="form-group">Key file: <input type="file" name="file"></div>

      <div class="form-group"><input type="submit" class="btn btn-large btn-primary" value="Login"></div>
    </form>
    </div>
    </center>