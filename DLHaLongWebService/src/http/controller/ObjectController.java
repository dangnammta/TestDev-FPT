/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http.controller;

import http.service.ServletUtil;
import configuration.Configuration;
import http.response.DataResponse;
import mongodb.mca.ObjectCollections;
import mongodb.mca.CountersCollection;
import mongodb.mca.ExceptionsCollection;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import org.bson.Document;

/**
 *
 * @author longmd
 */
public class ObjectController extends Controller {

    public ObjectController() {
        rootPath = "/portal/common";
    }

    @RouteInfo(method = "post", path = "/:object")
    public Route create() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));

                String json = request.body();
                Document document = Document.parse(json);
                Long _id = CountersCollection.getInstance().getNextValue(object);
                if (_id <= 0) {
                    return DataResponse.COUNTERS_ERROR;
                }
                document.append("_id", _id);

                return ObjectCollections.getInstance().create(object, document);
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

    @RouteInfo(method = "get", path = "/:object/:_id")
    public Route get() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                Long _id = Long.valueOf(request.params(":_id"));

                return ObjectCollections.getInstance().get(object, _id);
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

    @RouteInfo(method = "put", path = "/:object/:_id")
    public Route update() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                Long _id = Long.valueOf(request.params(":_id"));

                String json = request.body();
                Document document = Document.parse(json);
                document.append("_id", _id);

                return ObjectCollections.getInstance().update(object, _id, document);
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

    @RouteInfo(method = "delete", path = "/:object/:_id")
    public Route delete() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                Long _id = Long.valueOf(request.params(":_id"));

                return ObjectCollections.getInstance().delete(object, _id);
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

    @RouteInfo(method = "get", path = "/:object/list/get")
    public Route getList() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");

                return ObjectCollections.getInstance().getList(object, numberOfSkip, numberOfLimit);
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

    @RouteInfo(method = "post", path = "/:object/list/condition")
    public Route getListByCondition() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");

                String json = request.body();
                Document document = Document.parse(json);

                return ObjectCollections.getInstance().getListByCondition(object, document, numberOfSkip, numberOfLimit);
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
}
