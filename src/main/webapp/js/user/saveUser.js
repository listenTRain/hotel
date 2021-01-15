layui.use(['jquery','layer','laydate','form'], function() {
    var $ = layui.jquery,   //jquery
        layer = layui.layer,  //弹出层
        laydate = layui.laydate,  //日期模块
        form = layui.form;  //表单模块

    //日期时间选择器
    laydate.render({
        elem: '#createDate'
        ,type: 'datetime'
        ,format:'yyyy/MM/dd HH:mm:ss'
        ,value:new Date()
    });

    //初始化所有角色信息
    loadAllRoles();


    var usernameIf = false;  //验证用户名唯一性变量

    //加载所有角色信息
    function loadAllRoles() {
        $.ajax({
            type: "POST",  //请求方式，POST请求
            url: "roles/loadAll",   //访问服务器端的路径
            success: function (data) {  //请求执行正常函数回调
                var roleStr = '<option value="">--请选择角色--</option>';
                $.each(data,function (i, role) {
                    roleStr += '<option value="'+role.id+','+role.roleName+'">'+ role.roleName+'</option>';
                });
                $("#roleSel").html(roleStr); //将数据标签填充
                form.render('select'); //渲染下拉框
            },
            error:function () {  //请求执行异常时的函数回调
                layer.msg('服务器异常', {icon: 3,time:2000,anim: 6,shade:0.5});
            }
        })
    }

    //表单验证
    form.verify({
        //函数式的方式
        username: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length < 4 || value.length > 8) {
                return '用户名必须为4-8位';
            }
            checkUsername(value);
            if (!usernameIf){
                return '此用户名已被占用';
            }
        },
        //也支持下述数组的形式
        pwd: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格'],
        pwd2:function (value, item) {
            if (value != $("#pwd").val()){
                return '两次密码不一致';
            }
        }
    });

    //验证用户名的唯一性
    function checkUsername(username) {
        $.ajax({
            type:"POST",  //请求方式，POST请求
            url:"user/getCountByPramas",   //访问服务器端的路径
            async:false,  //允许ajax外部的变量获得去数据
            data:{"username":username},
            success:function (data) {  //请求执行正常函数回调
                if(data>0){
                    usernameIf = false;
                }else {
                    usernameIf = true;
                }
            },
            error:function () {  //请求执行异常时的函数回调
                layer.msg('服务器异常', {icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }

    //监听用户数据的添加提交
    form.on('submit(demo2)',function (data) {
        //得到角色id和角色名数组
        var arrRole = data.field.roleId.split(",");
        //删除roleId的数据
        delete data.field.roleId;
        var saveJsonUser = data.field;
        saveJsonUser['useStatus'] = '1';
        saveJsonUser['roleId'] = arrRole[0];
        saveJsonUser['isAdmin'] = arrRole[1];
        saveUser(saveJsonUser);  //调用房间数据添加函数
        return false;
    });

    //房间数据添加函数
    function saveUser(saveJsonUser) {
        $.ajax({
            url:"user/save",
            type:"post",
            data:saveJsonUser,
            success:function (data) {  //请求执行正常函数回调
                if(data=='success'){
                    layer.msg('用户信息添加成功。。', {icon: 1,time:2000,anim: 4,shade:0.5});
                }else {
                    layer.msg('用户信息添加失败！！', {icon: 2,time:2000,anim: 3,shade:0.5});
                }
            },
            error:function () {  //请求执行异常时的函数回调
                layer.msg('服务器异常', {icon: 3,time:2000,anim: 6,shade:0.5});
            }
        })
    }

});