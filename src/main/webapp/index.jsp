<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleindex.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarExtensionArchivo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarRegistro.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validarTamanoFotoPerfilRegistro.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="contenedor" style="background: url('${pageContext.request.contextPath}/resources/img/ayuntamiento.jpg') no-repeat center center fixed;
background-size: cover;">
   <div class="container">
    <div class="col-lg-5 col-md-8 col-sm-11 col-11">
     <c:if test="${errorRegistro != null}">
        <h1 class="bg-danger p-2 text-light">${errorRegistro}</h1>
     </c:if>
     <c:if test="${errorlogin != null}">
        <h1 class="bg-danger p-2 text-light">${errorlogin}</h1>
     </c:if>
     <form action="ControllerUsuario" class="bg-light p-3" method="post">
        <h2>Formulario de Login</h2>
        <div class="form-group">
           <label>Nombre: </label>
           <input type="text" name="nombreusuario" 
           class="form-control" value="${nameusuario}" required/>
        </div>
        <div class="form-group mt-3">
           <label>Contraseña: </label>
           <input type="password" name="claveusuario" 
           class="form-control" value="${passusuario}" required/>
        </div> 
        <div class="row mt-3">
          <input type="submit" class="btn btn-success"
          name="keyword" value="Acceder"/>
          <button type="button" class="btn btn-dark mt-2" data-bs-toggle="modal" 
          data-bs-target="#modalregistro">Registrarse</button>
        </div>
     </form>
    </div>
   </div>
  
  <div class="modal fade" id="modalregistro" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" style="max-width: 780px;">
    <div class="modal-content">
     <form action="ControllerUsuario" method="post" enctype="multipart/form-data">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Registrese con sus datos</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="row justify-content-between g-3">
          <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
            <label>Dni: </label>
            <input type="text" name="dniuser" id="textoDni"
            class="form-control" required/>
            <p id="errorDni" class="text-danger">Los 8 primeros carácteres son números y después una letra mayúscula (deben ir juntos).</p>
          </div>
          <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
            <label>Nombre: </label>
            <input type="text" name="nombreuser" id="textoNombre"
            class="form-control" required/>
            <p id="errorNombre" class="text-danger">Solo carácteres alfabéticos entre 4 y 20 carácteres (se permiten acentos y espacios en blanco).</p>
          </div>
        </div>
        <div class="row justify-content-between mt-2 g-3">
           <div class="form-group col-lg-12 col-md-12 col-sm-12 col-12">
              <label>Email: </label>
              <input type="text" name="mailuser" id="textoEmail"
              class="form-control" required/>
              <p id="errorEmail" class="text-danger">Debe tener entre 10 y 30 caracteres alfabéticos y opcionalmente numéricos, incluyendo el @ y cualquiera de estos tipos de dominios: .com .org .es .edu</p>
           </div>
        </div>
        <div class="row justify-content-between mt-2 g-3">
           <div class="form-group col-lg-5 col-md-5 col-sm-12 col-12">
              <label>Clave: </label>
              <input type="password" name="claveuser" id="textoClave"
              class="form-control" required/>
              <p id="errorClave" class="text-danger">La contraseña debe tener entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula, al menos una mayúscula y al menos un caracter no alfanumérico.</p>
           </div>
           <div class="form-group col-lg-7 col-md-7 col-sm-12 col-12">
              <label>Dirección: </label>
              <input type="text" name="direccionuser" id="textoDireccion" 
              class="form-control" required/>
              <p id="errorDireccion" class="text-danger">Debe tener una longitud entre 8 y 30 carácteres de cualquier tipo.</p>
           </div>
        </div>
        <div class="row justify-content-between mt-2 g-3">
           <div class="form-group col-12">
              <label>Foto perfil: </label>
              <input type="file" onchange="return fileValidation()"
              name="fotouser" class="file-select" id="lafoto"/>
              <p id="errorFotoperfil" class="text-danger mt-2">La foto de perfil es superior a 1MB, adjunte otra menos pesada.</p>
              <p id="errorExtension" class="text-danger mt-2">El archivo seleccionado solo puede ser una imagen con las extensiones: .jpeg, .jpg, .png o .gif.</p>
           </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
        <input type="submit" name="keyword" id="btnRegistro"
        class="btn btn-primary" value="Registrarse"/>
      </div>
     </form>
    </div>
  </div>
</div>
   
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>   
</body>
</html>