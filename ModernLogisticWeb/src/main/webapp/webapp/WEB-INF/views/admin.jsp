<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css" rel="stylesheet">
  <style type="text/css">
    body {
      padding-top: 60px;
      padding-bottom: 40px;
    }
  </style>
    <link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap-responsive.css" rel="stylesheet">
    
    <div class="container">
<form role="form" enctype="multipart/form-data" class="form-horizontal" action="./generateKey" method="post">
      <h2 class="form-signin-heading"></h2>
      <div class="form-group">
      <label for="inputEmail" class="sr-only">Login</label>
      <input type="text" class="form-control" name="name" placeholder="" required autofocus></div>
       <label for="inputEmail" class="sr-only">Database</label>
      <select  name="domain" class="sr-only" required>
     								 <option selected></option>
                                    <option>Sprinter</option>
                                    <option>Alter</option>
                                    <option>sprinter_curent</option>
                                    <option>alter_curent</option>
                                </select>
      	<div class="form-group"><input type="submit" class="btn btn-small btn-primary" value="generate key"></div>
    
    </form>
    </div>	
    </html>