import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception { 
        HashMap<Integer,String>nomes=new HashMap<Integer,String>();
        HashMap<Integer,Double>precos=new HashMap<Integer,Double>();    
        HashMap<Integer,Integer>quantidadeEmEstoque=new HashMap<Integer,Integer>();
        HashMap<Integer,LocalDateTime>dataDaVenda = new HashMap<Integer,LocalDateTime>(); 
        //HashMap<Integer,Integer> quantidadeVendida = new HashMap<Integer,Integer>();

        int opcao = 0;
        Scanner in = new Scanner(System.in);
        do {
            limparTela();
            mostrarMenu();
            opcao = lerOpcaoMenu(scanner);
            if (!processaOpcaoMenu(scanner,opcao,nomes,precos,quantidadeEmEstoque,dataDaVenda)) {
                System.out.println("Opção inválida. Tente Novamente...");
                Thread.sleep(2000);
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }
    
    private static boolean processaOpcaoMenu(Scanner scanner, int opcao, HashMap<Integer, String> nomes,
            HashMap<Integer, Double> precos, HashMap<Integer, Integer> quantidadeEmEstoque, HashMap<Integer, LocalDateTime>dataDaVenda ) throws IOException, InterruptedException {
            switch (opcao){
                case 1:
                    cadastrarProduto(scanner,nomes,precos,quantidadeEmEstoque); 
                    return true;
                case 2:
                    consultarProduto(scanner,nomes,precos,quantidadeEmEstoque); 
                    return true;
                case 3:
                    listarProdutos(nomes,precos,quantidadeEmEstoque); 
                    return true;
                case 4:
                    exibirRelatorioVendasPorPeriodo(); 
                    return true;
                case 5:
                    realizarVenda(); 
                    return true;
                case 6:
                    removerProduto(scanner,nomes,precos); 
                    return true;
                /*case 7:
                    alterarProduto(scanner,nomes,precos,quantidadeEmEstoque); 
                    return true;*/
                case 0:
                    sairDoProgrma();
                    return true;
                default: 
                    return false;
            }

    }

    private static void removerProduto(Scanner scanner2, HashMap<Integer, String> nomes,
            HashMap<Integer, Double> precos) throws IOException, InterruptedException {
                limparTela();
                System.out.println("Remover Produto");
                System.out.println("--------------------------------------------");
                System.out.println("Digite o codigo do produto");
                int codigo = scanner.nextInt();
                scanner.nextLine(); 
                if (nomes.containsKey(codigo)){
                    System.out.format("Valores originais:%n%s, R$ %.2f%n",nomes.get(codigo), precos.get(codigo));
                    System.out.println("Confirma a exclusão (S/N)? ");
                    String resposta = scanner.nextLine().trim();
                    
                    if(resposta.toUpperCase().equals("S")){
                        nomes.remove(codigo);
                        precos.remove(codigo);
                        System.out.println("--------------------------------------------");
                        System.out.println("Produto removido com sucesso!");
                        System.out.println(">> Presssione ENTER para voltar ao menu ...");
                    }else {
                        System.out.println("Remoção não confirmada.");
                        System.out.println(">> Presssione ENTER para voltar ao menu ...");
                    }
                }else {
                    System.out.println("Produto não encontrado!");
                    System.out.println(">> Pressione ENTER para voltar ao menu ...");
                }
                System.in.read();


    }

    private static void realizarVenda() throws IOException, InterruptedException {
        limparTela();
        

    }

    private static void exibirRelatorioVendasPorPeriodo() throws IOException, InterruptedException {
        limparTela();
        System.out.println("Relatorio em construção...");
        System.out.println(">> Pressione ENTER para voltar ao menu ...");
        System.in.read();

    }

    private static void listarProdutos(HashMap<Integer, String> nomes, HashMap<Integer, Double> precos,
        HashMap<Integer, Integer> quantidadeEmEstoque) throws IOException, InterruptedException {
        limparTela();
        System.out.println("Produtos Cadastrados");
        System.out.println("--------------------------------------------");
        System.out.format("%6s | %-10s | %6s | %8s%n","Código","Nome","Preço","Qtd Estoque");
        System.out.println("--------------------------------------------");
        for(int chave:nomes.keySet()){
            System.out.format("%06d | %-10s | %6.2f | %6s%n",chave,nomes.get(chave),precos.get(chave),quantidadeEmEstoque.get(chave));
        }
        System.out.println("--------------------------------------------");
        exibirMedia(precos);
        System.out.println("--------------------------------------------");
      
        System.out.print("\n>> Pressione ENTER para voltar ao menu ...");
        System.in.read();

    }
    

    private static void exibirMedia(HashMap<Integer, Double> precos) {
        double media = 0,mediafinal =0,maiorValor = 0, menorValor=999999999;
        

        for (Map.Entry<Integer,Double> p : precos.entrySet()){
            media = media + p.getValue();
            mediafinal = media/precos.size();

            if (p.getValue()>maiorValor){
                maiorValor = p.getValue();

            }
            if (p.getValue()<menorValor){
                menorValor =  p.getValue();

            }
        }
        System.out.format("O VALOR MÉDIO É: %6.2f | O MAIOR VALOR É: %6.2f | O MENOR VALOR É: %6.2f%n",mediafinal,maiorValor,menorValor);
        
    }

    private static void consultarProduto(Scanner scanner2, HashMap<Integer, String> nomes,
            HashMap<Integer, Double> precos, HashMap<Integer, Integer> quantidadeEmEstoque) throws IOException, InterruptedException {
                limparTela();
                System.out.println("Buscar Produto");
                System.out.println("--------------------------------------------");
                System.out.println("Digite o codigo do produto");
                int codigo = scanner.nextInt();
                scanner.nextLine(); 
                if (nomes.containsKey(codigo)){
                    System.out.format("O produto: %s encontrado. \n O preço do produto é:  R$ %.2f%n",nomes.get(codigo), precos.get(codigo));
                }else {
                    System.out.println("Produto não encontrado!");
                    System.out.println(">> Pressione ENTER para voltar ao menu ...");
                }
                System.in.read();

    }

    private static void cadastrarProduto(Scanner scanner2, HashMap<Integer, String> nomes,
        HashMap<Integer, Double> precos, HashMap<Integer, Integer> quantidadeEmEstoque) throws IOException, InterruptedException {
        limparTela();
        System.out.println("INSERIR UM PRODUTO");
        System.out.println("------------------------------------");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        Double preco = scanner.nextDouble();
        System.out.print("Quantidade em Estoque: ");
        Integer quatidadeEmEstoque = scanner.nextInt();
        int proximaChave = obterProximaChave(nomes.keySet());
        nomes.put(proximaChave,nome);
        precos.put(proximaChave,preco);
        quantidadeEmEstoque.put(proximaChave,quatidadeEmEstoque);
        System.out.println("------------------------------------");
        System.out.println("Produto inserido com sucesso.");
        System.out.println("Pressione ENTER para voltar ao menu...");
        System.in.read();
    }

    private static int obterProximaChave(Set<Integer> keySet) {
        if (keySet.size() > 0){
            return Collections.max(keySet) + 1; 
        }else return 1;
    }

    private static int lerOpcaoMenu(Scanner scanner) {
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private static void mostrarMenu() {
        System.out.println("CADASTRO DE PRODUTOS - MERCADO IAGO SILVA");
        System.out.println("------------------------------------");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Consultar Produto");
        System.out.println("3 - Listagem de Produtos");
        System.out.println("4 - Vendas por período");
        System.out.println("5 - Realizar Venda");
        System.out.println("6 - Remover produto");
        System.out.println("0 - Sair");
        System.out.println("------------------------------------");
    }

    private static void sairDoProgrma() throws InterruptedException, IOException {
        System.out.println("Saindo do Programa...");
        Thread.sleep(1500);
        limparTela();
    }

    public static void limparTela () throws IOException, InterruptedException {
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    }
}