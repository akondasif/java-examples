<%@page import="es.itnoroeste.core.security.acl.model.application.ApplicationCVOFacade"%>
<%@page import="es.itnoroeste.core.security.acl.model.module.ModuleCVOFacade"%>
<%@page import="es.itnoroeste.core.security.acl.model.action.ActionCVOFacade"%>
<%@page import="es.itnoroeste.core.security.authentication.model.rol.RolCVOFacade"%>
<%@page import="es.itnoroeste.core.security.authentication.model.group.GroupCVOFacade"%>
<%@page import="es.itnoroeste.core.security.estadisticas.model.estadisticasusuario.EstadisticasUsuarioCVOServiceFacade"%>
<%@page import="es.itnoroeste.core.security.estadisticas.model.estadisticasmodulo.EstadisticasModuloCVOServiceFacade"%>
<%@page import="es.itnoroeste.core.security.estadisticas.model.estadisticasaccion.EstadisticasAccionCVOServiceFacade"%>
<%@page import="es.itnoroeste.core.security.auditory.model.userauditory.UserAuditoryCVOServiceFacade"%>
<%@page import="es.itnoroeste.core.security.authentication.model.user.UserCVOFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="../../../../WEB-INF/Accion.tld" prefix="acl" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="wrapper_header">
    <tiles:useAttribute id="activeItem" name="activeItem" ignore="true" classname="java.lang.String"/>

    <div id="logo"><s:a action="Index"><img src="<s:url value="/images/logo_pp.png"/>" alt="Logo" /></s:a></div>

    <div class="top-nav">
        <ul>
                <ul>
