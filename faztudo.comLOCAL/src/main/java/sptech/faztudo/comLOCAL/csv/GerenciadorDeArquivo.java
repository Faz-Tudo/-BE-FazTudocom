package sptech.faztudo.comLOCAL.csv;

import sptech.faztudo.comLOCAL.domain.users.User;

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
                saida.format("%d;%s;%s;%s;%s;%s;%s;%s\n",user.getId(),user.getName(),user.getLastName(),user.getEmail(),user.getCpf(),user.getCity(),user.getState(),user.getPhone());


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

    public static void leArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo
        try {
            System.out.printf("%-7S %-20S %-30S %10S %-10S %-20S %-20S %-20S\n",
                    "ID", "Nome", "Sobrenome", "Email", "CPF", "Cidade", "Estado", "Telefone");

            while (entrada.hasNext()) {

                int id = entrada.nextInt();
                String nome = entrada.next();
                String email = entrada.next();
                String cpf = entrada.next();
                String cidade = entrada.next();
                String estado = entrada.next();
                String telefone = entrada.next();


                System.out.printf("%-7d %-20s %-30s %10s %10s %17s %17s %17s\n",
                        id, nome, email, cpf, cidade, estado, telefone);

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
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}
