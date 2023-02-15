<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylecomentarios.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarComentario.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
    <%@include file="../WEB-INF/includes/navegacion.jspf" %>
    <div class="container mt-3">
       <div class="row g-3">
    <div class="bg-light col-lg-7 col-md-7 col-sm-12 col-12 p-2"
    style="border-radius:10px;">
     <h2><img alt="" src="${pageContext.request.contextPath}/resources/img/icocomentar.png"
            width="70px" height="70px"/>
         ¿Que piensas del ayuntamiento?</h2>
      <div class="row justify-content-between">
         <div class="col-lg-6 col-md-6 col-sm-12 col-12">
            <form action="ControllerUsuario" class="p-3" 
       method="post">
         <label>Escribe un comentario:</label><br>
         <input type="text" style="background-color: transparent;border:transparent;
         outline:none;border-bottom:solid black 1px;" 
         name="ladescripcion" id="textoDescripcion">
         <p id="errorDescripcion" class="text-danger w-75 elerror">Debe tener entre 1 y 100 carácteres de cualquier tipo excepto saltos de línea (se permiten espacios en blanco y acentos).</p>
         <input type="hidden" name="elorden" value="${criterioorden}"/>
         <input type="submit" class="btn btn-primary mt-2" id="btnComentar"
         name="keyword" value="Comentar"/>
      </form>
         </div>
         <div class="col-lg-6 col-md-6 col-sm-12 col-12">
            <form action="ControllerUsuario" class="bg-light p-3" 
       method="post">
         <label>Ordenar comentarios por: </label><br>
         <div class="row justify-content-between g-2">
            <div class="col-lg-8 col-md-12">
                 <select name="criterioComments" class="form-select col-lg-12 col-12">
            <option value="ASC">Más antiguos</option>
            <option value="DESC"
               <c:if test="${criterioorden == 'DESC'}">
                   <c:out value=" selected"></c:out>
               </c:if>
            >Más recientes</option>
         </select>
            </div>
            <div class="col-lg-4 col-md-12">
               <input type="hidden" name="keyword" value="OrdenarComentarios"/>
         <input type="submit" class="btn btn-danger col-sm-12 col-12"
         name="keyword" value="Ordenar"/>
            </div>
         </div>
      </form>
         </div>
      </div>
       <c:if test="${not empty listacomentarios}">
       <div class="contenedor bg-light p-3" style="border-bottom-left-radius: 10px;border-bottom-right-radius: 10px;">
        <c:forEach var="comentario" items="${listacomentarios}">
         <div class="row justify-content-start">
           <div class="col-lg-2 col-md-3 col-sm-3 col-3">
             <img src="${pageContext.request.contextPath}/ControllerUsuario?theuser=${comentario.nombreusuario}&keyword=mostrarfotoperfil" 
            width="80px" height="80px" style="border-radius:100px;">
           </div>
           <div class="col-lg-2 col-md-3 col-sm-3 col-3 mt-4">
             <strong>&nbsp;&nbsp;${comentario.nombreusuario}</strong>
           </div>
         </div>
         <p style="font-size:20px;">${comentario.descripcion}</p>
         <div class="row justify-content-between">
            <div class="col-lg-4 col-md-4 col-sm-4 col-4">
               <c:if test="${comentario.nombreusuario == nombreusuario}">
                <a href="${pageContext.request.contextPath}/ControllerUsuario?ordencomment=${criterioorden}&elidcomentario=${comentario.idcomentario}&keyword=EliminarComentario" 
                class="text-danger me-2"><strong>Eliminar</strong></a>
               </c:if>
            </div>
            <div class="col-lg-4 col-md-5 col-sm-4 col-4">
                <strong class="text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${comentario.fechacomentario}"/></strong>
            </div>
         </div>
         <hr/>
        </c:forEach>
      </div>
      </c:if>
      <c:if test="${listacomentarios != null && listacomentarios.isEmpty()}">
          <h1 class="text-light">
             <c:out value="No hay comentarios disponibles"></c:out>
          </h1>
      </c:if>
    </div>
  </div>
   </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>   
</body>
</html>