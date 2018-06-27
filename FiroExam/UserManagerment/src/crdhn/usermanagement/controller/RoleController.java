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
import crdhn.usermanagement.model.Role;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import firo.utils.config.Config;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author hoaronal
 */
public class RoleController extends Controller {

	private ModelService modelService;
	private Class clazz;

	public RoleController() {
		rootPath = "/role";
		String dataBackend = Config.getParamString("service", "dataBackend", "hibernate");
		if ("hibernate".equals(dataBackend)) {
			modelService = new HibernateModelService();
			clazz = modelService.getEntityClassByName("Role");
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
				List<Role> list = (List<Role>) modelService.list(clazz, Role.class);
				attributes.put("objs", list);
				attributes.put("route", "role");
				attributes.put("path", "list");

				return RenderEngine.getInstance().render(attributes, "role/list.tpl");
			}
		};
	}

	@RouteInfo(method = "get", path = "/get/:id")
	public Route get() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String idRole = request.params(":id");

				Map<String, Object> attributes = new HashMap<>();
				Role roleModel = new Role();
				roleModel = (Role) modelService.get(clazz, Integer.parseInt(idRole), Role.class);

				attributes.put("obj", roleModel);
				return RenderEngine.getInstance().render(attributes, "role/info.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/delete/:id")
	public Route delete() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				JSONObject mapjson = new JSONObject();
				String idRole = request.params(":id");
				Role role = new Role();
				role = (Role) modelService.get(clazz, Integer.parseInt(idRole), Role.class);
				Map<String, Object> attributes = new HashMap<>();
				boolean check = modelService.delete(role, clazz);
				mapjson.put("result", 0);
				List<Role> list = (List<Role>) modelService.list(clazz, Role.class);
				attributes.put("objs", list);
				attributes.put("route", "role");
				attributes.put("path", "list");

				return RenderEngine.getInstance().render(attributes, "role/list.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/update/:id")
	public Route getUserUpdate() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				String idRole = request.params(":id");
				String me = request.requestMethod();
				Role roleModel = new Role();
				roleModel = (Role) modelService.get(clazz, Integer.parseInt(idRole), Role.class);

				attributes.put("obj", roleModel);
				attributes.put("route", "role");
				attributes.put("path", "update");
				return RenderEngine.getInstance().render(attributes, "role/update.tpl");
			}
		};
	}

	@RouteInfo(method = "post", path = "/update")
	public Route Update() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				String id = request.queryParams("id");
				String roleC = request.queryParams("role");
				String name = request.queryParams("name");
				String description = request.queryParams("description");
				Role role = (Role) modelService.get(clazz, Integer.parseInt(id), Role.class);

				role.setRole(Integer.parseInt(roleC));
				role.setName(name);
				role.setDescription(description);
				boolean check = modelService.update(role, clazz);
				List<Role> list = (List<Role>) modelService.list(clazz, Role.class);
				attributes.put("objs", list);
				return RenderEngine.getInstance().render(attributes, "role/list.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/create")
	public Route create() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();

				attributes.put("route", "role");
				attributes.put("path", "create");
				return RenderEngine.getInstance().render(attributes, "role/add.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/save")
	public Route save() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				String idRole = request.queryParams("role");
				String name = request.queryParams("name");
				String description = request.queryParams("description");
				Role role = new Role();
				role.setRole(Integer.parseInt(idRole));
				role.setName(name);
				role.setDescription(description);
				modelService.create(role, clazz);
				List<Role> list = (List<Role>) modelService.list(clazz, Role.class);
				attributes.put("objs", list);
				attributes.put("route", "role");
				attributes.put("path", "create");
				return RenderEngine.getInstance().render(attributes, "role/list.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/add")
	public Route add() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				String name = request.queryParams("name");
				System.out.println("name : " + name);

				JSONObject mapjson = new JSONObject();
				JSONArray jSONArray = new JSONArray();
				List<Role> list = (List<Role>) modelService.list(clazz, Role.class);
				for (int i = 0; i < list.size(); i++) {
					JSONObject roleDetailsJson = new JSONObject();
					roleDetailsJson.put("idRole", list.get(i).getIdRole());
					roleDetailsJson.put("name", list.get(i).getName());
					roleDetailsJson.put("role", list.get(i).getRole());
					roleDetailsJson.put("description", list.get(i).getDescription());
					jSONArray.add(roleDetailsJson);
				}
				System.out.println(jSONArray);
				mapjson.put("result", 1);
				mapjson.put("list", jSONArray);

				return mapjson;
			}
		};
	}

}
