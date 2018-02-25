<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form action="http://infynitix.com/" target="_blank">

    <!-- Imagen del perfil del usuario. -->
    <div class="profile_avatar">
        <img id="blah" src="<s:url value="/images/user.jpg"/>" alt="User" />
    </div>
    
    <!-- Script que se encarga de la recuperación de la imagen del usuario -->
    <script type="text/javascript" >
        var data;
        $(document).ready(function() {
            $.ajax({
                url: '<s:url value="/ajaxProfileImage"/>',
                data: "id=" +"<sec:authentication property="principal.username" htmlEscape="false"/>",
                type: "POST",
                dataType: "json",
                success: function(source){
                    data = source;
                    showInfo();
                },
                error: function(dato){
                    $('#blah').attr('src','<s:url value="/images/user.jpg"/>');
                }
            });							
        })

        function showInfo(){
            if(data['fileUploadContentType']!="" && data['fileUploadBase64']!=""){
                $('#blah').attr('src','data:'+data['fileUploadContentType']+';base64,'+data['fileUploadBase64']);
            }
        }

    </script>

    <div class="profile_links">
    <!-- Recuperamos el nombre de usuario para mostrarlo.
    Al mismo tiempo lo convertimos en un enlace a la sección de profile del usuario-->
    <s:a action="prv/About">
        <h2>        
            <sec:authentication property="principal.username" />         
        </h2>
    </s:a>

    <!-- Opciones del usuario -->
    <h4>
        <!-- Enlazamos con el portal de soporte. -->
        <s:a action="prv/About">Perfil</s:a> - 
        <!-- Opción de Logout -->
        <s:a action="logout.action">Salir</s:a>
    </h4>
    </div>
</form> 