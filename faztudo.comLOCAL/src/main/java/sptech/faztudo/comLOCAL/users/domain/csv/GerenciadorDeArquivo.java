package sptech.faztudo.comLOCAL.users.domain.csv;

import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GerenciadorDeArquivo {

    public static void gravaArquivoCsv(List<User> lista, String nomeArq) {
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

    public static List<User> leArquivoCsv(String nomeArq) {
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
                String nome = entrada.next();
                String sobrenome = entrada.next();
                String email = entrada.next();
                String cpf = entrada.next();
                String cidade = entrada.next();
                String estado = entrada.next();
                String telefone = entrada.next();
                String role = entrada.next();

                User user = new User(nome, sobrenome, email, cpf, cidade, estado, telefone, role);
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
            GerenciadorDeArquivo.gravaArquivoCsv(users, "ArquivoCSVOrdenado");
        }

        return users; // Retorna a lista ordenada (ou não) de usuários.
    }

}


//
