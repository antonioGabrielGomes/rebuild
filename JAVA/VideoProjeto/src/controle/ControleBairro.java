/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloBairro;

/**
 *
 * @author antonio
 */
public class ControleBairro {

    ConectaBanco conectaBairro = new ConectaBanco();

    public void InserirBairro(ModeloBairro mod) {
        conectaBairro.conexao();

        try {
            PreparedStatement pst = conectaBairro.conn.prepareStatement("insert into bairro (nome_bairro, id_cidade) values(?,?)");
            pst.setString(1, mod.getNome_bairro());
            pst.setInt(2, mod.getCod_cidade());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados armazenados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção do dados! ERRO " + ex);
        }

    }

    public void ExcluiBairro(ModeloBairro mod) {

        conectaBairro.conexao();

        try {
            PreparedStatement pst = conectaBairro.conn.prepareStatement("delete from bairro where id_bairro=?");
            pst.setInt(1, mod.getCod());
            System.out.println(mod.getCod());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados! \n ERRO " + ex);
        }

    }

    public void AlteraBairro(ModeloBairro mod) {

        conectaBairro.conexao();

        try {
            PreparedStatement pst = conectaBairro.conn.prepareStatement("update bairro set nome_bairro=?, id_cidade=? where id_bairro=?");
            pst.setString(1, mod.getNome_bairro());
            pst.setInt(2, mod.getCod_cidade());
            pst.setInt(3, mod.getCod());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados! \n ERRO " + ex);
        }

    }

}
