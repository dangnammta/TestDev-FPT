/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.http.matching;

import firo.Request;
import firo.RequestResponseFactory;
import firo.RouteImpl;
import firo.route.HttpMethod;
import firo.routematch.RouteMatch;

/**
 *
 * @author hoaronal
 */
public class Routes {

	static void execute(RouteContext context) throws Exception {

		Object content = context.body().get();

		RouteMatch match = context.routeMatcher().find(context.httpMethod(), context.uri(), context.acceptType());

		Object target = null;
		if (match != null) {
			target = match.getTarget();
		}
		else if (context.httpMethod() == HttpMethod.head && context.body().notSet()) {
			content
					= context.routeMatcher().find(HttpMethod.get, context.uri(), context.acceptType())
					!= null ? "" : null;
		}

		if (target != null) {
			Object result = null;

			if (target instanceof RouteImpl) {
				RouteImpl route = ((RouteImpl) target);

				if (context.requestWrapper().getDelegate() == null) {
					Request request = RequestResponseFactory.create(match, context.httpRequest());
					context.requestWrapper().setDelegate(request);
				}
				else {
					context.requestWrapper().changeMatch(match);
				}

				context.responseWrapper().setDelegate(context.response());

				Object element = route.handle(context.requestWrapper(), context.responseWrapper());
				result = route.render(element);
			}

			if (result != null) {
				content = result;

				if (content instanceof String) {
					String contentStr = (String) content;

					if (!contentStr.equals("")) {
						context.responseWrapper().body(contentStr);
					}
				}
			}
		}

		context.body().set(content);
	}

}
