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

    	public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/helpSemeq"+"?verifyServerCertificate=false&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Sao_Paulo"; 
        String usuario = "root";  
        String senha = ""; 
		Connection result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			result = DriverManager.getConnection(url, usuario, senha);
			return result;                        
        }  
        catch(Exception e){  
			JOptionPane.showMessageDialog(null, e, "ERRO", JOptionPane.ERROR_MESSAGE);  
        }
		return result;
	}


    public void executeSQL(String sql) {
        try {
            statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        } catch (SQLException sqlex) {
            System.out.println("Não foi possivel executar o comando: \n" + sqlex + "\n o sql passado foi: \n" + sql);
        }
    }
}
