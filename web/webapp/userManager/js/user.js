/**
 * 页面初始化执行
 */
$(function(){
    //grid初始化
    doInitGrid();
    //部门列表下拉框，部门树初始化
    doInitDepart();
    //执行一次查询
    doSearch();
});
/**
 初始化grid对象的代码块
 */
var mygrid;
function doInitGrid() {
    // 根据页面上定义的容器id 实例化grid对象
    mygrid = new dhtmlXGridObject("user_grid");
    // 设置网格图像的路径 指那些网格必要的图像，与网格中表示数据时使用的图像无关。
    mygrid.setImagePath(basePath + "/webui/plugg/dhtmlx/dhtmlxGrid/codebase/imgs/");
    mygrid.setHeader("<input id='titleChkBox' onclick='setAllCheckBox()' type='checkbox' value='0' >,序号,编辑,删除,浏览,姓名,帐号,部门,性别"); // 定义网格头
    mygrid.setInitWidthsP("3,7,10,10,10,20,20,10,10");
    mygrid.setColAlign("center,center,center,center,center,center,center,center,center");
    mygrid.setSkin("dhx_skyblue");
    mygrid.setColTypes("ch,ro,img,img,img,ro,ro,ro,ro");
    mygrid.init();
    // mygrid.loadXML("<%=basePath%>/webapp/userManager/grid_data.xml");
}
/**
 * 表头全选框
 */
function setAllCheckBox(){
    var flag = $('#titleChkBox').is(':checked');
    if(flag){
        mygrid.setCheckedRows(0,1);
    }else{
        mygrid.setCheckedRows(0,0);
    }
}
/**
 * 部门树设置
 * @type {{data: {simpleData: {enable: boolean}}}}
 */
var setting = {
    check: {
        enable: true,//每个节点上是否显示 CheckBox
        chkboxType: { "Y": "s", "N": "ps" } //父节点与子节点勾选是否相互影响
    },
    data: {
        simpleData: {//简单数据模式
            enable: true
        }
    },
    callback: {
        onClick:doSearchByTree,
    }
};

/**
 * 点击部门树节点查询
 * @param event
 * @param treeId
 * @param treeNode
 * @returns {boolean}
 */
function doSearchByTree(event,treeId,treeNode) {
    doSearch(treeNode.id);
    return false;
}
/**
 * 静态部门树节点
 * @type {*[]}
 */
// var zNodes =[
//     { id:3201, pId:0, name:"江苏省高级人民法院", open:true, iconSkin:"courtIcon"},
//     { id:320100, pId:3201, name:"南京中院",open:true, iconSkin:"crowdIcon"},
//     { id:32010003, pId:320100, name:"办公室", iconSkin:"personIcon"},
//     { id:32010001, pId:320100, name:"立案庭", iconSkin:"personIcon"},
//     { id:32010002, pId:320100, name:"业务庭", iconSkin:"personIcon"},
//     { id:320101, pId:3201, name:"苏州中院", iconSkin:"crowdIcon"},
//     { id:32010101, pId:320101, name:"执行局", iconSkin:"personIcon"}
// ];

/**
 * 部门树和部门列表下拉框初始化
 */
function doInitDepart() {
    $.ajax({
        type:"post",
        url:basePath + "/dept/getBmList.do",
        dataType: "json",
        ContentType: "application/json",
        success:function (res) {
            $("#yhbmlist").html(res);//将数据初始化至部门下拉框
            $("#yhbm").html(res);
        },
        error:function () {
            alert("未知异常！");
        }
    });
    $.ajax({
        type:"post",
        url:basePath + "/dept/getBmTree.do",
        dataType:"json",
        ContentType:"application/json",
        success:function (res) {
            var zNodes = res;
            //部门树初始化
            $.fn.zTree.init($("#treeCheck"), setting, zNodes);
        },
        error:function () {
            alert("未知异常！");
        }
    })
}

/**
 * 查询并加载到grid
 * @param bmdm
 */
function doSearch(bmdm) {
    var yhxx = $.trim($("#yhxx").val());
    if ($.trim(bmdm) == '') {
        bmdm = $.trim($("#yhbmlist").val());
    }
    //var yhbm = $.trim($("#yhbmlist").val());
    mygrid.clearAll();
    mygrid.loadXML(basePath + "/user/queryUserByYhxx.do?yhxx=" + encodeURI(yhxx) + "&yhbm=" + encodeURI(bmdm)+"&time="+new Date().getTime());

}

