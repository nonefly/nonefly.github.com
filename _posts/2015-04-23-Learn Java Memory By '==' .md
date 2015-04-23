---
layout: post
title: 以`==`理解java内存
date:  2015-4-23 16:43
meta_description:  
categories: 
 - javaSE
 - 内存
comments: true
---

## 引言

```java
public static void main(String[] args) {
		String str1 = new String("hello");
		String str2 = "hello";
		
		String str3 = new String("hello");
		String str4 = "hello";
		
		System.out.println(str1 == str2);
		System.out.println(str2 == str3);
		System.out.println(str3 == str4);
		String str5;// (str5 = str?)可以用==输出true?
}
```
先来一段java代码，如果上面变量都是`int`之类基本类型，也许结果显然全部相等。但是:
  - 如果现在是字符串呢？
  - 再换为随意创建的对象呢？
  - 再把`==`换为`equals()`呢？

## 分析
也许上面代码直接copy到Eclipse下瞬间便知答案，但是为什么？

首先定义了两组变量（str1，str2与str3，str4），方式一模一样，然后用`==`计算互相之间差异。    
那么我们就要知道`==`在java中比较的是地址。好吧，貌似知道了也不能直接解题。似乎还必须了解它们存放地址在哪，这样才知道是否地址相等。那便一个一个分析变量存储内存（*java内存介绍便不啰嗦了，可以看[这儿][1]*）。
```java
String str1 = new String("hello");
```
这段代码执行时，用了`new`关键字，于是在堆中创建了String对象，保存的值便是"hello"(任何对象创建都是类似)。然后在堆栈（栈）中保存一个引用类型（str1）[*引用类型在之后说明*]。而str1存放的是"hello"这个对象在堆中的地址(*可以想象C中指针指向一个实际值，但是java中不存在指针这一说法*)。**于是每`new`一个对象，必然在堆中新分配了空间**。对于String类型这是绝对的，而且只会在堆中保存对象。但是一个类存在的静态变量，会放在静态变量区，方法体等其他仍在堆中。

```java
String str2 = "hello";
```
而执行这段代码时，str2仍然是栈中引用类型，但是java会先在常量池中寻找有没有"hello"这个字符串，如果有，那么str2就初始化为找到的"hello"的引用（**不会在任何地方创建新对象**）。如果没找到，会在**常量池中创建，不同于`new`,堆中不创建对象**。至此，在常量池中和堆中各有一个"hello"。继续:

```java
String str3 = new String("hello");
String str4 = "hello";
```
接下来继续创建"hello"。str3时，又是`new`，想都不用想，前面`str1`分析中说了每次new都在堆中新创建对象。ok，现在堆中两个对象了，接着str4呢？继续在常量池创建？当然不，**直接等号初始化是在常量池创建，但是前提是没有这个**，而str2已经存在于常量池中，那么str4也就引用此处"hello"好了。至此所有创建的对象包括引用状态如下图:
![内存状态][2]
现在回到代码输出，比较地址？显然只有str2和str4引用同一个"hello" 地址，再其他任意两个地址都不相等。好吧，开始程序输出三个`false`.

>然后根据自己想法创建一个str5，想等于str1？可以这样`String str5 = str1`，str3同理。如果想等于str2，或者str4呢，也可以这样？当然，而且还可以这样：

```java
 String str5 = "hello";//为什么？
```

## 思考

如果输出用`equals()`而不用`==`呢？
运行程序，或者直接得出:所有的`str`都相等。那么么么。。。为什么O(∩_∩)O~？
看来又要去了解[`equals()的比较方式`][3]



  [1]: http://www.j2megame.org/index.php/content/view/2246/125.html
  [2]: {{ site.url }}/assets/images/2015/20150423.png
  [3]: www.nonefly.com