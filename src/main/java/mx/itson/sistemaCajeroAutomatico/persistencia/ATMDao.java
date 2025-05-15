/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.sistemaCajeroAutomatico.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.sistemaCajeroAutomatico.entidades.ATM;
import mx.itson.sistemaCajeroAutomatico.utils.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author bruns
 */
public class ATMDao {
    public static List<ATM> obtenerTodos() {
        List<ATM> atms = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<ATM> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(ATM.class);
            criteriaQuery.from(ATM.class);
            atms = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return atms;
    }
    
     public static boolean save(ATM a){
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            session.save(a);
            session.getTransaction().commit();
            
            resultado = a.getIdCliente() !=0;
            
            
        }catch(Exception ex){
            System.err.println("Ocurrió un error: " +  ex.getMessage());
        }
        return resultado;
    }
}
