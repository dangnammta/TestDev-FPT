/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;

import java.util.Map;

/**
 *
 * @author hoaronal
 */
public abstract class TemplateEngine {

	public String render(Object object) {
		ModelAndView modelAndView = (ModelAndView) object;
		return render(modelAndView);
	}

	public ModelAndView modelAndView(Object model, String viewName) {
		return new ModelAndView(model, viewName);
	}

	public String render(ModelAndView modelAndView){
		throw new UnsupportedOperationException("Not supported yet.");
	};
	
	public String render(Map<String, Object> attributes, String tplPath){
		throw new UnsupportedOperationException("Not supported yet.");
	};
}
