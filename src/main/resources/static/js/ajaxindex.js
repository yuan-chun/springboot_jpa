
function queryByName(){
    var search = {};
    search["userName"] = $('#userName').val();
    $.ajax({
        // url: "http://localhost:13500/user/findByUserName",
        url: local_web_root + "/user/findByUserName",
        async: false,
        timeout: 3000,
        contentType: "application/json",//发送给服务器参数类型
        dataType: 'json',//返回数据类型
        type: "GET",//get对应data为对象
        data:search,
        success: function (data) {
            console.log("SUCCESS : ", JSON.stringify(data));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });


}
function queryLikeName(){
    var search = {};
    search["userName"] = $('#userName').val();
    $.ajax({
        // url: "http://localhost:13500/user/findByUserName",
        url: local_web_root + "/user/findLikeName",
        async: false,
        timeout: 3000,
        contentType: "application/json",//发送给服务器参数类型
        dataType: 'json',//返回数据类型
        type: "GET",//get对应data为对象
        data:search,
        success: function (data) {
            console.log("SUCCESS : ", JSON.stringify(data));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });


}


function queryByPage(){
    $.ajax({
        // url: "http://localhost:13500/user/findByUserName",
        url: local_web_root + "/user/findByPage",
        async: false,
        timeout: 3000,
        dataType: 'json',//返回数据类型
        type: "GET",//get对应data为对象
        data:{
            page: 3,
            size: 3,
            "user.userName":"yuan",
            "user.id":8
        },
        success: function (data) {
            console.log("SUCCESS : ", JSON.stringify(data));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });


}

function findAll(){
    $.ajax({
        // url: "http://localhost:13500/user/findByUserName",
        url: local_web_root + "/user/findAll",
        async: false,
        timeout: 3000,
        dataType: 'json',//返回数据类型
        type: "GET",//get对应data为对象
        data:{
            "userName": "yuan",
            "password": "1234567",
            "id": 2,
            "sex": "1"
        },
        success: function (data) {
            console.log("SUCCESS : ", JSON.stringify(data));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });


}



function save(){
    var search = {};
    search["userName"] = $('#userName').val();
    alert(JSON.stringify(search));
    $.ajax({
        // url: "http://localhost:13500/user/findByUserName",
        url: local_web_root + "/user/save",
        async: false,
        timeout: 3000,
        contentType: "application/json",
        dataType: 'json',
        type: "PUT",
        data: JSON.stringify(				{
            "userName": "yuan",
            "password": "1234567",
            "height": "190",
            "createTime": "2018-12-12 12:11:11",
            "modifyTime": "2018-12-12 12:11:11",
            "deleteFlag": 2,
            "sex": "1"
        }),
        success: function (data) {
            console.log("SUCCESS : ", JSON.stringify(data));
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });


}

