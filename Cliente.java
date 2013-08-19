import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
     
     
    public class  Cliente{
           
            private Socket cliente;
            private int puerto = 8086;
           
                    public Cliente() throws IOException{
                            while(true) {
                                conectar(); 
                                System.out.println(">>: ");
                                this.mandarComandos();
                                this.RecibirDatos();
                            }
                    }
                   
                    public void conectar() throws IOException{
                            cliente = new Socket("localhost",puerto);
                    }
                    public void mandarComandos() throws IOException{
                           OutputStream cs = cliente.getOutputStream();
                           Scanner coman = new Scanner(System.in);
                           String cmd = coman.nextLine();
                           cs.write(cmd.getBytes().length);
                           cs.write(cmd.getBytes()); 
                    }
                    
                    public void RecibirDatos() {
                    byte[] buffer = new byte[1024];
                    int result;
                    InputStream is;                                            
                            try {
                                is = cliente.getInputStream();
                                while( (result = is.read(buffer,0,buffer.length))>=0){
                                                for(byte g : buffer){
                                                System.out.print((char)g);
                                                }
                                                buffer = new byte[1024];
                                }
                                
                        try {
                                is.close();
                                cliente.close();
                        } catch (IOException e) {
                                System.err.print("Error cerrando Conexion con archivo");
                        }
               
                        } catch (IOException ex) {  
                            ex.printStackTrace();
                                    }
                           
                    }
                   
                   
                    public static void main(String [] args) throws Exception{
                            new Cliente();
                           
                    }
    }