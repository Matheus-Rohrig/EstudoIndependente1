
//Importar sistema de datas
import java.util.Date;

//importar Scanner para leitura em terminal(Pop-up inventado é bucha)
import java.util.Scanner;

//Importações relacionadas a utilização de MOVE e MOVE(Só que para renomear)(Mesmo método, só que indica o mesmo destino com nome diferente :))
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        mainMenu();
    }

    public static void mainMenu() {

        Scanner in = new Scanner(System.in);

        boolean menuLoop = true;
        while(menuLoop) {
            imprimir("========================================\n");
            imprimir("SISTEMA DE GESTÃO DE ORDENS DE SERVIÇO\n");
            imprimir("========================================\n");
            imprimir("1 - Criar Ordem de Serviço\n");
            imprimir("2 - Alterar Ordem de Serviço\n");
            imprimir("3 - Consultar Ordem de Serviço\n");
            imprimir("4 - Listar Ordens de Serviço\n");
            imprimir("5 - Gerar Relatórios\n");
            imprimir("0 - Sair\n");
            imprimir("Escolha uma opção: ");

            int selecao = in.nextInt();

            switch(selecao){
                case 1: //redireciona a seu devido menu
                    createOSPage();
                    menuLoop = false;
                    break;
                case 2: //

                    menuLoop = false;
                    break;
                case 3:

                    menuLoop = false;
                    break;
                case 4:

                    menuLoop = false;
                    break;
                case 5:

                    menuLoop = false;
                    break;
                case 0:
                    imprimir("==================================================\n");
                    imprimir("Obrigado pela utilização deste aplicativo arcaico\n");
                    imprimir("==================================================\n");
                    System.exit(0);
                default:
                    imprimir("Opção inválida, por favor selecione uma das opções mencionadas\n");
            }
        }
    }
    public static void createOSPage() {
        //Como aqui é a criação, o primeiro arquivo começa em andamento e com nome genérico
        Arquivo file = new Arquivo("../OS/andamento/inicializacao.csv");

        //Replica o mesmo caminho para o Padrão do New I/O
        Path pathArquivo = Paths.get("../OS/andamento/inicializacao.csv");

        //Inicialização do Scanner
        Scanner in = new Scanner(System.in);

        boolean finalizado = false;
        String empresa = "",
                arquivo = "",
                respTecnico = "",
                descricao = "",
                observacao = "",
                estado = "";

        //0 - nome
        //1 - horas
        String[] funcionarios;
        String auxFuncionarios = "";

        //0 - Nome
        //1 - quantidade(Proibir letras, utilizar apenas números, mesmo sendo String)
        String[] horas;
        String auxHoras = "";
        //Variavel de validação de seleção
        boolean menuLoop = true;

        while(menuLoop) {
            imprimir("========================================\n");
            imprimir("              CRIAÇÃO DE OS             \n");
            imprimir("========================================\n");
            imprimir("1 - Definir nome da empresa\n");
            imprimir("2 - Definir nome dos funcionários e horas trabalhadas\n");
            imprimir("3 - Definir nome do arquivo\n");
            imprimir("4 - Definir responsável técnico pelo serviço\n");
            imprimir("5 - Descrever serviço(s) realizado(s)\n");
            imprimir("6 - Definir materiais utilizados\n");
            imprimir("7 - Escrever uma observação sobre\n");
            imprimir("8 - Definir estado do serviço(Andamento/Finalizado)\n");
            imprimir("9 - Salvar Dados\n");
            imprimir("0 - Sair\n");
            imprimir("Escolha uma opção: ");

            int selecao = in.nextInt();
            Date realClock = new Date();
            switch(selecao) {
                case 1:
                    empresa = in.next();

                    break;
                case 2:
                    boolean loop = true;
                    while (loop) {
                        imprimir("Digite o nome do funcionário\n");
                        auxFuncionarios = auxFuncionarios + ";" + in.next();
                        imprimir("Digite as Horas trabalhadas dele\n");
                        auxHoras = auxHoras + ";" + in.next();

                        imprimir("Deseja realizar a parada do cadastro de usuários\n?");
                        imprimir("1 - Continuar\n");
                        imprimir("obs:Opção inválida será considerada saída do cadastro\n");
                        int escolha = in.nextInt();
                        if(escolha == 1) loop = true;
                        else loop = false;
                    }

                    funcionarios = auxFuncionarios.split(";");
                    horas = auxHoras.split(";");
                    break;
                case 3:
                    arquivo = in.next();
                    //menuLoop = false;
                    break;
                case 4:
                    respTecnico = in.next();
                    //menuLoop = false;
                    break;
                case 5:
                    descricao = in.next();
                    //menuLoop = false;
                    break;
                case 6:
                    //menuLoop = false;
                    break;
                case 7:
                    observacao = in.next();
                    //menuLoop = false;
                    break;
                case 8:
                    int escolha  = 0;
                    imprimir("A Ordem de Serviço está finalizada?\n");
                    imprimir("1 - SIM\n");
                    imprimir("2 - NÂO\n");
                    while(escolha != 1 || escolha != 2) {

                        escolha = in.nextInt();

                        if(escolha == 1) finalizado = true;
                        else if (escolha == 2)finalizado = false;
                        else imprimir("Escolha uma opção válida\n");
                    }
                    //menuLoop = false;
                    break;
                case 9:

                    String filePath = "../OS/";
                    String fileName = filePath + empresa + "_" + realClock;

                    Arquivo fileArquivo = new Arquivo(fileName + ".csv");

                    fileArquivo.abrirEscrita();
                    fileArquivo.escreverLinha("Nome da Empresa: " + empresa + ";");
                    fileArquivo.escreverLinha("Serviços Realizados;Funcionarios;Horas;Responsavel Tecnico;");
                    fileArquivo.escreverLinha("Observação:;");

                    fileArquivo.fecharArquivo();
                    menuLoop = false;
                    break;
                case 0:
                    mainMenu();
                    menuLoop = false;
                    break;
                default:
                    imprimir("Opção inválida, por favor selecione uma das opções mencionadas\n");
            }
        }
    }

    public static void fileMover(Path caminhoOrigem, Path caminhoDestino) {
        try {
            Files.move(caminhoOrigem, caminhoDestino);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void fileRenamer(Path caminhoOrigem, Path novoNome) {
        try {
            Files.move(caminhoOrigem, novoNome);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void imprimir(String frase){
        System.out.print(frase);
    }
}