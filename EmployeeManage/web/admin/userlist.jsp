<%--
  Created by IntelliJ IDEA.
  User: Ryan
  Date: 2020/9/20
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function(){
        $("ul li:eq(1)").addClass("active");
    });
</script>

    <ul class="breadcrumbs">
        <li><a href="dashboard.html"><i class="iconfa-home"></i></a> <span class="separator"></span></li>
        <li><a href="table-static.html">Tables</a> <span class="separator"></span></li>
        <li>用户信息列表</li>
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
        <form name="searchForm" action="Useroperate?action2=searchadmin" class="searchbar" method="post"  style="padding-bottom: 0px">
            <span class="data_search">
			<select id="searchType" name="searchType" style="width: 120px;">
				<option value="name">管理员名称</option>
			</select>&nbsp;
                  <input type="text" class="span4" name="user_text" value="${userText}" style="height: 30px;width: 210px" placeholder="输入搜索内容..." />
                <button type="submit" style="margin-bottom: 10px;" class="btn btn-primary"onkeydown="if(event.keyCode==13) searchForm.submit()">搜索</button> &nbsp;
		</span>
        </form>
        <div class="pageicon"><span class="iconfa-table"></span></div>
        <div class="pagetitle">
            <h5>用户管理</h5>
            <h1>用户信息列表</h1>
        </div>
    </div><!--pageheader-->

    <div class="maincontent">
        <div class="maincontentinner">

            <h4 class="widgettitle" style="background:#444;">用户信息列表</h4>
            <table id="dyntable" class="table table-bordered table-infinite">
                <colgroup>
                    <col class="con0" style="align:center; width: 4%" />
                    <col class="con1" />
                    <col class="con0" />
                    <col class="con1" />
                    <col class="con0" />
                    <col class="con1" />
                </colgroup>
                <thead>
                <tr>
                    <th class="head0 nosort"><input type="checkbox" class="checkall" /></th>
                    <th class="head0">ID</th>
                    <th class="head1">登录名</th>
                    <th class="head1">用户名</th>
<%--                    <th class="head0">密码</th>--%>
                    <th class="head0">&nbsp;&nbsp;操作</th>

                </tr>
                </thead>
                <tbody>
          <c:forEach var="user" items="${users}">
                <tr class="gradeX">
                    <td class="aligncenter"><span class="center">
                            <input type="checkbox" />
                          </span></td>
                    <td>${user.id}</td>
                    <td>${user.loginname}</td>
                    <td>${user.username}</td>
<%--                    <td>${user.password}</td>--%>
                    <td class="centeralign">
                        &nbsp;<a href="Useroperate?action1=findUser_upd&userId=${user.id}" class="updatarow"><span class="icon-pencil"></span></a>&nbsp;
                        <a href="javascript:;" onclick="DelUser(${user.id})" class="del1eterow"><span class="icon-trash"></span></a>
                    </td>
                </tr>
          </c:forEach>
                </tbody>
            </table>

            <br /><br />



        </div><!--maincontentinner-->
    </div><!--maincontent-->



<script type="text/javascript">
    jQuery(document).ready(function(){
        prettyPrint();

        // check all checkboxes in table
        if(jQuery('.checkall').length > 0) {
            jQuery('.checkall').click(function(){
                var parentTable = jQuery(this).parents('table');
                var ch = parentTable.find('tbody input[type=checkbox]');
                if(jQuery(this).is(':checked')) {

                    //check all rows in table
                    ch.each(function(){
                        jQuery(this).attr('checked',true);
                        jQuery(this).parent().addClass('checked');	//used for the custom checkbox style
                        jQuery(this).parents('tr').addClass('selected'); // to highlight row as selected
                    });

                } else {

                    //uncheck all rows in table
                    ch.each(function(){
                        jQuery(this).attr('checked',false);
                        jQuery(this).parent().removeClass('checked');	//used for the custom checkbox style
                        jQuery(this).parents('tr').removeClass('selected');
                    });

                }
            });
        }

        // delete row in a table
        if(jQuery('.deleterow').length > 0) {
            jQuery('.deleterow').click(function(){
                var conf = confirm('Continue delete?');
                if(conf)
                    jQuery(this).parents('tr').fadeOut(function(){
                        jQuery(this).remove();
                        // do some other stuff here

                    });
                return false;
            });
        }



    });


    function DelUser(userId) {
        if(confirm("您确定要删除管理员信息吗？")) {
            window.location="Useroperate?action1=delUser&userId="+userId;
        }
    }

</script>