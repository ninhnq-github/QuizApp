function getServerTime()
{
    //console.log("Timeout was called");
    let prefix = document.getElementById('112233-page-dynamic-prefix').value;
    let data = {'auth':191122333, 'key': 256611129};
    let request = $.ajax({
        type: "GET",
        dataType: "html",
        url: prefix + "/TimeAPI",
        data: data
    });
    request.done(function (data) {
        $$("time-left-1210").innerHTML = data;
        if (data.toString() === "TIMEOUT")
            break_timer();
        console.log("time: " + data.toString());
    });
    request.fail(function (xhr, msg){
        window.alert(msg);
    })
    request.always(function (){
        //console.log("time out: Ajax was call")
    });
}

let interval = setInterval(getServerTime,1000);

function break_timer(){clearInterval(interval);}
