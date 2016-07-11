package com.rosteach.DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rosteach.entities.EntityManagerReferee;
import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;

@Repository
public class SPROutcomeInvoiceDAOImpl implements SPROutcomeInvoiceDAO{
	private EntityManager entityManager;
	
	@Override
	public List<SPROutcomeInvoice> getInvoicesByLocalDate(String database, String username, String password) {
		entityManager = new EntityManagerReferee().getConnection(database, username, password);
		entityManager.getTransaction().begin();
		
		LocalDate date = LocalDate.now();
		
		System.out.println("---------------date------------"+date);
		
		
		Query query = entityManager.createNativeQuery("select * from SPROUTCOMEINVOICE (Null,Null,'01.07.2016','01.07.2016',0,Null, Null,Null,0)", SPROutcomeInvoice.class);
				
		@SuppressWarnings("unchecked")
		List<SPROutcomeInvoice> invoices = query.getResultList();
		EntityManagerFactory emf = entityManager.getEntityManagerFactory();
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
		return invoices;
	}

	@Override
	public List<SPROutcomeInvoiceDetails> getInvoicesDetailsById(Integer id, String database, String username,
			String password) {
		entityManager = new EntityManagerReferee().getConnection(database, username, password);
		Query query = entityManager.createNativeQuery("select * from SPROUTCOMEINVOICEDET (+"+id+",Null,0,Null,Null,0,0)", SPROutcomeInvoiceDetails.class);
		
		System.out.println("---------startgetting det");
		@SuppressWarnings("unchecked")
		List<SPROutcomeInvoiceDetails> invoicesDetails = query.getResultList();
		
		System.out.println("----------------size"+invoicesDetails.size());
		return invoicesDetails;
	}
	
	
}
