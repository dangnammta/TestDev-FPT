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
public abstract class TemplateViewRouteImpl extends RouteImpl {

	public static TemplateViewRouteImpl create(String path,
			TemplateViewRoute route,
			TemplateEngine engine) {

		return create(path, Service.DEFAULT_ACCEPT_TYPE, route, engine);
	}

	public static TemplateViewRouteImpl create(String path,
			String acceptType,
			TemplateViewRoute route,
			TemplateEngine engine) {

		return new TemplateViewRouteImpl(path, acceptType, route) {
			@Override
			public String render(ModelAndView modelAndView) {
				return engine.render(modelAndView);
			}

			@Override
			public Object handle(Request request, Response response) throws Exception {
				return route.handle(request, response);
			}
		};
	}

	protected TemplateViewRouteImpl(String path, String acceptType, TemplateViewRoute route) {
		super(path, acceptType, route);
	}

	@Override
	public Object render(Object object) {
		ModelAndView modelAndView = (ModelAndView) object;
		return render(modelAndView);
	}

	public ModelAndView modelAndView(Object model, String viewName) {
		return new ModelAndView(model, viewName);
	}

	public abstract Object render(ModelAndView modelAndView);

}
