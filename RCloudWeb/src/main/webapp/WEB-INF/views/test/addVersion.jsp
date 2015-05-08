<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="<%=request.getContextPath()%>/uploadVersion/upload" method="POST" enctype="multipart/form-data">  
    file: <input type="file" name="file"/><br/>  
    file: <input type="file" name="file"/><br/>  
    <input type="submit" value="上传"/>  
</form>  