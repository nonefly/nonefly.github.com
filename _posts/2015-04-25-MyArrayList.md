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
