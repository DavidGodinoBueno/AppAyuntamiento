<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="overflow: hidden;">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleVerServicios.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody" style="overflow: hidden;">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
   <div class="container mt-5">
      <form action="ControllerUsuario" method="post"
      class="bg-dark p-3 text-light col-lg-7 col-md-7 col-sm-12 col-12"
      style="border-radius:10px;">
        <div class="row justify-content-between g-3">
           <h5>Buscar por categorías de servicios: </h5>
         <div class="col-lg-9 col-md-9 col-sm-12 col-12">
         <select name="category" class="form-select">
           <c:forEach items="${catservicios}" var="categoria">
              <option value="${categoria.idcategoria}"
                 <c:if test="${categoria.idcategoria == thecategory}">
                    <c:out value=" selected"></c:out>
                 </c:if>
              >
              ${categoria.descripcion}</option>
           </c:forEach>
         </select>
         </div>
         <div class="col-lg-3 col-md-3">
              <input type="hidden" name="keyword" value="BuscarServiciosByCategoria"/>
             <input type="submit" class="btn btn-primary col-sm-12 col-12" value="Buscar"/>
         </div>
        </div>
      </form>
      <br>
      <c:choose>
        <c:when test="${not empty listadoservicios}">
         <div class="cuadroservicios p-3" style="border-radius:10px;">
	  <h1 class="text-light">
	  <img alt="" src="${pageContext.request.contextPath}/ControllerUsuario?elidcat=${estacategoria.idcategoria}&keyword=verfotoscatservicios"
            width="70px" height="70px"/>
	  Servicios Públicos de Torrijos 
	  <c:if test="${estacategoria != null}">sobre ${estacategoria.descripcion}</c:if></h1>
	 <div class="row mt-1">
	  <c:forEach items="${listadoservicios}" var="servicio">
	   <div class="col-lg-3 col-md-5 col-sm-6 col-9 mt-4">
		<div class="card p-1" style="height: 380px;border:transparent;background-color:#4a3711;">
		    <img class="card-img-top" src="${pageContext.request.contextPath}/ControllerUsuario?elidservicio=${servicio.idservicio}&keyword=verfotosServicios" 
		    width="180px" height="200px" style="box-shadow:0px 0px 10px black;">
		   <div class="card-body">
		      <h3 class="card-title text-light">${servicio.nombre}</h3>
		      <div class="row justify-content-start">
		        <div class="col-lg-3 col-md-3 col-sm-3 col-3">
		            <a href="${pageContext.request.contextPath}/ControllerUsuario?lacateg=${thecategory}&ideservice=${servicio.idservicio}&keyword=DarLikeService" 
		            class="text-light" style="text-decoration: none;">
		              <img alt="" src="${pageContext.request.contextPath}/resources/img/icolike.png"
            width="20px" height="20px"/>&nbsp;&nbsp;${servicio.likes}
		            </a>
		        </div>
		        <div class="col-lg-3 col-md-3 col-sm-3 col-3">
		            <a href="${pageContext.request.contextPath}/ControllerUsuario?thiscateg=${thecategory}&thisidserv=${servicio.idservicio}&keyword=DarDislikeService" 
		            class="text-light" style="text-decoration: none;">
		               <img alt="" src="${pageContext.request.contextPath}/resources/img/icodislike.png"
            width="20px" height="20px"/>&nbsp;&nbsp;${servicio.dislikes}
		            </a>
		        </div>
		      </div>
		      <div class="row mt-2 g-1">
		      <a href="${pageContext.request.contextPath}/ControllerUsuario?idnoticiainfo=${noticia.idnoticia}&keyword=LeerMasNoticia" 
		      class="btn btn-light"><img alt="" src="${pageContext.request.contextPath}/resources/img/icolibro.png"
              width="20px" height="20px"/>&nbsp;Leer Más</a>
		      </div>
		    </div>
		   </div>
		</div>
	  </c:forEach>
	 </div>
	 </div>
	 </c:when>
	  <c:when test="${listadoservicios != null && listadoservicios.isEmpty()}">
	     <h1 class="text-center text-light mt-5">No hay servicios disponibles para esta categoría.</h1>
	  </c:when>
	  <c:when test="${estacategoria == null}">
	     <h1 class="text-center text-light mt-5">
	     <br><br><br><br>
	     Elige una categoría para ver los servicios</h1>
	  </c:when>
	</c:choose>
   </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>   
</body>
</html>