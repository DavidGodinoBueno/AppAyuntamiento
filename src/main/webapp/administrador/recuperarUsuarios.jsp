<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylemodificarusuario.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
    <%@include file="../WEB-INF/includes/navegacion.jspf" %>
       <div class="container mt-5">
          <c:if test="${confrecuser != null}">
             <h2 class="bg-success p-2 text-light col-lg-6 col-md-6 col-sm-12 col-12">
             ${confrecuser}</h2>
          </c:if>
          <c:if test="${not empty usuariosbaja}">
          <form class="bg-light p-3 col-lg-9 col-md-9 col-sm-12 col-12"
          style="border-radius:10px;">
             <h1 class="text-center">
             <img alt="" src="${pageContext.request.contextPath}/resources/img/icouser.png"
             width="80px" height="80px">
             Recuperar Usuarios Eliminados (de uno en uno)</h1>
             <div class="row justify-content-between mt-2 g-2">
                <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                   <label>Usuarios del Ayuntamiento de alta: </label>
                   <select multiple class="form-select" 
                   disabled style="height:300px;">
                      <c:forEach items="${usuariosalta}" var="usuario">
                         <option>${usuario.nombre}</option>
                      </c:forEach>
                   </select>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-12 col-12 mt-5">
                    <br><br><br><br><br>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="hidden" name="keyword" value="RecupUsuarios"/>
                    <input title="boton enviar" alt="boton enviar" 
                    src="${pageContext.request.contextPath}/resources/img/flechaizquierda.png" 
                    type="image" style="width:90px; height:90px;" class="btn btn-success"/>
                </div>
                <div class="col-lg-5 col-md-5 col-sm-12 col-12">
                   <label>Usuarios eliminados de Baja: </label>
                   <select name="userRecup" multiple class="form-select"
                   style="height:300px;">
                       <c:forEach items="${usuariosbaja}" var="usuario">
                          <option value="${usuario.idusuario}">
                          ${usuario.nombre}</option>
                       </c:forEach>
                   </select>
                </div>
             </div>
          </form>
         </c:if>
         <c:if test="${usuariosbaja != null && usuariosbaja.isEmpty()}">
            <h1 class="text-center text-light mt-5">No hay ningún usuario dado de baja.</h1>
         </c:if>
       </div>
    <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>