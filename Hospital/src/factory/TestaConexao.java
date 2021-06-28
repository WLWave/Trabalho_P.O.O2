
package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
  
    
    public static void main(String[] args) throws SQLException{
        
        Connection connection = new conexao().getConnection();
        System.out.println("Conexão Aberta");
        connection.close();
    }
    
    
}
