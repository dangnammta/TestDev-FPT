/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import static firo.Firo.addRoute;
import firo.utils.AnnotationUtils;
import java.lang.reflect.Method;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hoaronal
 */
public abstract class Controller {

	private static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	public String rootPath = "";

	public void initRoutes() {
		List<Method> listMethod = AnnotationUtils.findAnnotatedMethods(this.getClass(), RouteInfo.class);

		for (int i = 0; i < listMethod.size(); i++) {
			Method method = listMethod.get(i);
			if (method.getReturnType() == Route.class) {
				try {
					RouteInfo routeInfo = AnnotationUtils.getAnnotationForMethod(method.getName(), this);
					String[] m = routeInfo.method().split(",");
					
					Route route = (Route) method.invoke(this, new Object[]{});
					for (String m1 : m) {
						addRoute(m1, this.rootPath + routeInfo.path(), route);
						addRoute(m1, this.rootPath + "", route);
					}

				}
				catch (Exception ex) {
					LOG.error(ex.getMessage());
				}
			}
		}
	}

}
