<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylebuscarNoticiasEditDel.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
</head>
<body class="elbody">
   <%@include file="../WEB-INF/includes/navegacion.jspf" %>
     <div class="container mt-5">
        <div class="framenoticias p-3" style="border-radius:10px;">
           <h1 class="text-light">
	  <img alt="" src="${pageContext.request.contextPath}/resources/img/icoajustes.png"
            width="70px" height="70px"/>
	  Gestionar Fiestas</h1>
	   <div class="row justify-content-between g-2">
	      <div class="col-lg-3 col-md-5 col-sm-12 col-12">
	         <a href="${pageContext.request.contextPath}/ControllerAdmin?keyword=IrAltaFiesta" 
      class="btn btn-lg btn-light text-success">
      <img alt="" src="${pageContext.request.contextPath}/resources/img/icoadd.png"
            width="20px" height="20px"/>
      Nueva fiesta</a>
	      </div>
	      <div class="col-lg-5 col-md-5 col-sm-12 col-12">
	          <form action="ControllerAdmin" class="col-lg-8 col-md-10 col-sm-12 col-12" 
	        method="post">
	     <div class="row justify-content-between g-2">
	        <div class="col-lg-9 col-md-8">
	           <input type="text" placeholder="Buscar nombre fiestas" 
	         class="form-control col-sm-12 col-12" name="titnoticias"/>
	        </div>
	        <div class="col-lg-3 col-md-4">
	           <input type="hidden" name="keyword" value="BuscarFiestasEditDelete"/>
	         <input type="submit" class="btn btn-primary col-sm-12 col-12" value="Buscar"/>
	        </div>
	       </div>
	     </form>
	      </div>
	   </div>
	   <br/>
        </div>
     </div>
   <%@include file="../WEB-INF/includes/piepagina.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script> 
</body>
</html>