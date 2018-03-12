
package calculatorserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class Metodos extends Thread{
    
    private Socket metodos; //Socket dos metodos
    private int valor1,valor2,operacion,resultado; //Datos que enviaremos o servidor
    private InputStream is; //Datos de entrada o servidos
    private OutputStream os; //Datos de seaida o servidor
    
    public Metodos(Socket metodos){
        this.metodos = metodos;
        try{
            this.is=metodos.getInputStream();
            this.os=metodos.getOutputStream();
        }catch(IOException e){
            System.out.println("Error nos stream");
        }
    }

    @Override
    public void run() {
        while(metodos.isConnected()){ 
            try {
                if(is.available()>0){ //Si hay datos sen leer
                    try{
                        valor1 = is.read(); //Leo os datos
                        valor2 = is.read();
                        operacion = is.read();
                        switch(operacion){ //Comprobo a operacion
                            case 0:
                                resultado = valor1 + valor2;
                                break;
                            case 1:
                                resultado = valor1 - valor2;
                                break;
                            case 2:
                                resultado = valor1 * valor2;
                                break;
                            case 3:
                                resultado = valor1 / valor2;
                        }
                        os.write(resultado); //mando o resultado os datos de saida
                    }catch(IOException e){
                        System.out.println("Error o recibir os datos");
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error o crear datos");
            }
            
        }
    }
    
    
}
