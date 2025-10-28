public class Node {
	private int value, balanceamentFactor = 0;
	private Node nodeLeft, nodeRight;
	
	public Node(int value) {
		super();
		this.value = value;
	}
	
	public Node(int value, Node nodeLeft, Node nodeRight) {
		this.value = value;
		this.nodeLeft = nodeLeft;
		this.nodeRight = nodeRight;
	}
	
	public int getBalanceamentFactor() {
		return balanceamentFactor;
	}

	public void setBalanceamentFactor(int balanceamentFactor) {
		this.balanceamentFactor = balanceamentFactor;
	}
	
	

	public boolean isExternal(){
		if (nodeLeft == null && nodeRight == null )
			return true;
		return false;
	}
	
	//grau do nós
	public int length() {
		if (isExternal()) return 0;
		if (nodeLeft != null && nodeRight != null ) return 2;
		return 1;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getNodeLeft() {
		return nodeLeft;
	}
	public void setNodeLeft(Node nodeLeft) {
		this.nodeLeft = nodeLeft;
	}
	public Node getNodeRight() {
		return nodeRight;
	}
	public void setNodeRight(Node nodeRight) {
		this.nodeRight = nodeRight;
	}
	
	
}