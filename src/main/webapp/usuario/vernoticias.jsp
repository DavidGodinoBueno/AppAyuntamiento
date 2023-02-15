<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylevistanoticias.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
    <%@include file="../WEB-INF/includes/navegacion.jspf" %>
    <div class="container mt-5">
	<div class="cuadronoticias p-3" style="border-radius:10px;">
	  <h1 class="text-light">
	  <img alt="" src="${pageContext.request.contextPath}/resources/img/iconoticia.png"
            width="70px" height="70px"/>
	  Noticias de Torrijos</h1>
	  <form action="ControllerUsuario" class="col-lg-7 col-md-9 col-sm-12 col-12" 
	  method="post">
	     <div class="row justify-content-between g-2">
	        <div class="col-lg-5 col-md-5">
	           <input type="text" placeholder="Buscar título noticias" 
	         class="form-control col-sm-12 col-12" name="patrontitulo"
	         value="${lasiniciales}"/>
	        </div>
	        <div class="col-lg-4 col-md-3 col-sm-12 col-12">
	           <select name="ordenNoticias" class="form-select">
	              <option value="ASC">Más antiguas</option>
	              <option value="DESC"
	                 <c:if test="${ordenfecha == 'DESC'}">
	                    <c:out value=" selected"></c:out>
	                 </c:if>
	              >Más recientes</option>
	           </select>
	        </div>
	        <div class="col-lg-3 col-md-4">
	           <input type="hidden" name="keyword" value="BuscarNoticias"/>
	         <input type="submit" class="btn btn-primary col-sm-12 col-12" value="Buscar"/>
	        </div>
	     </div>
	  </form>
	 <div class="row mt-1">
	 <c:if test="${not empty lasnoticias}">
	  <c:forEach items="${lasnoticias}" var="noticia">
	   <div class="col-lg-3 col-md-5 col-sm-6 col-9 mt-4">
		<div class="card bg-light p-1" style="height: 450px;">
		    <img class="card-img-top" src="${pageContext.request.contextPath}/ControllerUsuario?idfotonoticia=${noticia.idnoticia}&keyword=fotosnoticias" width="180px" height="200px">
		   <div class="card-body">
		      <h3 class="card-title">${noticia.titulo}</h3>
		      <p class="text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${noticia.fechanoticia}"/></p> 
		      <div class="row g-1 mt-3">
		      <a href="${pageContext.request.contextPath}/ControllerUsuario?idnoticiainfo=${noticia.idnoticia}&keyword=LeerMasNoticia" 
		      class="btn btn-primary"><img alt="" src="${pageContext.request.contextPath}/resources/img/icolibro.png"
              width="20px" height="20px"/>&nbsp;Leer Más</a>
		      </div>
		    </div>
		   </div>
		</div>
	  </c:forEach>
	  </c:if>
	  <c:if test="${lasnoticias != null && lasnoticias.isEmpty()}">
	      <h1 class="text-center text-light mt-5">No hay noticias disponibles</h1>
	  </c:if>
	 </div>
	 </div>
    </div>
    <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>