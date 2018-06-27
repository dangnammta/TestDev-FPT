/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

/**
 *
 * @author hoaronal
 */
public abstract class ResponseTransformerRouteImpl extends RouteImpl {

	public static ResponseTransformerRouteImpl create(String path, Route route, ResponseTransformer transformer) {
		return create(path, Service.DEFAULT_ACCEPT_TYPE, route, transformer);
	}

	public static ResponseTransformerRouteImpl create(String path,
			String acceptType,
			Route route,
			ResponseTransformer transformer) {
		return new ResponseTransformerRouteImpl(path, acceptType, route) {
			@Override
			public Object render(Object model) throws Exception {
				return transformer.render(model);
			}

			@Override
			public Object handle(Request request, Response response) throws Exception {
				return route.handle(request, response);
			}
		};
	}

	protected ResponseTransformerRouteImpl(String path, String acceptType, Route route) {
		super(path, acceptType, route);
	}

	public abstract Object render(Object model) throws Exception;

}
