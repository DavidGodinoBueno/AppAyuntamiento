<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylegaleriasfotos.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mostrarFotoGrandeSeleccionadaAyuntamiento.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body style="background:linear-gradient(90deg, #cca466, #523c1b)">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
    <div class="container mt-3">
     <c:if test="${not empty listadoimagenes}">
	  <div class="cuadrofotos p-2">
	  <h2 class="text-center text-light">
	  <img alt="" src="${pageContext.request.contextPath}/resources/img/icogaleriafoto.png"
	  width="80px" height="80px">
	  Galería fotos Ayuntamiento</h2>
	 <div class="row mt-1">
	  <c:forEach items="${listadoimagenes}" var="imagen">
	   <div class="col-lg-3 col-md-5 col-sm-6 col-9 mt-4">
	      <input type="hidden" class="losidimagen" value="${imagen.idimagen}"/>
	      <img class="img-fluid img-thumbnail" id="laimagen${imagen.idimagen}" src="${pageContext.request.contextPath}/ControllerUsuario?idfotoayuntamiento=${imagen.idimagen}&keyword=fotoayuntamiento" width="280px" height="200px">
		</div>
	  </c:forEach>
	 </div>
	 <div id="cuadroimagen" class="thephoto">
	     <hr style="height:15px;"/>
	       <img id="imagengrande" width="450px" height="400px"
	       class="img-fluid img-thumbnail mt-3" style="text-align:center;"/>
	</div>
	 </div>
	</c:if>
	<c:if test="${listadoimagenes != null && listadoimagenes.isEmpty()}">
	   <br><br><br><br>
	   <div id="notFound" class="mt-5 p-3 text-center w-75">
	     <div class="mt-2"></div>
	     <img alt="" src="${pageContext.request.contextPath}/resources/img/iconopeli.png" 
	     width="120px" height="120px">
	     <h1 class="text-center">No hay ninguna imagen</h1>
	      <div class="mt-2"></div>
	   </div>
	</c:if>
    </div>
    <%@include file="../WEB-INF/includes/piepagina.jspf" %>
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>   
</body>
</html>