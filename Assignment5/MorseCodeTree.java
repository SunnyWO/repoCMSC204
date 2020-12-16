import java.util.ArrayList;

public class MorseCodeTree<T> implements LinkedConverterTreeInterface<T> {
	private TreeNode<T> root;

	MorseCodeTree(){
		root = new TreeNode<T>((T) "");
		buildTree();
		
	}
	
	@Override
	public TreeNode<T> getRoot() {
		return (TreeNode<T>) root;
	}

	@Override
	public void setRoot(TreeNode<T> newNode) {
		TreeNode<T> newRoot = new TreeNode<T>(newNode);
		this.root = newRoot;
	}

	@Override
	public LinkedConverterTreeInterface<T> insert(T code, T result) {
		addNode(root, code, result);
		return this;
	}

	@Override
	public void addNode(TreeNode<T> root, T code, T letter) {
		if(((String)code).length()==1) {
			if(((String)code).equals(".")) {
				if(root.leftChild==null) {
					root.leftChild = new TreeNode<T>(letter);
				}
				root.leftChild.data = letter;
			}
			if(((String)code).equals("-")) {
				if(root.rightChild==null) {
					root.rightChild = new TreeNode<T>(letter);
				}
				root.rightChild.data = letter;
			}
			return;
		}
		else {
			if(((String)code).charAt(0)=='.') {
				if(root.leftChild==null) {
					root.leftChild = new TreeNode<T>((T) "empty");
				}
				root = root.leftChild;
			}
			if(((String)code).charAt(0)=='-') {
				if(root.rightChild==null) {
					root.rightChild = new TreeNode<T>((T) "empty");
				}
				root = root.rightChild;
			}
			addNode(root, (T)((String)code).substring(1), letter);
		}
		
		
	}

	@Override
	public T fetch(String code) {
		return fetchNode(root,(T)code);
	}

	@Override
	public T fetchNode(TreeNode<T> root, T code) {
		if(code.equals("")) {
			return (T) "";
		}
		
		if(((String)code).length()==1) {
			if(((String)code).equals(".")) {
				return root.leftChild.data;
			}
			if(((String)code).equals("-")) {
				return root.rightChild.data;
			}
		}
		else {
			if(((String)code).charAt(0)=='.') {
				root = root.leftChild;
			}
			if(((String)code).charAt(0)=='-') {
				root = root.rightChild;
			}
			
		}
		return fetchNode(root, (T)((String)code).substring(1));
		
	}

	@Override
	public LinkedConverterTreeInterface<T> delete(T data) throws UnsupportedOperationException {
		// This operation is not supported in the MorseCodeTree
		return this;
	}

	@Override
	public LinkedConverterTreeInterface<T> update() throws UnsupportedOperationException {
		// This operation is not supported in the MorseCodeTree
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildTree() {
		insert((T)".-",(T)"a");
		insert((T)"-...",(T)"b");
		insert((T)"-.-.",(T)"c");
		insert((T)"-..",(T)"d");
		insert((T)".",(T)"e");
		insert((T)"..-.",(T)"f");
		insert((T)"--.",(T)"g");
		insert((T)"....",(T)"h");
		insert((T)"..",(T)"i");
		insert((T)".---",(T)"j");
		insert((T)"-.-",(T)"k");
		insert((T)".-..",(T)"l");
		insert((T)"--",(T)"m");
		insert((T)"-.",(T)"n");
		insert((T)"---",(T)"o");
		insert((T)".--.",(T)"p");
		insert((T)"--.-",(T)"q");
		insert((T)".-.",(T)"r");
		insert((T)"...",(T)"s");
		insert((T)"-",(T)"t");
		insert((T)"..-",(T)"u");
		insert((T)"...-",(T)"v");
		insert((T)".--",(T)"w");
		insert((T)"-..-",(T)"x");
		insert((T)"-.--",(T)"y");
		insert((T)"--..",(T)"z");
		
	}

	@Override
	public ArrayList<T> toArrayList() {
		LNRoutputTraversal(root,inOrderList);
		return inOrderList;
	}
	
	ArrayList<T> inOrderList = new ArrayList<T>();

	@Override
	public void LNRoutputTraversal(TreeNode<T> root, ArrayList<T> list) {
		if(root == null) {
			return;
		}
		
		LNRoutputTraversal(root.leftChild,inOrderList);
		
		inOrderList.add(root.data);
		
		LNRoutputTraversal(root.rightChild,inOrderList);
		
	}

	
	
}
