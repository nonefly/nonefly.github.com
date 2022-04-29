---
layout: post
title: java中的注解（Annotation）还是要知道一些滴
date: 2015-09-02
meta_description:
categories:
 - java
 - data structure
comments: true
browser_title: java中的注解（Annotation）还是要知道一些滴
---

注解的初识在自动生成重写诸如equals()等方法时头顶的那个@Override，还有消除那黄黄的警告线时的@SuppressWarnings，当时只是感觉可有可无的东西，对它就像只是看待一个不知道哪儿定义的一个方法而已。

之后感觉它的神奇就是在项目使用框架时了。学习框架时用的xml配置，已经感觉挺有创意了，并没有学习annotation，在一次实际项目开发中，hibernate，spring，springMVC通通注解。那简洁的xml配置和一行代码顶数句的注解更是感到神气的不行(xml与注解各有好处，非是误导注解一定强于xml)，然后才对注解刮目相看，准备多学习一些。

----------

**个人信息**

注解出生时间在jdk 1.5版本。地位呢，他是一个类型，地位同于class(类)，interface(接口)，当然还有一个平级但是和它一样不是非常常见的类型:enmu(枚举)。

**项目经历**

Hibernate中，用注解可以实现字段与数据库列的映射。

Spring中注解更是用着舒服，无论是Io还是AOP的配置，使用注解效率比起xml配置都是快捷很多。而且也是在框架学习时较多的使用注解，发现注解的强大。也由此觉得再使用的基础上应该有一些进一步的了解。

其他框架中如Junit 4版本开始后也是注解@Test代替了命名规则，数不胜数，相信在之后开发中，注解会被越来多的使用。

**技能分类**

注解有三种类型
1. 自定义注解:顾名思义，就是自己写的注解了，具体的实现在之后说明。
2. 内置注解: 也就是jdk中自己定义的注解，如之前所说@Override等都是。
3. 元注解: 可以注解在注解上的注解(看起来有点绕口，不错~)

```java
package java.lang;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

如上代码，这是源码中的`@Override`代码，这个其实就是个自定义注解(个人想法)，只不过是jdk自己写的，所以他就是**内置注解**。
其中有两行

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
```

这儿的`@Target`和`@Retention`就是元注解。当然不止这两个。
1. Documented     指示某一类型的注释将通过 javadoc 和类似的默认工具进行文档化。
2. Inherited     指示注释类型被自动继承。
3. Retention     指示注释类型的注释要保留多久。
4. Target     指示注释类型所适用的程序元素的种类。

> 具体的定义以及参数的解释[在API文档](http://tool.oschina.net/apidocs/apidoc?api=jdk-zh)或者源码【[帮您找好](http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8u40-b25/java/lang/annotation/Target.java?av=f)】中`java.lang.annotation`包都可以很明确的了解。包括**内置注解**都可以直接看jdk中的具体实现。

**实践**

在文档或jdk中元注解以及内置注解自定义都有源码参考，因此再需要了解的便是自定义注解。

自定义注解有三个:
1. 定义一个注解。在源码中和平时不一样的就是那个`@interface`,他于注解就相当于class于一个类，用`@interface`声明要定义的是一个注解，后面跟的自然是注解名称了。
2. 应用一个注解。注解定义后，根据定义的适用范围(类，方法，字段等等)使用注解。**注解不会直接影响原有代码，不管运行还是什么的**
3. 处理注解。在2中说注解不会影响代码，也许会想到不影响那怎么注入了，验证了等等。注解不会影响，但是处理注解就影响了。**注解相当于给墙上打了个‘拆’字，这个不会对房子有任何损坏（别说影响美观~），但是挖掘机看见‘拆’字就会扑上去，这就不能保证房子的安然了**。这儿对注解(能保留到运行期)的处理一般**通过java中的反射机制**来实现。


> 注解参数类型：
> 1. 所有基本数据类型（int,float,boolean,byte,double,char,long,short)
> 2. String、Class、enum、Annotation类型
> 3. 以上所有类型的数组


举个栗子:
```java
//1. 定义两个注解 StuId(用来说明学号)    StuSex(用来定义性别)

@Target(ElementType.FIELD)//作用于字段上
@Retention(RetentionPolicy.RUNTIME)//保留到运行期
public @interface StuId{
    /**此处定义成员也就是用注解时要传递的类似于参数的玩意，指定了默认值，使用时可以不写字段值
    *否则必须写:如 @StuId(stuId = "0001") 
    *如果是单一属性 一般声明为 String value(); 
    *这样在使用时可以简写为 @StuId("0001")
    */
    String stuId() default "000";//使用时可以 @StuId  字段值会取默认值000
    
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StuSex{
    public enum sex{
        男,女;
    }
    sex stuSex() default sex.男;
}

//2. 使用注解(房子写个"拆")
public class Student{
    
    //给字段注值应该在set方法上，字段上破坏了封装性，此处无视~
    
    @StuId(stuId = "0001")
    private String stuId;
    
    @StuSex(stuSex = sex.女)
    private String stuSex;
    
    //...省略getter和setter
}
//3. 处理注解(出动挖掘机...)
public static void getStuInfo(Class<?> clazz){
    Field[] fields = clazz.getDeclaredFields();
        
    for(Field field :fields){
        //找出其中是StuId类型的注解
        if(field.isAnnotationPresent(StuId.class)){
            StuId stuId = (StuId) field.getAnnotation(StuId.class);
            System.out.println("学号:"+stuId.stuId());
        }
        //找出其中是StuSex类型的注解
        else if(field.isAnnotationPresent(StuSex.class)){
            StuSex stuSex= (StuSex) field.getAnnotation(StuSex.class);
            System.out.println("性别:"+stuSex.stuSex());
        }
    }
}

//如上注解已经完成，下来测试:
public class Test {
    public static void main(String[] args) {
        DellStu.getStuInfo(Student.class);
    }
}

输出结果:
学号:0001
性别:女
//回看 2.注解使用 ，如果是这样，不写字段值
    @StuId(stuId = "0001")
    private String stuId;
    
    @StuSex(stuSex = sex.女)
    private String stuSex;
//那么输出结果(注解定义时设置的默认值):
学号:000
性别:男
```

**个人评价**

一般情况下是用不到自己去实现一个注解的，只有开发框架什么的大牛们才会用到（为了给我这菜鸟用...），但是还是有必要简单了解一些。

