<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html lang="en">
    <head>
    <title>XML</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="resources/css/login.css" />
        <!-- JS -->
        <script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="resources/js/jquery-ui.js"></script>
    </head>
<body>
    <div class="container">
        <video poster="resources/video/4462757.mp4" autoplay="true" loop>
            <source src="resources/video/4462757.mp4" type="video/mp4">
        </video>
        <div class="overlay"></div>
        <div class="logoForm">
            <c:url value="/j_spring_security_check" var="loginUrl"/>
            <form action="${loginUrl}" method="post">
                <h2 class="form-signin-heading">Войти в систему</h2>
                <input type="text" class="form-control-left" name="j_username" placeholder="Login" required autofocus value="">
                <input type="password" class="form-control-right" name="password" placeholder="Password" required value=""><br>
                <label>База данных: </label>
                <select name="domain" class="form-control" required="required">
                                    <option selected>Sprinter</option>
                                    <option>Alter</option>
                                    <option>sprinter_curent</option>
                                    <option>alter_curent</option>
                                </select><br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            </form>
        </div>
    </div>
</body>
</html>