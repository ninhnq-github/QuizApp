var $$ = function( id ) { return document.getElementById( id ); };

function doTest(qid){
    var prefix = $$('112233-page-dynamic-prefix').value;
    let isExecuted = confirm("Bạn có chắc muốn vào làm bài + " + qid + " + hay không?");
    console.log(isExecuted);
    if (isExecuted){
        window.location.replace(prefix + "/Quiz?QuizID=" + qid);
    }
}

function logout(){
    $$('112233-logout-form').submit();
}