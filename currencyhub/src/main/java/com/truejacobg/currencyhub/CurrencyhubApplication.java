package com.truejacobg.currencyhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyhubApplication {
	public static void main(String[] args) {
		SpringApplication.run(CurrencyhubApplication.class, args);


		//MongoClient mongoClient = MongoClients.create("mongodb+srv://client:client@" + "will Be Imported!");
		//MongoDatabase db = mongoClient.getDatabase("will Be Imported!");
		//MongoCollection col = db.getCollection("User");
		//Document doc = new Document("_id","2").append("name","Amelia Smith");
		//col.insertOne(doc);

	}

}
