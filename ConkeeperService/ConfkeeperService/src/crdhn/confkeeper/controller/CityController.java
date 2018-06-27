/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.confkeeper.controller;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;

/**
 *
 * @author hoaronal
 */
public class CityController extends Controller {

	public CityController() {
		rootPath = "/city";
	}

	@RouteInfo(method = "get", path = "/listCity")
	public Route getListCity() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				return "hellovietanh";
			}
		};
	}

	@RouteInfo(method = "get", path = "/get")
	public Route get() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				return "City";
			}
		};
	}
}
