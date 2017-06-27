<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="t_display">
	<tr class="t_row">
		<th class="t_header">NAME</th>
		<th class="t_header">EMAIL</th>
		<th class="t_header">OFFER</th>
	</tr>
	<c:forEach var="offer" items="${offers}">
		<tr class="t_row">
			<td class="t_column"><c:out value="${offer.user.name}"></c:out></td>
			<td class="t_column"><c:out value="${offer.user.email}"></c:out></td>
			<td class="t_column"><c:out value="${offer.text}"></c:out></td>
		</tr>
	</c:forEach>
</table>
