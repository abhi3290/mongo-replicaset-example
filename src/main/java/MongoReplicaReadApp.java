import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Abhishek on 2/20/2017.
 */
public class MongoReplicaReadApp {

	public static void main(String[] args) {
		// 1. Connect replica set by specifying all the members of replica set
		MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
				new ServerAddress("localhost", 27018),
				new ServerAddress("localhost", 27019)));

		//2. Get database created.
		MongoDatabase mongoDatabase = mongoClient.getDatabase("testreplicadb");

		//3. Get collection. If not exist , it creates new collection
		MongoCollection<Document> collection = mongoDatabase.getCollection("testreplicacollection");

		//4. Iterate all documents and print
		MongoCursor<Document> iterator = collection.find().iterator();
		while(iterator.hasNext()){
			Document empDocument = iterator.next();
			System.out.println("Document :"+ empDocument.toString());
		}

		//6. Close the Mongo Connection
		mongoClient.close();


	}
}
