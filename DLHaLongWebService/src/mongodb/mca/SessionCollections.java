/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.mca;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import http.response.DataResponse;
import mongodb.MongoDBConnector;
import java.security.SecureRandom;
import java.util.Base64;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

/**
 *
 * @author anbq
 */
public class SessionCollections {

    private static SessionCollections instance = null;
    private final JsonWriterSettings settings = JsonWriterSettings.builder()
            .outputMode(JsonMode.RELAXED)
            .objectIdConverter((value, writer) -> writer.writeString(value.toHexString()))
            .build();
    String objectName = "session";

    public synchronized static SessionCollections getInstance() {
        if (instance == null) {
            instance = new SessionCollections();
        }
        return instance;
    }

    public String findUserNameBySessionId(String sessionId) {
        Document session = getSession(sessionId);
        if (session == null) {
            return null;
        } else {
            return session.get("username").toString();
        }
    }

    public DataResponse startSession(String username) {
        SecureRandom generator = new SecureRandom();
        byte randomBytes[] = new byte[32];
        generator.nextBytes(randomBytes);


        String sessionKey = Base64.getEncoder().encodeToString(randomBytes);

        Document session = new Document("username", username)
                .append("sessionKey", sessionKey);
        Long _id = CountersCollection.getInstance().getNextValue(objectName);
                if (_id <= 0) {
                    return DataResponse.COUNTERS_ERROR;
                }
        session.append("_id", _id);
        MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectName);
        collection.insertOne(session);
        return new DataResponse(
                session.toJson(settings),
                DataResponse.DataType.JSON_STR,
                false
        );
    }

    public void endSession(String sessionKey) {
        MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectName);
        collection.deleteOne(eq("sessionKey", sessionKey));
    }

    public Document getSession(String sessionKey) {
        MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectName);
        return collection.find(eq("sessionKey", sessionKey)).first();
    }
}
