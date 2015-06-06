---
layout: post
title: 理解Ajax
date:  2015-06-06
meta_description:  
categories: 
 - java
 - ajax
comments: true
browser_title: 理解Ajax
---

#### 关于Ajax

全称（Asynchronous JavaScript and XML/异步的JavaScript和XML），这不是什么新的编程语言或或技术，而是对于多种技术的综合应用。

- ### **要之何用？**

    如果用过早期的浏览器就该有过一种‘忧伤’：注册一个账户，姓名年龄昵称密码等等填完了，点击注册，`用户名已存在`，瞬间泪奔。

    当然，这只戳中一个泪点。那这与Ajax有什么关系？之前的情况是因为提交信息是整个页面提交， 所以所有信息完整后才会去注册，顺便刷新下页面~。难道就不能写了名字就去查询有没有被用吗？当然可以。Ajax，可以在不用提交整个页面的情况下访问服务器。

    可以看出，Ajax作用就是在不影响正在浏览页面的情况下‘独自’去向服务端发送请求、获得数据。

- ### **这是什么？**

    那到底Ajax是什么？怎么做到的？那就要推出一个`神奇控件`：手机，额，不对，是`XMLHttpRequest`。

    先想想一个场景：自己正在介绍自己个人信息，然后突然忘了父母生日什么的。几十年前我们的做法是`回家问了再来`，现在呢？一边继续填写其他信息一边，打电话或者发短信问下家里，等回短信或电话了再填写刚才漏的。
	OK，这里的`手机`便可以大胆理解为`XMLHttpRequest`，这个就是页面的电话。

- ### **怎么使用？**

    手机打电话也许简单，`XMLHttpRequest`呢？功能一样，用起来其实也差不多了...

**1. 首先，获得`XMLHttpRequest`对象**

    打电话当然的有`手机`了，所以先创建`XMLHttpRequest`对象。只是注意的是为了兼容各种浏览器，`XMLHttpRequest`有所不同，老版本IE`XMLHttpRequest`叫做`ActiveX`

**2. 发送请求**

    有手机了就该问家里需要的信息了。**`open(请求方式, 请求地址, 是否异步)`**
    > - 请求方式:`GET`或者`POST`，手机还分发短信和打电话呢。这里也有两种方式，也就是`HTTP`协议中两种请求方式。
    > - 请求地址：手机获得消息需要手机号吗，`XMLHttpRequest`也要服务器地址了。
    > - 是否异步：`true`或`false`即发短信时候傻傻等着(false)回复呢，还是同时做后面的事(true)~

*这里值得注意的是`POST`请求时需要设置请求头`setRequestHeader("Content-Type","application/x-www-form-urlencoded")`;
而`GET`则不必须*
**3. 设置回应**
接受回应主要分两点：
1. 根据回应不同状态判断什么时候成功？
2. 成功接受回应后怎么处理？

**4. 请求数据**

电话通了或者短信过去得告诉想知道什么吧？所以发送时候还得传递参数`send(null);`
`POST`参数通过`send();`传递，而`GET`则在`URL`后以键值对形式传递，多个参数用`&&`分隔。
**以上是页面使用Ajax步骤，对应一个简单使用如下**

{% highlight java %}
window.onload=function(){AjaxRequest();}//加载页面时调用Ajax，也可以以任意需要的方式，比如输入框按键后onKeyUp,失去焦点等等
    var xmlHttp;
	//获得xmlHttp对象
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {//老版本IE
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {//其他浏览器
			xmlHttp = new XMLHttpRequest();
		}
	}
	//Ajax请求
	function AjaxRequest() {
	    //1. 获得xmlHttp对象（买手机）
		createXMLHttpRequest();
		//添加时间戳防止缓存，也可以加随机数等
		var url = " 服务器地址?timeStamp=" + new Date().getTime();
		//2. 发送请求（电话？短信？）
		xmlHttp.open("POST", url, true);
		//3. 设置回应（回掉函数）
		xmlHttp.onreadystatechange = stateChange;
		//2.设置请求头（POST方式必须）
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		//4. 请求数据（POST传参方式）
		xmlHttp.send(null);
	}
	
	//Ajax请求信息正确返回
	function stateChange() {
	    //4-1. 根据回应不同状态判断什么时候成功？
		if (xmlHttp.readyState == 4) {//4表示响应结束
			if (xmlHttp.status == 200) {//200表示正确响应
			//4-2. 成功接受回应后怎么处理？
				parseResults();
			}
		}
	}
	//解析数据
	function parseResults() {
		//获得返回数据
		var backData = xmlHttp.responseText;
		//具体处理
		...
	}
{% endhighlight %}
  服务端
{% highlight java %}
public class DeptsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String s = 需要返回的信息;
		out.println(s);
		out.flush();
		out.close();
	}
}
{% endhighlight %}
### **回顾**
`Asynchronous JavaScript and XML` 
**JavaScript在页面用了，那XML呢？和这个有什么关系？没有XML也是Ajax？**
狭义理解不算，但现在Ajax的定义主要指不刷新页面异步发送请求，因此没用XML也是Ajax。那不XML用什么？
XML主要来返回数据，而上述返回直接字符串，即文本。而一般常用传递方式有三种，根据不同需求选择不同方式
&nbsp; | 文本 | XML | JSON |
--|--|--|
描述能力 |  弱 | 强 | 较强
数据量 | 小 |  大  | 小
复杂度 | 小  |  大 | 小 
扩展性 |  弱 | 强 | 强
验证数据 | 不能 | 能 | 不能
安全性 | 安全 | 安全 | 不安全 
处理转义字符 | 不能 | 能 | 不能 

> Ajax的使用现在几乎已无处不在，也体现了他的重要。具体使用中很多的框架都可以让我们很好的使用它，比如jQuery，功能强大， 体积灵巧。具体的使用不同的集成有各自的语法，但最基本的概念流程都是一样的。
