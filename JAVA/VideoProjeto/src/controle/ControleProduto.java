package controle;

import modelo.ModeloProduto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControleProduto {

    ConectaBanco conecta = new ConectaBanco();
    ConectaBanco conectaAux = new ConectaBanco();
    ModeloProduto mod = new ModeloProduto();
    int codFornecedor;
    String nomeFornecedor;

    public void inserir(ModeloProduto modelo) {
        conecta.conexao();

        try {
            buscaCod(modelo.getFornecedor());

            PreparedStatement pst = conecta.conn.prepareStatement("insert into produto (nome_produto, preco_compra, preco_venda, quantidade, id_fornecedor) values(?,?,?,?,?)");
            pst.setString(1, modelo.getNomeProduto());
            pst.setFloat(2, modelo.getPrecoCompra());
            pst.setFloat(3, modelo.getPrecoVenda());
            pst.setInt(4, modelo.getQtdProduto());
            pst.setInt(5, codFornecedor);

            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção do Produto! \n ERRO " + ex);
        }

        conecta.desconecta();
    }

    public void buscaCod(String fornecedor) {
        try {
            conecta.executaSQL("select * from fornecedor where nome_fornecedor='" + fornecedor + "'");
            conecta.rs.first();
            codFornecedor = conecta.rs.getInt("id_fornecedor");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção do Produto! \n ERRO " + ex);
        }

    }

//    public void buscaNomeFornecedor(int cod){
//        conectaAux.conexao();
//        conectaAux.executaSQL("select * from fornecedor where id_fornecedor="+cod+"");
//        
//        try {
//            conectaAux.rs.first();
//            nomeFornecedor = conectaAux.rs.getString("nome_fornecedor");
//        } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, "Erro ao buscar o código!\n ERRO: "+ex);
//        }
//        
//        conectaAux.desconecta();
//    }
    public void editar(ModeloProduto modelo) {
        conecta.conexao();

        try {
            buscaCod(modelo.getFornecedor());

            PreparedStatement pst = conecta.conn.prepareStatement("update produto set nome_produto=?, preco_compra=?, preco_venda=?, quantidade=?, id_fornecedor=? where id_produto=?");
            pst.setString(1, modelo.getNomeProduto());
            pst.setFloat(2, modelo.getPrecoCompra());
            pst.setFloat(3, modelo.getPrecoVenda());
            pst.setInt(4, modelo.getQtdProduto());
            pst.setInt(5, codFornecedor);
            pst.setInt(6, modelo.getId());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na atualização do Produto! \n ERRO " + ex);
        }

        conecta.desconecta();
    }

    public void excluir(ModeloProduto modelo) {
        conecta.conexao();

        try {
            PreparedStatement pst = conecta.conn.prepareStatement("delete from produto where id_produto=?");
            pst.setInt(1, modelo.getId());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Produto! \n ERRO " + ex);
        }

        conecta.desconecta();
    }

//    public ModeloProduto buscaProduto(String pesquisar) {
//        conecta.conexao();
//
//        try {
//            conecta.executaSQL("select * from produto inner join fornecedor on produto.id_fornecedor=fornecedor.id_fornecedor  where nome_produto like '%" + pesquisar + "%'");
//            conecta.rs.first();
//
//            mod.setId(conecta.rs.getInt("id_produto"));
//            mod.setNomeProduto(conecta.rs.getString("nome_produto"));
//            mod.setPrecoCompra(conecta.rs.getFloat("preco_compra"));
//            mod.setPrecoVenda(conecta.rs.getFloat("preco_venda"));
//            mod.setQtdProduto(conecta.rs.getInt("quantidade"));
//            mod.setFornecedor(conecta.rs.getString("nome_fornecedor"));
//
//            conecta.desconecta();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao buscar o produto!\n ERRO: " + ex);
//        }
//
//        return mod;
//    }
    public ModeloProduto buscaProduto(String codigo) {
        conecta.conexao();

        try {
            conecta.executaSQL("select * from produto inner join fornecedor on produto.id_fornecedor=fornecedor.id_fornecedor  where produto.id_produto='" + codigo + "'");
            conecta.rs.first();

            mod.setId(conecta.rs.getInt("id_produto"));
            mod.setNomeProduto(conecta.rs.getString("nome_produto"));
            mod.setPrecoCompra(conecta.rs.getFloat("preco_compra"));
            mod.setPrecoVenda(conecta.rs.getFloat("preco_venda"));
            mod.setQtdProduto(conecta.rs.getInt("quantidade"));
            mod.setFornecedor(conecta.rs.getString("nome_fornecedor"));

            conecta.desconecta();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o produto!\n ERRO: " + ex);
        }

        return mod;
    }

    public ArrayList getProdutos(String pesquisar) {
        conecta.conexao();
        ArrayList dados = new ArrayList();
        String sql = "select * from produto inner join fornecedor on produto.id_fornecedor=fornecedor.id_fornecedor where produto.nome_produto like '%" + pesquisar + "%'";

        if (pesquisar == "") {
            sql = "select * from produto inner join fornecedor on produto.id_fornecedor=fornecedor.id_fornecedor";
        }

        try {
            conecta.executaSQL(sql);
            conecta.rs.first();

            do {
                dados.add(new Object[]{conecta.rs.getInt("id_produto"), conecta.rs.getString("nome_produto"), conecta.rs.getInt("quantidade"), String.valueOf(conecta.rs.getFloat("preco_venda"))});
            } while (conecta.rs.next());

            conecta.desconecta();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher os dados !\nERRO:" + ex);
        }

        return dados;
    }


}
