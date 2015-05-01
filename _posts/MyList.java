package com.nonefly.list;
/**
 * ���Ա��ӿ�
 * @author nonefly
 */
public interface MyList<T> {
	
	/** @return size ˳���Ԫ�ظ���*/
	public int length();
	
	/**
	 *  �������i��Ԫ��
	 * @param index ���Ա�����
	 * @return ��i��Ԫ��
	 */
	public T get(int index);
	
	/**
	 * ���ָ��Ԫ������
	 * @param element ��ѯԪ��
	 * @return ����
	 */
	public int indexOf(T element);
	
	/**
	 * ָ����������Ԫ��
	 * @param index ��������
	 * @param element ����Ԫ��
	 */
	public void insert (int index, T element );
	
	/**
	 * ˳���ĩβ����Ԫ��
	 * @param element
	 */
	public void add(T element);
	
	/**
	 * ɾ��ָ������Ԫ��
	 * @param index ����
	 * @return ɾ��Ԫ��
	 */
	public T delete (int index );
	
	/**
	 * ɾ�����һ��
	 * @return ɾ��Ԫ��
	 */
	public T remove();
	
	/**
	 * �ж�˳����Ƿ�Ϊ��
	 * @return boolean
	 */
	public boolean isEmpty();
	
	/**
	 * ���˳���
	 */
	public void clear();
	
}