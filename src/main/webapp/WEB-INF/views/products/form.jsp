<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration products</title>
</head>
<body>


		<form:form method="post" action="/housecode/products" commandName="product" enctype="multipart/form-data">
		<div>
			<label for="title">Title</label>
			<form:input type="text" name="title" id="title" path="title" />
			<form:errors path="title" />
		</div>
		<div>
			<label for="description">Description</label>
			<form:textarea rows="10" cols="20" name="description" id="description" path="description"  />
			<form:errors path="description" />
		</div>
		<div>
			<label for="releaseDate">Data de lançamento</label>
			<form:input path="releaseDate" type="date" />
			<form:errors path="releaseDate" />
		</div>
		<div>
			<label for="pages">Pages Number</label>
			<input type="text" name="pages" id="pages" />
			<form:errors path="pages" />
		</div>
		<div>
			<label for="summary">Sumario do livro</label>
			<input type="file" name="summary" id="summary" />
			<form:errors path="summaryPath" />
		</div>
		
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<input type="text" name="prices[${status.index}].value" id="price_${bookType}" />
				<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>
		<div>
			<input type="submit" value="Submit" />
		</div>
		
	</form:form>
	
</body>
</html>