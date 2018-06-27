


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import crdhn.usermanagement.model.User;
import crdhn.usermanagement.model.hibernate.HibernateModelService;
import crdhn.usermanagement.model.JDBC.JDBCModelService;
import crdhn.usermanagement.template.RenderEngine;
import crdhn.usermanagement.model.ModelService;
import crdhn.usermanagement.utils.FileUtil;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import firo.utils.config.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.xml.bind.DatatypeConverter;
import org.json.simple.JSONObject;

/**
 *
 * @author hoaronal
 */
public class UserController extends Controller {

	private ModelService modelService;
	private Class clazz;

	public UserController() {
		rootPath = "/user";
		String dataBackend = Config.getParamString("service", "dataBackend", "hibernate");
		if ("hibernate".equals(dataBackend)) {
			modelService = new HibernateModelService();
			clazz = modelService.getEntityClassByName("User");
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
				List<User> list = (List<User>) modelService.list(clazz, User.class);
				attributes.put("objs", list);
				attributes.put("route", "user");
				attributes.put("path", "list");

				return RenderEngine.getInstance().render(attributes, "user/list.tpl");
			}
		};
	}

	@RouteInfo(method = "get", path = "/get/:id")
	public Route get() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String idUser = request.params(":id");

				Map<String, Object> attributes = new HashMap<>();
				User userModel = new User();
				userModel = (User) modelService.get(clazz, Integer.parseInt(idUser), User.class);

				attributes.put("obj", userModel);
				return RenderEngine.getInstance().render(attributes, "user/info.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/delete/:id")
	public Route delete() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				JSONObject mapjson = new JSONObject();
				String idUser = request.params(":id");
				User user = new User();
				user = (User) modelService.get(clazz, Integer.parseInt(idUser), User.class);
				Map<String, Object> attributes = new HashMap<>();
				boolean check = modelService.delete(user, clazz);
				mapjson.put("result", 0);
				return mapjson;
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/update/:id")
	public Route getUserUpdate() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				String idUser = request.params(":id");
				String me = request.requestMethod();
				User userModel = new User();
				userModel = (User) modelService.get(clazz, Integer.parseInt(idUser), User.class);

				attributes.put("obj", userModel);
				attributes.put("route", "user");
				attributes.put("path", "update");
				return RenderEngine.getInstance().render(attributes, "user/update.tpl");
			}
		};
	}

	@RouteInfo(method = "post", path = "/update")
	public Route Update() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				
				String imagebase64 = request.queryParams("imagebase64");
				String id = request.queryParams("id");
				String username = request.queryParams("username");
				String birthday = request.queryParams("birthday");
				String email = request.queryParams("email");
				String fullname = request.queryParams("fullname");
				System.out.println(birthday);
				User user = (User) modelService.get(clazz, Integer.parseInt(id), User.class);
				String avatar = user.getAvatar();
				System.out.println(avatar);
				if (imagebase64.trim().length() > 0) {
					avatar = uploadImage(imagebase64, "User_id_" + id);
				}

				user.setUsername(username);
				user.setEmail(email);
				user.setUpdatedDate(new Date());
				user.setFullname(fullname);
				user.setAvatar(avatar);
				//upload info user
				boolean check = modelService.update(user, clazz);
				List<User> list = (List<User>) modelService.list(clazz, User.class);
				attributes.put("objs", list);
				attributes.put("route", "user");
				attributes.put("path", "update");
				return RenderEngine.getInstance().render(attributes, "user/list.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/create")
	public Route create() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();

				attributes.put("route", "user");
				attributes.put("path", "create");
				return RenderEngine.getInstance().render(attributes, "user/adduser.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/save")
	public Route save() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				String username = request.queryParams("username");
				String password = request.queryParams("password");
				String email = request.queryParams("email");
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setStatus(0);
				user.setFullname(username);
				user.setAvatar("default-avatar.jpg");
				user.setCreatedDate(new Date());
				user.setUpdatedDate(new Date());
				user.setActivedDate(new Date());
				user.setBirthday(new Date());
				System.out.println(user.getBirthday());
				modelService.create(user, clazz);
				List<User> list = (List<User>) modelService.list(clazz, User.class);
				attributes.put("objs", list);
				attributes.put("route", "user");
				attributes.put("path", "create");
				return RenderEngine.getInstance().render(attributes, "user/list.tpl");
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
				mapjson.put("result", 0);
				return mapjson;
			}
		};
	}

	@RouteInfo(method = "get,post", path = "/uploads")
	public Route upload() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				JSONObject mapjson = new JSONObject();
				mapjson.put("result", 0);
				return RenderEngine.getInstance().render(attributes, "user/exam.tpl");
			}
		};
	}

	@RouteInfo(method = "post,get", path = "/upload")
	public Route up() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Map<String, Object> attributes = new HashMap<>();
				String imagebase64 = request.queryParams("imagebase64");
//				String nameFile = uploadImage(imagebase64);
				String idUser = request.queryParams("id");
				User userModel = new User();
				userModel = (User) modelService.get(clazz, Integer.parseInt(idUser), User.class);
//				userModel.setAvatar(nameFile);
				boolean check = modelService.update(userModel, clazz);
				User user = (User) modelService.get(clazz, Integer.parseInt(idUser), User.class);
				System.out.println(user.getAvatar());
				attributes.put("obj", user);
				return RenderEngine.getInstance().render(attributes, "user/update.tpl");
			}
		};
	}

	public void uploads(Request request, String part) {
		try {
			File uploadDir = new File("upload");
			uploadDir.mkdir(); // create the upload directory if it doesn't exist

			Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

			request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

			try (InputStream input = request.raw().getPart(part).getInputStream()) { // getPart needs to use same "name" as input field in form
				Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
			}
			catch (ServletException ex) {
				Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		catch (IOException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public String uploadImage(String dataURL, String fileName) throws FileNotFoundException, IOException {

		String base64 = dataURL.substring(dataURL.indexOf("base64,") + 7);
		FileOutputStream outThumbnail;
		String workingDir = System.getProperty("user.dir");

		String path = workingDir + File.separator + "src" + File.separator + "resources" + File.separator + "img" + File.separator + fileName + ".png";
		File tempFile = new File(path);
		tempFile.getParentFile().mkdirs();
		tempFile.createNewFile();
		//Write data to fie
		outThumbnail = new FileOutputStream(tempFile);
		outThumbnail.write(DatatypeConverter.parseBase64Binary(base64));
		outThumbnail.close();
		return fileName + ".png";
	}
	
	public Date convertDate(String dateOfBirth) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			df.setLenient(false);
			Date date = df.parse(dateOfBirth);
			return date;
		}
		catch (ParseException ex) {
			Logger.getLogger(crdhn.usermanagement.controller.UserController.class.getName()).log(Level.SEVERE, null, ex);
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
