package controle;

import modelo.ModeloCliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloCliente;

public class ControleCliente {

    ConectaBanco conecta = new ConectaBanco();
    ConectaBanco conectaAux = new ConectaBanco();

    ModeloCliente modeloCliente = new ModeloCliente();
    int codBairro, codTelefone;
    String Bairro, Telefone;

    public void inserir(ModeloCliente mod) {

        conecta.conexao();

        try {

            buscaCod(mod.getBairro(), mod.getTelefone());

            PreparedStatement pst = conecta.conn.prepareStatement("insert into cliente (nome_cliente, endereco_cliente, rg_cliente, cpf_cliente, id_bairro) values (?,?,?,?,?) ");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getEndereco());
            pst.setString(3, mod.getRg());
            pst.setString(4, mod.getCpf());
            pst.setInt(5, codBairro);
            pst.execute();

            conecta.executaSQL("select * from telefone where numero_tel='" + mod.getTelefone() + "'");
            conecta.rs.first();
            codTelefone = conecta.rs.getInt("id_telefone");

            conecta.executaSQL("select * from cliente where nome_cliente='" + mod.getNome() + "'");
            conecta.rs.first();
            int codCliente = conecta.rs.getInt("id_cliente");

            pst = conecta.conn.prepareStatement("insert into itens_cli_tel(id_cli, id_tel) values(?,?)");
            //  buscaCod(mod.getBairro(), mod.getCidade(), "");
            pst.setInt(1, codCliente);
            pst.setInt(2, codTelefone);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção de dados! \n ERRO " + ex);
        }

