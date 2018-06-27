/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.http.matching;

import java.util.List;

import firo.FilterImpl;
import firo.Request;
import firo.RequestResponseFactory;
import firo.route.HttpMethod;
import firo.routematch.RouteMatch;

/**
 *
 * @author hoaronal
 */
final class AfterFilters {

	static void execute(RouteContext context) throws Exception {

		Object content = context.body().get();

		List<RouteMatch> matchSet = context.routeMatcher().findMultiple(HttpMethod.after,
				context.uri(),
				context.acceptType());

		for (RouteMatch filterMatch : matchSet) {
			Object filterTarget = filterMatch.getTarget();

			if (filterTarget instanceof FilterImpl) {

				if (context.requestWrapper().getDelegate() == null) {
					Request request = RequestResponseFactory.create(filterMatch, context.httpRequest());
					context.requestWrapper().setDelegate(request);
				}
				else {
					context.requestWrapper().changeMatch(filterMatch);
				}

				context.responseWrapper().setDelegate(context.response());

				FilterImpl filter = (FilterImpl) filterTarget;
				filter.handle(context.requestWrapper(), context.responseWrapper());

				String bodyAfterFilter = context.response().body();

				if (bodyAfterFilter != null) {
					content = bodyAfterFilter;
				}
			}
		}

		context.body().set(content);
	}

}
