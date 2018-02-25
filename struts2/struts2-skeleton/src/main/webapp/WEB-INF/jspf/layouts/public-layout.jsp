<!DOCTYPE HTML>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="es-ES">

    <head>
        <!-- ======================================================================= -->
        <!--  Librerías de etiquetas Java                                            -->
        <!-- ======================================================================= -->
        <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>                
        <%@ taglib prefix="s" uri="/struts-tags"%>

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
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/utopia-white.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/style.css"/>" charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/reset.css"/>"/>
        <link rel='stylesheet' type='text/css' href='<s:url value="/css/fullcalendar.css"/>' />
        <link rel='stylesheet' type='text/css' href='<s:url value="/css/custom.css"/>' />        
        <!-- Hoja de estilos para el editor wysiwyg -->
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/wysiwyg.css"/>"/>
        <!-- Hoja de estilos para el box de subida de ficheros -->
        <link rel="stylesheet" type="text/css" href="<s:url value="/js/plupload/jquery.plupload.queue/css/jquery.plupload.queue.css"/>" media="screen" />

        <!-- ======================================================================= -->
        <!--  Favicon                                                                -->
        <!-- ======================================================================= -->
        <link rel="shortcut icon" href="<s:url value="/favicon.ico"/>" />       

        <!--[if IE 8]>
            <link href="css/ie8.css" rel="stylesheet">
        <![endif]-->

        <!--[if lt IE 9]>
            <link rel="stylesheet" type="text/css" href="<s:url value="/css/ie/ie.css"/>" media="all">            
            <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>      
        <![endif]-->

        <!--[if gte IE 9]>
            <style type="text/css">
                .gradient {
                filter: none;
                }
            </style>
        <![endif]-->

        <!-- ======================================================================= -->
        <!--  Scripts comunes de todo el sitio                                       -->
        <!-- ======================================================================= -->                  

        <!--[if lt IE 9]>      
        <script src="<s:url value="/js/ie/css3-mediaqueries.js"/>"></script>
        <![endif]-->

        <script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/custom.js"/>"></script>        
        <script type="text/javascript" src="<s:url value="/js/jquery.wysiwyg.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/formwizard.js"/>" ></script>
        <script type="text/javascript" src="<s:url value="/js/jquery-ui-1.8.13.custom.min.js"/>" charset="utf-8"></script>
        <script type="text/javascript" src="<s:url value="/js/datepicker/jquery.ui.core.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/datepicker/jquery.ui.datepicker.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.tipsy.js"/>"></script><!--Tolltip-->        
        <script type='text/javascript' src='<s:url value="/js/fullcalendar.min.js"/>'></script>
        <script type="text/javascript" src="<s:url value="/js/elfinder.min.js"/>" charset="utf-8"></script>
        <script type="text/javascript" src="<s:url value="/js/FilterScript.js"/>" language="javascript" ></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.miniColors.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.msgbox.js"/>"></script>
        <script type='text/javascript' src='<s:url value="/js/jquery.simplemodal.js"/>'></script>
        <script type='text/javascript' src='<s:url value="/js/forms-style.js"/>'></script>
        <script type="text/javascript" src="http://bp.yahooapis.com/2.4.21/browserplus-min.js"></script>      
        <script type="text/javascript" src="<s:url value="/js/plupload/plupload.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/plupload/plupload.gears.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/plupload/plupload.silverlight.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/plupload/plupload.flash.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/plupload/plupload.browserplus.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/plupload/plupload.html4.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/plupload/plupload.html5.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/plupload/jquery.plupload.queue/jquery.plupload.queue.js"/>"></script>  
        <script type="text/javascript" src="<s:url value="/js/validation.js"/>"></script>  
       
        <!-- js para el widget de noticias rss de la barra lateral -->
        <script src="<s:url value="/js/jquery.zrssfeed.js"/>"></script>

        <script>    
            function goToByScroll(id){
                $('html,body').animate({scrollTop: $("#"+id).offset().top},'slow');
            }
        </script>

    </head>

    <body>

        <div class="container-fluid">

            <!-- =========================================================== -->
            <!--  NavBar                                                     -->
            <!-- =========================================================== -->
            
            <tiles:insertAttribute name="navbar" flush="true" ignore="true"/>
            
            <!-- =========================================================== -->
            <!--  Header                                                     -->
            <!-- =========================================================== -->

            <div class="header">
                <tiles:insertAttribute name="header" flush="true" ignore="true"/>
            </div> 

            <!-- =========================================================== -->
            <!--  Tabs                                                       -->
            <!-- =========================================================== -->

            <div class="tabs-contenor">
                <div class="profile">
                    <tiles:insertAttribute name="profile" flush="true" ignore="true"/>
                </div>
                <tiles:insertAttribute name="tabs" flush="true" ignore="true"/>
            </div>

            <!-- =========================================================== -->
            <!--  Main                                                       -->
            <!-- =========================================================== -->

            <div id="main">
                
                <div id="content">
                    <tiles:insertAttribute name="body" flush="true" ignore="true"/>
                </div>

            </div>

            <!-- =========================================================== -->
            <!--  Footer                                                     -->
            <!-- =========================================================== -->        

            <div id="footer">
                <tiles:insertAttribute name="footer" flush="true" ignore="true"/>
            </div>

        </div>

    </body>

</html>
