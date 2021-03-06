package app.demo.test;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Update {

	public static void main(String[] args) {

		// will read hibernate.cfg.xml file from src folder directly
		Configuration cfg = new Configuration();
		cfg.configure();

		System.out.println("configuraton done");

		// 20 database connection will create u SessionFactory
		// will perfrom DDL queries
		SessionFactory sf = cfg.buildSessionFactory();
		System.out.println("connection established with DB");

		// use 1 connection and JDBC statements to perform CRUD
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Food food = session.get(Food.class, 11);
		System.out.println(food);
		if (food != null) {

			// modify
			food.setFamousFor("spices");
			food.setName("pizza");
			food.setFoodFrom("italy");

			// update
			session.update(food);
		}

		tx.commit();
		// close resources
		session.close();
		sf.close();

	}

}
