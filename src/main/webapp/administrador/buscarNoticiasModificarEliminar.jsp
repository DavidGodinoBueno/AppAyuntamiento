<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylebuscarNoticiasEditDel.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
   <div class="container mt-5">
     <c:if test="${confdelnoticias != null}">
        <h2 id="delnoticia" class="bg-success p-2 text-light col-lg-5 col-md-7 col-sm-12 col-12">
        ${confdelnoticias}</h2>
     </c:if>
     <div class="framenoticias p-3" style="border-radius:10px;">
        <h1 class="text-light">
	  <img alt="" src="${pageContext.request.contextPath}/resources/img/icoajustes.png"
            width="70px" height="70px"/>
	  Gestionar Noticias</h1>
	   <div class="row justify-content-between g-2">
	      <div class="col-lg-3 col-md-5 col-sm-12 col-12">
	         <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAltaNoticia" 
      class="btn btn-lg btn-light text-success">
      <img alt="" src="${pageContext.request.contextPath}/resources/img/icoadd.png"
            width="20px" height="20px"/>
      Nueva noticia</a>
	      </div>
	      <div class="col-lg-5 col-md-5 col-sm-12 col-12">
	          <form action="ControllerAdmin" class="col-lg-8 col-md-10 col-sm-12 col-12" 
	        method="post">
	     <div class="row justify-content-between g-2">
	        <div class="col-lg-9 col-md-8">
	           <input type="text" placeholder="Buscar título noticias" 
	         class="form-control col-sm-12 col-12" name="titnoticias"
	         value="${partetitulo}"/>
	        </div>
	        <div class="col-lg-3 col-md-4">
	           <input type="hidden" name="keyword" value="BuscarNoticiasEditDelete"/>
	         <input type="submit" class="btn btn-primary col-sm-12 col-12" value="Buscar"/>
	        </div>
	       </div>
	     </form>
	      </div>
	   </div>
	   <br/>
	   <form action="ControllerAdmin" method="post">
	   <c:choose>
	      <c:when test="${not empty listanoticias}">
	          <table class="table table-striped table-hover table-bordered bg-light">
	             <tr>
	                <th colspan="5" class="bg-dark text-center text-light">
	                <h2>
	                <img alt="" src="${pageContext.request.contextPath}/resources/img/iconoticia.png"
            width="50px" height="50px"/>
	                Noticias a editar/eliminar</h2>
	                </th>
	             </tr>
	             <tr>
	                <th>ELIMINAR</th>
	                <th>TITULO</th>
	                <th>FOTO NOTICIA</th>
	                <th>FECHA</th>
	                <th>EDITAR</th>
	             </tr>
	             <c:forEach items="${listanoticias}" var="noticia">
	                <tr>
	                  <td><input type="checkbox" name="noticiaseliminar"
	                  value="${noticia.idnoticia}"/></td>
	                  <td>${noticia.titulo}</td>
	                  <td class="text-center"><img alt="" src="${pageContext.request.contextPath}/ControllerUsuario?idfotonoticia=${noticia.idnoticia}&keyword=fotosnoticias"
	                  width="100px" height="100px" style="border-radius:4px;"/></td>
	                  <td><fmt:formatDate pattern="dd-MM-yyyy" value="${noticia.fechanoticia}"/></td>
	                  <td><a href="${pageContext.request.contextPath}/ControllerAdmin?idnoticiaedit=${noticia.idnoticia}&keyword=NoticiaAEditar" class="btn btn-warning">
                          <img alt="" src="${pageContext.request.contextPath}/resources/img/icoeditar.png"
                          width="30px" height="30px">
                          </a>
                      </td>
	                </tr>
	             </c:forEach>
	             <tr>
	                <td colspan="5">
	                <input type="hidden" name="trozotitulo" value="${partetitulo}"/>
	                <input type="hidden" name="keyword" value="EliminarNoticias"/>
	                <input type="submit" class="btn btn-danger" value="Eliminar noticias"/></td>
	             </tr>
	          </table>
	      </c:when>
	      <c:when test="${listanoticias != null && listanoticias.isEmpty()}">
	          <h1 class="text-center text-light">No hay noticias coincidentes.</h1>
	      </c:when>
	   </c:choose>
	   </form>
     </div>
      
   </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>