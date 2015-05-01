package com.nonefly.list;
/**
 * ��ʽ���Ա���˫��������
 * @author nonefly
 * @param <T>
 * 2015-5-1
 */
public class MyLinkList<T> implements MyList<T> {
	// LinkedList<E>
	private Node header;
	private Node tail;
	private int size = 0;

	public MyLinkList() {
		header = null;
		tail = null;
	}

	public int length() {
		return size;
	}

	public T get(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index");
		return getNodeByIndex(index).data;
	}

	private Node getNodeByIndex(int index) {
		// ����indexλ�� ѡ���ͷ/β���� ���Ч��size >> 1 ���� size/2
		if (index < (size >> 1)) {// ��ͷ����
			Node node = header;
			for (int i = 0; i < index; i++)
				node = node.next;
			return node;
		} else {// ��벿�֣���β����
			Node node = tail;
			for (int i = size - 1; i > index; i--)
				node = node.prev;
			return node;
		}
	}

	public int indexOf(T element) {
		// ����д����������򵥣�ʵ�����㸴�Ӷ���ߣ���Ϊÿ��ѭ��if�л��д����Ƚ��ж�
		/*
		 * for (Node node = header; node != null; node = node.next) { if
		 * ((node.data == null && element == null)//elementΪ��ʱ || (element !=
		 * null && element.equals(node.data)))//element��Ϊ��ʱ return index;
		 * index++; } return -1;
		 */
		int index = 0;
		// ���Ի��Ƿֿ�д��Ҳ��Դ����д����
		if (element == null) {
			for (Node node = header; node != null; node = node.next) {
				if (node.data == null)
					return index;
				index++;
			}
		} else {
			for (Node node = header; node != null; node = node.next) {
				if (element.equals(node.data))
					return index;
				index++;
			}
		}
		return -1;
	}
	//ָ��λ�ò���
	public void insert(int index, T element) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index");
		if(header == null)
			add(element);
		else
			addBefore(element, getNodeByIndex(index));
	}
	//�ڽڵ�ǰ����Ԫ��
	private void addBefore(T element, Node n) {
		final Node pre = n.prev;
		final Node newNode = new Node(pre, element, n);//�½ڵ�
		if (pre == null)// ��ǰ�ڵ�
			header = newNode;
		else
			pre.next = newNode;//ǰ�ڵ�next->�½ڵ�
		n.prev = newNode;//��һ�ڵ�prev->�½ڵ�
		size++;
	}

	// β�巨
	public void add(T element) {
		final Node node = tail;
		final Node newNode = new Node(node, element, null);
		if (node == null)// ����Ϊ��
			header = newNode;
		else
			node.next = newNode;
		tail = newNode;
		size++;
	}

	/**
	 * ͷ�巨
	 * @param element
	 */
	public void addHead(T element) {
		final Node node = header;
		final Node newNode = new Node(null, element, node);
		if (node == null)// ����Ϊ��
			tail = newNode;
		else
			node.prev = newNode;
		header = newNode;
		size++;
		
	}

	public T delete(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index "+index);
		return unlink(getNodeByIndex(index));
	}
	//ɾ��ָ���ڵ�
	private T unlink(Node del) {
		final T data = del.data;
		final Node prev = del.prev;
		final Node next = del.next;
		if(prev == null){//ɾ���ڵ�Ϊͷ���
			header = next;
		}else{
			prev.next = next;
			del.prev = null;//��ɾ���ڵ�ǰ����Ϊ��
		}
		if(next == null){//ɾ���ڵ�Ϊβ���
			tail = prev;
		}else{
			next.prev = prev;
			del.next = null;//��ɾ���ڵ�����Ϊ��
		}	
		del.data = null;//��ɾ���ڵ�������Ϊ��
		size--;
		return data;
	}

	public T remove() {
		return unlink(tail);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		//Ҳ��Ӧ�ô�ͷ�������ÿ����нڵ㣿
		header = null;
		tail = null;
		size = 0;
	}
	@Override
	public String toString() {
		if(size == 0) return "[]";
		StringBuilder sb = new StringBuilder("[");
		for(Node node = header; node.next != null ; node = node.next)
			sb.append(node.data+", ");
		int len = sb.length();
		return sb.delete(len - 2, len).append("]").toString();
	}
	// �����ڵ�
	private class Node {
		T data;// �ڵ�����
		Node next;// ��һ���ڵ�
		Node prev;// ǰһ���ڵ�

		public Node() {
		}

		Node(Node prev, T data, Node next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}
}