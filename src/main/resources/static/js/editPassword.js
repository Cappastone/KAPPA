
// var serverContext = "[[@{/}]]";
// function savePass(){
//     var pass = $("#password").val();
//     var valid = pass == $("#confirm-password").val();
//     if(!valid) {
//         $("#error").show();
//         return;
//     }
//     $.post(serverContext + "user/updatePassword",
//         {password: pass, oldpassword: $("#password").val()} ,function(data){
//             window.location.href = serverContext +"/home.html?message="+data.message;
//         })
//         .fail(function(data) {
//             $("#errormsg").show().html("Passwords do not match");
//         });
// }