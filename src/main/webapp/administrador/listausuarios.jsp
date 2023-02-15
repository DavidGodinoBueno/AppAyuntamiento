<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/temporizadores.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styleusuarios.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
<script type="text/javascript">
     function mensaje(ellink) {
             var operacion = ellink.getAttribute("href");
    	    var opcion = confirm("¿Estas seguro que quieres eliminar al usuario?");
    	    if (opcion == true) {
        	    ellink.setAttribute("href", operacion);
    		} else {
        		setTimeout(function() {
        			ellink.setAttribute("href", operacion);
            	}, 2000);
        		ellink.setAttribute("href", "#");
    		}
     }
</script>
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
   <div class="container mt-3">
      <c:if test="${confirmardeluser != null}">
          <h2 id="deleteuser" class="bg-success p-2 text-light col-lg-5 col-md-5 col-sm-12 col-12">${confirmardeluser}</h2>
      </c:if>
      <c:choose>
        <c:when test="${not empty listausuarios}">
               <table id="tablausuarios" class="table table-striped table-hover table-bordered bg-light">
                  <thead>
                    <tr> 
                      <th colspan="7" class="bg-danger text-light text-center">
                      <h2>
                      <img alt="" src="${pageContext.request.contextPath}/resources/img/icouser.png"
                      width="60px" height="60px">
                      USUARIOS DEL AYUNTAMIENTO</h2></th>
                    </tr>
                    <tr class="bg-dark text-light text-center"> 
                      <th>DNI</th>
                      <th>NOMBRE</th>
                      <th>CORREO</th>
                      <th>FOTOPERFIL</th>
                      <th>DIRECCIÓN</th>
                      <th>ROL</th>
                      <th>ACCIONES</th>
                    </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${listausuarios}" var="usuario">
                        <tr> 
                          <td>${usuario.dni}</td>
                          <td>${usuario.nombre}</td>
                          <td>${usuario.correo}</td>
                          <td class="text-center"><img alt="" src="${pageContext.request.contextPath}/ControllerUsuario?theuser=${usuario.nombre}&keyword=mostrarfotoperfil"
                          width="90px" height="90px" style="border-radius: 100%;"></td>
                          <td>${usuario.direccion}</td>
                          <td>${usuario.rol}</td>
                          <td><a href="${pageContext.request.contextPath}/ControllerAdmin?eliduser=${usuario.idusuario}&keyword=EditarUsuario" class="btn btn-warning">
                          <img alt="" src="${pageContext.request.contextPath}/resources/img/icoeditar.png"
                          width="30px" height="30px">
                          </a>
                          <a class="btn btn-danger" href="${pageContext.request.contextPath}/ControllerAdmin?delthisuser=${usuario.nombre}&pageBeforeDelete=${firstRow}&keyword=EliminarUsuario"
                          onclick="mensaje(this)">
                          <img alt="" src="${pageContext.request.contextPath}/resources/img/cubobasura.png"
                          width="30px" height="30px">
                          </a>
                          </td>
                       </tr>
                     </c:forEach>
                  </tbody>
               </table>
               <h2 class="text-light">
                 <c:out value="Mostrando de ${firstRow+1} a ${lastRow} sobre ${totalusers} usuarios."/>
               </h2>
               <br>
        <div class="row g-2 col-6 justify-content-start">
           <div class="col-lg-4 col-md-4">
             <a href="${pageContext.request.contextPath}/ControllerAdmin?thefirstRow=${firstRow-rowsByPage}&keyword=PaginaAnteriorUsers" 
             class="btn btn-primary col-sm-12 col-12">Anterior</a>
           </div>
           <div class="col-lg-4 col-md-4">
            <a href="${pageContext.request.contextPath}/ControllerAdmin?firstRow=${firstRow=firstRow+rowsByPage}&keyword=PaginaSiguienteUsers" 
            class="btn btn-primary col-sm-12 col-12">Siguiente</a>
        </div>
            </div>
        </c:when>
      </c:choose>
   </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
   
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 

</body>
</html>