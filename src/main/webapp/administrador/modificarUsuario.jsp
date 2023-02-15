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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarModificarUsuario.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoFotoPerfilModificar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sincronizarCambioFotoUsuario.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
    <%@include file="../WEB-INF/includes/navegacion.jspf" %>
    <div class="container mt-5">
       <c:if test="${confirmaredicion != null}">
        <h2 id="usuariomodificado" class="bg-success text-light p-2 col-lg-4 col-md-4 col-sm-12 col-12">${confirmaredicion}</h2>
       </c:if>
       <form action="ControllerAdmin" class="bg-light p-4 col-lg-8 col-md-10 col-sm-11 col-11"
       method="post" enctype="multipart/form-data" style="border-radius:10px;">
           <div class="row justify-content-between g-3">
             <h2 class="text-center">Modificar Usuario</h2>
             <input type="hidden" name="iduser" value="${usuarioeditar.idusuario}"/>
             <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                <label>Dni: </label>
                <input type="text" name="dniuser" id="textoDni"
                class="form-control" value="${usuarioeditar.dni}" required/>
                <p id="errorDni" class="text-danger">Los 8 primeros carácteres son números y después una letra mayúscula (deben ir juntos).</p>
             </div>
             <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                <label>Nombre: </label>
                <input type="text" name="nombreuser" id="textoNombre"
                class="form-control" value="${usuarioeditar.nombre}" required/>
                <p id="errorNombre" class="text-danger">Solo carácteres alfabéticos entre 4 y 20 carácteres (se permiten acentos y espacios en blanco).</p>
             </div>
           </div>
           <div class="row justify-content-between mt-3 g-3">
             <div class="form-group mt-5 col-lg-5 col-md-5 col-sm-12 col-12">
                 <label>Rol: </label>
                 <select class="form-select" name="roluser">
                    <c:forEach items="${rolesusuario}" var="elrol">
                       <option value="${elrol.rol}"
                         <c:if test="${elrol.rol == usuarioeditar.rol}">
                           <c:out value=" selected"></c:out>
                         </c:if>
                       >${elrol.rol}</option>
                    </c:forEach>
                 </select>
              </div>   
              <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
                  <h4>FOTO PERFIL</h4>
                 <img alt="" src="${pageContext.request.contextPath}/ControllerUsuario?theuser=${usuarioeditar.nombre}&keyword=mostrarfotoperfil"
                 width="120px" height="120px" id="laimagen" style="border-radius:100%;">
                 <input type="file" name="fotoperfil" id="elperfil"
                 class="file-select mt-2" accept="image/gif,image/jpeg,image/jpg,image/png"
                 required/>
                 <p id="errorFotoperfil" class="text-danger mt-2">La foto de perfil es superior a 1MB, adjunte otra menos pesada.</p>
              </div>
           </div>
           <div class="row justify-content-between mt-2 g-3">
              <div class="form-group col-lg-10 col-md-10 col-sm-12 col-12">
                 <label>Correo:</label>
                 <div class="input-group">
                       <span class="input-group-text">@</span>
                       <input type="text" name="correouser" id="textoEmail"
                       class="form-control" value="${usuarioeditar.correo}" required/>
                 </div>
                 <p id="errorEmail" class="text-danger">Debe tener entre 10 y 30 caracteres alfabéticos y opcionalmente numéricos, incluyendo el @ y cualquiera de estos tipos de dominios: .com .org .es .edu</p>
              </div>
           </div>
           <div class="row justify-content-between mt-3 g-3">
              <div class="form-group col-lg-4 col-md-4 col-sm-12 col-12">
                <label>Contraseña: </label>
                <input type="text" name="claveuser" id="textoClave"
                class="form-control" value="${usuarioeditar.clave}" required/>
                <p id="errorClave" class="text-danger">La contraseña debe tener entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula, al menos una mayúscula y al menos un caracter no alfanumérico.</p>
              </div>
              <div class="form-group col-lg-7 col-md-7 col-sm-12 col-12">
                <label>Dirección: </label>
                <input type="text" name="direccionuser" id="textoDireccion"
                class="form-control" value="${usuarioeditar.direccion}" required/>
                <p id="errorDireccion" class="text-danger">Debe tener una longitud entre 8 y 30 carácteres de cualquier tipo.</p>
              </div>
           </div>
           <div class="row justify-content-around g-3 mt-3">
                <input type="hidden" name="keyword" value="ModificarUsuario"/>
                   <input type="submit" class="btn btn-warning col-lg-4 col-md-4 col-sm-12 col-12" 
                  id="btnModificar" value="Modificar usuario">
                
                   <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAListaUsuarios" 
                   class="btn btn-danger col-lg-4 col-md-4 col-sm-12 col-12">Cancelar</a>

           </div>
       </form>
    </div>
    <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>