<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylegestionservicios.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
     <div class="container mt-5">
       <c:if test="${confcolinter != null}">
          <h2 class="bg-success p-2 text-light col-lg-6 col-md-6 col-sm-12 col-12">${confcolinter}</h2>
       </c:if>
       <form action="ControllerAdmin" class="bg-light p-3 col-lg-6 col-md-6 col-sm-12 col-12" 
       method="post" style="border-radius:10px;">
          <h1 class="text-center">Cambiar color navegación.</h1>
          <div class="row justify-content-between mt-2 g-2">
            <div class="col-lg-10 col-md-10 col-sm-12 col-12">
             <label>Elige color: </label>
             <select name="colournav" class="form-select">
                <option value="bg-danger">Rojo</option>
                <option value="bg-primary"
                    <c:if test="${thecolour == 'bg-primary'}">
                      <c:out value=" selected"></c:out>
                   </c:if>
                >Azul</option>
                <option value="bg-warning"
                   <c:if test="${thecolour == 'bg-warning'}">
                      <c:out value=" selected"></c:out>
                   </c:if>
                >Amarillo</option>
                <option value="bg-success"
                   <c:if test="${thecolour == 'bg-success'}">
                      <c:out value=" selected"></c:out>
                   </c:if>
                >Verde</option>
                <option value="bg-dark"
                   <c:if test="${thecolour == 'bg-dark'}">
                      <c:out value=" selected"></c:out>
                   </c:if>
                >Negro</option>
             </select>
            </div>
          </div>
          <div class="row justify-content-around mt-5 g-2">
             <div class="col-lg-4 col-md-4">
               <input type="hidden" name="keyword" value="changecolournav"/>
               <input type="submit" class="btn btn-warning col-sm-12 col-12" value="Cambiar Color"/>
             </div>
             <div class="col-lg-4 col-md-4">
                <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAGestionInterfaz" 
                class="btn btn-danger col-sm-12 col-12">Cancelar</a> 
             </div>
          </div>
       </form>
     </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>