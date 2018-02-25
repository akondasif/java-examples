<!DOCTYPE HTML>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="es-ES">

    <head>
        <!-- ======================================================================= -->
        <!--  Librerías de etiquetas Java                                            -->
        <!-- ======================================================================= -->
        <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>                
        <%@ taglib prefix="s" uri="/struts-tags"%>
        <%@taglib uri='http://java.sun.com/jstl/core_rt' prefix='c' %>

        <!-- ======================================================================= -->
        <!--  Definición de los meta                                                 -->
        <!-- ======================================================================= -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />        
        <meta name="author" content="IT Notoeste" />

        <!-- ======================================================================= -->
        <!--  Título del sitio                                                       -->
        <!-- ======================================================================= -->
        <title><s:text name="common.title" /></title>

        <!-- ======================================================================= -->
        <!--  Hojas de estilo                                                        -->
        <!-- ======================================================================= -->    

        <link rel="stylesheet" type="text/css" href="<s:url value="/css/utopia-white.css"/>" />
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/utopia-responsive.css"/>" />        
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/validationEngine.jquery.css"/>" />
        <link rel="stylesheet" type="text/css" href="<s:url value="/less/index.less"/>" />   

        <!-- ======================================================================= -->
        <!--  Favicon                                                                -->
        <!-- ======================================================================= -->
        <link rel="shortcut icon" href="<s:url value="/favicon.ico"/>" />       

        <!--[if lt IE 9]>
            <link rel="stylesheet" type="text/css" href="<s:url value="/css/ie/ie.css"/>" media="all">            
            <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>      
        <![endif]-->

        <!-- ======================================================================= -->
        <!--  Scripts comunes de todo el sitio                                       -->
        <!-- ======================================================================= -->        

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

        <script>    
            function goToByScroll(id){
                $('html,body').animate({scrollTop: $("#"+id).offset().top},'slow');
            }
        </script>
    </head>

    <body>

        <div class="container-fluid">
            <tiles:insertAttribute name="body" flush="true" ignore="true"/>
        </div> <!-- end of container -->

        <!-- javascript placed at the end of the document so the pages load faster -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/utopia.js"></script>
        <script type="text/javascript" src="js/jquery.validationEngine.js"></script>
        <script type="text/javascript" src="js/jquery.validationEngine-en.js"></script>
        <script type="text/javascript">
            jQuery(function(){
                jQuery(".utopia").validationEngine('attach', {promptPosition : "topLeft", scroll: false});
            })
        </script>
    </body>
</html>