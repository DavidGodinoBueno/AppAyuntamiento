<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylemodificarnoticia.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarModificarNoticia.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoFotoEditNoticia.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sincronizarCambioFotoNoticia.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
   <div class="container mt-5">
       <c:if test="${confnoticiaedit != null}">
          <h2 id="notedit" class="bg-success text-light p-2 col-lg-6 col-md-6 col-sm-12 col-12">${confnoticiaedit}</h2>
       </c:if>
       <form action="ControllerAdmin" class="bg-light p-3 col-lg-8 col-md-8 col-sm-12 col-12"
       method="post" enctype="multipart/form-data" style="border-radius:10px;">
           <h2 class="text-center">
           <img alt="" src="${pageContext.request.contextPath}/resources/img/iconoticia.png"
            width="60px" height="60px"/>
           Modificar Noticia</h2>
           <input type="hidden" name="elidnoticia" value="${noticiaedit.idnoticia}"/>
           <div class="row justify-content-between mt-1 g-3">
             <div class="form-group col-lg-9 col-md-9 col-sm-12 col-12">
                <label>Titulo: </label>
                <input type="text" name="eltitulo" id="textoTitulo" 
                class="form-control" value="${noticiaedit.titulo}"/>
                <p id="errorTitulo" class="text-danger">Debe tener entre 10 y 80 carácteres alfabéticos y también numéricos (se permiten espacios en blanco, acentos).</p>
             </div>
           </div>
           <div class="row justify-content-between mt-2 g-3">
              <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                  <label>Descripción: </label>
                  <textarea rows="7" cols="10" name="ladescripcion" id="textoDescripcion"
                  class="form-control">${noticiaedit.descripcion}</textarea>
                  <p id="errorDescripcion" class="text-danger">Debe tener entre 20 y 500 carácteres de cualquier tipo excepto saltos de línea.</p>
              </div>
           <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
           <div class="input-group row mt-3">
           <div class="input-group-prepend">
               <div class="input-group-text justify-content-center">FOTO NOTICIA</div>
               <div class="border p-2 text-center" style="border-radius:10px;">
                 <img id="laimagen" src="${pageContext.request.contextPath}/ControllerUsuario?idfotonoticia=${noticiaedit.idnoticia}&keyword=fotosnoticias" 
                width="170px" height="150px" style="border-radius:5px;"/>
               </div>          
             </div>
           <input type="file" class="file-select mt-2" id="lafoto"
             name="photonoticia" accept="image/gif,image/jpeg,image/jpg,image/png"
            required/>
            <p id="errorFoto" class="text-danger mt-2">La imagen es superior a 1MB, adjunte una menos pesada.</p>
           </div>
           </div>
           </div>
           <div class="row justify-content-between mt-3 g-3">
              <div class="form-group col-lg-8 col-md-8 col-sm-12 col-12">
                  <label>Fecha noticia: </label>
                  <input type="date" class="form-control" name="lafechanoticia"
                  value="${noticiaedit.fechanoticia}"/>
              </div>
           </div>
           <div class="row justify-content-around mt-5 g-2">
              <input type="hidden" name="keyword" value="ModificarNoticia"/>
              <input type="submit" id="btnEditNoticia"
              class="btn btn-warning col-lg-4 col-md-4 col-sm-12 col-12" value="Modificar noticia"/>
              <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAGestionarNoticias" class="btn btn-danger col-lg-4 col-md-4 col-sm-12 col-12">Cancelar</a>
           </div>
       </form>
   </div>
  
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>