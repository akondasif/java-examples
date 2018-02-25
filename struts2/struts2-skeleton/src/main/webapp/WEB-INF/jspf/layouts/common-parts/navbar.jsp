<%@page import="es.itnoroeste.core.config.ConfigurationManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="top_header">
    <ul>
         <li>
                <a href='<%=ConfigurationManager.get("acl.navbar.empleados")%>'>Empleados</a>
        </li>
        <li>
            <s:url action="Index.action" var="viewTag" />
            <s:a  href="%{viewTag}">ACL</s:a>     
            </li>
       
    </ul>    
</div>
