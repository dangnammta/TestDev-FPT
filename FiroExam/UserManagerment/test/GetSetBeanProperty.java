/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.Expression;
import java.beans.Statement;

public class GetSetBeanProperty {

	public static void main(String[] args) throws Exception {

		Object o = new Bean();

		Statement stmt;
		Expression expr;

		// Set the value of property1
	    stmt = new Statement(o, "setProperty1", new Object[]{"My Prop Value"});
	    stmt.execute();

	    // Get the value of property1
	    expr = new Expression(o, "getProperty1", new Object[0]);
	    expr.execute();
	    System.out.println("Property1 value: " + (String)expr.getValue());

	    /////////////////////////////////////////////

	    // Set the value of property2
	    stmt = new Statement(o, "setProperty2", new Object[]{new Integer(345)});
	    stmt.execute();

	    // Get the value of property2
	    expr = new Expression(o, "getProperty2", new Object[0]);
	    expr.execute();
	    System.out.println("Property2 value: " + (Integer)expr.getValue());

	}

	public static class Bean {

		// Property property1
		private String property1;
	    // Property property2
		private int property2;

		public String getProperty1() {
			return property1;
		}
		public void setProperty1(String property1) {
			this.property1 = property1;
		}

		public int getProperty2() {
			return property2;
		}
		public void setProperty2(int property2) {
			this.property2 = property2;
		}

	}

}
