package es.florida.ejtema2;

import java.io.Serializable;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Principal {

	public static void main(String[] args) {
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Pelicula.class);
		ServiceRegistry registry = new
		StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// Ací les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
			
			
			// Cargar objetos de la BDD
			Pelicula peliculaId = (Pelicula) session.get(Pelicula.class, 6);
			if(peliculaId == null) {
				System.out.println("No hi ha ninguna pelicula amb id = 6");
			}else {
				System.out.println("Titol de la pelicula: " + peliculaId.getTitol());
				System.out.println("Director de la pelicula: " + peliculaId.getDirector());
				System.out.println("Puntuacio de la pelicula: " + peliculaId.getPuntuacio());
				System.out.println("Genere de la pelicula: " + peliculaId.getGenere());
			}
			
			// Persistencia de 2 objectes de tipo Pelicula
				// Session.persist()
					//	Pelicula pelicula1 = new Pelicula("Intocable", "Olivier Nakache, Éric Toledano", "8", "Comedia");
					//  session.persist(pelicula1);
			
				// Session.save()
					// Pelicula pelicula2 = new Pelicula("Las lista de Schindler", "Steven Spielberg", "8.7", "Bèl.lic");
					// Serializable id = session.save(pelicula2);
					// System.out.println("Pelicula creada con id: " + id);
					
			// Actualitza la información d’un objecte donat el seu id
				Pelicula pelicula3 = (Pelicula) session.load(Pelicula.class, 8);
				if(pelicula3 == null) {
					System.out.println("No hi ha ninguna pelicula amb id = 8");
				}else {
					pelicula3.setPuntuacio("9.2");
					session.update(pelicula3);
					System.out.println("s'han actualitzat les dades de la pelicula");
				}
				
			//Borrar un objecte donat el seu id
				Pelicula pelicula4 = new Pelicula();
				Scanner sc = new Scanner(System.in);
				System.out.print("Quina pelicula vols eliminar, introdueix un id: ");
		        int idBorrar = Integer.parseInt(sc.nextLine());
				pelicula4.setId(idBorrar);
				session.delete(pelicula4);
			    System.out.println("s'ha eliminat la pelicula correctament");
				
		        
		      
		// Commit de la transacció i tanca de sessió
		session.getTransaction().commit();
		sc.close();
		session.close();
	}

}
