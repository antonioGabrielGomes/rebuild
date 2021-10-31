package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.ModeloFornecedor;

public class ControleFornecedor {

    ConectaBanco conecta = new ConectaBanco();
    ConectaBanco conectaAux = new ConectaBanco();

    ModeloFornecedor modeloFornecedor = new ModeloFornecedor();
    int codBairro, codTelefone;
    String Bairro, Telefone;

    public void inserir(ModeloFornecedor mod) {

        conecta.conexao();

        try {

            buscaCod(mod.getBairro());

            PreparedStatement pst = conecta.conn.prepareStatement("insert into fornecedor (nome_fornecedor, endereco, id_bairro, cnpj_fornecedor) values (?,?,?,?) ");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getEndereco());
            pst.setInt(3, codBairro);
            pst.setString(4, mod.getCnpj());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção do fornecedor! \n ERRO " + ex);
        }

        conecta.desconecta();

    }

    public void excluir(ModeloFornecedor mod) {
        conecta.conexao();

        try {
            PreparedStatement pst = conecta.conn.prepareStatement("delete from fornecedor where id_fornecedor=?");
            pst.setInt(1, mod.getId());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Exclusão! \n ERRO " + ex);
        }

        conecta.desconecta();
    }

    public void alterar(ModeloFornecedor mod) {
        conecta.conexao();

        try {
            buscaCod(mod.getBairro());

            PreparedStatement pst = conecta.conn.prepareStatement("update fornecedor set nome_fornecedor=?, endereco=?, cnpj_fornecedor=?, id_bairro=? where id_fornecedor=?");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getEndereco());
            pst.setString(3, mod.getCnpj());
            pst.setInt(4, codBairro);
            pst.setInt(5, mod.getId());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração! \n ERRO " + ex);
        }

        conecta.desconecta();
    }

    public void buscaCod(String Bairro) {

        try {
            conecta.executaSQL("select * from bairro where nome_bairro='" + Bairro + "'");
            conecta.rs.first();
            this.codBairro = conecta.rs.getInt("id_bairro");

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

    public ModeloFornecedor first() {
        conecta.conexao();

        try {

            conecta.executaSQL("select * from fornecedor inner join bairro on fornecedor.id_bairro=bairro.id_bairro "
                    + "inner join cidades on bairro.id_cidade=cidades.id_cidade  "
                    + "inner join estados on cidades.id_estado=estados.id_estado");
            conecta.rs.first();

            modeloFornecedor.setId(conecta.rs.getInt("id_fornecedor"));
            modeloFornecedor.setNome(conecta.rs.getString("nome_fornecedor"));
            modeloFornecedor.setEndereco(conecta.rs.getString("endereco"));
            modeloFornecedor.setCnpj(conecta.rs.getString("cnpj_fornecedor"));
            modeloFornecedor.setBairro(conecta.rs.getString("nome_bairro"));
            modeloFornecedor.setCidade(conecta.rs.getString("nome_cidades"));
            modeloFornecedor.setEstado(conecta.rs.getString("sigla_estado"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o primeiro registro! \nERRO " + ex);

        }

        return modeloFornecedor;
    }

    public ModeloFornecedor last() {
        conecta.conexao();

        try {

            conecta.executaSQL("select * from fornecedor inner join bairro on fornecedor.id_bairro=bairro.id_bairro "
                    + "inner join cidades on bairro.id_cidade=cidades.id_cidade  "
                    + "inner join estados on cidades.id_estado=estados.id_estado");
            conecta.rs.last();

            modeloFornecedor.setId(conecta.rs.getInt("id_fornecedor"));
            modeloFornecedor.setNome(conecta.rs.getString("nome_fornecedor"));
            modeloFornecedor.setEndereco(conecta.rs.getString("endereco"));
            modeloFornecedor.setCnpj(conecta.rs.getString("cnpj_fornecedor"));
            modeloFornecedor.setBairro(conecta.rs.getString("nome_bairro"));
            modeloFornecedor.setCidade(conecta.rs.getString("nome_cidades"));
            modeloFornecedor.setEstado(conecta.rs.getString("sigla_estado"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o último registro! \nERRO " + ex);

        }

        return modeloFornecedor;
    }

    public ModeloFornecedor previuos() {

        try {
            conecta.rs.previous();

            modeloFornecedor.setId(conecta.rs.getInt("id_fornecedor"));
            modeloFornecedor.setNome(conecta.rs.getString("nome_fornecedor"));
            modeloFornecedor.setEndereco(conecta.rs.getString("endereco"));
            modeloFornecedor.setCnpj(conecta.rs.getString("cnpj_fornecedor"));
            modeloFornecedor.setBairro(conecta.rs.getString("nome_bairro"));
            modeloFornecedor.setCidade(conecta.rs.getString("nome_cidades"));
            modeloFornecedor.setEstado(conecta.rs.getString("sigla_estado"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o registro anterior! \nERRO " + ex);
        }

        return modeloFornecedor;
    }

    public ModeloFornecedor next() {

        try {
            conecta.rs.next();

            modeloFornecedor.setId(conecta.rs.getInt("id_fornecedor"));
            modeloFornecedor.setNome(conecta.rs.getString("nome_fornecedor"));
            modeloFornecedor.setEndereco(conecta.rs.getString("endereco"));
            modeloFornecedor.setCnpj(conecta.rs.getString("cnpj_fornecedor"));
            modeloFornecedor.setBairro(conecta.rs.getString("nome_bairro"));
            modeloFornecedor.setCidade(conecta.rs.getString("nome_cidades"));
            modeloFornecedor.setEstado(conecta.rs.getString("sigla_estado"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao setar o próximo registro! \nERRO " + ex);

        }

        return modeloFornecedor;
    }

    public ArrayList getFornecedores() {
        conecta.conexao();
        ArrayList dados = new ArrayList();

        try {
            conecta.executaSQL("select * from fornecedor");
            conecta.rs.first();

            do {
                dados.add(new Object[]{conecta.rs.getInt("id_fornecedor"), conecta.rs.getString("nome_fornecedor"), conecta.rs.getString("cnpj_fornecedor")});
            } while (conecta.rs.next());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher os dados !\nERRO:" + ex);
        }

        conecta.desconecta();
        return dados;
    }

}
