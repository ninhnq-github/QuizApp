var $$ = function( id ) { return document.getElementById( id ); };

function check_newQuestionBank()
{
    let checkbox = $$('add-new-ques-bank-checkbox');
    let textbox = $$('1231-textbox');
    let select = $$('1231-selection');
    console.log(checkbox.name);
    console.log(textbox.name);
    console.log(select.name);
    if (checkbox.checked)
    {
        textbox.disabled = false;
        select.disabled = true;
    }
    else
    {
        textbox.disabled = true;
        select.disabled = false;
    }
}

function addNewQuestion(){
    let quesFile = $$('1231-filename').value;
    if (quesFile === ""){
        alert("Question file not found!");
        return;
    }
    let quesBank = "null";
    if ($$('add-new-ques-bank-checkbox').checked) {
        quesBank = $$('1231-textbox').value;
        if (quesBank === ""){
            alert("Question bank name can be empty!");
            return;
        }
    } else
        quesBank = $$('1231-selection').value;
    let newbank = $$('add-new-ques-bank-checkbox').checked;
    let data = {
        addNewBank : newbank,
        questionsFile : quesFile,
        questionsBank : quesBank
    }
    let prefix = $$('112233-page-dynamic-prefix').value;
    $.ajax({
        url: prefix + "/AddQuestionAPI",
        type: "POST",
        data: data,
        success: function (msg){
            alert(msg);
        },
        error: function (error){
            alert(error);
        }
    });
}