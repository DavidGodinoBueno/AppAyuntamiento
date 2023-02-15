<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylealtanoticia.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
    <%@include file="../WEB-INF/includes/navegacion.jspf" %>
       <div class="container mt-5">
         <c:if test="${confaltacat != null}">
            <h2 class="bg-success p-2 text-light col-lg-7 col-md-7 col-sm-12 col-12">
            ${confaltacat}</h2>
         </c:if>
         <c:if test="${errorCategoria != null}">
            <h2 class="bg-danger p-2 text-light col-lg-7 col-md-7 col-sm-12 col-12">
            ${errorCategoria}</h2>
         </c:if>
         <form action="ControllerAdmin" class="bg-light p-3 col-lg-7 col-md-7 col-sm-12 col-12" 
         method="post" style="border-radius:10px;" enctype="multipart/form-data">
            <h1 class="text-center">
              <img alt="" src="${pageContext.request.contextPath}/resources/img/icocatego.png"
              width="80px" height="80px">
              Nueva Categoria de Servicios
            </h1>
            <div class="row justify-content-between mt-3 g-2">
               <div class="col-lg-8 col-md-8 col-sm-12 col-12">
                   <label>Descripción: </label>
                  <input type="text" name="descategoria"
                  class="form-control" value="${descat}" required/>
               </div>
            </div>
            <div class="row justify-content-between mt-3 g-2">
               <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                  <label>Foto categoría: </label>
                  <input type="file" name="fotocategoria" 
                  class="file-select" required/>
               </div>
            </div>
            <div class="row justify-content-around mt-5 g-2">
               <hr>
               <div class="col-lg-4 col-md-4">
                  <input type="hidden" name="keyword" value="AltaCategoria"/>
                  <input type="submit" class="btn btn-success col-sm-12 col-12" 
                  value="Agregar Categoria"/>
               </div>
               <div class="col-lg-4 col-md-4">
                  <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAGestionServicios" 
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