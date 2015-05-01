package com.nonefly.list;


/**
 * 顺序表操作
 * @author nonefly
 * 
 */
public class Test {
	public static void main(String[] args) {
		MyList<String> list = null;
		
		//list = new MyArrayList<String>();
		list = new MyLinkList<String>();

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
		System.out.println(list.length());//长度
		System.out.println(list.indexOf(null));//输出1
		System.out.println(list.indexOf("world"));//已删除，输出-1
	}
	
}
