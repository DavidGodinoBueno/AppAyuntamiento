<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylebuscarNoticiasEditDel.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
     <div class="container mt-5">
       <c:if test="${confdelservicios != null}">
           <h2 class="bg-success text-light p-2 col-lg-6 col-md-6 col-sm-12 col-12">
           ${confdelservicios}</h2>
       </c:if>
       <div class="framenoticias col-lg-10 col-md-10 col-sm-12 col-12" 
       style="border-radius:10px;padding:10px;">
          <h1 class="text-light">
	  <img alt="" src="${pageContext.request.contextPath}/resources/img/icoajustes.png"
            width="70px" height="70px"/>
	  Gestionar Servicios Públicos</h1>
          <form action="ControllerAdmin" method="post"
          class="col-lg-5 col-md-5 col-sm-12 col-12">
             <div class="row justify-content-between mt-3 g-2">
                <label class="text-light">Escribe parte del nombre sobre categorias de servicios: </label>
               <div class="col-lg-9 col-md-9 col-sm-12 col-12">
                  <input type="text" name="trozodescat"
                class="form-control" value="${patroncat}"/>
               </div>
               <div class="col-lg-3 col-md-3">
                  <input type="hidden" name="keyword" value="BuscarCategoriasServicios"/>
                  <input type="submit" class="btn btn-primary col-sm-12 col-12"
                value="Buscar"/>
               </div>
             </div>     
          </form>
          <br>
          <c:choose>
            <c:when test="${not empty lascategorias}">
               <table class="table table-striped table-hover table-bordered bg-light w-75">
                  <tr class="bg-dark">
                    <th colspan="3"><h2 class="text-center text-light">
                    Categorías de Servicios</h2></th>
                  </tr>
                  <tr>
                    <th>DESCRIPCIÓN</th>
                    <th>FOTO</th>
                    <th>VER SERVICIOS</th>
                  </tr>
                  <c:forEach items="${lascategorias}" var="categoria">
                     <tr>
                       <td>${categoria.descripcion}</td>
                       <td class="text-center"><img alt="" src="${pageContext.request.contextPath}/ControllerUsuario?elidcat=${categoria.idcategoria}&keyword=verfotoscatservicios"
                       width="90px" height="90px"/></td>
                       <td><a href="${pageContext.request.contextPath}/ControllerAdmin?theidcat=${categoria.idcategoria}&trozocat=${patroncat}&keyword=listarServiciosEditDelete" 
                       class="btn btn-info">Ver Servicios</a></td>
                     </tr>
                  </c:forEach>
               </table>
            </c:when>
            <c:when test="${lascategorias != null && lascategorias.isEmpty()}">
                <h1 class="text-light mt-5">No hay categorias coincidentes.</h1>
            </c:when>
          </c:choose>
          <br>
          <form action="ControllerAdmin" method="post">
          <c:choose>
             <c:when test="${not empty serviciosmanager}">
                <table class="table table-striped table-hover table-bordered bg-light w-75">
                   <tr class="bg-dark">
                     <th colspan="4">
                     <h3 class="text-light text-center">
                       <img alt="" src="${pageContext.request.contextPath}/resources/img/icoservices.png"
            width="50px" height="50px"/>
                       Servicios Editar/Eliminar sobre ${thiscategory.descripcion}
                     </h3></th>
                   </tr>
                   <tr>
                     <th>ELIMINAR</th>
                     <th>NOMBRE DEL SERVICIO</th>
                     <th>IMAGEN</th>
                     <th>EDITAR</th>
                   </tr>
                   <c:forEach items="${serviciosmanager}" var="servicio">
                      <tr>
                        <td><input type="checkbox" name="servicesdelete" 
                        value="${servicio.idservicio}"/></td>
                        <td>${servicio.nombre}</td>
                        <td class="text-center">
                        <img alt="" src="${pageContext.request.contextPath}/ControllerUsuario?elidservicio=${servicio.idservicio}&keyword=verfotosServicios"
                        width="100px" height="100px" style="border-radius:10px;"/></td>
                        <td><a href="${pageContext.request.contextPath}/ControllerAdmin?theidservice=${servicio.idservicio}&keyword=ServicioAEditar" 
                        class="btn btn-warning">
                        <img alt="" src="${pageContext.request.contextPath}/resources/img/icoeditar.png"
                          width="30px" height="30px"></a></td>
                      </tr>
                   </c:forEach>
                   <tr>
                     <td colspan="4">
                     <input type="hidden" name="inicialescat" value="${patroncat}"/>
                     <input type="hidden" name="esteidcat" value="${idcat}"/>
                     <input type="hidden" name="keyword" value="EliminarServicios"/>
                     <input type="submit" class="btn btn-danger"
                     value="Eliminar Servicios"/></td>
                   </tr>
                </table>
             </c:when>
             <c:when test="${serviciosmanager != null && serviciosmanager.isEmpty()}">
                 <h1 class="text-light">No hay servicios públicos disponibles.</h1>
             </c:when>
          </c:choose>
          </form>
       </div>
     </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>