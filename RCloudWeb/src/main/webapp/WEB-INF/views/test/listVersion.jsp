<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach  items="${list}" var="lista">
    	<li><a href="${pageContext.request.contextPath}/uploadVersion/download/${lista.name}/">${lista.name}</a></li>  
</c:forEach>