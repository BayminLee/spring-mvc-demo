var wjlxlimit = "*.jpg;*.png;";
var sizelimit= 1024 * 1024;
var BASE_PATH = "<%=basePath%>";
$(function () {
    initUpload();
    initSel();
    if(type == "view" || type == "update"){
        $.ajax({
            type:"POST",
            url:BASE_PATH+"/user/viewUser.do",
            data:{
                yhdm:yhdm,
                state:"view"
            },
            ContentType: "application/json",
            success:function (res) {
                //获取的json值回填至表单

                $("#yhid").val(res.YHID);
                $("#yhxm").val(res.YHXM);
                $("#yhkl").val(res.YHKL);
                $("#cfkl").val(res.YHKL);
                setSelVal($("#yhxb"),res.YHXB);
                $("#sfjy").prop('checked');
                if (res.SFJY == 1) {
                    setCheckVal("#sfjy",true);
                } else {
                    setCheckVal("#sfjy",false);
                }
                $("input[name='sfjy'][value="+res.SFJY+"]").attr("checked",true);
                setSelVal($("#yhbm"),res.YHBM);
                $("#pxh").val(res.PXH);
                $("#csrq").val(res.CSRQ);
                //用户ID设置为只读
                $("#yhid").attr("readOnly","true");
            }
        })
    }
    if (type == "view") {
        $.ajax({
            type:"POST",
            url:BASE_PATH+"/user/viewUser.do",
            data:{
                yhdm:yhdm,
                state:"view"
            },
            ContentType: "application/json",
            success:function (res) {
                //获取的json值回填至表单
                $("#yhid").val(res.YHID);
                $("#yhxm").val(res.YHXM);
                $("#yhkl").val(res.YHKL);
                $("#cfkl").val(res.YHKL);
                $("#yhxb").val(res.YHXB);
                if (res.SFJY == 1) {
                    setCheckVal("#sfjy",true);
                } else {
                    setCheckVal("#sfjy",false);
                }
                $("#yhbm").val(res.YHBM);
                $("#pxh").val(res.PXH);
                $("#csrq").val(res.CSRQ);
                //设置只读属性
                $(":input").attr("disabled", "disabled");
                $("#close").removeAttr("disabled");

                //隐藏保存按钮
                $("#save").hide();
            }
        })
    }
});

/**
 * 初始化部门和性别下拉框
 */
function initSel() {
    $.ajax({
        type:"post",
        url:BASE_PATH + "/dept/getBmList.do",
        dataType: "json",
        ContentType: "application/json",
        success:function (res) {
            $("#yhbm").html(res);
        },
        error:function () {
            alert("未知异常！");
        }
    });
    $.ajax({
        type:"post",
        url:BASE_PATH + "/user/getXbList.do",
        dataType:"json",
        ContentType: "application/json",
        success:function (res) {
            $("#yhxb").html(res);
        },
        error:function () {
            alert("性别下拉框初始化异常！");
        }
    })
}
/*function doInsert() {
    var rtn = check();
    if (!rtn) {
        return;
    }
    var yhidVal = trim($('#yhid').val());
    var yhxmVal = trim($('#yhxm').val());
    var yhxbVal = trim($('#yhxb').val()) ;
    var yhklVal = trim($('#yhkl').val()) ;
    var sfjyVal = trim($('input[name = "sfjy"]:checked').val());
    var cfklVal = trim($('#cfkl').val()) ;
    var yhbmVal = trim($('#yhbm').val()) ;
    var pxhVal = trim($('#pxh').val());
    var bmdmVal = "320100";
    var csrqVal = trim($('#csrq').val());
    
    if (trim(yhklVal) == trim(cfklVal)){
        var url =basePath+"/user/addUser.do";
        $.ajax({
            type:"POST",
            url:url,
            dataType:'json',
            ContentType: "application/json",
            data:{
                yhid:yhidVal,
                yhxm:yhxmVal,
                yhkl:yhklVal,
                yhxb:yhxbVal,
                sfjy:sfjyVal,
                yhbm:yhbmVal,
                pxh:pxhVal,
                yhdm:bmdmVal + yhidVal,
                csrq:csrqVal,
                type:type
            },
            success:function (res) {
                if(res.status == "success") {
                    alert(res.message);
                    doSearch();
                    window.close();
                }else {
                    alert(res.errorMsg);
                }
            },
            error: function () {
                alert("未知异常！");
            }
        })
    }

}*/

/**
 * 校验并输出
 */
function check() {
    var check = "ok";
    var mustArr = $(":input[class*='mustedit']");
    $.each(mustArr, function(i, n) {
        var val = $.trim($(n).val());
        var id = $.trim($(n).attr('id'));

        if (val == "") {
            check = "no"
            $(n).css("background", "#ff626f");
        } else {
            $(n).css("background", "#FFFFFF");
        }
    });
    if (check == "no") {
        alert("红色星号标志是必填项，请检查必填项!");
        return false;
    }
    return true;
}

function initUpload() {
    $("#yhtx").uploadify({
        'uploader': BASE_PATH + '/ext/uploader/uploadify.swf',
        'script': BASE_PATH + '/user/uploadFile.do',
        'cancelImg': BASE_PATH + '/ext/uploader/cancel.png',
        'folder': 'upload',
        'queueID': 'fileQueue',
        'fileExt': wjlxlimit,
        'fileDesc': wjlxlimit,
        'auto': true,
        'method' : 'GET',
        'multi': true,
        'sizeLimit': sizelimit,
        'height':'28',
        'width':'46',
        'wmode':'transparent',
        'buttonImg':BASE_PATH + '/images/imgbtn_yr-b.png',
        'scriptData': {'type':'importFile','ahdm': "",'contentPath': "" },
        'onAllComplete': checkWj,
        'onError': function (event,queueId,fileObj,errorObj) {
            if (errorObj.type == 'File Size') {
                alert("上传的文件超出大小限制，最大为" + sizelimit/(1024*1024) + "M，请调整您的文件大小");
            } else {
                alert(errorObj.type+"出错："+errorObj.info+"，请您重新尝试");
            }
        },
        'onSelectOnce':function(file){
            // $('body').mask("正在引入文书");
        }
    });
}

/**
 * 保存用户信息
 */
function doSave() {
    var rtn = check();
    if (!rtn) {
        return;
    }
    //序列化
    // var params = $("#userForm").serialize();
    if ($('input[name="sfjy"]').is(":checked") == true) {
        $('input:checkbox[name="sfjy"]').val("1");
    } else {
        $('input:checkbox[name="sfjy"]').val("0");
    }
    var formData = new FormData($("#userForm")[0]);
    var yhxm = trim($('#yhxm').val());
    var yhbm = trim($('#yhbm').val()) ;

    $.ajax({
        type: "POST",
        url: BASE_PATH + "/user/saveUser.do?type=" + encodeStr(type),
        data: formData,
        cache:false,
        processData:false,
        contentType:false,
        dataType:"json",
        success: function (rsp) {
            if (!rsp || rsp.status != "success") {
                alert("保存失败！");
                return;
            }
            alert("保存成功！");
            window.opener.doSearch();
            window.close();//关闭当前窗口
        },
        error: function () {
            alert("未知异常！");
        }
    })
}

function xzwj(){
    $('#file').click();
}
function checkWj() {
    alert(1)
}

