var wjlxlimit = "*.jpg;*.png;";
var sizelimit= 1024 * 1024;
$(function () {
    initUpload();
});

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
    var params = $("#userForm").serialize();
    // var formData = new FormData();
    // formData.append("file", $("#yhtx")[0].files);
    $.ajax({
        type: "POST",
        url: BASE_PATH + "/user/saveUser.do",
        data: params,
        success: function (rsp) {
            if (!rsp || rsp.status != "success") {
                alert("保存失败！");
                return;
            }
            alert("保存成功！");
            //......
        },
        error: function () {
            alert("未知异常！");
        }
    })
}

function checkWj() {
    alert(1)
}