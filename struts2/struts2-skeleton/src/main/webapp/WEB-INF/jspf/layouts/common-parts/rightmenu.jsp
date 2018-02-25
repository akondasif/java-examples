<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<script>
    $(function() {
        $( "#datepicker" ).datepicker();
    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#news').rssfeed('http://www.pp.es/actualidad/rss_1', {
            limit: 5,
            header: false
        });
    });
</script>

<div id="scrollcontainer">
    <div id="scrollbox">

        <!-- Accordion Menu -->
        <ul id="main-nav">

            <!-- Widget del calendatio -->
            <li class="righ-menu">
                <!-- Imagen de la cabecera del widget -->
                <img alt="Arrow" src="<s:url value="/images/menu-arrow.png"/>"> 
                <!-- Texto y enlace de la cabecera el widget -->
                <a class="nav-top-item current" href="#" style="padding-left: 15px;"> <!-- Add the class "current" to current menu item -->
                    Calendario
                </a>

                <!-- Capa en la que js carga el widget -->
                <div id="datepicker"></div>
            </li>

            <!-- Widget de rss de las noticias del partido -->
            <li class="righ-menu">
                <!-- Imagen de la cabecera del widget -->
                <img alt="Arrow" src="<s:url value="/images/menu-arrow.png"/>">
                <!-- Texto y enlace de la cabecera el widget -->
                <a class="nav-top-item current" href="#" style="padding-right: 15px; padding-left: 15px;">
                    Noticias Partido Popular
                </a>

                <!-- Capa en la que js carga el widget -->
                <div id="news"></div>
            </li>

        </ul>
        <!-- Accordion Menu END-->

    </div>                
</div>