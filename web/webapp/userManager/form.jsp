<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.tdh.jzgl.util.CommonUtil" %>
<%
    String basePath = CommonUtil.getContextURI(request);
    String yhdm = request.getParameter("yhdm");
    String type = request.getParameter("type");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>用户表单</title>
    <link rel="stylesheet" href="<%=basePath%>/webui/tdh/form/css/form.css" />
    <link rel="stylesheet" href="<%=basePath%>/webui/tdh/btn/css/btn.css" />
    <link href="<%=basePath%>/ext/uploader/uploadify.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="<%=basePath%>/webui/lib/jquery.min.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/tdh/form/js/tdh.form.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/tdh/btn/js/tdh.btn.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webui/plugg/DatePicker/WdatePicker.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/ext/uploader/swfobject.js"></script>
    <script type="text/javascript" src="<%=basePath%>/ext/uploader/jquery.uploadify.v2.1.4.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/webapp/userManager/js/form.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/webapp/userManager/js/user.js" ></script>

    <style>
        html{
            width: 100%;
            height: 100%;
            overflow: auto;
            overflow-x: hidden;
        }
    </style>
</head>
<body style="padding: 0 10px;box-sizing: border-box;">
<div class="tdh_form_title">用户信息表</div>
<%--<div class="tdh_form_subtitle">副标题</div>--%>
<div class="tdh_form_caption"><i class="tdh_form_tag"></i>基本信息</div>
<form id="userForm" name="userForm" enctype = "multipart/form-data">
    <table class="tdh_form">
        <colgroup>
            <col width="11%" />
            <col width="22%" />
            <col width="11%" />
            <col width="22%" />
            <col width="11%" />
            <col width="22%" />
        </colgroup>
        <tr>
            <td class="tdTitle"><i class="required">*</i>用户ID</td>
            <td class="tdCont"><input class="inputText mustedit" type="text" name="yhid" id="yhid"/></td>
            <td class="tdTitle"><i class="required">*</i>口令</td>
            <td class="tdCont"><input class="inputText mustedit" type="password" name="yhkl" id="yhkl"/></td>
            <td class="tdTitle"><i class="required">*</i>确认口令</td>
            <td class="tdCont"><input class="inputText mustedit" type="password" name="cfkl" id="cfkl"/></td>
        </tr>
        <tr>
            <td class="tdTitle"><i class="required">*</i>姓名</td>
            <td class="tdCont"><input class="inputText mustedit" type="text" name="yhxm" id="yhxm"/></td>
            <td class="tdTitle">性别</td>
            <td class="tdCont">
                <select class="inputSel" data-edit="true" name="yhxb" id="yhxb">
                    <%--<option value=""></option>--%>
                    <%--<option value="09_00003-1">男</option>--%>
                    <%--<option value="09_00003-2">女</option>--%>
                    <%--<option value="09_00003-255">其他</option>--%>
                </select>
            </td>
            <td class="tdTitle">部门</td>
            <td class="tdCont">
                <select class="inputSel" data-edit="true" name="yhbm" id="yhbm">
                    <%--<option value=""></option>--%>
                    <%--<option value="32010001">立案庭</option>--%>
                    <%--<option value="32010002">业务庭</option>--%>
                    <%--<option value="32010003">办公室</option>--%>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tdTitle">出生日期</td>
            <td class="tdCont"><input class="Wdate inputText" style="border: 0px" type="text" onClick="WdatePicker()" readonly="readonly" name="csrq" id="csrq"/></td>
            <td class="tdTitle">排序号</td>
            <td class="tdCont"><input class="inputText" type="text" name="pxh" id="pxh"/></td>
            <td class="tdTitle">禁用</td>
            <td class="tdCont">
                <label><input name="sfjy" class="inputCheck" type="checkbox" id="sfjy" /></label>

            </td>
        </tr>
        <tr>
            <td class="tdTitle">用户头像</td>
            <td class="tdCont" colspan="5">
                <a class="tdh_btn tdh_btn_white" onclick="selFile()"><i class="tdh_icon icon_upload"></i>选择要上传的文件</a>
                <input type="file" name="file" id="file" style="display: none"/>
            </td>
        </tr>
    </table>
</form>
<%--    按钮区域    --%>
<div style="text-align: center; padding-top: 30px;">
    <a id="save" class="tdh_btn tdh_btn_blue" onclick="doSave();"><i class="tdh_icon icon_save"></i>保存</a>
    <a class="tdh_btn tdh_btn_blue" onclick="window.close()"><i class="tdh_icon icon_return"></i>关闭</a>
</div>
</body>

<script>
    var type = "<%=type%>";
    var yhdm = "<%=yhdm%>";
    var BASE_PATH = "<%=basePath%>";
</script>
</html>

