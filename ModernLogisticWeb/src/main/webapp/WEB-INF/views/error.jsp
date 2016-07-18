<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--  <input type="hidden" id="url" value="<c:out value="${url}"/>"/>
 <c:set var="url" value="${url}" />
<%
String outp = (String) request.getAttribute("url"); //getParameter("url");// 
//response.sendRedirect("error");
out.println(" <html><script>alert('ALERTTTT')</script></html>");
%> --%>
<input type="hidden" id="url" value="<c:out value="${url}"/>"/>
<script type="text/javascript">
    var name = document.getElementById('url').value;
    alert('О Ш И Б К А :   '+name);
</script>


 **************************ERROR****************************************	
	Exception:  ${exception.message}										
 **************************ERROR****************************************	
    Cause:																	
	${url}																	
 --------------------------ERROR----------------------------------------- 	 

        	<c:forEach items="${exception.stackTrace}" var="ste">   
        		${ste} 
    		</c:forEach> 
