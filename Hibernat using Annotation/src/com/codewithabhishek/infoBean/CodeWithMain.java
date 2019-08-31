package com.codewithabhishek.infoBean;


import java.util.Scanner;



import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;




public class CodeWithMain {

	private static SessionFactory sessionfactory;

	private static Logger logger = Logger.getLogger(CodeWithMain.class);

	public static void main(String[] agrs) {

		try {
			StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml").build();
			Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
			logger.info("Hibernate serviceRegistry created");
			logger.info("Hibernate Configuration loaded");

			sessionfactory = metadata.getSessionFactoryBuilder().build();
			Session session = sessionfactory.openSession();
			Transaction tx = session.beginTransaction();

			logger.info("Session is Open for Transaction");

			InfoBean infobean = new InfoBean();
			System.out.println("Enter your name");
			Scanner sc = new Scanner(System.in);
            String name = sc.next();
            System.out.println("Enter your Last Name");
            String last = sc.next();
            System.out.println("Enter your address");
            String address = sc.next();
            
            infobean.setFirstname(name);
            infobean.setLastname(last);
            infobean.setAddress(address);
            
			logger.info("data has been stored in database");

			session.save(infobean);
			tx.commit();
			session.close();
			logger.info("session closed");
			
		
			
		} catch (Exception e) {

			logger.error("error"+e);
		}

	}

}
