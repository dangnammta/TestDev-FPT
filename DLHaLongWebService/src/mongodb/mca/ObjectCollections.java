/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.mca;

import com.mongodb.BasicDBList;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;
import com.mongodb.util.ObjectSerializer;
import configuration.Configuration;
import http.response.DataResponse;
import mongodb.MongoDBConnector;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

/**
 *
 * @author longmd
 */
public class ObjectCollections {

	private static ObjectCollections instance = null;
	private final JsonWriterSettings settings = JsonWriterSettings.builder()
			.outputMode(JsonMode.RELAXED)
			.objectIdConverter((value, writer) -> writer.writeString(value.toHexString()))
			.build();
	
//	private final UpdateOptions upsertOption = new UpdateOptions().upsert(true);

	public synchronized static ObjectCollections getInstance() {
		if (instance == null) {
			instance = new ObjectCollections();
		}
		return instance;
	}
	
	public DataResponse create(String object, Document document){
		try{
			MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
			collection.insertOne(document);
			return new DataResponse(
					new Document("_id", document.get("_id")).toJson(settings), 
					DataResponse.DataType.JSON_STR, 
					false
			);
		}catch (Exception ex){
			ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());
			return new DataResponse(-1, ex.getMessage());
		}
	}
	
	public DataResponse get(String object, Long _id){
		try {
			MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
			return new DataResponse(
					collection.find(Filters.eq("_id", _id)).first().toJson(settings), 
					DataResponse.DataType.JSON_STR, 
					false
			);
		}
		catch (Exception ex) {
			ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());
			return new DataResponse(-1, ex.getMessage());
		}
	}
	
	public DataResponse update(String object, Long _id, Document document){
		try{
			MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
			collection.replaceOne(Filters.eq("_id", _id), document);
			return DataResponse.SUCCESS;
		}catch (Exception ex){
			ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());
			return new DataResponse(-1, ex.getMessage());
		}
	}
	
	public DataResponse delete(String object, Long _id){
		try{
			MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
			collection.deleteOne(Filters.eq("_id", _id));
			return DataResponse.SUCCESS;
		}catch (Exception ex){
			ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());
			return new DataResponse(-1, ex.getMessage());
		}
	}
	
	public DataResponse getList(String object, int numberOfSkip, int numberOfLimit){
		try {
			MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
			BasicDBList list = new BasicDBList();
			
			MongoCursor<Document> cursor = collection.find().skip(numberOfSkip).limit(numberOfLimit).iterator();
			while (cursor.hasNext())
				list.add(cursor.next());
			
			return new DataResponse(
					JSON.serialize(list),
					DataResponse.DataType.JSON_STR, 
					false
			);
		}
		catch (Exception ex) {
			ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());
			return new DataResponse(-1, ex.getMessage());
		}
	}
	
	public DataResponse getListByCondition(String object, Document document, int numberOfSkip, int numberOfLimit){
		try {
			MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
			BasicDBList list = new BasicDBList();
			
			MongoCursor<Document> cursor = collection.find(document).skip(numberOfSkip).limit(numberOfLimit).iterator();
			while (cursor.hasNext())
				list.add(cursor.next());
			
			return new DataResponse(
					JSON.serialize(list),
					DataResponse.DataType.JSON_STR, 
					false
			);
		}
		catch (Exception ex) {
			ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());
			return new DataResponse(-1, ex.getMessage());
		}
	}
}
