/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Persistence;
import java.util.List;

/**
 *
 * @author spook
 */
public class ProdutoDAO {
    private Produto prod;
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = null;
    
    public void cadProd(String nome, String desc, double preco, int qtd){
        try{
            prod = new Produto();
            prod.setNome(nome);
            prod.setDescr(desc);
            prod.setPreco(preco);
            prod.setQtdEstoque(qtd);
            et = em.getTransaction();
            et.begin();
            em.persist(prod);
            et.commit();
        } catch( RuntimeException e){
            if(et.isActive()) et.rollback();
            throw e;
        } finally{
            em.close();
        }
    }
    
    public void editProd(int id, String nome, String desc, double preco, int qtd) throws NumberFormatException{
        try{
            prod = new Produto();
            et = em.getTransaction();
            et.begin();
            prod = em.find(Produto.class,new Integer(id));
            if(prod!=null){
                prod.setNome(nome);
                prod.setDescr(desc);
                prod.setPreco(preco);
                prod.setQtdEstoque(qtd);
            }
            et.commit();
        } catch( RuntimeException e){
            if(et.isActive()&& et!=null) et.rollback();
            throw e;
        } finally{
            em.close();
        }
    }
    public void remProd(int id){
        try{
            prod = new Produto();
            et = em.getTransaction();
            et.begin();
            prod = em.find(Produto.class,new Integer(id));
            if(prod!=null){
                em.remove(prod);
            }
            et.commit();
        } catch( RuntimeException e){
            if(et.isActive() && et!=null) et.rollback();
            throw e;
        } finally{
            em.close();
        }
    }
    
    public List<Produto> catProd(){
        Query qry = em.createQuery("SELECT p FROM Produto p ORDER BY p.id");
        return qry.getResultList();
    }
    public List<Produto> pesqID(int id){
        Query qry = em.createQuery("SELECT p FROM Produto p WHERE p.id = :varID ORDER BY p.id").setParameter("varID", id);
        return qry.getResultList();
    }
    
    public List<Produto> pesqNome(String nome){
        Query qry = em.createQuery("SELECT p FROM Produto p WHERE p.nome LIKE '%' || :varNome || '%' ORDER BY p.id").setParameter("varNome", nome);
        return qry.getResultList();
    }
    public List<Produto> pesqQtd(int qtd){
        Query qry = em.createQuery("SELECT p FROM Produto p WHERE p.qtdEstoque = :varQtd ORDER BY p.id").setParameter("varQtd", qtd);
        return qry.getResultList();
    }
}
