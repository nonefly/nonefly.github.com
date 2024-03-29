---
layout: post
title: 不要阻挡我偷懒，你肯定喜欢的软件
date: 2015-09-06
meta_description:
categories:
 - other
comments: true
---

朋友用了一个[aText](http://www.dreamxu.com/books/software/atext/index.html)，那看着使用的舒适眼馋的不行，然后只能在Mac下使用，而且貌似还得买...然后，没了。。。

----

#### **PhraseExpress(Windows)**

终于无意还是有意，发现了类似的一个替代品[PhraseExpress](http://www.phraseexpress.com/)，瞬间喜欢的不行。

> 首先，Windows下就可以使用
> 然后，对个人使用是免费的

安装的话，windows下安装东西，看不懂或者不太会那就下载下来next点到底吧...
不想去找的话可以[自提](http://pan.baidu.com/s/18uXye)  密码: 87k8
简单使用步骤可以[看这儿](http://jdev.tw/blog/1608/phraseexpress-autotext-enhance)，也可以看下文另一软件介绍，两者差不多（下面偷学这个的～）

大概说下这个软件干嘛的:

> 简单来说，自定义的用一些词汇替换另一些东西，可以定义快捷键或者命令等等，想想自动补全...

在使用电脑中，我们经常会输入一些很频繁很无聊的东西，比如账号了，手机了，email，etc。还有很多具体用在哪块，自己用了就知道顺手了。

----

#### **AutoKey(Linux)**

windows下用上个很方便，但是考虑到更多时候要在Linux下写东西，比如[我的个人博客](http://www.nonefly.com)用GitHub托管，编译用jekyll，git，markdown，环境都是部署在Linux(Ubuntu)下，所以当然要搜寻Linux下能用的了。

幸运的是，很快找到了[AutoKey](https://code.google.com/p/autokey/)，这个是开源的，来由貌似就是因为上面那个很好用，但是只能windows，然后就来了个类似的，个人初步实践两者看起来挺像。

**安装步骤**

    sudo add-apt-repository ppa:cdekter/ppa
    sudo apt-get update
    sudo apt-get install autokey-gtk

**使用**

**简单文本替换**

1. 右上角，点击new，创建一个Phrase，这个名字随意，就是自己看懂就行(例如 : mysite)
    ![](http://www.nonefly.com/assets/images/2015/first.png)

2. 右上角contents部分，就是你要在输入命令后想出现的，比如我输入[自己网址](http://www.nonefly.com)
    ![](http://www.nonefly.com/assets/images/2015/second.png)

3. 点击Abbreviation后面`set`，进入这个页面，旁边框里面add自己需要定义的命令，或者说简写的字符(此处谢了`bb`,即想在输`bb`后直接输出 ： http://www.nonefly.com)
    ![](http://www.nonefly.com/assets/images/2015/third.png)
4. 点击ok，save(ctrl+s也可以，通用...)。然后随便找个地方试试直接输入`bb`看看效果吧，你可以选择编辑器，浏览器，甚至终端，你喜欢就好。从此输** bb == http://www.nonefly.com**(ps：为写这句可纠结了，输入个bb自动换成网址，数次才好，⊙﹏⊙b汗)

类似vim配置，你可以用字符制定自己需要的代码什么的，比如你可以设置输入`main`就会出现
```java
int main(){
   return 0;
}
```


**添加当前时间**

写笔记什么的，经常需要记录当前时间作为一个保存，因此也可以设置快捷键

1. 还是老地方，如上第一步，只是这次New一个Script，名字仍然随意
2. 在上文本处写这样函数
 ![](http://www.nonefly.com/assets/images/2015/forth.png)

3. 其他同样，然而比如此时我指`dd`为快捷键

    当输入`dd`时，会出现如下:
    2015-09-06  09:26:22 CST

**然而大部分情况下，我们不要这种格式的，那就自己制定格式**

如下是一些常用格式及效果:

- `output = system.exec_command('date +"%Y-%m-%d"')
     keyboard.send_keys(output)`
     输出: 2015-09-07
-  `output = system.exec_command("date +%Y-%m-%d\ %I:%M")
    keyboard.send_keys(output)`
    输出: 2015-09-07 08:32
    *注意日期时间之间空格时，要加 \\ 转义，否则会出错，自己试出来的，未发现正确写法该如何*
- 还有其他格式根据自己习惯，按照上面的格式化下输出就行

**最后**

可用之处当然不只这么一点点了，就需要自己摸索了，当尝试了以后，相信一定会喜欢它的。
