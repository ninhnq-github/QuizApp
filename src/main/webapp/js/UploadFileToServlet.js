var $$ = function( id ) { return document.getElementById( id ); };

function UploadFileToServlet() {
    let prefix = $$('112233-page-dynamic-prefix').value;
    var url = prefix + "/UploadAPI";
    var form = $('#1312-form-add-new-question')[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: "multipart/form-data",
        processData: false,  // Important!
        contentType: false,
        cache: false,
        url: url,
        data: data,
        timeout: 600000,
        success: function (msg) {
            var response = JSON.parse(msg);
            var status = response.status;
            let filename = response.filename;
            $$('1231-filename').value = filename;
            console.log($$('1231-filename').value);
            console.log(filename);
            if (status == 1) {
                alert("File has been uploaded successfully");
            } else {
                alert("Couldn't upload file");
            }
        },
        error: function (msg) {
            alert("Couldn't upload file");
        }
    });
}