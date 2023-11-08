package sptech.faztudo.comLOCAL.users.domain.csv;

import sptech.faztudo.comLOCAL.users.UserRole;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GerenciadorDeArquivo {

    public static void gravarCSV(List<User> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.size(); i++) {

                User user = lista.get(i);
                saida.format("%d;%s;%s;%s;%s;%s;%s;%s;%s\n", user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getCpf(), user.getCity(), user.getState(), user.getPhone(), user.getRole());


            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static List<User> ordenarCSV(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        List<User> users = new ArrayList<>(); // Crie uma lista para armazenar os dados lidos do arquivo.

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo e preencher a lista de usuários
        try {
            while (entrada.hasNext()) {

                Integer id = Integer.parseInt(entrada.next());
                String nome = entrada.next();
                String sobrenome = entrada.next();
                String email = entrada.next();
                String cpf = entrada.next();
                String cidade = entrada.next();
                String estado = entrada.next();
                String telefone = entrada.next();
                String role = entrada.next();
                String senha = "?";

                User user = new User(id, nome, sobrenome, cpf, estado, cidade, telefone, email, senha, UserRole.valueOf(role));
                users.add(user);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
        }

        if (!deuRuim) {
            // Ordenar a lista de usuários por nome
            users.sort(Comparator.comparing(User::getName));

            // Gravar o arquivo CSV ordenado
            GerenciadorDeArquivo.gravarCSV(users, "ArquivoCSVOrdenado");
        }

        return users; // Retorna a lista ordenada (ou não) de usuários.
    }

    public static User acharCSV(String nomeArq, String nomeAProcurar) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        List<User> users = new ArrayList<>(); // Crie uma lista para armazenar os dados lidos do arquivo.

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo e preencher a lista de usuários
        try {
            while (entrada.hasNext()) {
                Integer id = Integer.parseInt(entrada.next());
                String nome = entrada.next();
                String sobrenome = entrada.next();
                String email = entrada.next();
                String cpf = entrada.next();
                String cidade = entrada.next();
                String estado = entrada.next();
                String telefone = entrada.next();
                String role = entrada.next();
                String senha = "?";

                User user = new User(id, nome, sobrenome, cpf, estado, cidade, telefone, email, senha, UserRole.valueOf(role));
                users.add(user);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
        }

        if (!deuRuim) {

            // Implementar pesquisa binária para procurar o nome desejado na lista de usuários
            int left = 0;
            int right = users.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                User midUser = users.get(mid);
                int comparison = nomeAProcurar.compareTo(midUser.getName());

                if (comparison == 0) {
                    // Nome encontrado, retornar o usuário correspondente
                    return midUser;
                } else if (comparison < 0) {
                    // O nome está à esquerda de midUser, ajustar a posição à esquerda
                    right = mid - 1;
                } else {
                    // O nome está à direita de midUser, ajustar a posição à direita
                    left = mid + 1;
                }
            }

            // Nome não encontrado
            System.out.println("Nome não encontrado: " + nomeAProcurar);
        } else {
            System.out.println("Problema ao ler o arquivo CSV.");
        }

        // Nome não encontrado, ou problema ao ler o arquivo, retornar null
        return null;
    }

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

// Abre o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

// Grava o registro e fecha o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(List<User> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00USUARIO"; //Verificar documento de layout
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Grava os registros de dados (ou registros de corpo)
        for (User a : lista) {
            String corpo = "02";
            corpo += String.format("%7d", a.getId()); //Completar de acordo com documento
            corpo += String.format("%10s", a.getName());
            corpo+= String.format("%15s",a.getLastName());
            corpo+= String.format("%50s",a.getEmail());
            corpo+= String.format("%20s",a.getCpf());
            corpo+= String.format("%29s",a.getCity());
            corpo+= String.format("%10s",a.getState());
            corpo+= String.format("%15s",a.getPhone());
            corpo+= String.format("%10s",a.getRole());
            //Gravando corpo no arquivo:
            gravaRegistro(corpo, nomeArq);
            // Incrementa o contador de registros de dados gravados
            contaRegDados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);

        gravaRegistro(trailer, nomeArq);
    }

    public static List<User> leArquivoTxt(String nomeArq) {

        BufferedReader entrada = null;
        String registro, tipoRegistro;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;

        List<User> listaLida = new ArrayList<>();


        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }


        try {
            registro = entrada.readLine();
            while (registro != null) {

                tipoRegistro = registro.substring(0,2);

                System.out.println(registroz);

                if (tipoRegistro.equals("02")) {

                    String name = registro.substring(9, 19).trim();
                    String lastName = registro.substring(19, 34).trim();
                    String email = registro.substring(34, 84).trim();
                    String cpf = registro.substring(84, 104).trim();
                    String city = registro.substring(104, 133).trim();
                    String state = registro.substring(133, 143).trim();
                    String phone = registro.substring(143, 158).trim();
                    UserRole role = UserRole.valueOf(registro.substring(158, 168).trim());
                    String senha = registro.substring(168, 192).trim();

                    contaRegDadosLidos++;

                    User a = new User(name,lastName,cpf,state,city,phone,email,senha,role);
                    listaLida.add(a);

                }
                else {
                    System.out.println("Registro inválido");
                }

                registro = entrada.readLine();

            }

            entrada.close();

        }

        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        //

        return listaLida;
    }

}


//
