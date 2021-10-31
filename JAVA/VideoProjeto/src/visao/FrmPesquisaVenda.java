/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ControlePesquisa;
import controle.ModeloTabela;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modelo.ModeloVenda;

/**
 *
 * @author antonio
 */
public class FrmPesquisaVenda extends javax.swing.JFrame {

    int tabela = 0, codVenda;
    String codCliente;
    float preco_produto, total;

    ControlePesquisa controle = new ControlePesquisa();
    ModeloVenda modelo = new ModeloVenda();

    public FrmPesquisaVenda() {

        initComponents();

        
        try {
            MaskFormatter form = new MaskFormatter("##/##/####");
            jFormattedTextFieldData.setFormatterFactory(new DefaultFormatterFactory(form));
        } catch (ParseException ex) {
            Logger.getLogger(FrmVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // data do sistema
        //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //Date hoje = new Date();
        //jFormattedTextFieldData.setText(df.format(hoje));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFieldData = new javax.swing.JFormattedTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisa = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItensDetalhesVenda = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Vendas");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Digite uma data:");

        jFormattedTextFieldData.setEnabled(false);
        jFormattedTextFieldData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldDataFocusGained(evt);
            }
        });
        jFormattedTextFieldData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldDataActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jTablePesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablePesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePesquisaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePesquisa);

        jLabel8.setText("Tabela de Pesquisa");

        jTableItensDetalhesVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableItensDetalhesVenda);

        jLabel9.setText("Detalhes da Venda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonPesquisar))
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel1.setText("Formulário de Vendas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePesquisaMouseClicked

    }//GEN-LAST:event_jTablePesquisaMouseClicked

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        
        ArrayList dados = controle.pesquisarVendas(jFormattedTextFieldData.getText());
        tabelaPesquisaVendas(dados);
        
        
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jFormattedTextFieldDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataFocusGained
        //total = Float.parseFloat(jTextFieldValorItem.getText()) * Integer.parseInt(jTextFieldQuantidade.getText());
        //jTextFieldValorTotal.setText(String.valueOf(total));
    }//GEN-LAST:event_jFormattedTextFieldDataFocusGained

    private void jFormattedTextFieldDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDataActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPesquisaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPesquisaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPesquisaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPesquisaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPesquisaVenda().setVisible(true);
            }
        });
    }

    public void tabelaPesquisaVendas(ArrayList dados) {     
        String[] Colunas = new String[]{"ID", "Data Venda", "Valor da Venda", "Cliente"};

        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTablePesquisa.setModel(modelo);

        jTablePesquisa.getColumnModel().getColumn(0).setPreferredWidth(160);
        jTablePesquisa.getColumnModel().getColumn(0).setResizable(false);

        jTablePesquisa.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTablePesquisa.getColumnModel().getColumn(1).setResizable(false);
        jTablePesquisa.getTableHeader().setReorderingAllowed(false);
        jTablePesquisa.setAutoResizeMode(jTablePesquisa.AUTO_RESIZE_OFF);
        jTablePesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTablePesquisa.getTableHeader().setReorderingAllowed(false);
        jTablePesquisa.setAutoResizeMode(jTablePesquisa.AUTO_RESIZE_OFF);
        jTablePesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
  
    public void tabelaDetalhes(ArrayList dados) {        
        String[] Colunas = new String[]{"Descrição", "Quantidade", "Valor Total"};

        dados = controle.getVendas(codVenda);
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTableItensDetalhesVenda.setModel(modelo);

        jTableItensDetalhesVenda.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTableItensDetalhesVenda.getColumnModel().getColumn(0).setResizable(false);

        jTableItensDetalhesVenda.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableItensDetalhesVenda.getColumnModel().getColumn(1).setResizable(false);
        jTableItensDetalhesVenda.getTableHeader().setReorderingAllowed(false);
        jTableItensDetalhesVenda.setAutoResizeMode(jTableItensDetalhesVenda.AUTO_RESIZE_OFF);
        jTableItensDetalhesVenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTableItensDetalhesVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableItensDetalhesVenda.getColumnModel().getColumn(2).setResizable(false);

        jTableItensDetalhesVenda.getTableHeader().setReorderingAllowed(false);
        jTableItensDetalhesVenda.setAutoResizeMode(jTableItensDetalhesVenda.AUTO_RESIZE_OFF);
        jTableItensDetalhesVenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JFormattedTextField jFormattedTextFieldData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableItensDetalhesVenda;
    private javax.swing.JTable jTablePesquisa;
    // End of variables declaration//GEN-END:variables
}
