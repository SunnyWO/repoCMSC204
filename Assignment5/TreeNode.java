
public class TreeNode<T> {
	T data;
	TreeNode<T> leftChild;
	TreeNode<T> rightChild;
	
	// Create a new TreeNode with left and right child set to null and data set to the dataNode
	public TreeNode(T dataNode){
		leftChild = null;
		rightChild = null;
		data = dataNode;
	}
	
	//Return the data within this TreeNode
	public TreeNode(TreeNode<T> node){
		leftChild = node.leftChild;
		rightChild = node.rightChild;
		data = node.data;
	}
	
	//Return the data within this TreeNode
	public T getData() {
		return this.data;
	}
	

}
