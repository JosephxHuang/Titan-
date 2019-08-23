package db.mongodb;

import java.text.ParseException;

import org.bson.Document;//bson = �G�i���json

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

public class MongoDBCreation {
  // Run as Java application to create MongoDB collections with index.
  public static void main(String[] args) throws ParseException {
		// Step 1, connetion to MongoDB
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);

		// Step 2, remove old collections.
		db.getCollection("users").drop();
		db.getCollection("items").drop();

		// Step 3, create new collections
		IndexOptions indexOptions = new IndexOptions().unique(true);
		db.getCollection("users").createIndex(new Document("user_id", 1), indexOptions);//�qjson {} �Φ��ഫ��document�Φ�
		db.getCollection("items").createIndex(new Document("item_id", 1), indexOptions);//Document 

		// Step 4, insert fake user data and create index.�榡 vs row Document
		db.getCollection("users").insertOne(
				new Document().append("user_id", "1111").append("password", "3229c1097c00d497a0fd282d586be050")
						.append("first_name", "John").append("last_name", "Smith"));//����json�����

		mongoClient.close();
		System.out.println("Import is done successfully.");
  }
}
