/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package Model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class JavaMail{
    public static void sendMail(String recepient){
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail = "lostspirit1996@gmail.com";
        String password = "88238392deca";
        
        Session session = Session.getInstance(properties, new Authenticator(){
            
            protected PasswordAuthentication getPasswordPasswordAuthentication(){
                
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
       // mail.smtp.outh
        // mail.smtp.starttls.enable
       // mail.smtp.host - smtp.gmail.com
       // mail.smtp.port - 587
       Message message = prepareMessage();
    }

    private static Message prepareMessage() {
        return null;
        /*
        Message message = new MimeMessage(session);
        
    }


}
    }*/
package Model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMail {

    public static String exportarBD(String host, String bd, String usuario, String senha, String location) throws IOException {
        StringBuffer dump = new StringBuffer();
        //local onde se encontra o MysqlDump
        dump.append("C:\\xampp\\mysql\\bin\\mysqldump.exe");
        //solicitando o host ex:localhost
        dump.append(" -h");
        dump.append(host);
        //solicitando o usuario
        dump.append(" -u");
        dump.append(usuario);
        //solicitando a senha
        dump.append(" -p");
        dump.append(senha);
        //solicitando o bd
        dump.append(" --add-drop-database -B");//se existir o sql, sobrescreverá
        dump.append(bd);
        //solicitando o local para salvar ex:c\\mysql
        dump.append(" -r");
        dump.append(location);
        dump.append(gerarNomeComData());//esse metodo devolverá o ano seguido da hora ex:mysql20170609_073230
        dump.append(".sql");//add a extensão .sql

        Process p;
        p = Runtime.getRuntime().exec(dump.toString());
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Banco de Dados foi Exportado com sucesso para a pasta \n" + location;

    }

    /**
     *
     * @return a data com o horario
     */
    private static String gerarNomeComData() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd _ hh:mm:ss");

        Calendar c = Calendar.getInstance();
        String s = sdf.format(c.getTime());
        return s.replace("/", "").replace(" ", "").replace(":", "");
    }
}
