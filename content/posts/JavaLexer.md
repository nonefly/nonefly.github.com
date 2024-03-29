---
layout: post
title: java词法分析器
date: 2015-04-11
meta_description:  
categories: 
 - java
 - 编译原理
 - 词法分析
comments: true
---

## 一、 实验目的
    1. 通过实验对编译系统的基本理论、编译程序的基本结构有更为深入的理解和掌握；    
    2. 掌握编译程序设计的基本方法和步骤；       
    3. 能够设计实现编译系统的重要环节词法分析，同时增强编写和调试程序的能力。    

## 二、 实验要求

1. 单词的分类。     
>可将所有标识符归为一类；将常数归为另一类；保留字和分隔符则采取一词一类。    
2. 符号表的建立。     
>可事先建立一保留字表，以备在识别保留字时进行查询。变量名表及常数表则在词法分析过程中建立。    
3. 单词串的输出形式。   
>所输出的每一单词，均按形如（CLASS,VALUE）的二元式编码。   
4.数据获取及存储。    
>本设计中默认./src/output.txt读取源文件，运算结果存储./src/output.txt文件中    

##  三、 单词分类表   
>注：具体及详细编码以*com.nonefly.test.KeyTypes*类中定义为准   

####  1. 关键字表    
>关键字，java中共50个关键字，如下，对其按顺序一字一编码    
    "abstract", "boolean", "break", "byte","case", "catch", "char", "class", "continue", "default", "do","double", "else", "extends", "final", "finally", "float", "for","if", "implements", "import", "instanceof", "int", "interface",    "long", "native", "new", "package", "private", "protected",    "public", "return", "short", "static", "super", "switch","synchronized", "this", "throw","throws", "transient", "try","void","volatile","while","strictfp","enum","goto","const","assert"   

|助记符|种别编码|关键字|
|------|:------:|---|
|ABSTRACT|1     |abstract|
|BOOLEAN |2     |boolean|
|BREAK   |3     |break|
|BYTE    |4     |byte|
|CASE    |5     |case|
|...     |...   |...|
|ASSERT  |50    |assert|

#### 2. 运算符表   
    运算符，设计中涉及到+ - * / > = < & | ~十种，对于组合运算符如++ +=等未定义，将其分开描述。          

运算符|助记符|种别编码
:------:|:--------:|:---:
+|PLUS|51
-|MIN|52
*|MUL|53
/|DIV|54
&|AND|55
\||OR|56
~|NOT|57
\>|GT|58
=|EQ|59
<|LT|60


#### 3. 分隔符表
界符（分隔符）涉及如下几种   
>, ;  {  }  (  ) [  ]  _  :  .  " \未一词一符定义，统一归为 SEPARATORS（助记符）：61（单词种别）    
#### 4. 其他（标识符、常数、非法字符）
|类型|种别编码|助记符|
|------|--------|---|
|标识符|71   | ID |
|常数 | 0    |DIGIT|
|非法字符|-1     |ERROR|

## 四、单词状态图

![aaa.jpg][1]

##  五、 算法描述

    程序中用到的函数列表:

函数名或变量|解释
---|---|-----
TestLexer(String fileSrc)|通过路径构造词法分析器
StringBuffer buffer；|文件读入缓冲区
char ch; |字符变量，存放最新读进的源程序字符
String strToken;|字符数组，存放构成单词符号的字符串
boolean isLetter(char ch)|判断ch是否为字母
boolean isDigit(char ch)|判断是否为数字
boolean isKeyWord(String)|判断是否为关键字
boolean isOperator(char)|判断是否为运算符
boolean isSeparators(char)|判断是否为分隔符
void analyse()|分析程序（关键代码）
void getChar()|将下一字符读到ch中，搜索指示器前移一个字符
void getBC()|检查ch空白则调用getChar()至ch中进入非空白字符
void concat()|将ch连接到strToken之后
void retract()|将搜索指示器回调一个字符位置，将ch值为空白字
void writeFile(String,String)|按照二元式规则写入文件
int getType(String args)|利用反射获取种别编码

##   六、 程序结构

#### 1. 整体目录结构如下,其中：
  a.FileUtil.java 对文件的读写等操作      
  b.KeyTypes.java 定义了单词种类对应的种别编码    
  c.TestLexer.java 是分析器类，继承KeyTypes类，对源程序词法分析   
  d.TypeUtil.java 是一个简单的判断字符种类的类    
  e.MainTest.java 是测试主方法类，是程序入口，通过源程序路径创建TestLexer类，调用其中词法分析方法，最后结果保存在文件中   
  ![ddd1.png][2]

#### 2. 上述类中定义方法如下，方法详细功能见（算法描述）中表格  
![ddd2.png][3]

##   七、 运行结果

####  1. 准备

    在input.txt中输入源程序（我们以本程序源代码为例，此文件必须有），output.txt中为空，作为结果输出(可以不创建，运行时自动创建)如下图:
![bbb.png][4]

####  2. 运行

    在文本中，按照要求，我们以（种类编码，VALUE）格式保存结果，在控制台中，为方便观察，以（助记符，VALUE）格式打印出。（运行结果如下）
![ccc.png][5]

## 八、 设计技巧及体会

#####  这是学习编译原理时的第一次实验，回忆试验中的过程，虽然对编程语言的提升并没有太多，但是重要的是让我们了解编程语言在通往机器与之交流的一个重要环节。了解词法分析对源程序处理分类的思想。    
#####   在设计中，实现了简单的要求并加入了自己一些想法，最终程序也可以成功运行并能按要求对结果保存。当然，其中还有一些不足，其中一部分因为与大部分设计几乎一样，因此便"简写"。比如对分隔符种类处理，类似于操作符以及关键字处理，因此并没有一词一类，而是统一作为一类。还有对于注释的分析，因为"//"的注释没有想到很好的处理办法让忽视代码到行尾，因此将其当做普通分隔符处理。(后续中在得到老师帮助后已解决注释问题，对于//单行注释以及/* */多行注释都可以处理)    
*对于注释处理测试如下   
#####   我们用main()方法所在类代码测试，其中两种注释类型都有包括*在输出结果中，显然已经忽略注释内容（如图中标记处），即使两种即使嵌套使用也可以正确处理    
![eee.png][6]

#####   此次的实验收获还是很大的，除了上述，因为用java完成对java语言的分析，因此对这门编程语言在回顾熟悉的基础上又有了新的收获。而且尝试用了平时不用的方式，比如定义常量类，通过反射以字符串获取属性值，也许在使用中因不常用有所欠缺，但按所想完成功能感觉还是很好的~   

##  九、源程序     
  源代码 [【查看】][7]

  [1]: {{ site.url }}/assets/images/2015/aaa.png
  [2]: {{ site.url }}/assets/images/2015/ddd1.png
  [3]: {{ site.url }}/assets/images/2015/ddd2.png
  [4]: {{ site.url }}/assets/images/2015/bbb.png
  [5]: {{ site.url }}/assets/images/2015/ccc.png
  [6]: {{ site.url }}/assets/images/2015/eee.png
  [7]: https://github.com/nonefly/mycode
