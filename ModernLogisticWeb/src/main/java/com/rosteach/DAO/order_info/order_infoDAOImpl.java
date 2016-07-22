package com.rosteach.DAO.order_info;



import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.rosteach.entities.Order_info;

@Repository
public class order_infoDAOImpl implements Order_infoDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 
    EntityManager em =  emf.createEntityManager();

    @Override
	public void createOrder(long order_kod, String order_number, long buyer, Date order_date, String user) {
	    Order_info ordInfo = new Order_info();
	    ordInfo.setOrder_kod(order_kod);
	    ordInfo.setBuyer(buyer);
	    ordInfo.setOrder_number(order_number);
	    ordInfo.setOrder_date(order_date);
	    ordInfo.setOrder_user(user);
	    em.getTransaction().begin();
	    em.persist(ordInfo);
	    em.getTransaction().commit();
	    
	}

	@Override
	public Order_info getOrder_info_byKod(long order_kod) {
		System.out.println(order_kod + " ORDER_KOD");
		List<Order_info> oi =  em.createNativeQuery("Select * from users_auth.order_info where order_kod = "+order_kod, Order_info.class).getResultList();
		System.out.println(oi.size() + " OI_SIZE");

		return oi.get(0);
	}

	@Override
	public void persistOrder(Order_info orderInfo) {
		em.getTransaction().begin();
	    em.persist(orderInfo);
	    em.getTransaction().commit();
		
	}
	
	

}
