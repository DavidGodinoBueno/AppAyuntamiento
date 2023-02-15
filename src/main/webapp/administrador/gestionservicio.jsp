<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylegestionservicios.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
      <div class="container mt-5">
      <br><br>
       <div class="row justify-content-around g-3">
         <div class="col-lg-5 col-md-5 col-sm-12 col-12">
            <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrANuevoServicio" 
          class="enlaces btn btn-light text-success p-5" 
          style="font-size:50px;border-radius:10px;border:solid green 10px;
          height:270px;">
          <img alt="" src="${pageContext.request.contextPath}/resources/img/icoadd.png"
            width="80px" height="80px"/>
          Nuevo Servicio</a>
         </div>
         <div class="col-lg-5 col-md-5 col-sm-12 col-12">
            <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAEditDeleteServicios" 
          class="enlaces btn btn-light text-success p-5" 
          style="font-size:40px;border-radius:10px;border:solid green 10px;
          height:270px;">
          <img alt="" src="${pageContext.request.contextPath}/resources/img/icoajustes.png"
            width="90px" height="90px"/>
          Editar/eliminar Servicios</a>
         </div>
       </div>
       <div class="row justify-content-around mt-3 g-3">
         <div class="col-lg-5 col-md-5 col-sm-12 col-12">
            <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAltaCatService" 
          class="enlaces btn btn-light text-success p-5" 
          style="font-size:40px;border-radius:10px;border:solid green 10px;
          height:270px;">
          <img alt="" src="${pageContext.request.contextPath}/resources/img/icocatego.png"
            width="90px" height="90px"/>
          Alta Categoria Servicios</a>
         </div>
       </div>
      </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>