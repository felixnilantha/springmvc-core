<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h3>Authorized uses only.</h3>

<table class="t_display">
	<tr class="t_row">
		<th class="t_header">USER NAME</th>
		<th class="t_header">EMAIL</th>
		<th class="t_header">ROLE</th>
		<th class="t_header">ENABLED</th>
	</tr>
	<c:forEach var="user" items="${users}">
		<tr class="t_row">
			<td class="t_column"><c:out value="${user.username}"></c:out></td>
			<td class="t_column"><c:out value="${user.email}"></c:out></td>
			<td class="t_column"><c:out value="${user.authority}"></c:out></td>
			<td class="t_column"><c:out value="${user.enabled}"></c:out></td>
		</tr>
	</c:forEach>
</table>
