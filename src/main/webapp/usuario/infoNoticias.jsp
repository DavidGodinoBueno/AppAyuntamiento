<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleinfonoticia.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
   <div class="container mt-5">
      <div class="cuadroinfo p-4 col-lg-8 col-md-8 col-sm-12 col-12" style="border-radius:10px;">
        <h2 class="text-light">
        <img alt="" src="${pageContext.request.contextPath}/resources/img/icoinfo.png"
        width="80px" height="80px">
        Información Noticia</h2>
       <div class="row justify-content-between mt-2">
          <div class="col-lg-12 col-md-12 col-sm-12 col-12">
              <h2 class="text-light">Título: <span style="color:yellow;">${infoNoticia.titulo}</span></h2>
          </div>
       </div>
       <div class="row justify-content-between mt-2 g-3">
          <div class="col-lg-5 col-md-12 col-sm-12 col-12">
             <label><strong class="text-light">Descripción: </strong></label>
             <div style="text-align:justify;color:beige;">${infoNoticia.descripcion}</div>
          </div>
          <div class="col-lg-5 col-md-12 col-sm-12 col-12">
           <div class="input-group row mt-3">
           <div class="input-group-prepend">
               <div class="input-group-text justify-content-center">FOTO NOTICIA</div>
                 <img class="img-fluid img-thumbnail mt-2" src="${pageContext.request.contextPath}/ControllerUsuario?idfotonoticia=${infoNoticia.idnoticia}&keyword=fotosnoticias" 
                width="350px" height="200px" style="border-radius:5px;"/>         
             </div>
           </div>
          </div>
       </div>
       <div class="row justify-content-between mt-3 g-3">
          <div class="col-lg-5 col-md-5 col-sm-5 col-5">
             <h5 class="text-light">Fecha: </h5>
             <h5 style="color:beige;"><fmt:formatDate pattern="dd-MM-yyyy" value="${infoNoticia.fechanoticia}"/></h5>
          </div>
          <div class="col-lg-5 col-md-5 col-sm-5 col-5">
             <h5 class="text-light">Autor: </h5>
             <h5 style="color:beige;">&copy ${infoNoticia.autor}</h5>
          </div>
       </div>
       <hr style="height:10px;"/>
       <a href="${pageContext.request.contextPath}/ControllerUsuario?keyword=IrAlHome" class="btn btn-danger mt-3">Volver al home</a>
      </div>
   </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>