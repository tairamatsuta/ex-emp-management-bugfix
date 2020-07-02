$(function(){
    $.ajax({
        url: "http://localhost:8080/employee/autoComplete",
        dataType: "json",
        type: "GET"
    }).then(function(data){
        $("#autoComplete_name").autocomplete({
            source : data,
            autoFocus: true,
            minLength: 1
        });
        },function(){
    });
});