var $$ = function( id ) { return document.getElementById( id ); };

function onflagged(qid) {
    var prefix = $$('112233-page-dynamic-prefix').value;
    let qindex = $$(qid + '_index').value;
    let checkbox  = qid + ':flaggedcheckbox';
    let box = 'quiznavbutton_' + qid;
    let img = qid + ':flaggedimg';
    if ($$(checkbox).checked){
        $$(box).classList.add('flagged');
        $$(img).src =  prefix + '/icon/flagged.svg';
        setFlag(qindex, 'true');
    }
    else{
        $$(box).classList.remove('flagged');
        $$(img).src = prefix + '/icon/unflagged.svg';
        setFlag(qindex, 'false');
    }
}

function clearchoice(quesid, box)
{
    $$(quesid+'answer-1').checked = true;
    $$(quesid+'_clearchoice').hidden = true;
    $$(box+'_img').style.backgroundColor = "rgba(0,189,49,0)";
    let submit_id = 'submit_answer_' + quesid;
    $$(submit_id.toString()).value = "";
    $$('quiznavbutton_' + quesid).title = "Chưa trả lời";
    let qindex = $$(quesid + '_index').value;
    setAnswer(qindex, -1);
}

function setClearchoice(quesid,box,ans)
{
    var prefix = $$('112233-page-dynamic-prefix').value;
    $$(quesid+'_clearchoice').hidden = false;
    var url = prefix + '/icon/checked.png';
    $$(box+'_img').style.backgroundColor = "#95e0f5";
    let ans_id = quesid + 'answer' + ans;
    let submit_id = 'submit_answer_' + quesid;
    $$(submit_id).value = $$(ans_id).value;
    $$('quiznavbutton_' + quesid).title = "Đã trả lời";
    let qindex = $$(quesid + '_index').value;
    setAnswer(qindex, ans);
}

function submitQuiz() {
    let isExecuted = confirm("Bạn có chắc là muốn nộp bài hay không?");
    if (isExecuted)
        $$('Quiz-test-submit-form').submit();
}

function setAnswer(quesid, ansid){
    var prefix = $$('112233-page-dynamic-prefix').value;
    if (prefix==null) prefix="";
    request_data = {
        question: quesid,
        answer: ansid
    }
    let request = $.ajax({
        url: prefix + '/AnswerAPI',
        type: 'POST',
        dataType: 'text',
        data: request_data
    });
    request.done(function (data) {
        console.log("send answer done!!!");
    });
    request.fail(function (msg){
        //window.alert(msg);
        console.log(msg);
    })
    request.always(function (){
        //console.log("time out: Ajax was call")
    });
}

function setFlag(quesid, flag){
    var prefix = $$('112233-page-dynamic-prefix').value;
    if (prefix==null) prefix="";
    request_data = {
        question: quesid,
        flag: flag
    }
    let request = $.ajax({
        url: prefix + '/FlagAPI',
        type: 'POST',
        dataType: 'text',
        data: request_data
    });
    request.done(function (data) {
        console.log("send flag done!!!");
    });
    request.fail(function (msg){
        //window.alert(msg);
        console.log(msg);
    })
    request.always(function (){
        //console.log("time out: Ajax was call")
    });
}

function docReady(fn) {
    // see if DOM is already available
    if (document.readyState === "complete" || document.readyState === "interactive") {
        // call on next available tick
        setTimeout(fn, 1);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}