        conecta.desconecta();

    }

    public void excluir(ModeloCliente mod) {
        conecta.conexao();

        try {
            PreparedStatement pst = conecta.conn.prepareStatement("delete from itens_cli_tel where id_cli=?");
            pst.setInt(1, mod.getId());
            pst.execute();

            pst = conecta.conn.prepareStatement("delete from cliente where id_cliente=?");
            pst.setInt(1, mod.getId());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Exclusão! \n ERRO " + ex);
        }

    }

    public void alterar(ModeloCliente mod) {
        conecta.conexao();
        conectaAux.conexao();

        try {
            buscaCod(mod.getBairro(), mod.getTelefone());

            PreparedStatement pst = conecta.conn.prepareStatement("update itens_cli_tel set id_tel=? where id_cli=?");
            pst.setInt(1, codTelefone);
            pst.setInt(2, mod.getId());
            pst.execute();

            pst = conecta.conn.prepareStatement("update cliente set nome_cliente=?, endereco_cliente=?, rg_cliente=?, cpf_cliente=?, id_bairro=? where id_cliente=?");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getEndereco());
            pst.setString(3, mod.getRg());
            pst.setString(4, mod.getCpf());
            pst.setInt(5, codBairro);
            pst.setInt(6, mod.getId());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração! \n ERRO " + ex);
        }

        conectaAux.desconecta();
        conecta.desconecta();
    }

    public void buscaCod(String Bairro, String tel) {

        try {
            conecta.executaSQL("select * from bairro where nome_bairro='" + Bairro + "'");
            conecta.rs.first();
            this.codBairro = conecta.rs.getInt("id_bairro");

            conecta.executaSQL("select * from telefone where numero_tel='" + tel + "'");
            conecta.rs.first();
            this.codTelefone = conecta.rs.getInt("id_telefone");

        } catch (SQLException ex) {
            //Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void buscaNomes(int codBairro, int codCid) {

        try {
            conecta.executaSQL("select * from bairro where id_bairro=" + codBairro);
            conecta.rs.first();
            Bairro = conecta.rs.getString("nome_bairro");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar os Nomes! \n ERRO " + ex);
        }

    }

    public ModeloCliente first() {
        conecta.conexao();
        conectaAux.conexao();

        try {

            conecta.executaSQL("select * from cliente");
            conecta.rs.first();

            modeloCliente.setId(conecta.rs.getInt("id_cliente"));
            modeloCliente.setNome(conecta.rs.getString("nome_cliente"));
            modeloCliente.setEndereco(conecta.rs.getString("endereco_cliente"));
            modeloCliente.setCpf(conecta.rs.getString("cpf_cliente"));
            modeloCliente.setRg(conecta.rs.getString("rg_cliente"));

            conectaAux.executaSQL("select * from bairro where id_bairro='" + conecta.rs.getInt("id_bairro") + "'");
            conectaAux.rs.first();
            modeloCliente.setBairro(conectaAux.rs.getString("nome_bairro"));

            conectaAux.executaSQL("select * from itens_cli_tel where id_cli='" + conecta.rs.getInt("id_cliente") + "'");
            conectaAux.rs.first();

            conectaAux.executaSQL("select * from telefone where id_telefone='" + conectaAux.rs.getInt("id_tel") + "'");
            conectaAux.rs.first();

            modeloCliente.setTelefone(conectaAux.rs.getString("numero_tel"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o primeiro registro! \nERRO " + ex);

        }

        conectaAux.desconecta();

        return modeloCliente;
    }

    public ModeloCliente last() {
        conecta.conexao();
        conectaAux.conexao();

        try {

            conecta.executaSQL("select * from cliente");
            conecta.rs.last();

            modeloCliente.setId(conecta.rs.getInt("id_cliente"));
            modeloCliente.setNome(conecta.rs.getString("nome_cliente"));
            modeloCliente.setEndereco(conecta.rs.getString("endereco_cliente"));
            modeloCliente.setCpf(conecta.rs.getString("cpf_cliente"));
            modeloCliente.setRg(conecta.rs.getString("rg_cliente"));

            conectaAux.executaSQL("select * from bairro where id_bairro='" + conecta.rs.getInt("id_bairro") + "'");
            conectaAux.rs.first();
            modeloCliente.setBairro(conectaAux.rs.getString("nome_bairro"));

            conectaAux.executaSQL("select * from itens_cli_tel where id_cli='" + conecta.rs.getInt("id_cliente") + "'");
            conectaAux.rs.first();

            conectaAux.executaSQL("select * from telefone where id_telefone='" + conectaAux.rs.getInt("id_tel") + "'");
            conectaAux.rs.first();

            modeloCliente.setTelefone(conectaAux.rs.getString("numero_tel"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o último registro! \nERRO " + ex);

        }

        conectaAux.desconecta();

        return modeloCliente;
    }

    public ModeloCliente previuos() {
        conectaAux.conexao();

        try {

            conecta.rs.previous();

            modeloCliente.setId(conecta.rs.getInt("id_cliente"));
            modeloCliente.setNome(conecta.rs.getString("nome_cliente"));
            modeloCliente.setEndereco(conecta.rs.getString("endereco_cliente"));
            modeloCliente.setCpf(conecta.rs.getString("cpf_cliente"));
            modeloCliente.setRg(conecta.rs.getString("rg_cliente"));

            conectaAux.executaSQL("select * from bairro where id_bairro='" + conecta.rs.getInt("id_bairro") + "'");
            conectaAux.rs.first();
            modeloCliente.setBairro(conectaAux.rs.getString("nome_bairro"));

            conectaAux.executaSQL("select * from itens_cli_tel where id_cli='" + conecta.rs.getInt("id_cliente") + "'");
            conectaAux.rs.first();

            conectaAux.executaSQL("select * from telefone where id_telefone='" + conectaAux.rs.getInt("id_tel") + "'");
            conectaAux.rs.first();

            modeloCliente.setTelefone(conectaAux.rs.getString("numero_tel"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o registro anterior! \nERRO " + ex);

        }

        conectaAux.desconecta();

        return modeloCliente;
    }

    public ModeloCliente next() {
        conectaAux.conexao();

        try {

            conecta.rs.next();

            modeloCliente.setId(conecta.rs.getInt("id_cliente"));
            modeloCliente.setNome(conecta.rs.getString("nome_cliente"));
            modeloCliente.setEndereco(conecta.rs.getString("endereco_cliente"));
            modeloCliente.setCpf(conecta.rs.getString("cpf_cliente"));
            modeloCliente.setRg(conecta.rs.getString("rg_cliente"));

            conectaAux.executaSQL("select * from bairro where id_bairro='" + conecta.rs.getInt("id_bairro") + "'");
            conectaAux.rs.first();
            modeloCliente.setBairro(conectaAux.rs.getString("nome_bairro"));

            conectaAux.executaSQL("select * from itens_cli_tel where id_cli='" + conecta.rs.getInt("id_cliente") + "'");
            conectaAux.rs.first();

            conectaAux.executaSQL("select * from telefone where id_telefone='" + conectaAux.rs.getInt("id_tel") + "'");
            conectaAux.rs.first();

            modeloCliente.setTelefone(conectaAux.rs.getString("numero_tel"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o próximo registro! \nERRO " + ex);

        }

        conectaAux.desconecta();

        return modeloCliente;
    }

    public ArrayList getClientes() {
        conecta.conexao();
        ArrayList dados = new ArrayList();

        try {
            conecta.executaSQL("select * from cliente inner join itens_cli_tel on cliente.id_cliente = itens_cli_tel.id_cli inner join telefone on itens_cli_tel.id_tel=telefone.id_telefone inner join bairro on cliente.id_bairro=bairro.id_bairro inner join cidades on bairro.id_cidade=cidades.id_cidade");
            conecta.rs.first();

            do {
                dados.add(new Object[]{conecta.rs.getString("nome_cliente"), conecta.rs.getString("endereco_cliente"), conecta.rs.getString("numero_tel"), conecta.rs.getString("nome_cidades")});
            } while (conecta.rs.next());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher os dados !\nERRO:" + ex);
        }

        conecta.desconecta();
        return dados;
    }

}
