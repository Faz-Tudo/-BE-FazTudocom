package sptech.faztudo.comLOCAL.users.domain.serviceProvider;

import org.springframework.stereotype.Service;

@Service
public class ServiceFila<T> {
    private int tamanho;
    private T[] fila;

    // Construtor
    public ServiceFila() {
        this.tamanho = 0;
        this.fila = (T[]) new Object[30];
    }

    // Métodos


    public boolean isEmpty() {

        if(tamanho == 0 ){
            return true;
        }
        return false;
    }


    public boolean isFull() {

        if(tamanho == fila.length){
            return true;
        }
        return false;
    }

    public void insert(T info) {

        if(tamanho < fila.length){
            fila[tamanho] = info;
            tamanho++;

        }else{
            throw new IllegalStateException("Fila cheia");
        }

    }


    public T peek() {

        return fila[0];
    }


    public T poll() {

        T aux = fila[0];

        if(!isEmpty()){

            for (int i = 0; i < fila.length-1; i++) {

                fila[i] = fila[i+1];

            }
            tamanho--;
        }else{
            return null;
        }
        return aux;
    }


    public void exibe() {

        for (int i = 0; i < fila.length; i++) {
            System.out.println(fila[i]);
        }
    }


    public int getTamanho(){
        return tamanho;
    }
}
