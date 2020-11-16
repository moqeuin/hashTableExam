package hashTableExam;

import java.util.LinkedList;

/*

해쉬
배열은 고유의 인덱스 번호 가지고 있어서 데이터를 탐색할 때 인덱스번호만 알고 있어도 
빠른 탐색이 가능하다. 단점으로는 추가나 삭제를 할 경우 빈 자리를 만들거나 메꿔야하기 때문에
기존의 데이터를 이동시켜야하기 때문에 많은 시간이 소요된다.
연결리스트는 데이터를 한 노드의 저장시키고 인근 노드들을 참조주소를 저장하면서 데이터를 추가하거나
삭제할 때 참조주소만 변경해서 빠른 처리가 가능하다.
단점으로는 데이터를 탐색할 때 처음부터 마지막까지 순회검색을 해야하기 때문에 검색속도가 느리다.
이러한 문제를 해결하기 위해서 해쉬라는 자료구조를 구상했다.
해시는 내부적으로 자체배열을 가지고 있다.
데이터를 추가할 때 자신의 고유의 해쉬코드라는 자신만의 고유한(unique) 정수를 가지고 
데이터를 저장할 배열을 찾는다. 해쉬코드는 객체나 기본자료형들의 고유의 값을 해쉬함수를 이용해서
해쉬코드를 만든다. 이러한 해쉬코드를 지정알고리즘으로 인덱스번호로 변환시켜서 배열에 접근한다.
하지만 이러한 해쉬코드도 충돌이 일어날 수 있기 때문에 분리연결법으로 각 배열의 요소를 다시
연결리스트로 구성해서 각 노드마다 키와 value로 저장시킨다.
그러면 중복된 인덱스에 데이터를 추가할 때 노드를 추가하면서 충돌을 막을 수 있다.


*/
class HashTable{
	// 연결리스트의 배열
	LinkedList<Node>[] data;
	
	// 연결리스트에 저장할 노드
	class Node{
		
		String key;
		String value;
		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
	}
	
	// 해쉬테이블 생성시 크기를 정한다
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		this.data = new LinkedList[size];
	}
	
	// 해쉬코드값을 key를 통해 구한다
	int getHashCode(String key) {
		int hashCode = 0;
		for (char c : key.toCharArray()) {
			hashCode += c;
		}
		return hashCode;
	}
	// 배열의 인덱스를 해쉬코드값으로 구한다
	int converToIndex(int hashCode) {
		return hashCode%data.length;
	}
	
	// ket와 대응되는 노드가 있는지 리스트에서 탐색한다
	Node searchKey(LinkedList<Node> list, String key) {
		
		if(list == null) return null;
		for (Node node : list) {
			if(node.key.equals(key)) {

				return node;
			}
		}
		return null;
	}
	
	// 해쉬테이블에 데이터 삽입
	void put(String key, String value) {
		int hashCode = getHashCode(key);
		int index = converToIndex(hashCode);
		// 인덱스번호에 대응하는 리스트를 대입
		LinkedList<Node> list = data[index];
		// 인덱스번호와 대응하는 리스트가 없으면 새로 생성한다
		if(list == null) {
			
			list = new LinkedList<Node>();
			data[index] = list;
			
		}
		// key를 통해서 노드가 있는지 확인
		Node node = searchKey(list, key);
		// 노드가 없으면 대입한 key와 value로 노드를 만들고 리스트에 노드를 추가
		if(node == null){
			node = new Node(key, value);
			data[index].add(node);
		}
		// 기존에 노드가 있으면 value를 덮어씌운다
		else {
			
			node.value = value;
		}
	}
	
	// 데이터 추출
	String get(String key) {
		
		int hashCode = getHashCode(key);
		int index = converToIndex(hashCode);
		LinkedList<Node> list = data[index];
		Node node = searchKey(list, key);
		
		return (node == null)?"==no data==":node.value;
	}
	
	
	
	
}
public class HashTableExam {

	public static void main(String[] args) {
		HashTable ht = new HashTable(10);
		ht.put("one", "첫 번째 글입니다.");
		System.out.println(ht.get("one"));
	}

}
