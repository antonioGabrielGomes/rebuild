
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloCidade;

public class ControleCidade {
    
    ConectaBanco conectaCidade = new ConectaBanco();
    
    public void InserirCidade(ModeloCidade mod){
        
        conectaCidade.conexao();
        
        try {
            PreparedStatement pst = conectaCidade.conn.prepareStatement("insert into cidades (nome_cidades,id_estado) values(?,?)");
            pst.setString(1, mod.getNome());
            pst.setInt(2, mod.getCod_estado());
            pst.execute();
                
            JOptionPane.showMessageDialog(null, "Dados armazenados com sucesso!");
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro na inserção dos dados! \n ERRO "+ex);       
        }
        
        conectaCidade.desconecta();
        
    }
    
    public void ExcluiCidade(ModeloCidade mod){
        conectaCidade.conexao();
        
        try {
            PreparedStatement pst = conectaCidade.conn.prepareStatement("delete from cidades where id_cidade=?");
            pst.setInt(1, mod.getCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados! \n ERRO "+ex);       
        }
        
    }
    
    public void AlteraCidade(ModeloCidade mod){
        conectaCidade.conexao();
        
        try {         
            PreparedStatement pst = conectaCidade.conn.prepareStatement("update cidades set nome_cidades=?, id_estado=? where id_cidade=?");
            pst.setString(1, mod.getNome());
            pst.setInt(2, mod.getCod_estado());
            pst.setInt(3, mod.getCod());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados! \n ERRO "+ex);    
        }
        
        
        
    }
    
}
