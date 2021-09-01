var $$ = function( id ) { return document.getElementById( id ); };
function getServerTime()
{
    //console.log("Timeout was called");
    let prefix = document.getElementById('112233-page-dynamic-prefix').value;
    let data = {'TimeStart': $$('112233-time-start').value,
                'TimeLimit': $$('112233-time-limit').value };
    let request = $.ajax({
        type: "GET",
        dataType: "html",
        url: prefix + "/TimeAPI",
        data: data
    });
    request.done(function (data) {
        $$("time-left-1210").innerHTML = data;
        if (data.toString() === "TIMEOUT")
        {
            console.log("time: " + data.toString());
            break_timer();
            alert("Đã hết giờ làm bài!");
            $$('Quiz-test-submit-form').submit();
        }
    });
    request.fail(function (data){
        //window.alert(msg);
        console.log(msg);
    })
    request.always(function (){
        //console.log("time out: Ajax was call")
    });
}

let interval = setInterval(getServerTime,1000);

function break_timer(){clearInterval(interval);}
