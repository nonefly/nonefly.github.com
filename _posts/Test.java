package com.nonefly.list;


/**
 * ˳�������
 * @author nonefly
 * 
 */
public class Test {
	public static void main(String[] args) {
		MyList<String> list = null;
		
		//list = new MyArrayList<String>();
		list = new MyLinkList<String>();

		//��
		list.add("hello");
		list.add("world");
		list.add("nonefly");
		//��
		list.insert(2, null);//ע����֤Ԫ��null��ȡ
		list.insert(3, "I am");
		System.out.println(list);
		//ɾ
		list.delete(1);
		System.out.println(list);
		//��ѯ
		System.out.println(list.length());//����
		System.out.println(list.indexOf(null));//���1
		System.out.println(list.indexOf("world"));//��ɾ�������-1
	}
	
}