import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {
	private Node next;
	private LinkedList<Node> availableAdjacent;
	private Node parent;
	private int key;
	public static final int INFINITE = Integer.MAX_VALUE;
	boolean isAvailable = false;
	boolean isNull = false;
	private int locx;
	private int locy;
	public boolean isChecked;
	public boolean isUsed;
	public boolean isThing;

	public boolean isThing() {
		return isThing;
	}

	public void setThing(boolean isThing) {
		this.isThing = isThing;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public Stack prev = new Stack();

	public Node() {
		this.setNext(null);
		this.setParent(null);
		this.setKey(INFINITE);
		this.setChecked(false);
		this.setUsed(false);
		this.setThing(false);
	}
	
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public LinkedList<Node> getAvailableAdjacent() {
		return availableAdjacent;
	}

	public void setAvailableAdjacent(LinkedList<Node> linkedList) {
		this.availableAdjacent = linkedList;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getLocx() {
		return locx;
	}

	public void setLocx(int locx) {
		this.locx = locx;
	}

	public int getLocy() {
		return locy;
	}

	public void setLocy(int locy) {
		this.locy = locy;
	}


	

}