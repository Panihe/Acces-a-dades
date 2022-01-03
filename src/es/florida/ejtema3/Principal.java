package es.florida.ejtema3;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class Principal {
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
		MongoCollection<Document> coleccion = database.getCollection("Peliculas");
		// CRUD operations
		
		// Crear 
//		Document doc = new Document();
//		doc.append("Id", "9");
//		doc.append("Titol", "Malditos Bastardos");
//		doc.append("Director", "Quentin Tarantino");
//		doc.append("Puntuacio", "7.8");
//		doc.append("Genere", "Bélico");
//		coleccion.insertOne(doc);

		//coleccion.insertMany();

		
		// Llegir
		Bson query = eq("Id", "8");
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
//			JSONObject obj = new JSONObject(cursor.next().toJson());
//			System.out.println(obj.getString("Id"));
		}
		
		// Actulitzar
//		coleccion.updateOne(eq("Id", "9"), new Document("$set",
//				new Document("Id", "9")));

		
		// Esborrar
		//coleccion.deleteOne(eq("Id", "9"));
		
		mongoClient.close();

	}

}
