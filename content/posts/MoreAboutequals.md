---
layout: post
title: 再多一点理解java中的equals()
date: 2015-08-27
meta_description:
categories:
 - java
 - data structure
comments: true
browser_title: 再多一点理解java中的equals()
---

equals()这个方法，在学习的时候总是用来和 `==` 作以区分:
equals()用来比较对象的值，`==`用来比较地址。
如此来说当然算不上错，只是有局限性。**虽然equals()方法被用来比较值，但不是它的实质**。

#### **那equals()的实质是什么？**

是一个所有对象的一个方法...这不是废话么？好吧，equals()就是`==`，前提你没动她，她还是当初的她...
equals()方法在Object中定义，而所有的类的都最终继承了Object，因此Object类中的equals()才是原有的面貌:
```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

就这样，就是用`==`比较的。

#### **那么为什么还要equals()**

既然equals()就是`==`，那么为什么还要equals()？
其实equals()个人觉得可以理解为对`==`的重写。不同于c++中可以重写操作符，java中的操作符是不能重写的，而`==`的比较很单纯，就是比较直接"看到"的东西是否一样。`西红柿==番茄`输出就是`false`，才不会管你是不是指的同一类东西。就是这么任性，不服？不服你用`equals()`啊。
因为我们当然不可能服了，因此我们就用equals()了，用equals()的原因就是我们可以自己定义比较规则，想让谁平等地位就能~

#### **equals()该怎么用**

先打一段"官腔"，引用下API中的定义...
 equals 方法在非空对象引用上实现相等关系： 

> * 自反性：对于任何非空引用值 x，x.equals(x) 都应返回 true。
> * 对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。
> * 传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
> * 一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上equals比较中所用的信息没有被修改。
> * 对于任何非空引用值 x，x.equals(null) 都应返回 false。

前面说了那么多equals()的坏话（当然也有拍他马屁），然后就借虎威"为非作歹下":
```java
public class Equals {
    //运行结果测试
    public static void main(String[] args) {
        Equals e = new Equals();
        Dog dog1 = e.new Dog("dog", "black");
        Dog dog2 = e.new Dog("dog", "black");
        System.out.println(dog1.equals(dog2));
    }
    
    class Dog{
        String name;
        String clolor;
        public Dog() {
        }
        public Dog(String name, String clolor) {
            this.name = name;
            this.clolor = clolor;
        }
    }
}
```

- 如上程序，两只狗狗即使名字颜色这仅有的属性都已经完全一样，结果仍然是false。因为equale()未重写就用之前列出的Object中的方式，等同于输出`dog1==dog2`,而`==`的比较原则就是只有同一只才是相等，长的再像也没用。
- 按照正常想法应该是如果颜色名字都一样了，便可以认为两只狗相等了，那么就重写equals()

```java
@Override
public boolean equals(Object obj) {
    if(this == obj)
        return true;//自己和自己比当然相等了
    
    if(obj instanceof Dog){//当然要比的也得是狗狗了
        Dog d = (Dog)obj;
        //名字、颜色一样就认为一样了
        return this.name.equals(d.name)
                && this.clolor.equals(d.clolor);
    }
    return false;
}
```


- 神经大条一点，只要是狗就是一样的
```java
@Override
public boolean equals(Object obj) {
    return obj instanceof Dog;
}
```
- 喝了点酒，看什么都是一样的
```java
@Override
public boolean equals(Object obj) {
    return true;
}
```

#### **equals()与hashCode()**

一般重写equals()都会建议按规则重写hashCode()方法，然而并不像说hashCode()规则.虽然API也建议重写了，但想说的是，并非必须这样.
hashCode()重写的目的最重要的在于使用集合框架时会用到，重写会提高效率，此处不详细描述.如果自定义类作为`map`的key，那么hashCode()就得重写了.一般情况下并没有必要.

#### **总之**

1. 基本对象类型（byte,short,int,long,float,double,boolean,char）直接用==比较，这是毫无疑问的，因为他们的变量存的就是值。

2. > 对象类型也可以用==比较，此时比较的是这个引用存的地址（注:此地址是内存地址，不同于hash值），就是那么一串应该是十六进制的数字，他不会管这地址代表谁，只看值。因此也就是说只有是同一个对象才是true。

  > 而对象比较用equals()比较，代表两个对象类似，至于具体长得像到什么程度，根据自己定义。因为一般定义两个对象里面成员一样就一样，所以片面认为equals()比较直，==比较地址了。要是不重写equals()，equals()也是比较地址好吧。或者说==也就是比较值啊，他就比较引用存的那个地址的值，只是没用解析这个地址代表什么而已。

3. equals()和hashCode()的确关系很好，但并不代表必须形影不离。


[关于==运算符][1]，也可以看看之前的理解，用以区别。


[1]: http://www.nonefly.com/java/%E5%86%85%E5%AD%98/2015/04/23/Learn%20Java%20Memory%20By%20'=='%20/
