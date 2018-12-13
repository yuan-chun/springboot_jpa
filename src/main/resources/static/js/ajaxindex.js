
function fire_ajax_submit(){
    // alert("aaaa");
    // var search = {};
    // search["userName"] = $('#username').val();
    // $("#btn-search").prop("disabled", true);
    // $.ajax({
    //     type: 'POST',
    //     contentType: "application/json",
    //     url: local_web_root + "user/findByUserName",
    //     data: JSON.stringify(search),
    //     dataType: 'json',
    //     cache: false,
    //     timeout: 600000,
    //     success: function(data){
    //         var json = "<h4>Ajax Response</h4><pre>"
    //             + JSON.stringify(data, null, 4) + "</pre>";
    //         $('#feedback').html(json);
    //         console.log("SUCCESS : ", data);
    //         $("#btn-search").prop("disabled", false);
    //     },
    //     error: function(e){
    //         var json = "<h4>Ajax Response</h4><pre>"
    //             + e.responseText + "</pre>";
    //         $('#feedback').html(json);
    //
    //         console.log("ERROR : ", e);
    //         $("#btn-search").prop("disabled", false);
    //
    //     }
    // })


    var search = {};
    search["userName"] = $('#username').val();
    alert(JSON.stringify(search));
    $.ajax({
        url: "http://localhost:13500/user/findByUserName",
        async: false,
        timeout: 3000,
        contentType: "application/json",
        dataType: 'json',
        type: "POST",
        data: JSON.stringify(search),
        success: function (data) {
            alert(1)
        },
        error: function () {
            alert(2)
        }
    });


}
