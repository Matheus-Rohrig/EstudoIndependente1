
//Importar sistema de datas
import java.io.File;
import java.time.LocalDate;
//importar Scanner para leitura em terminal(Pop-up inventado é bucha)
import java.time.format.DateTimeFormatter;
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

        //Inicialização do Scanner
        Scanner in = new Scanner(System.in);

        boolean finalizado = false;
        String empresa = null,
                respTecnico = null,
                descricao = null,
                observacao = null;

        //0 - nome
        //1 - horas
        String[] funcionarios = null;
        String auxFuncionarios = "Funcionario(s)";

        //0 - Nome
        //1 - quantidade(Proibir letras, utilizar apenas números, mesmo sendo String)
        String[] horas = null;
        String auxHoras = "Horas";

        String[] materiais = null;
        String[] quantidade = null;
        String auxQuantidades = "Quantidade";
        String auxMateriais = "Materiais";

        //Variavel de validação de seleção
        boolean menuLoop = true;

        while(menuLoop) {
            imprimir("========================================\n");
            imprimir("              CRIAÇÃO DE OS             \n");
            imprimir("========================================\n");
            imprimir("1 - Definir nome da empresa*\n");
            imprimir("2 - Definir nome dos funcionários e horas trabalhadas*\n");
            imprimir("3 - Definir responsável técnico pelo serviço*\n");
            imprimir("4 - Descrever serviço(s) realizado(s)*\n");
            imprimir("5 - Definir materiais utilizados\n");
            imprimir("6 - Escrever uma observação sobre\n");
            imprimir("7 - Definir estado do serviço(Andamento/Finalizado)\n");
            imprimir("8 - Salvar Dados\n");
            imprimir("0 - Sair\n");
            imprimir("Escolha uma opção: \n");

            int selecao = in.nextInt();
            in.nextLine();
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String actualDate= date.format(formatter);
            switch(selecao) {
                //Nome da empresa
                case 1:
                    empresa = in.nextLine();
                    break;
                //Funcionario e horas
                case 2:
                    boolean loop = true;
                    while (loop) {
                        imprimir("Digite o nome do funcionário\n");
                        auxFuncionarios = auxFuncionarios + ";" + in.nextLine();
                        imprimir("Digite as Horas trabalhadas dele\n");
                        auxHoras = auxHoras + ";" + in.nextLine();

                        imprimir("Deseja realizar a parada do cadastro?\n");
                        imprimir("1 - Continuar\n");
                        imprimir("obs:Opção inválida será considerada saída do cadastro\n");
                        int escolha = in.nextInt();
                        if(escolha == 1) loop = true;
                        else loop = false;
                    }

                    funcionarios = auxFuncionarios.split(";");
                    horas = auxHoras.split(";");
                    break;
                //Responsável técnico
                case 3:
                    respTecnico = in.nextLine();
                    break;
                //Descrição
                case 4:
                    descricao = in.nextLine();
                    break;
                //Materiais utilizados
                case 5:
                    loop = true;
                    while (loop) {
                        imprimir("Digite o material utilizado\n");
                        auxMateriais = auxMateriais + ";" + in.nextLine();
                        imprimir("Digite a quantidade utilizada dele\n");
                        auxQuantidades = auxQuantidades + ";" + in.nextLine();

                        imprimir("Deseja realizar a parada do cadastro Materiais\n?");
                        imprimir("1 - Continuar\n");
                        imprimir("obs:Opção inválida será considerada saída do cadastro\n");
                        int escolha = in.nextInt();
                        if(escolha == 1) loop = true;
                        else loop = false;
                    }
                    materiais = auxMateriais.split(";");
                    quantidade = auxQuantidades.split(";");
                    break;
                //Observação
                case 6:
                    observacao = in.nextLine();
                    //menuLoop = false;
                    break;
                //OS em andamento ou finalizada
                case 7:
                    int escolha  = 0;
                    imprimir("A Ordem de Serviço está finalizada?\n");
                    imprimir("1 - SIM\n");
                    imprimir("2 - NÂO\n");
                    while(escolha != 1 && escolha != 2) {

                        escolha = in.nextInt();

                        if(escolha == 1) finalizado = true;
                        else if (escolha == 2)finalizado = false;
                        else imprimir("Escolha uma opção válida\n");
                    }
                    //menuLoop = false;
                    break;
                //Salvar arquivo
                case 8:
                    //Primeiro passo, validar os obrigatórios - Sem essa de cadastrar empresa sem nome...
                    boolean validacao = true;
                    if(empresa == null) {
                        System.out.println("O campo empresa precisar ser preenchida");
                        validacao = false;
                    }
                    if(respTecnico == null) {
                        System.out.println("É necessário ter o nome do responsável");
                    }
                    if(funcionarios == null || horas == null) {
                        System.out.println("É necessário ter o nome e as horas de ao menos um funcionário");
                    }
                    if(descricao == null) {
                        System.out.println("É necessário a descrição do realizado");
                    }
                    String filePath = "OS/andamento/";

                    if(finalizado) {
                        filePath = "OS/finalizada/";
                    }

                    String fileName = filePath + empresa + "_" + actualDate + ".csv";


                    try {
                        File createArchive = new File(fileName);
                        if (createArchive.createNewFile()) {
                            System.out.println("Arquivo criado com sucesso");
                        }else {
                            System.out.println("Arquivo já existe");
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    Arquivo fileArquivo = new Arquivo(fileName);

                    //Abre a escrita
                    fileArquivo.abrirEscrita();
                    //Escreve nome da empresa
                    fileArquivo.escreverLinha("Nome da Empresa: " + empresa + ";");
                    //Escreve os serviços realizados
                    fileArquivo.escreverLinha("Serviços Realizados:; " + descricao + ";");

                    //Itera por todos funcionarios e os coloca na lista
                    if(funcionarios != null && horas != null) {
                        for (int i = 0; i < funcionarios.length; i++) {
                            fileArquivo.escreverLinha(funcionarios[i] + ";" + horas[i] + ";");
                        }
                    }
                    //Escreve o responsavel técnico
                    fileArquivo.escreverLinha("Responsavel Tecnico:;" + respTecnico + ";");

                    //Escreve a observação se ela existir
                    if(observacao != null) {
                        fileArquivo.escreverLinha("Observação:;");
                        fileArquivo.escreverLinha(observacao + ";");
                    }
                    //Itera pela lista de materiais e escreve no documento caso existir algum
                    if(materiais != null && quantidade != null) {
                        for (int i = 0; i < materiais.length; i++) {
                            fileArquivo.escreverLinha(materiais[i] + ";" + quantidade[i] + ";");
                        }
                    }

                    //fecha arquivo
                    fileArquivo.fecharArquivo();
                    menuLoop = false;
                    break;

                //Voltar ao menu anterior(Menu inicial)
                case 0:
                    mainMenu();
                    menuLoop = false;
                    break;
                //Caso serem capazes de errarem o simples
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