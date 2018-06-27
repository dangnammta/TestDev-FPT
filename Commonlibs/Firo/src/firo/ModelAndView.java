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
public class ModelAndView {

	private Object model;
	private String viewName;

	public ModelAndView(Object model, String viewName) {
		super();
		this.model = model;
		this.viewName = viewName;
	}

	public Object getModel() {
		return model;
	}

	public String getViewName() {
		return viewName;
	}

}
