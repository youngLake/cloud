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