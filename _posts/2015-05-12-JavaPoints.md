---
layout: post
title: java散点
date:  2015-05-12
meta_description:  
categories: 
 - java
 - points
comments: true
browser_title: java散点
---
 
### 1. 三目运算符右结合
 > true?false:true == true?false:true;这个表达式等于true?false:（true == （true?false:true））; => true?false:(true == false); => true?false:false;最终应该得到false。

### 2. &，\|，~ 为位运算符，也是Boolean类型的逻辑运算符
 > 此时与&&，|| 区别：&&或|| 做了优化，&& 前为false或者 \|| 前为true，则不继续运算，而& \| 则做完。

### 3. final,finally,finalize
   - `finally` 
        异常处理补充，总会执行的代码（无论是否异常），try中return时，会在return前执行 
   - `finalize`  
        方法名,Object中定义，释放资源。
   - `final`修饰符    
    + final成员   
     成员初始化后值不能再改变。
    + final方法
     方法可被继承但不可重写。编译时将主体插入而不是调用。
    + final类
     类不可被继承

### 4. 传值OR传引用？
  > 都是传一个副本（拷贝），都是传值
    基本类型或者引用类型，都会传一个拷贝，基本类型传的是另一个一样值的变量，因此传参数过去后改变不会影响。

 > **引用类型事实上传的也是另一个一样值的引用，改变传的值也不会影响。**这句可能会不被赞同，如下代码（Pig是一个简单的包含一个name成员的类，便不列出代码)，将pig这个引用传入后，为pig重新引用一个类（名pig1），输出pig的name属性：
 
{% highlight java %}
public class Test {
	public static void main(String[] args) {
		Pig pig = new Pig("pig");
		changeName(pig);
		System.out.println(pig.getName());//"pig"而不是"pig1"
	}
	static void changeName(Pig pig){
		pig = new Pig("pig1"); 
	}
}
{% endhighlight %}
 
 > 那如果要改值呢？传递的pig虽是另一个pig，但是他的值（即对象堆中的地址两个一样的，因此可以通过传入的pig这个引用操作传入之前指向的对象），如下代码便可以改变name
{% highlight java %}
static void changeName(Pig pig){
	pig.setName("pig1"); //main()方法则输出"pig1"
}
{% endhighlight %}


 ***因此可以说都是传值，所谓传递引用，只是传递的引用的值可以被用来操作对应的对象***

 




