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

//如果想要发送异步请求，我们需要一个关键的对象 XMLHttpRequest
var xmHttpRequest;
function createXMLHttpRequest(){
    if (window.XMLHttpRequest){
        //符合DOM2标准的浏览器 ， xmHttpRequest的创建方式
        xmHttpRequest = new XMLHttpRequest();
    }else if (window.ActiveXObject){//IE浏览器
        try{
            xmHttpRequest = new ActiveXObject("Microsoft.XMLHTTP")
        }catch (e){
            xmHttpRequest = new ActiveXObject("Msxm12.XMLHTTP")
        }
    }
}
function ckUname(uname){
    createXMLHttpRequest();
    var url = "user.do?operate=ckUname&uname="+uname;
    xmHttpRequest.open("GET",url,true);
    //设置回调函数0 1 2 3 4
    xmHttpRequest.onreadystatechange = ckUnameCB;
    //发送请求
    xmHttpRequest.send();
}

function ckUnameCB(){
    if (xmHttpRequest.readyState == 4 && xmHttpRequest.status==200){
        // xmHttpRequest.responseText表示 服务器端响应给我的文本内容
        // alert(xmHttpRequest.responseText);
        var responseText = xmHttpRequest.responseText;
        //{'uname':'1'}
        alert(responseText);
        if (responseText=="{'uname':'1'}"){
            alert("用户名已经被注册!");
        }else {
            alert("用户名可以注册!");
        }
    }
}