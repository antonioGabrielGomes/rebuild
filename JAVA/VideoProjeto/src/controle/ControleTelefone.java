
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloTelefone;


/**
 *
 * @author antonio
 */
public class ControleTelefone {
    
    ConectaBanco conectaTelefone = new ConectaBanco();
    
    public void InserirTelefone(ModeloTelefone mod){
        
        conectaTelefone.conexao();
        
        try {    
            PreparedStatement pst = conectaTelefone.conn.prepareStatement("insert into telefone (numero_tel) values(?)");
            pst.setString(1, mod.getTel());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Dados armazenados com sucesso!");
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro na inserção dos dados! \n ERRO "+ex);       
        }
        
    }
    
    public void ExcluiTelefone(ModeloTelefone mod){
        conectaTelefone.conexao();
        
        try {
            PreparedStatement pst = conectaTelefone.conn.prepareStatement("delete from telefone where id_telefone=?");
            pst.setInt(1, mod.getCod());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados! \n ERRO "+ex);       
        }
        
    }
    
    public void AlteraTelefone(ModeloTelefone mod){
        conectaTelefone.conexao();
        
        try {         
            PreparedStatement pst = conectaTelefone.conn.prepareStatement("update telefone set numero_tel=? where id_telefone=?");
            pst.setString(1, mod.getTel());
            pst.setInt(2, mod.getCod());
                        
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados! \n ERRO "+ex);    
        }
        
        
        
    }
    
}
