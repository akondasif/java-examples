<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<div id="wrapper_header">
<tiles:useAttribute id="activeItem" name="activeItem" ignore="true" classname="java.lang.String"/>
    
<div id="logo"><s:a action="Index"><img src="<s:url value="/images/logo_pp.png"/>" alt="Logo" /></s:a></div>

<div class="top-nav">
    <ul>
        <li>
            <s:a action="logout.action"><img src="<s:url value="/images/icons/error.png"/>" />Salir</s:a>
        </li>        
    </ul>
</div>
</div> <!-- end wrapper menu-->