package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloVenda;

public class ControlePesquisa {

    ConectaBanco conecta = new ConectaBanco();
    ModeloVenda modelo = new ModeloVenda();
    int codProduto = 0;

    public void adicionaItem(ModeloVenda mod, int produtoQtdRestante) {
        achaCodProduto(mod.getNomeProduto());

        try {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement("insert into itens_venda_produto(id_venda, id_produto, quantidade_produto) values(?,?,?)");
            pst.setInt(1, mod.getIdVenda());
            pst.setInt(2, codProduto);
            pst.setInt(3, mod.getQtdItem());
            pst.execute();

            // baixa de estoque
            pst = conecta.conn.prepareStatement("update produto set quantidade=? where id_produto=?");
            pst.setInt(1, produtoQtdRestante);
            pst.setInt(2, codProduto);
//            pst.execute();

            JOptionPane.showMessageDialog(null, "Produto adicionado!");

            conecta.desconecta();
        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }

    }

    public void achaCodProduto(String nome) {
        try {
            conecta.conexao();

            conecta.executaSQL("select * from produto where nome_produto='" + nome + "'");
            conecta.rs.first();
            codProduto = conecta.rs.getInt("id_produto");

        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        conecta.desconecta();
    }

    public int iniciarVenda() {
        int codVenda = 0;

        try {
            conecta.conexao();

            PreparedStatement pst = conecta.conn.prepareStatement("insert into venda (valor_venda) values (?)");
            pst.setFloat(1, 0);
            pst.execute();

            conecta.executaSQL("select * from venda");
            conecta.rs.last();
            codVenda = conecta.rs.getInt("id_venda");

            conecta.desconecta();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro abrir nova venda!\n ERRO:" + ex);
        }

        return codVenda;
    }

    public ArrayList getClientes(String nome) {
        ArrayList dados = new ArrayList();

        try {
            conecta.conexao();
            conecta.executaSQL("select * from cliente where nome_cliente like '%" + nome + "%'");

            conecta.rs.first();
            do {
                dados.add(new Object[]{conecta.rs.getInt("id_cliente"), conecta.rs.getString("nome_cliente")});
            } while (conecta.rs.next());

            conecta.desconecta();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\n ERRO:" + ex);
        }

        return dados;
    }

    public ArrayList getProdutos(String produto) {
        ArrayList dados = new ArrayList();

        try {
            conecta.conexao();
            conecta.executaSQL("select * from produto where nome_produto like '%" + produto + "%'");

            conecta.rs.first();
            do {
                dados.add(new Object[]{conecta.rs.getInt("id_produto"), conecta.rs.getString("nome_produto"), conecta.rs.getInt("quantidade")});
            } while (conecta.rs.next());

            conecta.desconecta();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\n ERRO:" + ex);
        }

        return dados;
    }

    public int getProduto(String nome) {
        int quantidade = 0;
        conecta.conexao();
        try {
            conecta.conexao();
            conecta.executaSQL("select * from produto where nome_produto='" + nome + "'");
            conecta.rs.first();

            quantidade = conecta.rs.getInt("quantidade");

            conecta.desconecta();
        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar a quantidade!\n ERRO:" + ex);
        }

        return quantidade;
    }

    public float getPrecoProduto(String nome) {
        float preco_venda = 0;

        try {
            conecta.conexao();
            conecta.executaSQL("select * from produto where nome_produto='" + nome + "'");
            conecta.rs.first();
            preco_venda = conecta.rs.getFloat("preco_venda");
            conecta.desconecta();
        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro ao obter dados sobre produto!\n ERRO:" + ex);
        }

        return preco_venda;
    }

    public ArrayList getVendas(int codVendas) {
        ArrayList dados = new ArrayList();

        try {
            String sql = "select * from produto inner join itens_venda_produto on produto.id_produto = itens_venda_produto.id_produto inner join venda on venda.id_venda=itens_venda_produto.id_venda where venda.id_venda='" + codVendas + "'";
            conecta.conexao();
            conecta.executaSQL(sql);
            conecta.rs.first();

            do {
                float valorProduto = conecta.rs.getFloat("preco_venda");
                int qtdVendida = conecta.rs.getInt("quantidade_produto");

                dados.add(new Object[]{conecta.rs.getString("nome_produto"), qtdVendida, valorProduto * qtdVendida});

            } while (conecta.rs.next());

            conecta.desconecta();
        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro ao preencher array list\n ERRO:" + ex);
        }

        return dados;

    }

    public float somaProduto(int codVenda) {

        conecta.conexao();
        float total = 0;

        try {
            conecta.executaSQL("select * from itens_venda_produto inner join produto on itens_venda_produto.id_produto=produto.id_produto where id_venda='" + codVenda + "'");
            conecta.rs.first();

            do {
                total = total + conecta.rs.getFloat("preco_venda") * conecta.rs.getInt("quantidade_produto");
            } while (conecta.rs.next());

            conecta.desconecta();
        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro ao somar total!\nERRO:" + ex);
        }

        return total;
    }

    public void fechaVenda(ModeloVenda modelo) {
        conecta.conexao();

        try {
            PreparedStatement pst = conecta.conn.prepareStatement("update venda set data_venda=?, valor_venda=?, id_cliente=? where id_venda=?");
            pst.setString(1, modelo.getData());
            pst.setFloat(2, modelo.getValorVenda());
            pst.setInt(3, Integer.parseInt(modelo.getNomeCliente())); // id
            pst.setInt(4, modelo.getIdVenda());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Venda Finalizada com sucesso!");

        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro ao finalizar venda! \nERRO: " + ex);
        }

        conecta.desconecta();
    }

    public void cancelaVenda() {
        conecta.conexao();
        PreparedStatement pst;
                       
        try {
            conecta.executaSQL("select * from venda inner join itens_venda_produto on venda.id_venda=itens_venda_produto.id_venda inner join produto on itens_venda_produto.id_produto=produto.id_produto where venda.valor_venda=0");
            conecta.rs.first();
            
            do{
                int qtdProd = conecta.rs.getInt("quantidade");
                int qtdVend = conecta.rs.getInt("quantidade_produto");
                int soma = qtdProd + qtdVend;
                //System.out.println(soma);
                pst = conecta.conn.prepareStatement("update produto set quantidade=? where id_produto=?");
                pst.setInt(1, soma);
                pst.setInt(2, conecta.rs.getInt("id_produto"));
                pst.execute();     
                
                pst = conecta.conn.prepareStatement("delete from itens_venda_produto where id_venda=?");
                pst.setInt(1, conecta.rs.getInt("id_venda"));
                pst.execute();
                
            }while(conecta.rs.next());            
            
            pst = conecta.conn.prepareStatement("delete from venda where valor_venda=?");
            pst.setInt(1, 0);
            pst.execute();

        } catch (SQLException ex) {
            conecta.desconecta();
            JOptionPane.showMessageDialog(null, "Erro ao cancelar venda! \nERRO: " + ex);
        }

        conecta.desconecta();
    }

    public ArrayList pesquisarVendas(String data) {
        
        ArrayList dados = new ArrayList();

        try {
            conecta.conexao();
            conecta.executaSQL("select * from venda inner join cliente on venda.id_cliente=cliente.id_cliente where data_venda='"+data+"'");

            conecta.rs.first();
            do {
                dados.add(new Object[]{conecta.rs.getInt("id_cliente"), conecta.rs.getString("nome_cliente")});
            } while (conecta.rs.next());

            conecta.desconecta();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\n ERRO:" + ex);
        }

        return dados;

    }

}
