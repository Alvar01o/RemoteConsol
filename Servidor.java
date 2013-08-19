 import java.io.*;
    import java.net.ServerSocket;
    import java.net.Socket;
     
     
    public class Servidor {
            private ServerSocket servidor;
            private Socket cliente;
            private int puerto = 8086;
           BufferedReader buff;
           OutputStream sender;
                    public Servidor() throws IOException, Throwable {
                            Comandos cmd = new Comandos();
                            while (true){
                            this.EsperarCliente();
                            InputStream cm  = cmd.EjecutarProceso(this.RecibirComando());
                            this.EnviarDatos(cm);
                            this.cerrarConexion();
                            }
                    }
                    public String RecibirComando() throws IOException{
                        int i;
                        InputStream s = cliente.getInputStream();
                        i = s.read();
                        byte[] b = new byte[i];
                        s.read(b);
                        String st =new String(b);
                        System.out.println("longitud ="+b.length+" comando "+st);
                        return st;
                    }
                    public void EnviarDatos(InputStream datos) throws IOException{
                                    char[] buffer = new char[2024];
                                    int result;
                                            buff = new  BufferedReader(new InputStreamReader(datos));
                                            sender = cliente.getOutputStream();   
                                                    while( (result = buff.read(buffer,0,buffer.length))>=0){
                                                                    String tmp = new String(buffer);
                                                                    sender.write(tmp.getBytes());
                                                                    buffer = new char[1024];
                                                    }
                                            try {
                                                    System.out.println("Envio de datos satisfactorio");
                                                    datos.close();
                                            } catch (IOException e) {
                                                    System.err.print("Error cerrando Conexion con archivo");
                                            }
                            }
                   
                    public void EsperarCliente(){
                            try {
                                    servidor = new ServerSocket(puerto);
                            } catch (IOException e) {
                                    System.err.print("No se pudo reservar puerto");
                            }
                            try {
                                   System.out.println("Sevidor activo");
                                   cliente = servidor.accept();
                                   System.out.println("Cliente aceptado");
                            } catch (IOException e) {
                                    System.err.print("No se pudo recepcionar cliente");                            
                            }
                            
                    }
                    
                    public void cerrarConexion(){
                            try {
                                    this.sender.close();
                                    this.cliente.close();
                                    this.servidor.close();
                            } catch (IOException e ) {
                                            System.out.println("Error al cerrar la conexion");
                            }
                           
                    }
                    public static void main(String [] args) throws Throwable{
                            new Servidor();
                    }
    }