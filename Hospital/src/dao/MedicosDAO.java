
package dao;

import factory.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Medicos;


public class MedicosDAO {
    
    private Connection connection;
    int id_medicos;
    String nome_medicos;
    String crm_medicos;
    String especialidade_medicos;
    double salario_medicos;
    
    public MedicosDAO(){
        
        this.connection = new conexao().getConnection();
        
      
    }
    
    public void inserir(Medicos medicos){
        
        String sql = "insert into medicos (nome_medicos, crm_medicos"
                + "especialidade_medicos, salario_medicos) values(?,?,?,?)";
        
        try {
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,medicos.getNome_medicos());
            stmt.setString(2,medicos.getCrm_medicos());
            stmt.setString(3,medicos.getEspecialidade_medicos());
            stmt.setDouble(4,medicos.getSalario_medicos());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException u) {
            
            throw new RuntimeException(u);
            
        }
       
        
        
    }
    
    public void alterar(Medicos medicos) 
    {
        
        Connection con = conexao.getConnection();
        
        PreparedStatement pstm = null;
        
        try 
        {
            pstm = con.prepareStatement("update into medicos (nome_medicos, crm_medicos, especialidade_medicos, salario_medicos) values (?, ?, ?, ?) ");
           
            pstm.setString(1,medicos.getNome_medicos());
            pstm.setString(2,medicos.getCrm_medicos());
            pstm.setString(3,medicos.getEspecialidade_medicos());
            pstm.setDouble(4,medicos.getSalario_medicos());
            
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "sucesso.", JOptionPane.INFORMATION_MESSAGE);
        } 
        
        
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Erro na alteração: "+ex, "falha.", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro: "+ex);
        }
        finally {
            conexao.closeConnection(con, pstm);
        }
    }
    
    public void remover (Medicos medicos) {
        
        Connection con = conexao.getConnection();
        PreparedStatement pstm = null;
        
        try {
            pstm = con.prepareStatement("delete from medicos (nome_medicos, crm_medicos, especialidade_medicos, salario_medicos) values (?, ?, ?, ?) ");
           
            pstm.setString(1,medicos.getNome_medicos());
            pstm.setString(2,medicos.getCrm_medicos());
            pstm.setString(3,medicos.getEspecialidade_medicos());
            pstm.setDouble(4,medicos.getSalario_medicos());
            
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Removido com sucesso!", "sucesso.", JOptionPane.INFORMATION_MESSAGE);
  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover: "+ex, "Erro.", JOptionPane.ERROR_MESSAGE);
  
        }
        finally {
            conexao.closeConnection(con, pstm);
        }
    }
    
    public List<Medicos> listar(){
        
        List<Medicos> medicos = new ArrayList<>();
        
        Connection con = conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try 
        {
            pstm = con.prepareStatement("select * from medicos;");
            rs = pstm.executeQuery();
            
            while(rs.next ()){
                
                Medicos medicos = new Medicos();
                
                medicos.setIdMedicos(1);
                medicos.setNome_medicos(rs.getString("Nome"));
                medicos.setCrm_medicos(rs.getString("crm"));
                medicos.setEspecialidade_medicos(rs.getString("Especialidade"));
                medicos.setSalario_medicos(rs.getDouble("Salário"));
                
                medicos.add(medicos);
                
            }
        }
        
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ex,"ERRO", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            conexao.closeConnection(con, pstm);
        }
        
        
        
        return null;
    }
}
