<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags"%>

<script src="<s:url value="/js/jquery.zrssfeed.js"/>"></script>

<script type="text/javascript">
    //http://www.zazar.net/developers/jquery/zrssfeed/
    $(document).ready(function () {
        $('#news').rssfeed('http://feeds.reuters.com/reuters/oddlyEnoughNews', {
            limit: 5,
            header: false
        });
    });
</script>

<div class="container">       
    <div class="inside">
        <div class="wrapper">

            <!-- aside -->
            <aside>
                <h2>&Uacute;ltimas <span>Noticias</span></h2>
                <!-- .news -->
                <div id="news"></div>
                <!-- /.news -->                               
            </aside>

            <!-- content -->
            <section id="content">
                <article>
                    <h2>Bienvenido <span>a nuestra empresa!</span></h2>
                    <p>Design Company is a free web template created by TemplateMonster.com 
                        team. This website template is optimized for 1024X768 screen resolution. 
                        It is also HTML5 &amp; CSS3 valid.</p>
                    <figure><a href="#"><img src="<s:url value="/images/banner1.jpg"/>" alt=""></a></figure>
                    <p>This website template has several pages: <a href="index.html">Home</a>,
                        <a href="about.html">About us</a>, <a href="privacy.html">
                            Privacy Policy</a>, <a href="gallery.html">Gallery</a>, 
                        <a href="contacts.html">Contact us</a> (note that contact us form -
                        doesn't work), <a href="sitemap">Site Map</a>.</p>
                    This website template can be delivered in two packages - with PSD 
                    source files included and without them. If you need PSD source files, 
                    please go to the template download page at TemplateMonster to 
                    leave the e-mail address that you want the template ZIP package 
                    to be delivered to.
                </article> 
            </section>
        </div>
    </div>
</div>

