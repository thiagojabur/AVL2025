
public class Start {
	public static void main(String[] args) {

		AVL avlTeste = new AVL(4);
		
		avlTeste.insert(3);		
		avlTeste.insert(2);
		avlTeste.insert(14);
		avlTeste.insert(18);
		avlTeste.insert(1);

		System.out.println("Grau da Raiz: " +  avlTeste.getRoot().length());
		System.out.print("Pré ordem: ");
		avlTeste.preOrderPrint();
		System.out.println("");
		System.out.print("Em ordem: ");
		avlTeste.inOrderPrint();
		System.out.println("");
		System.out.print("Pós ordem: ");
		avlTeste.postOrderPrint();
		System.out.println("");
		System.out.print("É estritamente binária: ");
		System.out.print(avlTeste.isStrictBinaryTree());
		System.out.println("");
		System.out.print("Quantidade de nós: ");
		System.out.println(avlTeste.findNodeAmount());

		System.out.print("Quantidade máxima de níveis (profundidade): ");
		System.out.println(avlTeste.findDepth());

		
		System.out.print("Em ordem: ");
		avlTeste.inOrderPrint();	
		
		System.out.println();
		System.out.println("Raiz: " + avlTeste.getRoot().getValue());
	
		System.out.println(avlTeste.findDepth(avlTeste.search(3)));
		
		int verificarBF = 14;
		avlTeste.setBalanceamentFactor(avlTeste.search(verificarBF));
		System.out.println("Fator de balanceamento do " + verificarBF + ": " + avlTeste.search(verificarBF).getBalanceamentFactor());
	
		System.out.print("É balanceada: ");
		System.out.println(avlTeste.isBalanced());
	}
}
