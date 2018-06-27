/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.confkeeper.controller;

import crdhn.confkeeper.template.RenderEngine;
import firo.Controller;
import firo.ModelAndView;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hoaronal
 */
public class UserController extends Controller {

	public UserController() {
		rootPath = "/user";
	}

	@RouteInfo(method = "get", path = "/listuser")
	public Route getListUser() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
					attributes.put("posts", "demo freemarker");
				return RenderEngine.getInstance().render(new ModelAndView(attributes, "posts.ftl"));
			}
		};
	}
	
	@RouteInfo(method = "get", path = "/user")
	public Route getUser() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				return "xfxcxcxcxc";
			}
		};
	}

	
}
