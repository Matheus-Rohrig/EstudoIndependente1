import java.util.Date;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        mainMenu();
    }

    public static void imprimir(String frase){
        System.out.print(frase);
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
            //imprimir("6 - Salvar Dados\n");
            //imprimir("7 - Carregar Dados\n");
            imprimir("0 - Sair\n");
            imprimir("Escolha uma opção: ");

            int selecao = in.nextInt();

            switch(selecao){
                case 1:
                    createOSPage();
                    menuLoop = false;
                    break;
                case 2:

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
                /*
                case 6:

                    menuLoop = false;
                    break;

                 */
                /*
                case 7:

                    menuLoop = false;
                    break;

                */
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
        Scanner in = new Scanner(System.in);

        String empresa = "", arquivo = "", respTecnico = "", descricao = "", observacao = "", estado = "";

        //0 - nome
        //1 - horas
        String[][] funcionarios = new String[20][2];
        String auxFuncionarios = "";

        //0 - Nome
        //1 - quantidade(Proibir letras, utilizar apenas números, mesmo sendo String)
        String[][] materiais = new String[20][2];

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
            switch (selecao) {
                case 1:
                    menuLoop = false;
                    break;
                case 2:
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
                case 6:
                    menuLoop = false;
                    break;
                case 7:
                    menuLoop = false;
                    break;
                case 8:
                    menuLoop = false;
                    break;
                case 9:

                    String filePath = "../OS/";
                    String fileName = filePath + empresa + "_" + realClock;

                    Arquivo file = new Arquivo(fileName + ".csv");

                    file.abrirEscrita();
                    file.escreverLinha("Nome da Empresa: " + empresa);
                    file.escreverLinha("Funcionarios;horas;");
                    file.escreverLinha(empresa);

                    file.fecharArquivo();
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
}