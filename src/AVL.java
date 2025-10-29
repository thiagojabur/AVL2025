public class AVL {
	Node root;

	public AVL(int value) {
		root = new Node(value);
	}
	
	// Método auxiliar para obter a altura
	private int height(Node n) {
			if (n == null)
				return -1; // Altura de um nó nulo é -1 
			return n.getHeight();
	}
	
	//chamada
	public void insert(int value) {
		root = insert(value, root);
	}
	

	// Método recursivo privado
		private Node insert(int value, Node node) {
			// 1. Inserção normal de Árvore Binária de Busca
			if (node == null) {
				System.out.println("Inserindo " + value);
				return (new Node(value));
			}

			if (value < node.getValue())
				node.setNodeLeft(insert(value, node.getNodeLeft()));
			else if (value > node.getValue())
				node.setNodeRight(insert(value, node.getNodeRight()));
			else // Valores duplicados não são permitidos
				return node;

			// 2. Atualizar a altura deste nó (ancestral)
			// A altura é 1 + a altura da maior subárvore (esquerda ou direita)
			node.setHeight(1 + Math.max(height(node.getNodeLeft()), height(node.getNodeRight())));

			// 3. Obter o fator de balanceamento deste nó
			int balance = getBalanceFactor(node);

			// 4. Se o nó ficar desbalanceado, existem 4 casos de rotação

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
				System.out.println("Rotação Dupla à Direita (LR) em " + node.getValue());
				node.setNodeLeft(simpleRotationLeft(node.getNodeLeft()));
				return simpleRotationRight(node);
			}

			// Caso Direita-Esquerda (RL)
			if (balance < -1 && value < node.getNodeRight().getValue()) {
				System.out.println("Rotação Dupla à Esquerda (RL) em " + node.getValue());
				node.setNodeRight(simpleRotationRight(node.getNodeRight()));
				return simpleRotationLeft(node);
			}

			// 5. Retorna o nó (balanceado)
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
	
	//chamada
		public Node search(int value) {
			return search(value, root);
		}
	
		// Chamada pública
		public void delete(int value) {
			System.out.println("Removendo " + value);
			root = delete(value, root);
		}

		// Método recursivo privado de remoção e balanceamento
		private Node delete(int value, Node node) {
			// 1. Remoção padrão de Árvore Binária de Busca (BST)
			if (node == null) {
				System.out.println("Valor " + value + " não encontrado.");
				return node; // Valor não encontrado
			}

			// Navega até o nó a ser removido
			if (value < node.getValue()) {
				node.setNodeLeft(delete(value, node.getNodeLeft()));
			} else if (value > node.getValue()) {
				node.setNodeRight(delete(value, node.getNodeRight()));
			} else {
				// Nó com o valor encontrado!

				// Caso 1: Nó com 0 ou 1 filho
				if (node.getNodeLeft() == null || node.getNodeRight() == null) {
					Node temp = (node.getNodeLeft() != null) ? node.getNodeLeft() : node.getNodeRight();

					if (temp == null) {
						// 0 filhos (folha)
						node = null;
					} else {
						// 1 filho
						node = temp; // O filho "sobe" e substitui o nó
					}
				} else {
					// Caso 2: Nó com 2 filhos
					// Encontra o sucessor in-order (menor valor da subárvore direita)
					Node successor = findMostLeft(node.getNodeRight());

					// Copia o valor do sucessor para este nó
					node.setValue(successor.getValue()); // <-- VER NOTA IMPORTANTE ABAIXO

					// Remove o nó sucessor (que agora é duplicado) da subárvore direita
					node.setNodeRight(delete(successor.getValue(), node.getNodeRight()));
				}
			}

			// 2. Lógica de Balanceamento AVL (Executa no "caminho de volta" da recursão)
			
			// Se a árvore ficou vazia (só tinha 1 nó)
			if (node == null) {
				return node;
			}

			// Atualiza a altura do nó atual
			node.setHeight(1 + Math.max(height(node.getNodeLeft()), height(node.getNodeRight())));

			// Calcula o fator de balanceamento
			int balance = getBalanceFactor(node);

			// --- 4 Casos de Rotação ---

			// Caso 1: Desbalanceado à Esquerda (LL ou LR)
			if (balance > 1) {
				// Verifica o filho da esquerda para decidir
				if (getBalanceFactor(node.getNodeLeft()) >= 0) {
					// Caso LL (Simples à Direita)
					return simpleRotationRight(node);
				} else {
					// Caso LR (Dupla Direita)
					System.out.println("Rotação Dupla à Direita (LR) em " + node.getValue());
					node.setNodeLeft(simpleRotationLeft(node.getNodeLeft()));
					return simpleRotationRight(node);
				}
			}

			// Caso 2: Desbalanceado à Direita (RR ou RL)
			if (balance < -1) {
				// Verifica o filho da direita para decidir
				if (getBalanceFactor(node.getNodeRight()) <= 0) {
					// Caso RR (Simples à Esquerda)
					return simpleRotationLeft(node);
				} else {
					// Caso RL (Dupla Esquerda)
					System.out.println("Rotação Dupla à Esquerda (RL) em " + node.getValue());
					node.setNodeRight(simpleRotationRight(node.getNodeRight()));
					return simpleRotationLeft(node);
				}
			}

			// 3. Retorna o nó (agora balanceado)
			return node;
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
	
	private int getBalanceFactor(Node n) {
		if (n == null)
			return 0;
		return height(n.getNodeLeft()) - height(n.getNodeRight());
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
	
	
	// Rotação Simples à Direita (Caso LL)
		private Node simpleRotationRight(Node a) {
			System.out.println("Rotação Simples à Direita (LL) em " + a.getValue());
			Node b = a.getNodeLeft();
			Node T2 = b.getNodeRight();

			// Realiza a rotação
			b.setNodeRight(a);
			a.setNodeLeft(T2);

			// Atualiza alturas (IMPORTANTE: 'a' primeiro, pois agora é filho de 'b')
			a.setHeight(1 + Math.max(height(a.getNodeLeft()), height(a.getNodeRight())));
			b.setHeight(1 + Math.max(height(b.getNodeLeft()), height(b.getNodeRight())));

			// Retorna a nova raiz
			return b;
		}

		// Rotação Simples à Esquerda (Caso RR)
		private Node simpleRotationLeft(Node a) {
			System.out.println("Rotação Simples à Esquerda (RR) em " + a.getValue());
			Node b = a.getNodeRight();
			Node T1 = b.getNodeLeft();

			// Realiza a rotação
			b.setNodeLeft(a);
			a.setNodeRight(T1);

			// Atualiza alturas ('a' primeiro, pois agora é filho de 'b')
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