/**
 * 删除单个用户
 * @param yhdm
 */
function deleteUser(yhdm) {
    if(window.confirm('你确定要删除吗')){
        $.ajax({
            type:"post",
            url:basePath+"/user/deleteUserByYhdm.do",
            data:{
                yhdm:yhdm
            },
            success:function (res) {
                alert("删除成功！");
                doSearch();
                // mygrid.deleteRow(yhdm);
            },
            error:function () {
                alert("删除失败！");
            }

        })

    }

}
/**
 根据选中的用户id删除用户
 */
function doDelete() {
    if (confirm("你确定要删除选中的用户吗？")) {
        var users = mygrid.getCheckedRows(0);
        if (users == "") {
            alert("您未选中任何用户");
        } else {
            $.ajax({
                type : "POST",
                url : basePath+"/user/deleteUsers.do",
                data : {
                    listUser : users
                },
                dataType : "text",
                success : function(data) {
                    alert(data);    //data是返回的操作转态success
                    //将users转化为数组，批量删除
                    // var userArr = [];
                    // userArr = users.split(",");
                    // for (var i = 0; i < userArr.length; i++) {
                    //     mygrid.deleteRow(userArr[i]);
                    // }
                    doSearch();
                },
                error : function(data) {
                    alert("删除功能异常！");
                }
            });
        }
    } else {
    }
}

/**
 * 删除树节点
 */
function removeNodes() {
    var treeObj = $.fn.zTree.getZTreeObj("treeCheck");
    var selectnodes = treeObj.getCheckedNodes(true);//获取勾选节点 数组对象 Array(JSON)
    var nodeIds = new Array();

    if (selectnodes.length == 0) {
        alert("您尚未选中任何部门");
    } else {
        for (var i = 0; i < selectnodes.length; i++) {
            if (selectnodes[i].isParent) {
                alert("选中的部门包含子部门，不可删除");
                return;
            } else {
                nodeIds[i] = selectnodes[i].id;

            }
        }
        if (confirm("确定删除选中部门？")) {
            $.ajax({
                type:"post",
                url: basePath + "/dept/removeDept.do",
                data: {
                    nodeIds:nodeIds
                },
                dataType : "json",
                traditional : true,
                success:function (data) {
                    if (data.indexOf("containUser") > -1) {
                        alert("选中部门包含用户，无法删除");
                    } else if (data.indexOf("success") > -1) {
                        for (var i = 0; i < selectnodes.length; i++) {
                            treeObj.removeNode(selectnodes[i]);
                        }
                        alert("删除成功！");
                    } else {
                        alert("删除失败！");
                    }
                },
                error:function (data) {
                    alert("删除异常！");
                }
            })
        } else {
            return;
        }
    }
}

/**
 * 新增一个用户
 */
function addUser() {
    openwindow("add", "");
}

/**
 * 修改用户信息
 * @param yhdm
 */
function updateUser(yhdm) {
    openwindow("update",yhdm);
}
/**
 * 根据主键 用户代码 查看个人信息
 * @param   yhdm
 */
function viewUser(yhdm) {
    openwindow("view",yhdm);
}

function openwindow(type, yhdm) {
    var iWidth = 800; //弹出窗口的宽度;
    var iHeight = 400; //弹出窗口的高度;
    //window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
    var iTop = (window.screen.height - 30 - iHeight) / 2; //获得窗口的垂直位置;
    var iLeft = (window.screen.width - 10 - iWidth) / 2; //获得窗口的水平位置;
    //对参数值进行编码加密，同时防止后端乱码
    window.open("webapp/userManager/form.jsp?type=" + encodeStr(type) + "&yhdm=" + encodeStr(yhdm), 'table', 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth +
        ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft +
        ',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

function selFile(){
    $("#file").click();
}
/**
 * 去除空格
 * @param value
 * @returns {*}
 */
function trim(value) {
    if (value)
        value = value.replace(/^\s*|\s*$/g, "");
    if (!value)
        return "";
    else
        return value;
}
/**
 * 对参数编码
 * @param {Object} val
 */
function encodeStr(val) {
    return encodeURIComponent(encodeURIComponent(trim(val)));
}