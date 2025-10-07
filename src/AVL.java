public class AVL {
	Node root;

	public AVL(int value) {
		root = new Node(value);
	}
	
	//chamada
	public void insert(int value) {
		insert(value, root);
	}

	public Node getParent(Node element) {
			return getParent(element, root);
	}

	public Node getParent(Node element, Node root) {
		
		if (root==null) return null;
		int value = element.getValue();
		
		if (root.getNodeLeft() != null && root.getNodeLeft().getValue() == value) return root;
		if (root.getNodeRight() != null && root.getNodeRight().getValue() == value) return root;
		
		//chamar a função ela mesma 
		if (value < root.getValue()) 
			return getParent(element, root.getNodeLeft());
		else 
			return getParent(element, root.getNodeRight());
	}
	
	private int getChildrenNumber(Node node) {
		if (node.getNodeLeft() == null && node.getNodeRight() == null) 
			return 0;
		else if (node.getNodeLeft() != null && node.getNodeRight() == null ||
				 node.getNodeLeft() == null && node.getNodeRight() != null	) 
			 return 1;
		return 2;
	}
	
	public boolean delete(int value) {
		
		Node toDelete = search(value, root);
		if (toDelete == null) 
			return false;
		
		//se não tem filho
		if (toDelete.getNodeLeft() == null && toDelete.getNodeRight() == null) {
			if (getParent(toDelete) == null) {
				//é raiz
				root = null;
				return true;
			}
				
			if (getParent(toDelete).getValue() < value) {
				//o elemento a ser excluido é o  filho da direita
				getParent(toDelete).setNodeRight(null);
			} else
				getParent(toDelete).setNodeLeft(null);
			
			
		}
		else if (toDelete.getNodeLeft() != null && toDelete.getNodeRight() == null ||
			     toDelete.getNodeLeft() == null && toDelete.getNodeRight() != null	) {
			System.out.println("tem 1 filho");
			Node filho;
			
			if (toDelete.getNodeLeft() != null) 
				filho = toDelete.getNodeLeft();
			else 
				filho = toDelete.getNodeRight();
			
			System.out.println("filho " + filho.getValue());
			
			if (getParent(toDelete) == null) {
				//é raiz
				root = filho;
				return true;
			}
			
			//descobrir se o toDelete é um filho da esquerda ou da direita
			
			if (toDelete.getValue() > getParent(toDelete).getValue()) {
				//toDelete é um filho da direita
				//ligar pai no filho
				getParent(toDelete).setNodeRight(filho);
			} else {
				//toDelete é um filho da esquerda
				getParent(toDelete).setNodeLeft(filho);
			}
			
		}	
		else if (getChildrenNumber(toDelete) == 2) {
			System.out.println("tem 2 filhos");
	
			//encontrando o sucessor do toDelete
			
			Node successor = getSuccessor(toDelete);
			
			
			if (getChildrenNumber(successor) < 2) {
				
				//trocar o sucessor pelo toDelete
				successor.setNodeLeft(toDelete.getNodeLeft());
				getParent(successor).setNodeLeft(null);
				
				if (getParent(toDelete) == null) {
					//é raiz
					if (toDelete.getNodeRight().getValue() != successor.getValue()) {
						toDelete.getNodeRight().setNodeLeft(successor.getNodeRight());
						successor.setNodeRight(toDelete.getNodeRight());
					}
					root = successor;
					
					return true;
					
				}
				
				
				if (getParent(toDelete).getValue() < successor.getValue()) {
					getParent(toDelete).setNodeRight(successor); 
				}
				else {
					getParent(toDelete).setNodeLeft(successor); 
				}
				
				
				
				//para não ligar nele mesmo 
				if (toDelete.getNodeRight() != successor ) {
					successor.setNodeRight(toDelete.getNodeRight());
					toDelete.setNodeRight(null);
					
				}
				
				toDelete.setNodeRight(null); 

				
                toDelete.setNodeLeft(null);
			}
				
		}
		
		return true;
	}
	
	//para ser recursivo
	private void insert(int value, Node root) {
		//tem que ter uma condição de parada
		//chegar na folha 
		//criando um novo nó
		
		if (root.getValue() == value) return;
		
		if (root.getNodeLeft() == null 
				&& root.getValue() > value) {		
			Node newNode = new Node(value);
			root.setNodeLeft(newNode);
			return;
	    }	
		else 
		if (root.getNodeRight() == null 
				&& root.getValue() < value) {
			Node newNode = new Node(value);
			root.setNodeRight(newNode);
			return;
	    }
		
		//chamar a função ela mesma com parametros diferentes
		if (value < root.getValue()) 
			insert(value, root.getNodeLeft());
		else 
			insert(value, root.getNodeRight());
		
	}
	
	//chamada
		public Node search(int value) {
			return search(value, root);
		}
	
		
		//para ser recursivo
		private Node search(int value, Node root) {
	
			if (root==null) return null;
			if (root.getValue() == value) return root;
			
			//chamar a função ela mesma 
			if (value < root.getValue()) 
				return search(value, root.getNodeLeft());
			else 
				return search(value, root.getNodeRight());
		}

		public int getSuccessor(int value) {
			Node node = search(value, root);
			
			if (getSuccessor(node) == null) return value;
			return getSuccessor(node).getValue();
		}
		
		private Node getSuccessor(Node element) {
			  return getSuccessor(element, root);
		}
		
		private Node getSuccessor(Node element, Node root) {
			if (element.getNodeRight() != null) {
		        return findMostLeft(element.getNodeRight());
		    } 
			return null;		   
		}
		
		private Node findMostLeft(Node node) {
		    if (node.getNodeLeft() == null) {
		        return node;
		    } else {
		        return findMostLeft(node.getNodeLeft());
		    }
		}	

	public Node getRoot() {
		return root;
	}

	public boolean isStrictBinaryTree() {
		return isStrictBinaryTree(root);
	}
	private boolean isStrictBinaryTree(Node v) {
		if (v == null) //condição de saida, percorreu a arvore toda e não achou filho único
			return true;
		
		//se achou filho único, ou seja, só um lado é null 
		if ((v.getNodeLeft()== null && v.getNodeRight() != null) 
			||
			(v.getNodeLeft() != null && v.getNodeRight() == null)) {
		    return false;
		}
		
		return isStrictBinaryTree(v.getNodeLeft()) &&
			   isStrictBinaryTree(v.getNodeRight());
	
	}
	
	public int findNodeAmount() {
		return findNodeAmount(root); 
	}
	
	//encontra a quantidade de nós da árvore 
	private int findNodeAmount (Node tree) {
	    if (tree == null) {
	        return 0;
	    } else 
	        return (findNodeAmount(tree.getNodeRight())
	        		+ findNodeAmount(tree.getNodeLeft()) + 1);
	}

	public int findDepth () {
		return findDepth(root); 
	}
	//encontra profundidade da árvore
	public int findDepth (Node tree) {
	    if (tree == null) {
	        return -1;
	    } else 
	    	return Math.max(findDepth(tree.getNodeLeft()), 
	    			findDepth(tree.getNodeRight()))+1;
	    
	}
	
	public void setBalanceamentFactor(Node node) {
	    node.setBalanceamentFactor(
	    		findDepth(node.getNodeLeft()) - 
	    		findDepth(node.getNodeRight()));
	}
	
	void preOrderPrint() {
		preOrderPrint(root);
	}
	
	private void preOrderPrint(Node v) {
		if (v == null) //condição de saida
			return;
		
		//raiz primeiro
		System.out.print(v.getValue() + " ");
		//subarvore esquerda
		preOrderPrint(v.getNodeLeft());		
		//subarvore direita
		preOrderPrint(v.getNodeRight());
	}

	void inOrderPrint() {
		inOrderPrint(root);
	}
	
	private void inOrderPrint(Node v) {
		if (v == null) //condição de saida
			return;
		
		//subarvore esquerda
		inOrderPrint(v.getNodeLeft());		
		//raiz no meio
		System.out.print(v.getValue() + " ");
		//subarvore direita
		inOrderPrint(v.getNodeRight());
	}
	
	void postOrderPrint() {
		postOrderPrint(root);
	}
	
	private void postOrderPrint(Node v) {
		if (v == null) //condição de saida
			return;
		
		//subarvore esquerda
		postOrderPrint(v.getNodeLeft());		
		//subarvore direita
		postOrderPrint(v.getNodeRight());
		//raiz por último 
		System.out.print(v.getValue() + " ");
	}
}