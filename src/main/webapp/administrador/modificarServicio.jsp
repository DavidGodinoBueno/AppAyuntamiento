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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarModificarServicio.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoFotoEditServicio.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sincronizarCambioFotoNoticia.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
     <%@include file="../WEB-INF/includes/navegacion.jspf" %>
       <div class="container mt-5">
          <c:if test="${confmodservice != null}">
             <h2 class="bg-success p-2 text-light col-lg-6 col-md-6 col-sm-12 col-12">
             ${confmodservice}</h2>
          </c:if>
          <form action="ControllerAdmin" class="bg-light p-3 col-lg-8 col-md-8 col-sm-12 col-12"
       method="post" enctype="multipart/form-data" style="border-radius:10px;">
           <h2 class="text-center">
           <img alt="" src="${pageContext.request.contextPath}/resources/img/icoservices.png"
            width="60px" height="60px"/>
           Modificar Servicio Público</h2>
           <input type="hidden" name="elidservicio" value="${servicioedit.idservicio}"/>
           <div class="row justify-content-between mt-1 g-3">
             <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                <label>Nombre: </label>
                <input type="text" name="nameservice" id="textoNombre" 
                class="form-control" value="${servicioedit.nombre}"/>
                <p id="errorNombre" class="text-danger">Debe tener entre 5 y 70 caracteres (se permiten acentos, espacios en blanco y los caracteres : " , / .).</p>
             </div>
             <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                  <label>Elegir categoría: </label>
                  <select name="categservice" class="form-select">
                     <c:forEach items="${listadocategorias}" var="categoria">
                        <option value="${categoria.idcategoria}"
                           <c:if test="${categoria.idcategoria == servicioedit.idcategoria}">
                              <c:out value=" selected"></c:out>
                           </c:if>
                        >
                        ${categoria.descripcion}</option>
                     </c:forEach>
                  </select>
              </div>
           </div>
           <div class="row justify-content-between mt-2 g-3">
              <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                  <label>Información relativa: </label>
                  <textarea rows="7" cols="10" name="infoservice"
                  class="form-control">${servicioedit.informacion}</textarea>
              </div>
           <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
           <div class="input-group row mt-3">
           <div class="input-group-prepend">
               <div class="input-group-text justify-content-center">IMAGEN SERVICIO</div>
               <div class="border p-2 text-center" style="border-radius:10px;">
                 <img id="laimagen" src="${pageContext.request.contextPath}/ControllerUsuario?elidservicio=${servicioedit.idservicio}&keyword=verfotosServicios" 
                width="170px" height="150px" style="border-radius:5px;"/>
               </div>          
             </div>
           <input type="file" class="file-select mt-2" id="lafoto"
             name="photoservice" accept="image/gif,image/jpeg,image/jpg,image/png"
            required/>
            <p id="errorFoto" class="text-danger mt-2">La imagen es superior a 1MB, adjunte otra menos pesada.</p>
           </div>
           </div>
           </div>
           <div class="row justify-content-around mt-5 g-2">
              <input type="hidden" name="keyword" value="ModificarServicio"/>
              <input type="submit" id="btnEditServicio"
              class="btn btn-warning col-lg-4 col-md-4 col-sm-12 col-12" value="Modificar servicio"/>
              <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAEditDeleteServicios" class="btn btn-danger col-lg-4 col-md-4 col-sm-12 col-12">Cancelar</a>
           </div>
       </form>
       </div>
     <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>