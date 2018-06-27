/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import crdhn.usermanagement.*;
import java.util.HashMap;
import java.util.Map;

import firo.Filter;
import firo.Request;
import firo.Response;

import static firo.Firo.after;
import static firo.Firo.before;
import static firo.Firo.get;
import static firo.Firo.halt;

/**
 *
 * @author hoaronal
 */
public class LoginFilter {

	private static Map<String, String> usernamePasswords = new HashMap<>();

	public static void main(String[] args) {

		usernamePasswords.put("foo", "bar");
		usernamePasswords.put("admin", "admin");

		before(new Filter() {
			@Override
			public void handle(Request request, Response response) {
				String user = request.queryParams("user");
				String password = request.queryParams("password");

				String dbPassword = usernamePasswords.get(user);
				if (!(password != null && password.equals(dbPassword))) {
					halt(401, "You are not welcome here!!!");
				}
			}
		});
		
		before(new Filter() {
			@Override
			public void handle(Request request, Response response) throws Exception {
				boolean authenticated = request.session().attribute("username") != null;
				
				if (!authenticated && request.body().toLowerCase().contains("hendrix")) {
					
					halt(401, "Only logged in users are allowed to mess with Jimi.");
					
				}
			}
		});


		before("/hello", (request, response) -> {
			response.header("Foo", "Set by second before filter");
		});

		get("/hello", (request, response) -> {
			return "Hello World!";
		});

		after("/hello", (request, response) -> {
			response.header("spark", "added by after-filter");
		});

	}
}
