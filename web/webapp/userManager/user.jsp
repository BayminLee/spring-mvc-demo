<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.tdh.jzgl.util.CommonUtil" %>
<%
    String basePath = CommonUtil.getContextURI(request);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>用户信息主页user.jsp</title>
    <link rel="stylesheet" href="<%=basePath%>/webui/tdh/form/css/form.css" />
    <link rel="stylesheet" href="<%=basePath%>/webui/tdh/btn/css/btn.css" />
    <link href="<%=basePath%>/ext/uploader/uploadify.css" rel="stylesheet" type="text/css" />

    <%--  jQuery  --%>
    <script type="text/javascript" src="<%=basePath%>/webui/lib/jquery.min.js" ></script>

    <script type="text/javascript" src="<%=basePath%>/webui/tdh/form/js/tdh.form.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/tdh/btn/js/tdh.btn.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/DatePicker/WdatePicker.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/ext/uploader/swfobject.js"></script>
    <script type="text/javascript" src="<%=basePath%>/ext/uploader/jquery.uploadify.v2.1.4.min.js"></script>

    <%--  部门树相关  --%>
    <link rel="stylesheet" href="<%=basePath%>/webui/plugg/ztree/css/metroStyle/metroStyle.css" />
    <link rel="stylesheet" href="<%=basePath%>/webui/plugg/ztree/css/metroStyle/diy.css" />
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/ztree/js/jquery.ztree.core.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/ztree/js/jquery.ztree.excheck.min.js" ></script>

    <%--  user dhtmlxgrid数据  --%>
    <link rel="stylesheet" href="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/dhtmlxgrid.css" />
    <link rel="stylesheet" href="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/ext/dhtmlxgrid_pgn_bricks.css" />
    <link rel="stylesheet" href="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css" />
    <link rel="stylesheet" href="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/tdh/grid.css" />
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/dhtmlxcommon.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/dhtmlxgrid.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/ext/dhtmlxgrid_filter.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/dhtmlx/dhtmlxGrid/codebase/dhtmlxgridcell.js" ></script>


    <%--<link rel="stylesheet" href="<%=basePath%>/webui/plugg/layui/css/modules/layer/default/layer.css">--%>
    <%--<script type="text/javascript" src="<%=basePath%>/webui/plugg/layui/lay/modules/layer.js" ></script>--%>
    <script type="text/javascript" src="<%=basePath%>/webapp/userManager/js/user.js"></script>

    <style>
        *{
            margin: 0;
            padding: 0;
        }
        html,body{
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
    </style>
</head>
<body>
<!-- 整个页面 -->
<div style="width: 100%; height: 100%;">
    <!-- 顶部 -->
    <div class="tdh_form_search" style="width: 100%; height: 100px">
        <table class="tdh_form" style="margin-top: 10px">
            <colgroup>
                <col width="100" />
                <col width="300" />
                <col width="100" />
                <col width="300" />
                <col width="*" />
            </colgroup>
            <tr>
                <td class="tdTitle">用户ID/姓名：</td>
                <td class="tdCont">
                    <input id="yhxx" class="inputText" type="text" />
                </td>
                <td class="tdTitle">用户部门：</td>
                <td class="tdCont">
                    <select id="yhbmlist" class="inputSel">
                        <%--<option value=""></option>--%>
                        <%--<option value="32010001">立案庭</option>--%>
                    </select>
                </td>
                <td align="right">
                    <a class="tdh_btn tdh_btn_blue" onclick="doSearch()">
                        <i class="tdh_icon icon_search"></i>查询
                    </a>
                    <a class="tdh_btn tdh_btn_blue" onclick="addUser()">
                        <i class="tdh_icon icon_add"></i>新增
                    </a>
                    <a id="delete" class="tdh_btn tdh_btn_blue" onclick="doDelete()" >
                        <i class="tdh_icon icon_del"></i>删除
                    </a>
                </td>
            </tr>
        </table>
    </div>

    <!-- 底部=左+右 -->
    <div style="width: 100%; height: calc(100% - 100px)">
        <!-- 左： 部门树 -->
        <div style="width: 200px; height: 100%; float: left;background-color: #f7f7f3">
                 <ul id="treeCheck" class="ztree"></ul>
                 <a class="tdh_btn" style="" onclick="removeNodes()"><i class="tdh_icon icon_del"></i>删除</a>
        </div>

        <!-- 右：数据表框 -->
        <div id="userDiv" style="height: 100%;width: calc(100% - 200px); overflow: auto" >
            <div id="user_grid" style="width: 100%;height: 100%;padding: 0px;margin: 0px">

            </div>
        </div>
    </div>
</div>
<script>
    var basePath = "<%=basePath%>";
</script>
</body>
</html>

