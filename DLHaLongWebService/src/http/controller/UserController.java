/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http.controller;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import configuration.Configuration;
import http.response.DataResponse;
import mongodb.MongoDBConnector;
import mongodb.mca.CountersCollection;
import mongodb.mca.ExceptionsCollection;
import mongodb.mca.ObjectCollections;
import mongodb.mca.SessionCollections;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bson.Document;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 *
 * @author longmd
 */
public class UserController extends Controller {

    private final Random random = new SecureRandom();
    private final String objectName = "User";

    public UserController() {
        rootPath = "/User";
    }

    @RouteInfo(method = "put", path = "/:_id/changePassword")
    public Route changePassword() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");

                return DataResponse.SUCCESS;
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/logIn")
    public Route logIn() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String json = request.body();
                Document jSonData = Document.parse(json);
                String username = jSonData.getString("username");
                String password = jSonData.getString("password");
                MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectName);
                Document user = collection.find(eq("username", username)).first();
                if (user == null) {
                    return DataResponse.AUTHENTICATION_FAIL;
                }
                String hashedAndSalted = user.get("password").toString();
                String salt = hashedAndSalted.split(",")[1];
                if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
                    return DataResponse.AUTHENTICATION_FAIL;
                }
                return SessionCollections.getInstance().startSession(username);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/signUp")
    public Route signUp() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String json = request.body();
                Document user = Document.parse(json);
                MongoCollection<Document> collectionUser = MongoDBConnector.getInstance().getCollection(objectName);
                Document checkUser = collectionUser.find(eq("username", user.getString("username"))).first();
                if (checkUser != null) {
                    return DataResponse.USER_EXITS;
                }
                String passwordHash = makePasswordHash(user.getString("password"), Integer.toString(random.nextInt()));
                user.replace("password", passwordHash);
                Long _id = CountersCollection.getInstance().getNextValue(objectName);
                if (_id <= 0) {
                    return DataResponse.COUNTERS_ERROR;
                }
                user.append("_id", _id);
                return ObjectCollections.getInstance().create(objectName, user);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "delete", path = "/logOut/:_key")
    public Route logOut() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String sessionKey = String.valueOf(request.params(":_key"));
                SessionCollections.getInstance().endSession(sessionKey);
                return DataResponse.SUCCESS;
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/resetPassword")
    public Route forgotPassword() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");

                return DataResponse.SUCCESS;
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }

    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return Base64.getEncoder().encodeToString(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
}
