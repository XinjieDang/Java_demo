<%--
  Created by IntelliJ IDEA.
  User: Ryan
  Date: 2020/9/22
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="breadcrumbs">
    <li><a href="../dashboard.html"><i class="iconfa-home"></i></a> <span class="separator"></span></li>
    <li><a href="../forms.html">Forms</a> <span class="separator"></span></li>
    <li>部门信息管理</li>

    <li class="right">
        <a href="" data-toggle="dropdown" class="dropdown-toggle"><i class="icon-tint"></i> Color Skins</a>
        <ul class="dropdown-menu pull-right skin-color">
            <li><a href="default">Default</a></li>
            <li><a href="navyblue">Navy Blue</a></li>
            <li><a href="palegreen">Pale Green</a></li>
            <li><a href="red">Red</a></li>
            <li><a href="green">Green</a></li>
            <li><a href="brown">Brown</a></li>
        </ul>
    </li>
</ul>

<div class="pageheader">
    <form action="../results.html" method="post" class="searchbar">
        <input type="text" name="keyword" placeholder="To search type and hit enter..." />
    </form>
    <div class="pageicon"><span class="iconfa-pencil"></span></div>
    <div class="pagetitle">
        <h5>新增部门信息</h5>
        <h1>新增部门信息</h1>
    </div>
</div><!--pageheader-->

<div class="maincontent">
    <div class="maincontentinner">

        <!-- START OF DEFAULT WIZARD -->


        <div class="clearfix"></div><br /><br />

        <!-- START OF DEFAULT WIZARD (INVERSE)-->
        <div class="widgetbox box-inverse">
            <h4 class="widgettitle">Form Bordered</h4>
            <div class="widgetcontent nopadding">
                <form class="stdform stdform2" method="post" action="deptControl?action2=savaDept">
                    <p>
                        <label>部门名称：</label>
                        <span class="field"><input type="text" name="depname" id="username" class="input-xxlarge" /></span>
                    </p>

                    <p>
                        <label>部门描述：</label>
                        <span class="field"><input type="text" name="depdescribe" id="lastname2" class="input-xxlarge" /></span>
                    </p>

                    <p class="stdformbutton">
                        <button class="btn btn-primary" type="submit">确定</button>
                    </p>
                </form>
            </div><!--widgetcontent-->
        </div><!--widget-->

        <!-- END OF TABBED WIZARD -->

        <div class="footer">
            <div class="footer-left">
                <span>&copy; 2013. Shamcey Admin Template. All Rights Reserved.</span>
            </div>
            <div class="footer-right">
                <span>Designed by: <a href="http://themepixels.com/">ThemePixels</a></span>
            </div>
        </div><!--footer-->


    </div><!--maincontentinner-->
</div><!--maincontent-->



