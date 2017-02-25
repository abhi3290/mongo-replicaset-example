import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Created by WeConnect on 2/25/2017.
 */
public class MongoReplicaInsertApp {

	public static void main(String[] args) {

		// 1. Connect replica set by specifying all the members of replica set
		MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
				new ServerAddress("localhost", 27018),
				new ServerAddress("localhost", 27019)));

		//2. Get database already created.
		MongoDatabase mongoDatabase = mongoClient.getDatabase("testreplicadb");

		//3. Get collection. If not exist , it creates new collection
		MongoCollection<Document> collection = mongoDatabase.getCollection("testreplicacollection");

		//4. Create document to be insert.
		Document document = new Document();
		document.append("rep_name", "newRepName");

		//5. Insert document
		collection.insertOne(document);

		//6. Close document
		mongoClient.close();

	}
}
