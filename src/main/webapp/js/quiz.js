var $$ = function( id ) { return document.getElementById( id ); };

function onflagged(checkbox, box, img) {
    var prefix = $$('112233-page-dynamic-prefix').value;
    if ($$(checkbox).checked){
        $$(box).classList.add('flagged');
        $$(img).src =  prefix + '/icon/flagged.svg';
    }
    else{
        $$(box).classList.remove('flagged');
        $$(img).src = prefix + '/icon/unflagged.svg';
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
    //console.log($$(ans_id.toString()).value);
    //console.log($$(submit_id.toString()).value);
    //console.log(submit_id);
    //console.log(ans_id);
    //console.log(box+'_img');
}

function submitQuiz() {
    let isExecuted = confirm("Bạn có chắc là muốn nộp bài hay không?");
    if (isExecuted)
        $$('Quiz-test-submit-form').submit();
}