<!--                <li>
                <c:if test="${activeItem == 'DASHBOARD'}">
                    <s:a action="admin/AdminDashBoard" cssClass="active"> <img src="<s:url value="/images/icons/dashboard.png"/>"/>Inicio</s:a>
                </c:if>
                <c:if test="${activeItem != 'DASHBOARD'}">
                    <s:a action="admin/AdminDashBoard"> <img src="<s:url value="/images/icons/dashboard.png"/>"/>Inicio</s:a>           
                </c:if>

            </li>-->
            <!--almacenamos el nombre del usuario que está logueado en userId-->
            <s:set var="userName">
                <sec:authentication property="principal.username" htmlEscape="false"/>
            </s:set>

            <acl:hasPermission actionId="<%= ApplicationCVOFacade.GET_PAGINATED_LIST_APPLICATION_METHOD_ID%>" userName='${userName}' />   
            <s:set var="permisoApp">${permiso}</s:set>
          

            <acl:hasPermission actionId='<%= ModuleCVOFacade.GET_PAGINATED_LIST_MODULE_METHOD_ID%>' userName='${userName}' />   
            <s:set var="permisoMod">${permiso}</s:set>
        
            <acl:hasPermission actionId='<%= ActionCVOFacade.GET_PAGINATED_LIST_ACTION_METHOD_ID%>' userName='${userName}' />   
            <s:set var="permisoAct">${permiso}</s:set>
        

            <s:if test="%{#permisoApp == 'true'|| #permisoRolApp == 'true' }">   
                <li>        
                    <c:if test="${activeItem == 'APPLICATION'}">
                        <s:a action="admin/ApplicationList" cssClass="active"> <img src="<s:url value="/images/icons/form.png"/>" />Aplicaciones</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'APPLICATION'}">
                        <s:a action="admin/ApplicationList"> <img src="<s:url value="/images/icons/form.png"/>" />Aplicaciones</s:a>
                    </c:if>
                </li>
            </s:if>
            <s:elseif test="%{#permisoMod == 'true'|| #permisoRolMod == 'true'}" >
                <li>        
                    <c:if test="${activeItem == 'APPLICATION'}">
                        <s:a action="admin/ModuleList" cssClass="active"> <img src="<s:url value="/images/icons/form.png"/>" />Aplicaciones</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'APPLICATION'}">
                        <s:a action="admin/ModuleList"> <img src="<s:url value="/images/icons/form.png"/>" />Aplicaciones</s:a>
                    </c:if>
                </li>
            </s:elseif>  
            <s:elseif test="%{#permisoAct == 'true'|| #permisoRolAct == 'true' }"> 
                <li>        
                    <c:if test="${activeItem == 'APPLICATION'}">
                        <s:a action="admin/ActionList" cssClass="active"> <img src="<s:url value="/images/icons/form.png"/>" />Aplicaciones</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'APPLICATION'}">
                        <s:a action="admin/ActionList"> <img src="<s:url value="/images/icons/form.png"/>" />Aplicaciones</s:a>
                    </c:if>
                </li>
            </s:elseif>   

            <acl:hasPermission actionId='<%= UserCVOFacade.PAGINATEDLIST_USER_METHOD_ID%>' userName='${userName}' />   
            <s:set var="permisoUser">${permiso}</s:set>
        
            <acl:hasPermission actionId='<%= GroupCVOFacade.GET_PAGINATED_LIST_GROUP_METHOD_ID%>' userName='${userName}' />   
            <s:set var="permisoGroup">${permiso}</s:set>
         

            <acl:hasPermission actionId='<%= RolCVOFacade.GET_PAGINATED_LIST_ROL_METHOD_ID%>' userName='${userName}' />   
            <s:set var="permisoRol1">${permiso}</s:set>
           

            <s:if test="%{#permisoRolUser == 'true'|| #permisoUser == 'true' }">   
                <li>
                    <c:if test="${activeItem == 'USUARIO'}">
                        <s:a action="admin/UsuarioList" cssClass="active"> <img src="<s:url value="/images/icons/user.png"/>" />Usuarios</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'USUARIO'}">
                        <s:a action="admin/UsuarioList"> <img src="<s:url value="/images/icons/user.png"/>" />Usuarios</s:a>
                    </c:if>
                </li>
            </s:if>

            <s:elseif test="%{#permisoGroup == 'true'|| #permisoRolGroup == 'true'}" >
                <li>
                    <c:if test="${activeItem == 'USUARIO'}">
                        <s:a action="admin/GroupList" cssClass="active"> <img src="<s:url value="/images/icons/user.png"/>" />Usuarios</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'USUARIO'}">
                        <s:a action="admin/GroupList"> <img src="<s:url value="/images/icons/user.png"/>" />Usuarios</s:a>
                    </c:if>
                </li>
            </s:elseif>  

            <s:elseif test="%{#permisoRol1 == 'true'|| #permisoRolRol == 'true' }"> 
                <li>
                    <c:if test="${activeItem == 'USUARIO'}">
                        <s:a action="admin/RolList" cssClass="active"> <img src="<s:url value="/images/icons/user.png"/>" />Usuarios</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'USUARIO'}">
                        <s:a action="admin/RolList"> <img src="<s:url value="/images/icons/user.png"/>" />Usuarios</s:a>
                    </c:if>
                </li>
            </s:elseif>   


            <acl:hasPermission actionId='<%= UserAuditoryCVOServiceFacade.GET_PAGINATED_LIST_CVO_METHOD_ID%>' userName='${userName}' />   
            <s:set var="permisoAudit">${permiso}</s:set>        

            <s:if test="%{#permisoAuditRol == 'true'|| #permisoAudit == 'true' }">  
                <li>
                    <c:if test="${activeItem == 'AUDITORIA'}">
                        <s:a action="admin/AuditoriaUsuarioList" cssClass="active"> <img src="<s:url value="/images/icons/plugins.png"/>" />Auditor&iacute;a</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'AUDITORIA'}">
                        <s:a action="admin/AuditoriaUsuarioList"> <img src="<s:url value="/images/icons/plugins.png"/>" />Auditor&iacute;a</s:a>
                    </c:if>
                </li>        
            </s:if>

            <!--Compruebo si tengo permiso para obtener el grupo y en ese caso muestro el boton -->
            <acl:hasPermission actionId='<%=EstadisticasAccionCVOServiceFacade.FIND_ESTADISTICAS_ACCION_CVO_METHOD_ID%>' userName='${userName}' /> 
            <s:set var="permisoEstadisticasAccion">${permiso}</s:set>
        

            <!--Compruebo si tengo permiso para obtener el grupo y en ese caso muestro el boton -->
            <acl:hasPermission actionId='<%=EstadisticasModuloCVOServiceFacade.FIND_ESTADISTICAS_MODULO_CVO_METHOD_ID%>' userName='${userName}' /> 
            <s:set var="permisoEstadisticasModulo">${permiso}</s:set>
                 

            <!--Compruebo si tengo permiso para obtener el grupo y en ese caso muestro el boton -->
            <acl:hasPermission actionId='<%=EstadisticasUsuarioCVOServiceFacade.FIND_ESTADISTICAS_USUARIO_CVO_METHOD_ID%>' userName='${userName}' /> 
            <s:set var="permisoEstadisticasUsuario">${permiso}</s:set>
            

            <s:if test="%{#permisoEstadisticasAccion == 'true'|| #permisoEstadisticasAccionRol == 'true' }">     
                <li>
                    <c:if test="${activeItem == 'ESTADISTICAS'}">
                        <s:a action="admin/EstadisticasPorAccionDashBoard" cssClass="active"> <img src="<s:url value="/images/icons/stats.jpg"/>" />Estad&iacute;sticas</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'ESTADISTICAS'}">
                        <s:a action="admin/EstadisticasPorAccionDashBoard"> <img src="<s:url value="/images/icons/stats.jpg"/>" />Estad&iacute;sticas</s:a>
                    </c:if>    
                </li>   
            </s:if>

            <s:elseif test="%{#permisoEstadisticasModulo == 'true'|| #permisoEstadisticasModuloRol == 'true' }">     
                <li>
                    <c:if test="${activeItem == 'ESTADISTICAS'}">
                        <s:a action="admin/EstadisticasPorModuloDashBoard" cssClass="active"> <img src="<s:url value="/images/icons/stats.jpg"/>" />Estad&iacute;sticas</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'ESTADISTICAS'}">
                        <s:a action="admin/EstadisticasPorModuloDashBoard"> <img src="<s:url value="/images/icons/stats.jpg"/>" />Estad&iacute;sticas</s:a>
                    </c:if>    
                </li>   
            </s:elseif>  

            <s:elseif test="%{#permisoEstadisticasUsuario == 'true'|| #permisoEstadisticasUsuarioRol == 'true' }">     
                <li>
                    <c:if test="${activeItem == 'ESTADISTICAS'}">
                        <s:a action="admin/EstadisticasPorUsuarioDashBoard" cssClass="active"> <img src="<s:url value="/images/icons/stats.jpg"/>" />Estad&iacute;sticas</s:a>
                    </c:if>
                    <c:if test="${activeItem != 'ESTADISTICAS'}">
                        <s:a action="admin/EstadisticasPorUsuarioDashBoard"> <img src="<s:url value="/images/icons/stats.jpg"/>" />Estad&iacute;sticas</s:a>
                    </c:if>    
                </li>   
            </s:elseif>  


            <!--            <acl:hasPermission actionId="6" userName='${userName}' />              
            <s:set var="permiso">${permiso}</s:set>
            <s:if test="%{#permiso == 'true'}"> 
                <li>
                <c:if test="${activeItem == 'CONFIGURACION'}">
                    <s:a action="admin/Configuracion" cssClass="active"> <img src="<s:url value="/images/icons/plugins.png"/>" />Configuracion </s:a>
                </c:if>
                <c:if test="${activeItem != 'CONFIGURACION'}">
                    <s:a action="admin/Configuracion"> <img src="<s:url value="/images/icons/plugins.png"/>" />Configuracion </s:a>
                </c:if>
            </li>   
            </s:if>    -->


            <li>
                <s:a action="logout.action"><img src="<s:url value="/images/icons/error.png"/>" />Salir</s:a>
            </li>        
        </ul>
    </div>

</div> <!-- end wrapper menu-->