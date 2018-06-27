/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http.controller;

import configuration.Configuration;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import http.response.DataResponse;
import mongodb.mca.ExceptionsCollection;
import mongodb.mca.ObjectCollections;
import org.json.JSONArray;
import org.json.JSONObject;
import render.RenderMain;

/**
 *
 * @author namdv
 */
public class HomeController extends Controller {

    public static final String _className = "=============HomeController";

    public HomeController() {
    }

    @RouteInfo(method = "get", path = "/portal")
    public Route renderHome() {
        return (Request request, Response response) -> {
            System.out.println("Render Home");
            return RenderMain.getInstance().renderHome(request, response);
        };
    }

    @RouteInfo(method = "get", path = "/portal/api/home/data")
    public Route getDataHome() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
//                String sessionKey = String.valueOf(request.params(":_key"));
//                SessionCollections.getInstance().endSession(sessionKey);
                DataResponse homeData = ObjectCollections.getInstance().get("Home", 1l);
                DataResponse tour = ObjectCollections.getInstance().getList("Tour", 0, 8);
                DataResponse attraction = ObjectCollections.getInstance().getList("Attraction", 0, 4);
                DataResponse event = ObjectCollections.getInstance().getList("Event", 0, 4);
                DataResponse hotel = ObjectCollections.getInstance().getList("Hotel", 0, 8);
                DataResponse restaurant = ObjectCollections.getInstance().getList("Restaurant", 0, 8);
                JSONObject result = new JSONObject();
                if (homeData.getError() != DataResponse.SUCCESS.getError()
                        || tour.getError() != DataResponse.SUCCESS.getError()
                        || attraction.getError() != DataResponse.SUCCESS.getError()
                        || event.getError() != DataResponse.SUCCESS.getError()
                        || hotel.getError() != DataResponse.SUCCESS.getError()
                        || restaurant.getError() != DataResponse.SUCCESS.getError()) {
                    return DataResponse.UNKNOWN_EXCEPTION;
                } else {
                    result.put("home", new JSONObject(homeData.getData().toString()));
                    result.put("tour", new JSONArray(tour.getData().toString()));
                    result.put("attraction", new JSONArray(attraction.getData().toString()));
                    result.put("event", new JSONArray(event.getData().toString()));
                    result.put("hotel", new JSONArray(hotel.getData().toString()));
                    result.put("restaurant", new JSONArray(restaurant.getData().toString()));
                    return new DataResponse(result, DataResponse.DataType.JSON_STR, false);
                }
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
