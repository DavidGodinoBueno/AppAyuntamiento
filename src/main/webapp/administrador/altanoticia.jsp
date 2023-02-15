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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarAltaNoticia.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoFotoNuevaNoticia.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
   <div class="container mt-5">
       <c:if test="${confirmaltanoticia != null}">
          <h2 id="newNoticia" class="text-light bg-success p-2 col-lg-6 col-md-6 col-sm-12 col-12">${confirmaltanoticia}</h2>
       </c:if>
       <c:if test="${errorNoticia != null}">
          <h2 class="bg-danger p-2 text-light col-lg-6 col-md-6 col-sm-12 col-12">${errorNoticia}</h2>
       </c:if>
      <form action="ControllerAdmin" class="bg-light p-3 col-lg-8 col-md-8 col-sm-12 col-12"
      method="post" enctype="multipart/form-data" style="border-radius: 10px;">
         <h1 class="text-center">
          <img alt="" src="${pageContext.request.contextPath}/resources/img/iconoticia.png"
            width="70px" height="70px"/>
         Nueva noticia</h1>
         <div class="row justify-content-between mt-2 g-3">
            <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
               <label>Título: </label>
               <input type="text" name="titulonoticia" id="textoTitulo"
               class="form-control" value="${titnoticia}" required/>
               <p id="errorTitulo" class="text-danger">Debe tener entre 10 y 80 carácteres alfabéticos y también numéricos (se permiten espacios en blanco, acentos).</p>
            </div>
            <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
               <label>Fecha: </label>
               <input type="date" name="fechanoticia"
               class="form-control" value="${fecnoticia}" required/>
            </div>
         </div>
         <div class="row justify-content-between mt-3 g-3">
            <div class="form-group col-lg-9 col-md-9 col-sm-12 col-12">
               <label>Descripción: </label>
               <textarea rows="5" cols="12" id="textoDescripcion" placeholder="Ha ocurrido..."
               class="form-control" name="descripcionNoticia" required>${desnoticia}</textarea>
               <p id="errorDescripcion" class="text-danger">Debe tener entre 20 y 500 carácteres de cualquier tipo excepto saltos de línea.</p>
            </div>
         </div>
         <div class="row justify-content-between mt-3 g-3">
             <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                <label>Foto noticia: </label>
                <input type="file" class="file-select" name="fotonoticia"
                id="lafoto" onchange="return fileValidation()" required/>
                <p id="errorFoto" class="text-danger mt-2">La imagen es superior a 1MB, adjunte una menos pesada.</p>
                <p id="errorExtension" class="text-danger mt-2">El archivo seleccionado solo puede ser una imagen con las extensiones: .jpeg, .jpg, .png o .gif.</p>
             </div>
         </div>
         <input type="hidden" name="autornoticia" value="${nombreusuario}"/>
         <input type="hidden" name="keyword" value="AgregarNoticia"/>
         <div class="row justify-content-center">
             <input type="submit" id="btnNuevaNoticia" class="btn btn-success col-lg-6 col-md-6 col-sm-11 col-11 mt-5" 
         value="Agregar noticia"/>
         </div>
      </form>
   </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>