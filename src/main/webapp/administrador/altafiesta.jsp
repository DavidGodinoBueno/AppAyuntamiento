<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylealtanoticia.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarAltaFiesta.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoFotoFiesta.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarExtensionArchivo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
    <%@include file="../WEB-INF/includes/navegacion.jspf" %>
      <div class="container mt-5">
         <c:if test="${confaltafiesta != null}">
            <h2 id="altafiesta"
            class="bg-success p-2 text-light col-lg-6 col-md-6 col-sm-12 col-12">
            ${confaltafiesta}</h2>
         </c:if>
         <c:if test="${errorFiesta != null}">
            <h2 class="bg-danger p-2 text-light col-lg-6 col-md-6 col-sm-12 col-12">
               ${errorFiesta}
            </h2>
         </c:if>
         <form action="ControllerAdmin" method="post"
         class="bg-light p-3 col-lg-8 col-md-8 col-sm-12 col-12"
         enctype="multipart/form-data" style="border-radius:10px;">
              <h1 class="text-center">
                <img alt="" src="${pageContext.request.contextPath}/resources/img/icofiestas.png"
            width="80px" height="80px"/>
                Nueva Fiesta
              </h1>
              <div class="row justify-content-between mt-3 g-2">
                 <div class="col-lg-8 col-md-8 col-sm-12 col-12">
                    <label>Nombre: </label>
                    <input type="text" name="namefiesta" id="nombre"
                    class="form-control" value="${nombrefiesta}" required/>
                    <p id="errorNombre" class="text-danger">Debe tener entre 10 y 30 caracteres alfabéticos y opcionalmente numéricos (se permiten acentos y espacios en blanco.)</p>
                 </div>
              </div>
              <div class="row justify-content-between mt-3 g-2">
                  <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                      <label>Fecha que empieza: </label>
                      <input type="date" name="iniciofiesta"
                      class="form-control" value="${startfiesta}" required/>
                  </div>
                  <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                      <label>Fecha que termina: </label>
                      <input type="date" name="finfiesta"
                      class="form-control" value="${endfiesta}" required/>
                  </div>
              </div>
              <hr/>
              <div class="row justify-content-between mt-3 g-2">
                  <div class="col-lg-9 col-md-9 col-sm-12 col-12">
                     <label>Información relativa: </label>
                     <textarea rows="5" cols="10" name="infofiesta"
                     class="form-control">${informacionfiesta}</textarea>
                  </div>
              </div>
              <div class="row justify-content-between mt-3 g-2">
                 <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                    <label>Agrega una foto: </label>
                    <input type="file" name="fotofiesta"
                    class="file-select" id="lafoto" onchange="return fileValidation()"
                    required/>
                    <p id="errorFoto" class="text-danger mt-2">La imagen es superior a 1MB, adjunte una menos pesada.</p>
                    <p id="errorExtension" class="text-danger mt-2">El archivo seleccionado solo puede ser una imagen con las extensiones: .jpeg, .jpg, .png o .gif.</p>
                 </div>
              </div>
              <div class="row justify-content-around mt-5 g-2">
                 <div class="col-lg-4 col-md-4">
                     <input type="hidden" name="keyword" value="AgregarFiesta"/>
                     <input type="submit" id="btnAltaFiesta" class="btn btn-success col-sm-12 col-12"
                     value="Agregar fiesta"/>
                 </div>
                 <div class="col-lg-4 col-md-4">
                     <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAGestionarFiestas" 
                     class="btn btn-danger col-sm-12 col-12">
                     Cancelar</a>
                 </div>
              </div>
         </form>
      </div>
    <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>