public class Start {

	public static void main(String[] args) {

		System.out.println("--- INICIANDO TESTES DA �RVORE AVL ---");

		// ########################################################
		// ## 1. TESTES DE INSER��O
		// ########################################################
		System.out.println("\n## 1. Testando Inser��es ##");

		// Teste 1: Rota��o Simples � Direita (LL)
		AVL avl_LL = new AVL(30);
		System.out.println("\n--- Teste 1: Rota��o LL (Simples Direita) ---");
		System.out.println("Inserindo 30, 20, 10...");
		avl_LL.insert(20);
		avl_LL.insert(10); // Deve causar Rota��o LL no n� 30
		printTreeDetails(avl_LL, "Resultado LL (Raiz deve ser 20)");

		// Teste 2: Rota��o Simples � Esquerda (RR)
		AVL avl_RR = new AVL(10);
		System.out.println("\n--- Teste 2: Rota��o RR (Simples Esquerda) ---");
		System.out.println("Inserindo 10, 20, 30...");
		avl_RR.insert(20);
		avl_RR.insert(30); // Deve causar Rota��o RR no n� 10
		printTreeDetails(avl_RR, "Resultado RR (Raiz deve ser 20)");

		// Teste 3: Rota��o Dupla � Direita (LR)
		AVL avl_LR = new AVL(30);
		System.out.println("\n--- Teste 3: Rota��o LR (Dupla Direita) ---");
		System.out.println("Inserindo 30, 10, 20...");
		avl_LR.insert(10);
		avl_LR.insert(20); // Deve causar Rota��o LR no n� 30
		printTreeDetails(avl_LR, "Resultado LR (Raiz deve ser 20)");

		// Teste 4: Rota��o Dupla � Esquerda (RL)
		AVL avl_RL = new AVL(10);
		System.out.println("\n--- Teste 4: Rota��o RL (Dupla Esquerda) ---");
		System.out.println("Inserindo 10, 30, 20...");
		avl_RL.insert(30);
		avl_RL.insert(20); // Deve causar Rota��o RL no n� 10
		printTreeDetails(avl_RL, "Resultado RL (Raiz deve ser 20)");

		// ########################################################
		// ## 2. TESTES DE REMO��O
		// ########################################################
		System.out.println("\n\n## 2. Testando Remo��es ##");
		
		// Teste 5: Remo��o causando Rota��o RR (Simples Esquerda)
		System.out.println("\n--- Teste 5: Remo��o -> Rota��o RR ---");
		AVL avl_Del_RR = new AVL(30);
		avl_Del_RR.insert(20);
		avl_Del_RR.insert(40);
		avl_Del_RR.insert(50);
		printTreeDetails(avl_Del_RR, "�rvore antes da remo��o:");
		avl_Del_RR.delete(20); // Remo��o do 20 desbalanceia o 30 (fator -2)
		printTreeDetails(avl_Del_RR, "Ap�s remover 20 (Raiz deve ser 40)");

		// Teste 6: Remo��o causando Rota��o LL (Simples Direita)
		System.out.println("\n--- Teste 6: Remo��o -> Rota��o LL ---");
		AVL avl_Del_LL = new AVL(30);
		avl_Del_LL.insert(20);
		avl_Del_LL.insert(40);
		avl_Del_LL.insert(10);
		printTreeDetails(avl_Del_LL, "�rvore antes da remo��o:");
		avl_Del_LL.delete(40); // Remo��o do 40 desbalanceia o 30 (fator +2)
		printTreeDetails(avl_Del_LL, "Ap�s remover 40 (Raiz deve ser 20)");

		// Teste 7: Remo��o causando Rota��o RL (Dupla Esquerda)
		System.out.println("\n--- Teste 7: Remo��o -> Rota��o RL ---");
		AVL avl_Del_RL = new AVL(30);
		avl_Del_RL.insert(20);
		avl_Del_RL.insert(50);
		avl_Del_RL.insert(40);
		printTreeDetails(avl_Del_RL, "�rvore antes da remo��o:");
		avl_Del_RL.delete(20); // Remo��o do 20 desbalanceia o 30 (fator -2), filho 50 (fator +1)
		printTreeDetails(avl_Del_RL, "Ap�s remover 20 (Raiz deve ser 40)");

		// Teste 8: Remo��o causando Rota��o LR (Dupla Direita)
		System.out.println("\n--- Teste 8: Remo��o -> Rota��o LR ---");
		AVL avl_Del_LR = new AVL(30);
		avl_Del_LR.insert(20);
		avl_Del_LR.insert(40);
		avl_Del_LR.insert(25);
		printTreeDetails(avl_Del_LR, "�rvore antes da remo��o:");
		avl_Del_LR.delete(40); // Remo��o do 40 desbalanceia o 30 (fator +2), filho 20 (fator -1)
		printTreeDetails(avl_Del_LR, "Ap�s remover 40 (Raiz deve ser 25)");

		// ########################################################
		// ## 3. TESTE COMPLEXO 
		// ########################################################
		System.out.println("\n\n## 3. Teste Complexo ##");
		
		AVL avlTeste = new AVL(4);
		avlTeste.insert(2);		
		avlTeste.insert(1); // Causa Rota��o LL em 4. Raiz vira 2.
		System.out.println("--- Inserido 4, 2, 1 ---");
		printTreeDetails(avlTeste, "�rvore ap�s rota��o LL:");

		avlTeste.insert(5);
		avlTeste.insert(3);
		avlTeste.insert(7);
		avlTeste.insert(18); // Causa Rota��o RR em 5. Raiz da sub�rvore vira 7.
		System.out.println("\n--- Inserido 5, 3, 7, 18 ---");
		printTreeDetails(avlTeste, "�rvore ap�s rota��o RR:");

		avlTeste.insert(20); // Causa Rota��o RR em 7.
		System.out.println("\n--- Inserido 20 ---");
		printTreeDetails(avlTeste, "�rvore ap�s rota��o RR:");
		
				
		System.out.println("\n--- Verifica��es Finais da �rvore Complexa ---");
		System.out.println("Raiz: " + avlTeste.getRoot().getValue());
		System.out.print("� estritamente bin�ria? ");
		System.out.println(avlTeste.isStrictBinaryTree());
		System.out.print("Quantidade de n�s: ");
		System.out.println(avlTeste.findNodeAmount());
		System.out.print("Altura (profundidade): h=");
		System.out.println(avlTeste.findDepth());
		System.out.print("Est� balanceada (verifica��o)? ");
		System.out.println(avlTeste.isBalanced());

		// Teste de remo��o de 2 filhos (com sucessor) e rebalanceamento
		System.out.println("\n--- Teste de Remo��o Complexa (remover raiz 2) ---");
		avlTeste.delete(2); // Remove a raiz, que tem 2 filhos.
		                   // Sucessor � 3. N� 3 substitui 2.
		                   // A remo��o do 3 da sua posi��o original causa desbalanceamento.
		printTreeDetails(avlTeste, "�rvore final ap�s remover 2:");
		System.out.println("Raiz final: " + avlTeste.getRoot().getValue());
		System.out.println("Balanceada? " + avlTeste.isBalanced());
	}

	/**
	 * M�todo auxiliar para imprimir detalhes da �rvore de forma limpa.
	 * @param tree A �rvore AVL a ser impressa
	 * @param title Um t�tulo para a impress�o
	 */
	public static void printTreeDetails(AVL tree, String title) {
		System.out.println(title);
		if (tree.getRoot() == null) {
			System.out.println("�rvore est� vazia.");
			return;
		}
		System.out.print("  Em Ordem:   ");
		tree.inOrderPrint();
		System.out.println();
		System.out.print("  Pr� Ordem:  ");
		tree.preOrderPrint();
		System.out.println();
		System.out.print("  P�s Ordem:  ");
		tree.postOrderPrint();
		System.out.println();
		System.out.println("  Raiz: " + tree.getRoot().getValue() + " | Altura: " + tree.findDepth());
		System.out.println("  Balanceada? " + tree.isBalanced());
	}
}