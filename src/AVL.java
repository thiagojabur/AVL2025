public class AVL {
	Node root;

	public AVL(int value) {
		root = new Node(value);
	}
	
	// M�todo auxiliar para obter a altura
	private int height(Node n) {
			if (n == null)
				return -1; // Altura de um n� nulo � -1 
			return n.getHeight();
	}
	
	//chamada
	public void insert(int value) {
		root = insert(value, root);
	}
	

	// M�todo recursivo privado
		private Node insert(int value, Node node) {
			// 1. Inser��o normal de �rvore Bin�ria de Busca
			if (node == null) {
				System.out.println("Inserindo " + value);
				return (new Node(value));
			}

			if (value < node.getValue())
				node.setNodeLeft(insert(value, node.getNodeLeft()));
			else if (value > node.getValue())
				node.setNodeRight(insert(value, node.getNodeRight()));
			else // Valores duplicados n�o s�o permitidos
				return node;

			// 2. Atualizar a altura deste n� (ancestral)
			// A altura � 1 + a altura da maior sub�rvore (esquerda ou direita)
			node.setHeight(1 + Math.max(height(node.getNodeLeft()), height(node.getNodeRight())));

			// 3. Obter o fator de balanceamento deste n�
			int balance = getBalanceFactor(node);

			// 4. Se o n� ficar desbalanceado, existem 4 casos de rota��o

			// Caso Esquerda-Esquerda (LL)
			if (balance > 1 && value < node.getNodeLeft().getValue()) {
				return simpleRotationRight(node);
			}

			// Caso Direita-Direita (RR)
			if (balance < -1 && value > node.getNodeRight().getValue()) {
				return simpleRotationLeft(node);
			}

			// Caso Esquerda-Direita (LR)
			if (balance > 1 && value > node.getNodeLeft().getValue()) {
				System.out.println("Rota��o Dupla � Direita (LR) em " + node.getValue());
				node.setNodeLeft(simpleRotationLeft(node.getNodeLeft()));
				return simpleRotationRight(node);
			}

			// Caso Direita-Esquerda (RL)
			if (balance < -1 && value < node.getNodeRight().getValue()) {
				System.out.println("Rota��o Dupla � Esquerda (RL) em " + node.getValue());
				node.setNodeRight(simpleRotationRight(node.getNodeRight()));
				return simpleRotationLeft(node);
			}

			// 5. Retorna o n� (balanceado)
			return node;
		}

	public Node getParent(Node element) {
			return getParent(element, root);
	}

	public Node getParent(Node element, Node root) {
		
		if (root==null) return null;
		int value = element.getValue();
		
		if (root.getNodeLeft() != null && root.getNodeLeft().getValue() == value) return root;
		if (root.getNodeRight() != null && root.getNodeRight().getValue() == value) return root;
		
		//chamar a fun��o ela mesma 
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
	
	//chamada
		public Node search(int value) {
			return search(value, root);
		}
	
		// Chamada p�blica
		public void delete(int value) {
			System.out.println("Removendo " + value);
			root = delete(value, root);
		}

		// M�todo recursivo privado de remo��o e balanceamento
		private Node delete(int value, Node node) {
			// 1. Remo��o padr�o de �rvore Bin�ria de Busca (BST)
			if (node == null) {
				System.out.println("Valor " + value + " n�o encontrado.");
				return node; // Valor n�o encontrado
			}

			// Navega at� o n� a ser removido
			if (value < node.getValue()) {
				node.setNodeLeft(delete(value, node.getNodeLeft()));
			} else if (value > node.getValue()) {
				node.setNodeRight(delete(value, node.getNodeRight()));
			} else {
				// N� com o valor encontrado!

				// Caso 1: N� com 0 ou 1 filho
				if (node.getNodeLeft() == null || node.getNodeRight() == null) {
					Node temp = (node.getNodeLeft() != null) ? node.getNodeLeft() : node.getNodeRight();

					if (temp == null) {
						// 0 filhos (folha)
						node = null;
					} else {
						// 1 filho
						node = temp; // O filho "sobe" e substitui o n�
					}
				} else {
					// Caso 2: N� com 2 filhos
					// Encontra o sucessor in-order (menor valor da sub�rvore direita)
					Node successor = findMostLeft(node.getNodeRight());

					// Copia o valor do sucessor para este n�
					node.setValue(successor.getValue()); // <-- VER NOTA IMPORTANTE ABAIXO

					// Remove o n� sucessor (que agora � duplicado) da sub�rvore direita
					node.setNodeRight(delete(successor.getValue(), node.getNodeRight()));
				}
			}

			// 2. L�gica de Balanceamento AVL (Executa no "caminho de volta" da recurs�o)
			
			// Se a �rvore ficou vazia (s� tinha 1 n�)
			if (node == null) {
				return node;
			}

			// Atualiza a altura do n� atual
			node.setHeight(1 + Math.max(height(node.getNodeLeft()), height(node.getNodeRight())));

			// Calcula o fator de balanceamento
			int balance = getBalanceFactor(node);

			// --- 4 Casos de Rota��o ---

			// Caso 1: Desbalanceado � Esquerda (LL ou LR)
			if (balance > 1) {
				// Verifica o filho da esquerda para decidir
				if (getBalanceFactor(node.getNodeLeft()) >= 0) {
					// Caso LL (Simples � Direita)
					return simpleRotationRight(node);
				} else {
					// Caso LR (Dupla Direita)
					System.out.println("Rota��o Dupla � Direita (LR) em " + node.getValue());
					node.setNodeLeft(simpleRotationLeft(node.getNodeLeft()));
					return simpleRotationRight(node);
				}
			}

			// Caso 2: Desbalanceado � Direita (RR ou RL)
			if (balance < -1) {
				// Verifica o filho da direita para decidir
				if (getBalanceFactor(node.getNodeRight()) <= 0) {
					// Caso RR (Simples � Esquerda)
					return simpleRotationLeft(node);
				} else {
					// Caso RL (Dupla Esquerda)
					System.out.println("Rota��o Dupla � Esquerda (RL) em " + node.getValue());
					node.setNodeRight(simpleRotationRight(node.getNodeRight()));
					return simpleRotationLeft(node);
				}
			}

			// 3. Retorna o n� (agora balanceado)
			return node;
		}
		
		//para ser recursivo
		private Node search(int value, Node root) {
	
			if (root==null) return null;
			if (root.getValue() == value) return root;
			
			//chamar a fun��o ela mesma 
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
		if (v == null) //condi��o de saida, percorreu a arvore toda e n�o achou filho �nico
			return true;
		
		//se achou filho �nico, ou seja, s� um lado � null 
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
	
	//encontra a quantidade de n�s da �rvore 
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
	//encontra profundidade da �rvore
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
	
	private int getBalanceFactor(Node n) {
		if (n == null)
			return 0;
		return height(n.getNodeLeft()) - height(n.getNodeRight());
	}
	
	void preOrderPrint() {
		preOrderPrint(root);
	}
	
	private void preOrderPrint(Node v) {
		if (v == null) //condi��o de saida
			return;
		
		//raiz primeiro
		System.out.print(v.getValue() + " ");
		//subarvore esquerda
		preOrderPrint(v.getNodeLeft());		
		//subarvore direita
		preOrderPrint(v.getNodeRight());
	}
	
	
	// Rota��o Simples � Direita (Caso LL)
		private Node simpleRotationRight(Node a) {
			System.out.println("Rota��o Simples � Direita (LL) em " + a.getValue());
			Node b = a.getNodeLeft();
			Node T2 = b.getNodeRight();

			// Realiza a rota��o
			b.setNodeRight(a);
			a.setNodeLeft(T2);

			// Atualiza alturas (IMPORTANTE: 'a' primeiro, pois agora � filho de 'b')
			a.setHeight(1 + Math.max(height(a.getNodeLeft()), height(a.getNodeRight())));
			b.setHeight(1 + Math.max(height(b.getNodeLeft()), height(b.getNodeRight())));

			// Retorna a nova raiz
			return b;
		}

		// Rota��o Simples � Esquerda (Caso RR)
		private Node simpleRotationLeft(Node a) {
			System.out.println("Rota��o Simples � Esquerda (RR) em " + a.getValue());
			Node b = a.getNodeRight();
			Node T1 = b.getNodeLeft();

			// Realiza a rota��o
			b.setNodeLeft(a);
			a.setNodeRight(T1);

			// Atualiza alturas ('a' primeiro, pois agora � filho de 'b')
			a.setHeight(1 + Math.max(height(a.getNodeLeft()), height(a.getNodeRight())));
			b.setHeight(1 + Math.max(height(b.getNodeLeft()), height(b.getNodeRight())));

			// Retorna a nova raiz
			return b;
		}
	
	boolean isBalanced() {
		return isBalanced(root);
	}
	
	
	
	private boolean isBalanced(Node v) {
		if (v == null) 
			return true;
		
		setBalanceamentFactor(v);
		
		return (Math.abs(v.getBalanceamentFactor()) <= 1) 
				&& isBalanced(v.getNodeLeft()) 
				&& isBalanced(v.getNodeRight());
	}
	
	
	
	

	void inOrderPrint() {
		inOrderPrint(root);
	}
	
	private void inOrderPrint(Node v) {
		if (v == null) //condi��o de saida
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
		if (v == null) //condi��o de saida
			return;
		
		//subarvore esquerda
		postOrderPrint(v.getNodeLeft());		
		//subarvore direita
		postOrderPrint(v.getNodeRight());
		//raiz por �ltimo 
		System.out.print(v.getValue() + " ");
	}
}