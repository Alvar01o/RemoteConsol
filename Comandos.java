    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
     
     
    public class Comandos {
            private static Process exec;
           
            public  InputStream EjecutarProceso(String comando) throws Throwable{
                   
                  if(ifexist(getOs().split(" "), "Linux")) {
                      System.out.println("Linux");
                   exec = Runtime.getRuntime().exec(comando);                
                  } else if (ifexist(getOs().split(" "), "Windows")) {
                    exec = Runtime.getRuntime().exec("cmd.exe /C " +comando);
                  }
                    System.out.println(getOs());
                    return exec.getInputStream();
            }
            
            public boolean ifexist (String[] os , String os_){
                for(String o : os){
                    if(o.equals(os_)) {
                        return true;
                    } else { 
                        return false;
                    }
                }
                return false;
            }   
            
            public String getOs(){
                return System.getProperty("os.name");
            } 
            public void ejecutarComando(InputStream EjecutarProceso) throws IOException{
                    char[] buffer = new char[1024];
                    int result;
                            BufferedReader buff = new  BufferedReader(new InputStreamReader(EjecutarProceso));
                            while( (result = buff.read(buffer,0,buffer.length))>=0){
                                    for(char c : buffer){
                                            System.out.print(c);
                                    }
                            }
                    EjecutarProceso.close();
            }
             
    }