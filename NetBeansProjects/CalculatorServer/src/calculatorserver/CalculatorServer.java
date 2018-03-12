
package calculatorserver;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class CalculatorServer {
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(); //Socket para o servidor
        SocketAddress addr = new InetSocketAddress("localhost", 6000); //Ip e porto
        server.bind(addr); //Socket escoitando
        System.out.println("Server listo para recibir peticions");
        while(true){
            Socket conexion = server.accept(); //Acepta a conexion
            Metodos m = new Metodos(conexion); //Crea o fio dos metodos
            m.start(); 
        }
        
    }
    
}
