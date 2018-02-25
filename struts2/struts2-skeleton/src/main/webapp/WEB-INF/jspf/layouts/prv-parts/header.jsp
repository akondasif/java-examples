<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<div id="wrapper_header">
<tiles:useAttribute id="activeItem" name="activeItem" ignore="true" classname="java.lang.String"/>
    
<div id="logo"><s:a action="Index"><img src="<s:url value="/images/logo_pp.png"/>" alt="Logo" /></s:a></div>

<div class="top-nav">
    <ul>
<!--        <li>
            <c:if test="${activeItem == 'DASHBOARD'}">
                <s:a action="prv/PrvDashBoard" cssClass="active"> <img src="<s:url value="/images/icons/dashboard.png"/>"/>Inicio</s:a>            
            </c:if>
            <c:if test="${activeItem != 'DASHBOARD'}">
                <s:a action="prv/PrvDashBoard"> <img src="<s:url value="/images/icons/dashboard.png"/>"/>Inicio</s:a>            
            </c:if> 
        </li>        
        -->
        
        <li>
            <c:if test="${activeItem == 'ABOUT'}">
                <s:a action="prv/About" cssClass="active"> <img src="<s:url value="/images/icons/user.png"/>" />Perfil</s:a>
            </c:if>
            <c:if test="${activeItem != 'ABOUT'}">
                <s:a action="prv/About"> <img src="<s:url value="/images/icons/user.png"/>" />Perfil</s:a>
            </c:if>
        </li>  
        
        
        <li>
            <c:if test="${activeItem == 'SECURITY'}">
                <s:a action="prv/Security" cssClass="active"> <img src="<s:url value="/images/icons/plugins.png"/>" />Configuración</s:a>
            </c:if>
            <c:if test="${activeItem != 'SECURITY'}">
                <s:a action="prv/Security"> <img src="<s:url value="/images/icons/plugins.png"/>" />Configuración</s:a>
            </c:if>
        </li>       
        
        
<!--        <li>
            <c:if test="${activeItem == 'MESSAGES'}">
                <s:a action="prv/MailBoxList" cssClass="active"> <img src="<s:url value="/images/icons/notification.png"/>" />Mensajes</s:a>
            </c:if>
            <c:if test="${activeItem != 'MESSAGES'}">
                <s:a action="prv/MailBoxList"> <img src="<s:url value="/images/icons/notification.png"/>" />Mensajes</s:a>
            </c:if>
        </li>       -->
        <li>
            <s:a action="logout.action"><img src="<s:url value="/images/icons/error.png"/>" />Salir</s:a>
        </li>        
    </ul>
</div>
</div> <!-- end wrapper menu-->