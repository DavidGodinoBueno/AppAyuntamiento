<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylealtanoticia.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarExtensionArchivo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarAltaServicio.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoFotoNuevoServicio.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
    <%@include file="../WEB-INF/includes/navegacion.jspf" %>
        <div class="container mt-5">
          <c:if test="${errorServicio != null}">
             <h2 class="bg-danger text-light p-2 col-lg-6 col-md-6 col-sm-12 col-12">
                ${errorServicio}
             </h2>
          </c:if>
          <c:if test="${confaltaservicio != null}">
             <h2 class="bg-success text-light p-2 col-lg-6 col-md-6 col-sm-12 col-12">${confaltaservicio}</h2>
          </c:if>
          <form action="ControllerAdmin" class="bg-light p-3 col-lg-8 col-md-8 col-sm-12 col-12"
          method="post" enctype="multipart/form-data" style="border-radius:10px;">
             <h1 class="text-center">
             <img alt="" src="${pageContext.request.contextPath}/resources/img/icoservices.png"
            width="70px" height="70px"/>
             Nuevo Servicio Público</h1>
             <div class="row justify-content-between mt-2 g-3">
                <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                   <label>Nombre: </label>
                   <input type="text" name="nombreservicio" id="textoNombre"
                   class="form-control" value="${nameservicio}"/>
                   <p id="errorNombre" class="text-danger">Debe tener entre 5 y 70 caracteres (se permiten acentos, espacios en blanco y los caracteres : " , / .).</p>
                </div>
                <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                   <label>Elige categoría: </label>
                   <select name="categoriaservicio" class="form-select">
                      <c:forEach items="${listadocategorias}" var="categoria">
                         <option value="${categoria.idcategoria}"
                            <c:if test="${categoria.idcategoria == catservicio}">
                               <c:out value=" selected"></c:out>
                            </c:if>
                         >
                         ${categoria.descripcion}</option>
                      </c:forEach>
                   </select>
                </div>
             </div>
             <div class="row justify-content-between mt-3 g-3">
                <div class="col-lg-10 col-md-10 col-sm-12 col-12">
                   <label>Información adicional: </label>
                   <textarea rows="7" cols="10" name="infoservicio"
                   class="form-control">${inservicio}</textarea>
                </div>
             </div>
             <div class="row justify-content-between mt-3 g-3">
                <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                   <label>Foto Servicio Público: </label>
                   <input type="file" name="fotoservicio" id="lafoto" class="file-select"
                   onchange="return fileValidation()" required/>
                   <p id="errorFoto" class="text-danger mt-2">La foto es superior a 1MB, adjunte otra menos pesada.</p>
                   <p id="errorExtension" class="text-danger mt-2">El archivo seleccionado solo puede ser una imagen con las extensiones: .jpeg, .jpg, .png o .gif.</p>
                </div>
             </div>
             <div class="row justify-content-around mt-3 g-2">
                <div class="col-lg-4 col-md-4">
                   <input type="hidden" name="keyword" value="AgregarServicio"/>
                   <input type="submit" class="btn btn-success col-sm-12 col-12" 
                   id="btnAltaService" value="Agregar"/>
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