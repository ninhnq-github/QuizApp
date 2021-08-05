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
}

function setClearchoice(quesid,box)
{
    var prefix = $$('112233-page-dynamic-prefix').value;
    $$(quesid+'_clearchoice').hidden = false;
    var url = prefix + '/icon/checked.png';
    $$(box+'_img').style.backgroundColor = "#95e0f5";
    console.log(box+'_img')
}

