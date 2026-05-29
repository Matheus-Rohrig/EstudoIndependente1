
//Importar sistema de datas

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

//importar Scanner para leitura em terminal(Pop-up inventado é bucha)
//Importações relacionadas a utilização de MOVE e MOVE(Só que para renomear)(Mesmo método, só que indica o mesmo destino com nome diferente :))

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
                case 2: //Redireciona ao menu de edição de  OS
                    editOsPage();
                    menuLoop = false;
                    break;
                case 3: //Redireciona ao menu de consulta de OS

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

        String[] funcionarios = null;
        String auxFuncionarios = "";

        String[] horas = null;
        String auxHoras = "";

        String[] materiais = null;
        String[] quantidade = null;
        String auxQuantidades = "";
        String auxMateriais = "";

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
                    imprimir("Digite o nome da Empresa:\n");
                    empresa = in.nextLine();
                    break;
                //Funcionario e horas
                case 2:
                    boolean loop = true;
                    while (loop) {
                        imprimir("Digite o nome do funcionário\n");
                        imprimir("Para sair digite FIM\n");
                        String auxLeitura = in.nextLine();

                        if(auxLeitura.toUpperCase().equals("FIM")) {
                            break;
                        }

                        auxFuncionarios = auxFuncionarios + ";" + auxLeitura;
                        imprimir("Digite as Horas trabalhadas dele\n");
                        auxHoras = auxHoras + ";" + in.nextLine();

                    }

                    funcionarios = auxFuncionarios.split(";");
                    funcionarios = Arrays.stream(funcionarios)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    horas = auxHoras.split(";");
                    horas = Arrays.stream(horas)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    break;
                //Responsável técnico
                case 3:
                    imprimir("Digite o Responsável técnico\n");
                    respTecnico = in.nextLine();
                    break;
                //Descrição
                case 4:
                    imprimir("Digite a Descrição do serviço\n");
                    descricao = in.nextLine();
                    break;
                //Materiais utilizados
                case 5:
                    loop = true;
                    while (loop) {
                        imprimir("Digite o material utilizado\n");
                        imprimir("Para sair digite FIM\n");
                        String auxLeitura = in.nextLine();

                        if(auxLeitura.toUpperCase().equals("FIM")) {
                            break;
                        }
                        auxMateriais = auxMateriais + ";" + auxLeitura;
                        imprimir("Digite a quantidade utilizada dele\n");
                        auxQuantidades = auxQuantidades + ";" + in.nextLine();

                    }
                    materiais = auxMateriais.split(";");
                    materiais = Arrays.stream(materiais)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    quantidade = auxQuantidades.split(";");
                    quantidade = Arrays.stream(quantidade)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    break;
                //Observação
                case 6:
                    imprimir("Digite a observação\n");
                    observacao = in.nextLine();
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
                    //Escreve o cabecalho
                    fileArquivo.escreverLinha("Empresa;Descricao;Responsavel;Funcionario(s);Horas;Observacao;Materiai(s);Quantidade");

                    String dados = empresa + ";" + descricao + ";" + respTecnico + ";";

                    //Itera por todos funcionarios e os coloca na lista
                    if(funcionarios != null) {
                        for (int i = 0; i < funcionarios.length; i++) {
                            dados = dados + funcionarios[i] + "|";
                        }
                    }
                    //Caso campo nulo, ao menos delimita
                    dados = dados + ";";
                    //Itera por todos e os coloca na lista
                    if(horas != null) {
                        for (int i = 0; i < horas.length; i++) {
                            dados = dados + horas[i] + "|";
                        }
                    }
                    //Caso campo nulo, ao menos delimita
                    dados = dados + ";";
                    //Escreve a observação
                    dados = dados + observacao + ";";

                    //Itera pela lista de materiais e escreve no documento caso existir algum
                    if(materiais != null) {
                        for (int i = 0; i < materiais.length; i++) {
                            dados = dados + materiais[i] + "|";
                        }
                    }

                    dados = dados + ";";

                    //Itera pela lista de materiais e escreve no documento caso existir algum
                    if(quantidade != null) {
                        for (int i = 0; i < quantidade.length; i++) {
                            dados = dados + quantidade[i] + "|";
                        }
                    }
                    //Caso campo nulo, ao menos delimita
                    dados = dados + ";";

                    fileArquivo.escreverLinha(dados);

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

    public static void editOsPage() {
        Scanner in = new Scanner(System.in);
        File file = new File("OS/andamento");

        boolean finalizado = false;
        String empresa = null,
                respTecnico = null,
                descricao = null,
                observacao = null;

        String[] funcionarios = null;
        String auxFuncionarios = "Funcionario(s)";

        String[] horas = null;
        String auxHoras = "Horas";

        String[] materiais = null;
        String[] quantidade = null;
        String auxQuantidades = "Quantidade";
        String auxMateriais = "Materiais";

        File[] archivesOnFolder = file.listFiles();

        for (int i = 0; i < archivesOnFolder.length; i++) {
            imprimir((i+1) + " - " + archivesOnFolder[i].getName() + "\n");
        }

        imprimir("Selecione o arquivo que deseja editar:\n");
        imprimir("(Digite a numeração do arquivo mostrado na lista acima)\n");

        int selecaoArchive =  (in.nextInt() - 1);
        Arquivo fileEdit = new Arquivo("OS/andamento/" + archivesOnFolder[selecaoArchive].getName());

        boolean menuLoop = true;
        String auxFinalizado = null;
        if(finalizado) {
            auxFinalizado = "Finalizada";
        } else {
            auxFinalizado = "Andamento";
        }

        while(menuLoop) {
            imprimir("========================================\n");
            imprimir("              EDIÇÃO DE OS             \n");
            imprimir("========================================\n");
            imprimir("1 - Alterar nome da empresa\n");
            imprimir("2 - Alterar nome dos funcionários e horas trabalhadas\n");
            imprimir("3 - Alterar responsável técnico pelo serviço\n");
            imprimir("4 - Alterar Descrição serviço(s) realizado(s)\n");
            imprimir("5 - Alterar materiais utilizados\n");
            imprimir("6 - Alterar observação sobre o serviço\n");
            imprimir("7 - Alterar estado do serviço(Andamento/Finalizado)\n");
            imprimir("8 - Salvar Alterações\n");
            imprimir("9 - Exibir OS\n");
            imprimir("0 - Sair\n");
            imprimir("Escolha uma opção: \n");

            int selecao = in.nextInt();
            in.nextLine();

            fileEdit.abrirLeitura();
            //Ler o cabeçalho, atualemnte sem função alguma
            fileEdit.lerLinha();

            String auxDados = fileEdit.lerLinha();
        /*
        posições no array
        0 - empresa
        1 - Descricao
        2 - Responsavel
        3 - Funcionario(s) - repartir com |
        4 - Horas
        5 - Observacao
        6 - Materiai(s) repartir com |
        7 - Quantidade
         */
            String[] auxArray = auxDados.split(";");

            empresa = auxArray[0];
            descricao = auxArray[1];
            respTecnico = auxArray[2];
            funcionarios = auxArray[3].split("\\|");
            horas = auxArray[4].split("\\|");
            observacao = auxArray[5];
            materiais = auxArray[6].split("\\|");
            quantidade = auxArray[7].split("\\|");

            fileEdit.fecharArquivo();

            switch(selecao){
                case 1:
                    imprimir("Redigite o nome de empresa:\n");
                    empresa = in.nextLine();
                    menuLoop = false;
                    break;
                case 2:
                    boolean loop = true;
                    while (loop) {
                        imprimir("Redigite o nome do funcionário\n");
                        imprimir("Para sair digite FIM\n");
                        String auxLeitura = in.nextLine();

                        if(auxLeitura.toUpperCase().equals("FIM")) {
                            break;
                        }

                        auxFuncionarios = auxFuncionarios + ";" + auxLeitura;
                        imprimir("Redigite as Horas trabalhadas dele\n");
                        auxHoras = auxHoras + ";" + in.nextLine();

                    }

                    funcionarios = auxFuncionarios.split(";");
                    funcionarios = Arrays.stream(funcionarios)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    horas = auxHoras.split(";");
                    horas = Arrays.stream(horas)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    menuLoop = false;
                    break;
                case 3:
                    imprimir("Redigite o novo responśavel  técnico\n");
                    respTecnico = in.nextLine();
                    menuLoop = false;
                    break;
                case 4:
                    imprimir("Redigite a descrição de serviço\n");
                    menuLoop = false;
                    break;
                case 5:
                    loop = true;
                    while (loop) {
                        imprimir("Redigite o(s) material(s) utilizado\n");
                        imprimir("Para sair digite FIM\n");
                        String auxLeitura = in.nextLine();

                        if(auxLeitura.toUpperCase().equals("FIM")) {
                            break;
                        }
                        auxMateriais = auxMateriais + ";" + auxLeitura;
                        imprimir("Redigite a quantidade utilizada dele\n");
                        auxQuantidades = auxQuantidades + ";" + in.nextLine();

                    }
                    materiais = auxMateriais.split(";");
                    materiais = Arrays.stream(materiais)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    quantidade = auxQuantidades.split(";");
                    quantidade = Arrays.stream(quantidade)
                            .filter(s -> s != null && !s.trim().isEmpty() && !"null".equalsIgnoreCase(s.trim()))
                            .toArray(String[]::new);
                    menuLoop = false;
                    break;
                case 6:
                    imprimir("Redigite a observação\n");
                    observacao = in.nextLine();
                    menuLoop = false;
                    break;
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
                    menuLoop = false;
                    break;
                case 8:

                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    String actualDate= date.format(formatter);

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


                    String fileName = "OS/andamento/" + archivesOnFolder[selecaoArchive].getName();

                    Arquivo fileArquivo = new Arquivo(fileName);

                    //Abre a escrita
                    fileArquivo.abrirEscrita();
                    //Escreve o cabecalho
                    fileArquivo.escreverLinha("Empresa;Descricao;Responsavel;Funcionario(s);Horas;Observacao;Materiai(s);Quantidade");

                    String dados = empresa + ";" + descricao + ";" + respTecnico + ";";

                    //Itera por todos funcionarios e os coloca na lista
                    if(funcionarios != null) {
                        for (int i = 0; i < funcionarios.length; i++) {
                            dados = dados + funcionarios[i] + "|";
                        }
                    }
                    //Caso campo nulo, ao menos delimita
                    dados = dados + ";";
                    //Itera por todos e os coloca na lista
                    if(horas != null) {
                        for (int i = 0; i < horas.length; i++) {
                            dados = dados + horas[i] + "|";
                        }
                    }
                    //Caso campo nulo, ao menos delimita
                    dados = dados + ";";
                    //Escreve a observação
                    dados = dados + observacao + ";";

                    //Itera pela lista de materiais e escreve no documento caso existir algum
                    if(materiais != null) {
                        for (int i = 0; i < materiais.length; i++) {
                            dados = dados + materiais[i] + "|";
                        }
                    }

                    dados = dados + ";";

                    //Itera pela lista de materiais e escreve no documento caso existir algum
                    if(quantidade != null) {
                        for (int i = 0; i < quantidade.length; i++) {
                            dados = dados + quantidade[i] + "|";
                        }
                    }
                    //Caso campo nulo, ao menos delimita
                    dados = dados + ";";

                    fileArquivo.escreverLinha(dados);

                    //fecha arquivo
                    fileArquivo.fecharArquivo();
                    menuLoop = false;
                    break;
                case 9:
                    showOS("OS/andamento/" + archivesOnFolder[selecaoArchive].getName());
                    menuLoop = false;
                    break;
                case 0:
                    mainMenu();
                    menuLoop = false;
                    break;
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

    public static void showOS(String caminho) {
        Arquivo file = new Arquivo(caminho);

        boolean finalizado = false;
        String empresa = null,
                respTecnico = null,
                descricao = null,
                observacao = null;

        String[] funcionarios = null;
        String auxFuncionarios = "Funcionario(s)";

        String[] horas = null;
        String auxHoras = "Horas";

        String[] materiais = null;
        String[] quantidade = null;
        String auxQuantidades = "Quantidade";
        String auxMateriais = "Materiais";

        String auxFinalizado = null;
        if(finalizado) {
            auxFinalizado = "Finalizada";
        } else {
            auxFinalizado = "Andamento";
        }

        file.abrirLeitura();
        //Ler o cabeçalho, atualemnte sem função alguma
        file.lerLinha();

        String auxDados = file.lerLinha();
        /*
        posições no array
        0 - empresa
        1 - Descricao
        2 - Responsavel
        3 - Funcionario(s) - repartir com |
        4 - Horas
        5 - Observacao
        6 - Materiai(s) repartir com |
        7 - Quantidade
         */
        String[] auxArray = auxDados.split(";");

        empresa = auxArray[0];
        descricao = auxArray[1];
        respTecnico = auxArray[2];
        funcionarios = auxArray[3].split("\\|");
        horas = auxArray[4].split("\\|");
        observacao = auxArray[5];
        materiais = auxArray[6].split("\\|");
        quantidade = auxArray[7].split("\\|");

        file.fecharArquivo();

        imprimir("========================================\n");
        imprimir("            ORDEM DE SERVIÇO            \n");
        imprimir("========================================\n");
        imprimir("Estado do serviço: " + auxFinalizado + "\n");
        imprimir("========================================\n");

        imprimir("EMPRESA\n");
        imprimir("----------------------------------------\n");
        imprimir("Nome da empresa: " + empresa + "\n");
        imprimir("\n");

        imprimir("FUNCIONÁRIOS\n");
        imprimir("----------------------------------------\n");
        for(int i = 0; i < funcionarios.length; i++){
            imprimir("Funcionário " + (i+1) + " :" + funcionarios[i] + "\n");
            imprimir("Horas trabalhadas: "+ horas[i] + "\n");
        }
        imprimir("\n");

        imprimir("RESPONSÁVEL TÉCNICO\n");
        imprimir("----------------------------------------\n");
        imprimir("Nome: " + respTecnico + "\n");
        imprimir("\n");

        imprimir("DESCRIÇÃO DO SERVIÇO\n");
        imprimir(descricao + "\n");
        imprimir("\n");

        imprimir("MATERIAIS UTILIZADOS\n");
        imprimir("----------------------------------------\n");
        for(int i = 0; i < materiais.length; i++){
            imprimir("Material: " + (i+1) + " :" + materiais[i] + "\n");
            imprimir("qQuantidade: " + quantidade[i] + "\n");
        }

        imprimir("\n");

        imprimir("OBSERVAÇÕES\n");
        imprimir("----------------------------------------\n");
        imprimir(observacao + "\n");
        imprimir("\n");

        imprimir("========================================\n");
        imprimir("         FIM DA ORDEM DE SERVIÇO        \n");
        imprimir("========================================\n");
    }
}