package crdhn.usermanagement;

import crdhn.usermanagement.template.RenderEngine;
import firo.Filter;
import firo.Firo;
import static firo.Firo.before;
import static firo.Firo.halt;
import static firo.Firo.post;
import firo.Request;
import firo.Response;
import firo.utils.config.Config;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

/**
 *
 * @author hoaronal
 */
public class ServiceDaemon {

	private static Map<String, String> usernamePasswords = new HashMap<>();

	public static void main(String[] args) throws NoSuchMethodException {

		Firo.getInstance().staticFileLocation("/resources");
		Firo.getInstance().init(Config.getParamString("service", "host", "localhost"), Config.getParamInt("service", "port", 8113));
		Firo.getInstance().initializeControllerFromPackage(Config.getParamString("service", "controllerPackage", "com.example.controller"));
		
		before(new Filter() {
			@Override
			public void handle(Request request, Response response) {
				usernamePasswords.put("quanghoa", "quanghoa");
				String user = "quanghoa";
				String password = "quanghoa";

				String dbPassword = usernamePasswords.get(user);
				if (!(password != null && password.equals(dbPassword))) {
					Map<String, Object> attributes = new HashMap<>();
					halt(401, RenderEngine.getInstance().render(attributes, "user/index.tpl"));
				}

			}
		});
	}
}
