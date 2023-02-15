<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylevistanoticias.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/relojDigital.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body style="background:linear-gradient(90deg, #cca466, #523c1b)">
     <%@include file="../WEB-INF/includes/navegacion.jspf" %>
       <div class="container mt-5">
          <div class="cuadronoticias p-3">
             <h1 class="text-light">
             <img alt="" src="${pageContext.request.contextPath}/resources/img/icofiestas.png"
            width="80px" height="80px"/>
             Fiestas de Torrijos</h1>
             <form action="ControllerUsuario" 
             class="col-lg-5 col-md-5 col-sm-12 col-12"
             method="post">
                <div class="row justify-content-between mt-2 g-2">
                  <label class="text-light">Buscar fiestas por mes del año: </label>
                   <div class="col-lg-8 col-md-8 col-sm-12 col-12">
                      <select name="mes" class="form-select">
                         <option value="01">Enero</option>
                         <option value="02"
                            <c:if test="${estemes == '02'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Febrero</option>
                         <option value="03"
                            <c:if test="${estemes == '03'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Marzo</option>
                         <option value="04"
                            <c:if test="${estemes == '04'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Abril</option>
                         <option value="05"
                            <c:if test="${estemes == '05'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Mayo</option>
                         <option value="06"
                            <c:if test="${estemes == '06'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Junio</option>
                         <option value="07"
                            <c:if test="${estemes == '07'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Julio</option>
                         <option value="08"
                             <c:if test="${estemes == '08'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Agosto</option>
                         <option value="09"
                             <c:if test="${estemes == '09'}">
                                 <c:out value=" selected"></c:out>
                             </c:if>
                         >Septiembre</option>
                         <option value="10"
                            <c:if test="${estemes == '10'}">
                               <c:out value=" selected"></c:out>
                            </c:if>
                         >Octubre</option>
                         <option value="11"
                            <c:if test="${estemes == '11'}">
                                <c:out value=" selected"></c:out>
                            </c:if>
                         >Noviembre</option>
                         <option value="12" <c:if test="${estemes == '12'}">
                               <c:out value=" selected"></c:out>
                           </c:if>
                         >Diciembre</option>
                      </select>
                   </div>
                   <div class="col-lg-4 col-md-4">
                       <input type="hidden" name="keyword" value="BuscarFiestas"/>
                       <input type="submit" class="btn btn-primary col-sm-12 col-12" value="Buscar"/>
                   </div>
                </div>
             </form>
             <form action="ControllerUsuario" method="post">
             <div class="row mt-1">
	         <c:if test="${not empty fiestasbymes}">
	          <c:forEach items="${fiestasbymes}" var="fiesta">
	           <div class="col-lg-3 col-md-5 col-sm-6 col-9 mt-4">
		       <div class="card p-1" style="height:430px; background-color:beige;">
		        <img class="card-img-top" src="${pageContext.request.contextPath}/ControllerUsuario?elidfiesta=${fiesta.idfiesta}&keyword=verfotosfiestas" 
		        width="180px" height="200px">
		        <div class="card-body">
		          <input type="hidden" name="idfiesta" value="${fiesta.idfiesta}"/>
		          <h3 class="card-title">${fiesta.nombre}</h3>
		          <p class="text-muted">Del <fmt:formatDate pattern="dd-MM-yyyy" value="${fiesta.fechainicio}"/> al 
		          <fmt:formatDate pattern="dd-MM-yyyy" value="${fiesta.fechafin}"/></p> 
		          <p class="text-muted">La fiesta dura: ${fiesta.rangodias} días.</p>
		          <div class="row g-1 mt-3">
		          <input type="hidden" name="keyword" value="InfoFiesta"/>
                  <input type="submit" class="btn btn-primary" value="Leer mas"
                  <c:if test="${fiesta.informacion.length() == 0 || fiesta.informacion == null}">
                       <c:out value=" disabled"></c:out>
                  </c:if>/>
		        </div>
		       </div>
		      </div>
		     </div>
	       </c:forEach>
	      </c:if>
	      <c:if test="${fiestasbymes != null && fiestasbymes.isEmpty()}">
	          <h1 class="text-center text-light mt-5">No hay fiestas disponibles.</h1>
	      </c:if>
	      <c:if test="${fiestasbymes == null}">
	          <h1 class="text-center text-light mt-5">Elige un mes para ver las fiestas.</h1>
	      </c:if>
	      </div>
	      </form>
          </div>
       </div>
     <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>