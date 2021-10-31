
package controle;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeloTabela extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;
    
    public ModeloTabela(ArrayList lin, String[] col){
        setLinhas(lin);
        setColunas(col);
    }
    
    public ArrayList getLinhas(){
        return this.linhas;
    }
    
    public void setLinhas(ArrayList dados){
        this.linhas = dados;
    }
    
    public String[] getColunas(){
        return this.colunas;
    }
    
    public void setColunas(String[] nomes){
        this.colunas = nomes;
    }
    
    public int getColumnCount(){
        return this.colunas.length;
    }
    
    public int getRowCount(){
        return this.linhas.size();
    }
    
    public String getColumnName(int numCol){
        return this.colunas[numCol];
    }
    
    public Object getValueAt(int numLin, int numCol){
        Object[] linha = (Object[])getLinhas().get(numLin);
        return linha[numCol];
    }
    
    
}
