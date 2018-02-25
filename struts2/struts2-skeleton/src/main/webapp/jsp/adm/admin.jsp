<%@page import="es.itnoroeste.core.config.ConfigurationManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Cargamos las librerias de etiquetas que necesitamos para esta pagina -->
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!-- ----------------------------------------------------------------------- -->
<!-- Mensajes de error de cabecera.                                          -->
<!-- ----------------------------------------------------------------------- -->
<s:if test="hasActionErrors()">
    <div class="notification error png_bg">
        <a class="close" href="#">
            <img alt="close" title="Close this notification" src="<s:url value="/images/icons/cross_grey_small.png"/>">
        </a>
        <div>
            <strong>Error</strong> <s:actionerror  escape="false"/>
        </div>
    </div>
</s:if>

<!-- ----------------------------------------------------------------------- -->
<!-- Mensajes de ok de cabecera.                                             -->
<!-- ----------------------------------------------------------------------- -->
<s:if test="hasActionMessages()">
    <div class="notification success png_bg">
        <a class="close" href="#">
            <img alt="close" title="Close this notification" src="<s:url value="/images/icons/cross_grey_small.png"/>">
        </a>
        <div>
            <strong>Correcto</strong> <s:actionmessage  escape="false"/>
        </div>    
    </div>
</s:if>

<!-- ----------------------------------------------------------------------- -->
<!-- Contenido de la jsp                                                     -->
<!-- ----------------------------------------------------------------------- -->
<div class="tab_content" id="auditoria">
    <div class="element-box">

        <h1>Content Grid</h1>
        <h1><%= ConfigurationManager.get("base.config.test") %></h1>
        <p>You can very well use the 960 grid system if you'd like.</p>

        <!-- Grafico que contiene una grafica del número de usuario por tiempo. -->

        <!-- Zona que contiene varias gráficas sobre el uso del sitio. Numero de 
        visitas, páginas vistas, visitas nuevas, etc.-->        
        <div class="content-box">
            <div class="content-box-header">
                <h4>Uso del sitio !</h4>
            </div> <!-- End .content-box-header -->

            <div class="content-box-content">
                <h4>Infynitix IT consulting</h4>
                <p>Infynitix is a global IT consulting, technology services company and outsourcing company. Bringing together vast experience, comprehensive capabilities across all IT functions and extensive research in IT domains, Infynitix works with clients to help them become top- notch businesses.  Our strength builds on our extensive industry expertise in IT consulting, technology and outsourcing. We help our clients to perform at the highest levels in order to create sustainable value for all their stakeholders. Leveraging on our expert domain knowledge, R&amp;D expertise and technology capabilities, we create solutions to help our clients across the world.</p>
                <div id="area-chart"></div>
            </div> <!-- End .content-box-content -->
        </div>

        <div class="clear"></div>
        
        <div class="content-box half-box">
            <div class="content-box-header">
                <h4>Half width !</h4>
            </div> <!-- End .content-box-header -->
            <div class="content-box-content">
                <h4>Maecenas dignissim</h4>
                <p>Infynitix is a global IT consulting, technology services company and outsourcing company. Bringing together vast experience, comprehensive capabilities across all IT functions and extensive research in IT domains, Infynitix works with clients to help them become top- notch businesses.</p>
            </div> <!-- End .content-box-content -->
        </div>

        <div class="content-box half-box right">
            <div class="content-box-header">
                <h4>Half width !</h4>
            </div> <!-- End .content-box-header -->
            <div class="content-box-content">
                <h4>Maecenas dignissim</h4>
                <p>Infynitix is a global IT consulting, technology services company and outsourcing company. Bringing together vast experience, comprehensive capabilities across all IT functions and extensive research in IT domains, Infynitix works with clients to help them become top- notch businesses.</p>
            </div> <!-- End .content-box-content -->
        </div>

        <div class="clear"></div>

        <div class="content-box half-box">
            <div class="content-box-header">
                <h4>Half width !</h4>
            </div> <!-- End .content-box-header -->
            <div class="content-box-content">
                <h4>Maecenas dignissim</h4>
                <p>Infynitix is a global IT consulting, technology services company and outsourcing company. Bringing together vast experience, comprehensive capabilities across all IT functions and extensive research in IT domains, Infynitix works with clients to help them become top- notch businesses.</p>
            </div> <!-- End .content-box-content -->
        </div>

        <div class="content-box half-box right">
            <div class="content-box-header">
                <h4>Half width !</h4>
            </div> <!-- End .content-box-header -->
            <div class="content-box-content">
                <h4>Maecenas dignissim</h4>
                <p>Infynitix is a global IT consulting, technology services company and outsourcing company. Bringing together vast experience, comprehensive capabilities across all IT functions and extensive research in IT domains, Infynitix works with clients to help them become top- notch businesses.</p>
            </div> <!-- End .content-box-content -->
        </div>

    </div>
</div>
