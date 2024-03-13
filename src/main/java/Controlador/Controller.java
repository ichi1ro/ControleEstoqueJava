/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Produto;
import javax.swing.table.DefaultTableModel;
import DAO.ProdutoDAO;
import java.util.ArrayList;
/**
 *
 * @author spook
 */
public class Controller {
    
    /**
     *
     * 
     * @param tbl
     */
    public void catalogar(javax.swing.JTable tbl){
        ProdutoDAO pdao = new ProdutoDAO();
        DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
        modelo.setNumRows(0);
        for (Produto p : pdao.catProd()){
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getDescr(),
                p.getPreco(),
                p.getQtdEstoque()
                });
            }
    }
    public void pesquisar(javax.swing.JRadioButton rb1, javax.swing.JRadioButton rb2, javax.swing.JRadioButton rb3,javax.swing.JTextField txt, javax.swing.JTable tbl){
        ProdutoDAO pdao = new ProdutoDAO();
        DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
        modelo.setNumRows(0);
        if(rb1.isSelected() && !txt.getText().isBlank()){
            for (Produto p : pdao.pesqID(Integer.parseInt(txt.getText()))){
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getDescr(),
                p.getPreco(),
                p.getQtdEstoque()
                });
            }
           
        }else if(rb2.isSelected() && !txt.getText().isBlank()){
            for (Produto p : pdao.pesqNome(txt.getText())){
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getDescr(),
                p.getPreco(),
                p.getQtdEstoque()
                });
            }
        }else if(rb3.isSelected() && !txt.getText().isBlank()){
            for (Produto p : pdao.pesqQtd(Integer.parseInt(txt.getText()))){
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getDescr(),
                p.getPreco(),
                p.getQtdEstoque()
                });
            }
        }else{
            this.catalogar(tbl);
        }
    }
    public ArrayList<String> selecionarTupla(javax.swing.JTable tbl, int linhaSelecionada){
        ArrayList<String> linha = new ArrayList<>();
        for(int i =1;i<tbl.getColumnCount();i++){
            linha.add((tbl.getValueAt(linhaSelecionada, i)).toString());
        }
        return linha;
    }
    public void cadastrar(String nome, String desc, double preco, int qtd){
         new ProdutoDAO().cadProd(nome, desc, preco, qtd);    
    }
    public void editar(int id, String nome, String desc, double preco, int qtd){
        new ProdutoDAO().editProd(id, nome, desc, preco, qtd);
    }
    public void remover(int id){
        new ProdutoDAO().remProd(id);
    }
}
