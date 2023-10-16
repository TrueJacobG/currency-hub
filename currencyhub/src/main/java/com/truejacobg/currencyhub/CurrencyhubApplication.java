package com.truejacobg.currencyhub;

import com.mongodb.client.MongoCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@SpringBootApplication
public class CurrencyhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyhubApplication.class, args);

		MongoClient mongoClient = MongoClients.create("mongodb+srv://client:client@sarektest.dbiwcvg.mongodb.net/?retryWrites=true&w=majority&appName=AtlasApp");
		//MongoDatabase db = mongoClient.getDatabase("currencyHub");
		//MongoCollection col = db.getCollection("User");
		//Document doc = new Document("_id","2").append("name","Amelia Smith");
		//col.insertOne(doc);

	}

}
