public class Start {

	public static void main(String[] args) {

		System.out.println("--- INICIANDO TESTES DA ÁRVORE AVL ---");

		// ########################################################
		// ## 1. TESTES DE INSERÇÃO
		// ########################################################
		System.out.println("\n## 1. Testando Inserções ##");

		// Teste 1: Rotação Simples à Direita (LL)
		AVL avl_LL = new AVL(30);
		System.out.println("\n--- Teste 1: Rotação LL (Simples Direita) ---");
		System.out.println("Inserindo 30, 20, 10...");
		avl_LL.insert(20);
		avl_LL.insert(10); // Deve causar Rotação LL no nó 30
		printTreeDetails(avl_LL, "Resultado LL (Raiz deve ser 20)");

		// Teste 2: Rotação Simples à Esquerda (RR)
		AVL avl_RR = new AVL(10);
		System.out.println("\n--- Teste 2: Rotação RR (Simples Esquerda) ---");
		System.out.println("Inserindo 10, 20, 30...");
		avl_RR.insert(20);
		avl_RR.insert(30); // Deve causar Rotação RR no nó 10
		printTreeDetails(avl_RR, "Resultado RR (Raiz deve ser 20)");

		// Teste 3: Rotação Dupla à Direita (LR)
		AVL avl_LR = new AVL(30);
		System.out.println("\n--- Teste 3: Rotação LR (Dupla Direita) ---");
		System.out.println("Inserindo 30, 10, 20...");
		avl_LR.insert(10);
		avl_LR.insert(20); // Deve causar Rotação LR no nó 30
		printTreeDetails(avl_LR, "Resultado LR (Raiz deve ser 20)");

		// Teste 4: Rotação Dupla à Esquerda (RL)
		AVL avl_RL = new AVL(10);
		System.out.println("\n--- Teste 4: Rotação RL (Dupla Esquerda) ---");
		System.out.println("Inserindo 10, 30, 20...");
		avl_RL.insert(30);
		avl_RL.insert(20); // Deve causar Rotação RL no nó 10
		printTreeDetails(avl_RL, "Resultado RL (Raiz deve ser 20)");

		// ########################################################
		// ## 2. TESTES DE REMOÇÃO
		// ########################################################
		System.out.println("\n\n## 2. Testando Remoções ##");
		
		// Teste 5: Remoção causando Rotação RR (Simples Esquerda)
		System.out.println("\n--- Teste 5: Remoção -> Rotação RR ---");
		AVL avl_Del_RR = new AVL(30);
		avl_Del_RR.insert(20);
		avl_Del_RR.insert(40);
		avl_Del_RR.insert(50);
		printTreeDetails(avl_Del_RR, "Árvore antes da remoção:");
		avl_Del_RR.delete(20); // Remoção do 20 desbalanceia o 30 (fator -2)
		printTreeDetails(avl_Del_RR, "Após remover 20 (Raiz deve ser 40)");

		// Teste 6: Remoção causando Rotação LL (Simples Direita)
		System.out.println("\n--- Teste 6: Remoção -> Rotação LL ---");
		AVL avl_Del_LL = new AVL(30);
		avl_Del_LL.insert(20);
		avl_Del_LL.insert(40);
		avl_Del_LL.insert(10);
		printTreeDetails(avl_Del_LL, "Árvore antes da remoção:");
		avl_Del_LL.delete(40); // Remoção do 40 desbalanceia o 30 (fator +2)
		printTreeDetails(avl_Del_LL, "Após remover 40 (Raiz deve ser 20)");

		// Teste 7: Remoção causando Rotação RL (Dupla Esquerda)
		System.out.println("\n--- Teste 7: Remoção -> Rotação RL ---");
		AVL avl_Del_RL = new AVL(30);
		avl_Del_RL.insert(20);
		avl_Del_RL.insert(50);
		avl_Del_RL.insert(40);
		printTreeDetails(avl_Del_RL, "Árvore antes da remoção:");
		avl_Del_RL.delete(20); // Remoção do 20 desbalanceia o 30 (fator -2), filho 50 (fator +1)
		printTreeDetails(avl_Del_RL, "Após remover 20 (Raiz deve ser 40)");

		// Teste 8: Remoção causando Rotação LR (Dupla Direita)
		System.out.println("\n--- Teste 8: Remoção -> Rotação LR ---");
		AVL avl_Del_LR = new AVL(30);
		avl_Del_LR.insert(20);
		avl_Del_LR.insert(40);
		avl_Del_LR.insert(25);
		printTreeDetails(avl_Del_LR, "Árvore antes da remoção:");
		avl_Del_LR.delete(40); // Remoção do 40 desbalanceia o 30 (fator +2), filho 20 (fator -1)
		printTreeDetails(avl_Del_LR, "Após remover 40 (Raiz deve ser 25)");

		// ########################################################
		// ## 3. TESTE COMPLEXO 
		// ########################################################
		System.out.println("\n\n## 3. Teste Complexo ##");
		
		AVL avlTeste = new AVL(4);
		avlTeste.insert(2);		
		avlTeste.insert(1); // Causa Rotação LL em 4. Raiz vira 2.
		System.out.println("--- Inserido 4, 2, 1 ---");
		printTreeDetails(avlTeste, "Árvore após rotação LL:");

		avlTeste.insert(5);
		avlTeste.insert(3);
		avlTeste.insert(7);
		avlTeste.insert(18); // Causa Rotação RR em 5. Raiz da subárvore vira 7.
		System.out.println("\n--- Inserido 5, 3, 7, 18 ---");
		printTreeDetails(avlTeste, "Árvore após rotação RR:");

		avlTeste.insert(20); // Causa Rotação RR em 7.
		System.out.println("\n--- Inserido 20 ---");
		printTreeDetails(avlTeste, "Árvore após rotação RR:");
		
				
		System.out.println("\n--- Verificações Finais da Árvore Complexa ---");
		System.out.println("Raiz: " + avlTeste.getRoot().getValue());
		System.out.print("É estritamente binária? ");
		System.out.println(avlTeste.isStrictBinaryTree());
		System.out.print("Quantidade de nós: ");
		System.out.println(avlTeste.findNodeAmount());
		System.out.print("Altura (profundidade): h=");
		System.out.println(avlTeste.findDepth());
		System.out.print("Está balanceada (verificação)? ");
		System.out.println(avlTeste.isBalanced());

		// Teste de remoção de 2 filhos (com sucessor) e rebalanceamento
		System.out.println("\n--- Teste de Remoção Complexa (remover raiz 2) ---");
		avlTeste.delete(2); // Remove a raiz, que tem 2 filhos.
		                   // Sucessor é 3. Nó 3 substitui 2.
		                   // A remoção do 3 da sua posição original causa desbalanceamento.
		printTreeDetails(avlTeste, "Árvore final após remover 2:");
		System.out.println("Raiz final: " + avlTeste.getRoot().getValue());
		System.out.println("Balanceada? " + avlTeste.isBalanced());
	}

	/**
	 * Método auxiliar para imprimir detalhes da árvore de forma limpa.
	 * @param tree A árvore AVL a ser impressa
	 * @param title Um título para a impressão
	 */
	public static void printTreeDetails(AVL tree, String title) {
		System.out.println(title);
		if (tree.getRoot() == null) {
			System.out.println("Árvore está vazia.");
			return;
		}
		System.out.print("  Em Ordem:   ");
		tree.inOrderPrint();
		System.out.println();
		System.out.print("  Pré Ordem:  ");
		tree.preOrderPrint();
		System.out.println();
		System.out.print("  Pós Ordem:  ");
		tree.postOrderPrint();
		System.out.println();
		System.out.println("  Raiz: " + tree.getRoot().getValue() + " | Altura: " + tree.findDepth());
		System.out.println("  Balanceada? " + tree.isBalanced());
	}
}