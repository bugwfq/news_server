<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<style type="text/css">
	body{padding: 0px;margin: 0px;}
#header{width: auto;min-height: 70px;line-height: 70px;}
#header{border-bottom: medium solid #39A631;}
span.title_con{font-family:"微软雅黑";font-style: normal;font-size: 2em;font-weight:800 ;color: #39A631;padding-left: 1em;}
#content{width: auto;text-align: center;background-image: url(../img/hovertree_login_bg.jpg);background-size: 100% 100%;}
#footer{width: auto;text-align:center;font-family:Arial, Helvetica, sans-serif}
#footer{border-top: thin solid #39A631;}#footer a{color:black;}
.con{width: 80%;height: 30em;min-width:300px;}
.con{border: thin solid #8EC172;}
.con_title{background-color: #8EC172;width: auto;height: 3.5em;line-height: 3.5em;text-align: center;min-width:260px;}
.con_title_sp{font-family: "微软雅黑";font-size: 1.5em;font-weight: 800;color: #FFF;}
.submit-btn{width: 8em;height: 2em;background-color: #62ab00;border-radius: 4px;border: 1px;color: #fff;font-family:"微软雅黑";font-size: 1em;font-weight: bold;}
.con_date{font-size: 1}
</style>
<title>易通软件教育</title><base target="_blank" />
</head>
<body>

<div id="header">
<div class="header_title">
<span class="title_con">老王新闻网</span>
</div>
</div>

<div id="content">
<center>
<div class="con_title">
<span class="con_title_sp">${title}</span>
</div>
<div class="con_date">
<span class="con_title_sp"><font color="black" size="3">各地新闻           经济参考报               ${date}</font> </span>
</div><br/>
<div class="con_panel">
<font color="black" size="2">${content}</font> 

</div>
</center>
</div>

<div id="footer">
&copy; 加入我们：<a href="http://hovertree.com/">联系客服</a>
</div>

</body>
</html>