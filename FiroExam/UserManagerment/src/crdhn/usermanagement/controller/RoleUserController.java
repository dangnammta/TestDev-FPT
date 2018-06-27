/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.controller;

import crdhn.usermanagement.model.hibernate.HibernateModelService;
import crdhn.usermanagement.model.JDBC.JDBCModelService;
import crdhn.usermanagement.template.RenderEngine;
import crdhn.usermanagement.model.ModelService;
import crdhn.usermanagement.entity.RoleUser;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import firo.utils.config.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author hoaronal
 */
public class RoleUserController extends Controller {

	private static ModelService modelService;
	private static Class clazz;

	public RoleUserController() {
		rootPath = "/roleuser";
		String dataBackend = Config.getParamString("service", "dataBackend", "hibernate");
		if ("hibernate".equals(dataBackend)) {
			modelService = new HibernateModelService();
			clazz = modelService.getEntityClassByName("RoleUser");
		}
		else if ("thrift".equals(dataBackend)) {
			modelService = new JDBCModelService();
		}
	}

	@RouteInfo(method = "get", path = "/list")
	public Route list() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				List<RoleUser> list = (List<RoleUser>) modelService.lists(clazz, RoleUser.class);
				attributes.put("objs", list);

				return RenderEngine.getInstance().render(attributes, "roleuser/list.tpl");
			}
		};
	}

}
