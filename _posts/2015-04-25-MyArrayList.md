---
layout: post
title: 顺序线性表
date:  2015-4-25
meta_description:  
categories: 
 - java
 - data structure
comments: true
browser_title: 顺序线性表
---

线性表的实现可以是顺序的（数组实现），也可以是非顺序（链式）的，
如下便是顺序表的实现，相当于简化版的java工具类中`ArrayList`。链式结构的实现（实现为一个双向链表），相当于java工具类中`LinkedList`,具体实现可以参考[这里][1]。

`MyList`为自定义的一个线性表接口，说明了一些线性表应该具有的功能:
{% highlight java %}
package com.nonefly.list;
/**
 * 线性表接口
 * @author nonefly
 */
public interface MyList<T> {
    
    /** @return size 线性表元素个数*/
    public int length();
    
    /**
     *  获得索引i处元素
     * @param index 线性表索引
     * @return 第i处元素
     */
    public T get(int index);
    
    /**
     * 获得指定元素索引
     * @param element 查询元素
     * @return 索引
     */
    public int indexOf(T element);
    
    /**
     * 指定索引插入元素
     * @param index 插入索引
     * @param element 插入元素
     */
    public void insert (int index, T element );
    
    /**
     * 顺序表末尾插入元素
     * @param element
     */
    public void add(T element);
    
    /**
     * 删除指定索引元素
     * @param index 索引
     * @return 删除元素
     */
    public T delete (int index );
    
    /**
     * 删除最后一个
     * @return 删除元素
     */
    public T remove();
    
    /**
     * 判断顺序表是否为空
     * @return boolean
     */
    public boolean isEmpty();
    
    /**
     * 清空顺序表
     */
    public void clear();
    
}
{% endhighlight %}

下面是顺序线性表的实现（通过数组来存储表中元素），它实现了`MyList`接口中的方法，同时添加了自己的一些方法，如:为了输出方便，重写了`toString`等.

*事实上，在这里用接口只是为了方便提醒自己不要漏掉某些重要功能，在练习表的实现中，不实现`MyList`接口也可以正确运行。*

{% highlight java %}
package com.nonefly.list;
import java.util.Arrays;
public class MyArrayList<T> implements MyList<T>{
    private int size = 0;
    private int capacity;
    private Object[] elementData;
     
    public MyArrayList(int initialCapacity) {
        capacity = 1;
        while(capacity < initialCapacity){
            capacity <<= 1;
        }
        this.elementData = new Object[capacity];
    }
    public MyArrayList(){
        this(10);
    }
    
    public int length(){
        return size;
    }
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index, size - 1);
        return (T) elementData[index];
    }
    
    public int indexOf(T element) {
        
        if(element == null){
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        }else{
            for (int i = 0; i < size; i++)
                if (element.equals(elementData[i]))
                    return i;
        }
        return -1;
        
    }
    
    public void insert (int index, T element ){
        checkIndex(index, size);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }
    
    public void add(T element) {
        insert(size, element);
    }
    
    private void ensureCapacity(int minCapacity) {
        if(capacity < minCapacity){
            while(capacity < minCapacity){
                capacity <<= 1;
            }
            elementData = Arrays.copyOf(elementData, capacity);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public T delete (int index ){
        checkIndex(index, size - 1);
        T oldValue = (T) elementData[index];
        int numMoved = size - index -1;
        if(numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return oldValue;
    }
    
    public T remove(){
         return delete(size - 1);
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public void clear(){
        Arrays.fill(elementData, null);
        size = 0;
    }
    
    @Override
    public String toString() {
        if(size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < size; i++)
            sb.append(elementData[i]+", ");
        int len = sb.length();
        return sb.delete(len - 2, len).append("]").toString();
    }
    
    private void checkIndex(int index, int max) {
        if(index > max || index < 0 )
            throw new IndexOutOfBoundsException("index"+index);
    }
}
{% endhighlight %}

 最后，完成表的实现我们可以写一个测试方法运行验证写的是否正确。下面的是一个简单的测试，没有对所有方面验证：

{% highlight java %}
package com.nonefly.list;
/**
 * 线性表测试
 * @author nonefly
 */
public class Test {
    public static void main(String[] args) {
        MyList<String> list = null;
        
        list = new MyArrayList<String>();//顺序表测试
        //list = new MyLinkList<String>();//链表测试

        //增
        list.add("hello");
        list.add("world");
        list.add("nonefly");
        //改
        list.insert(2, null);//注意验证元素null存取
        list.insert(3, "I am");
        System.out.println(list);
        //删
        list.delete(1);
        System.out.println(list);
        //查询
        System.out.println("表长度为："+list.length());//长度
        System.out.println("null元素所在索引："+list.indexOf(null));//输出1
        System.out.println("'world'元素所在索引："+list.indexOf("world"));//已删除，输出-1，表示错误
    }
    
}
{% endhighlight %}   

测试结果如下：![运行结果][2]


  [1]: http://www.nonefly.com/java/data%20structure/2015/05/01/MyLinkedList/
  [2]: {{ site.url }}/assets/images/2015/arraylist.png
