/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.controller;

import crdhn.usermanagement.model.User;
import crdhn.usermanagement.model.hibernate.HibernateModelService;
import crdhn.usermanagement.model.JDBC.JDBCModelService;
import crdhn.usermanagement.template.RenderEngine;
import crdhn.usermanagement.model.ModelService;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import firo.utils.config.Config;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author hoaronal
 */
public class LoginController extends Controller {

	private ModelService modelService;
	private Class clazz;

	public LoginController() {
		rootPath = "/home";

	}

	@RouteInfo(method = "get", path = "")
	public static Route index() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				return RenderEngine.getInstance().render(attributes, "user/index.tpl");
			}
		};
	}
	
	public Date convertDate(String dateOfBirth) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			df.setLenient(false);
			Date date = df.parse(dateOfBirth);
			return date;
		}
		catch (ParseException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
			return new Date();
		}
	}

	public int checkDate(String dateOfBirth, User user) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			df.setLenient(false);
			if (dateOfBirth == null || dateOfBirth.equals("")) {
				return 2;
			}
			Date date = df.parse(dateOfBirth);
			if (date.after(new Date())) {
				return 3;
			}
			else {
				user.setBirthday(date);
				return 0;
			}
		}
		catch (ParseException ex) {
			return 1;
		}
	}

	private boolean validatedateOfBirth(String dateOfBirth, User user) {
		int check = checkDate(dateOfBirth, user);
		switch (check) {
			case 0:
				return true;
			case 1:
//			msg_dob = getMessage("user.uv06.dobvalidate");
				return false;
			case 2:
//			msg_dob = getMessage("user.uv06.dob_blank");
				return false;
			case 3:
//			msg_dob = getMessage("user.uv06.dob_after_today");
				return false;
			default:
				return false;
		}
	}
}
