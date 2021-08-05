function getServerTime()
{
    //console.log("Timeout was called");
    var prefix = document.getElementById('112233-page-dynamic-prefix').value;
    var data = {'auth':191122333, 'key': 256611129};
    var request = $.ajax({
        type: "GET",
        dataType: "html",
        url: prefix + "/ServerTime",
        data: data
    });
    request.done(function (data) {
        $$("time-left-1210").innerHTML = data;
        console.log("time: " + data.toString());
    });
    request.fail(function (xhr, msg){
        window.alert(msg);
    })
    request.always(function (){
        //console.log("time out: Ajax was call")
    });
}

setInterval(getServerTime,1000);