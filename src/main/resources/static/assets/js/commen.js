function echoUserName(gurl,eid) {
    $.ajax(
        {
            url:gurl,
            async:true,
            type:"GET",
            success:function (data) {
                console.log(data);
                $("#"+eid).text(data);
                $("#"+eid).append('<span class="user-level"></span><span class="caret"></span>');
            }
        }
    );
}
//回显用户名
echoUserName("getMyUserName","myUserName");

function judgeRole(gurl,eid) {
    $.ajax(
        {
            url:gurl,
            async:true,
            type:"GET",
            success:function (data) {
                console.log(data);
                // $("#"+eid).text(data);
                if(data!=null&&data=="1"){
                    $("#"+eid).append('<li class="nav-item">\n' +
                        '\t\t\t\t\t\t\t<a href="user">\n' +
                        '\t\t\t\t\t\t\t\t<i class="la la-font"></i>\n' +
                        '\t\t\t\t\t\t\t\t<p>账户管理</p>\n' +
                        '\t\t\t\t\t\t\t\t<!--<span class="badge badge-danger">25</span>-->\n' +
                        '\t\t\t\t\t\t\t</a>\n' +
                        '\t\t\t\t\t\t</li>');
                }
            }
        }
    );
}
//根据角色显示账户管理
judgeRole("getMyRole","menuNav");

//编辑用户信息
function editMyProfile(){
    $.get({
        url:"/getMyProfile",
        dataType:"json",
        success:function (data) {
            if (data.returnCode=="1"){
                $("#editProfile").modal('show');
                $("#myUserId").val(data.data.id);
                $("#myLoginName").val(data.data.loginName);
                $("#myUsername1").val(data.data.username);
                $("#myPassword").val('');
            }
        }
    });
}
//
// $("#updateProfile").onclick(function updateProfile() {

// });

$(document).on('click', '#updateProfile', function() {
    var user={};
    user.id=$("#myUserId").val();
    user.loginName=$("#myLoginName").val();
    user.username=$("#myUsername1").val();
    user.password=$("#myPassword").val();
    $.post({
        url:"/updateProfile",
        type:"post",
        data:JSON.stringify(user),
        contentType: 'application/json',
        dataType:'json',
        success:function (data) {
            if (data.returnCode=="1"){
                $("#editProfile").modal('hide');
                $.notify({
                    message: '修改成功！',
                    type: 'success'
                });
                echoUserName("getMyUserName","myUserName");
                $("#myUserId").val('');
                $("#myLoginName").val('');
                $("#myUsername1").val('');
                $("#myPassword").val('');
            }
        }
    });
});