package com.nonefly.list;
/**
 * 线性表接口
 * @author nonefly
 */
public interface MyList<T> {
	
	/** @return size 顺序表元素个数*/
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
