$(document).ready(function(){
    $('.collapsible').collapsible();

    $('span[id^="spanAvatar_"]').click(function(){
        var $imgAvatarPrincipal = $("#avatarPrincipal");
        var $spanAvatar = $(this);

        var idAvatarSeleccionado = $spanAvatar.attr("data-id-avatar");
        var srcAvatarSeleccionado = $spanAvatar.children().attr('src');

        $imgAvatarPrincipal.attr("data-id-avatar", idAvatarSeleccionado);
        $imgAvatarPrincipal.prop("src", srcAvatarSeleccionado);

        $('span[id^="spanAvatar_"]').each(function(){
            $(this).removeClass("selected-avatar");
        });

        $spanAvatar.addClass("selected-avatar");

    });


    $('#btn-modificar-datos').click(function(){

        var userDTO = {};
        userDTO.username = $('#username').val();
        userDTO.email = $('#email').val();
        userDTO.idAvatar = $("#avatarPrincipal").attr("data-id-avatar");


        modificarDatos(userDTO);

    });


    $('#btn-change-password').click(function(){

        var currentPassword = $('#currentPassword').val();
        var newPassword = $('#newPassword').val();
        var repeatedNewPassword = $('#repeatedNewPassword').val();

        if(newPassword !== repeatedNewPassword){
            alert('Las nuevas contraseñas no coinciden');
            return;
        }

        var changePasswordDTO = {};
        changePasswordDTO.currentPassword = currentPassword;
        changePasswordDTO.newPassword = newPassword;
        changePasswordDTO.repeatedNewPassword = repeatedNewPassword;

        changePassword(changePasswordDTO);

    });



});


function modificarDatos(userDTO){
    $.ajax({
        url: baseURL + 'editar-datos-usuario',
        method: 'POST',
        data: JSON.stringify(userDTO),
        contentType: 'application/json'
    })
        .done(function () {
            window.location.reload();
        })
        .fail(function (xhr, status) {
            var error = JSON.parse(xhr.responseText);
            console.log(error.msg);
        });
}


function changePassword(changePasswordDTO){

    $.ajax({
        url: baseURL + 'editar-password-usuario',
        method: 'POST',
        data: JSON.stringify(changePasswordDTO),
        contentType: 'application/json'
    })
        .done(function () {
            window.location.reload();
        })
        .fail(function (xhr, status) {
            var error = JSON.parse(xhr.responseText);
            console.log(error.msg);
        });
}
