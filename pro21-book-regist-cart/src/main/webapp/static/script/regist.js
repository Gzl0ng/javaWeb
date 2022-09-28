function $(id){
    return document.getElementById(id);
}

function preRegist(){
    //window里有document还有history

    //DOM方式:Document
// var unameTxt = document.getElementById("unameTxt");
    //BOM方式:Browser 浏览器对象模型
    // document.forms[0].uname

//用户名不能为空，而且是6~16位数组和字母组成
    var unameTxt = $("unameTxt");
    var unameReg = /[0-9a-zA-Z]{6,16}/;
    var uname = unameTxt.value;
    var unameSpan = $("unameSpan");
    if (!unameReg.test(uname)){
        unameSpan.style.visibility="visible";
        return false;
    }else {
        unameSpan.style.visibility="hidden";
    }

    //密码的长度至少为8位
    var pwdTxt = $("pwdTxt");
    var pwd = pwdTxt.value;
    var pwdReg =  /[\w]{8,}/;  ///[.]{8,}/;
    var pwdSpan = $("pwdSpan");
    if (!pwdReg.test(pwd)){
        pwdSpan.style.visibility="visible";
        return false;
    }else {
        pwdSpan.style.visibility="hidden";
    }

    //密码二次输入不一致
    var pwd2 = $("pwdTxt2").value;
    var pwdSpan2 = $("pwdSpan2");
    if (pwd2!=pwd){
        pwdSpan2.style.visibility="visible";
        return false;
    }else {
        pwdSpan2.style.visibility="hidden";
    }

    // 请输入正确的邮箱格式
    var email = $("emailTxt").value;
    var emailSpan = $("emailSpan");
    var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!emailReg.test(email)){
        emailSpan.style.visibility="visible";
        return false;
    }else {
        emailSpan.style.visibility="hidden";
    }

    return true;
}