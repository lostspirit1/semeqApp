package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionFactory {

    public Statement statement;
    public ResultSet resultset;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bd_relatorios";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
    try{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USER,PASS);
    }catch (ClassNotFoundException | SQLException ex){
        throw new RuntimeException("Error na conexão:" ,ex);
    }
    }
   /* public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/bd_relatorios" + "?verifyServerCertificate=false&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Sao_Paulo";
        String usuario = "root";
        String senha = "";
        Connection result = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException exc) {
            System.out.println("Erro no driver, ClassNotFoundException " + exc.getMessage());
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no Banco de Dados! \n" + exc.getMessage(), "Erro ao Conectar no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }*/

}
