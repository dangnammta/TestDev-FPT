
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hoaronal
 */
public class Test {

	public static void main(String[] args) throws ParseException {
		String firstdate = "22/01/2017";
		String enddate = "25/01/2017";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "24/01/2017";
		Date first = sdf.parse(firstdate);
		Date end = sdf.parse(enddate);
		Date current = sdf.parse(dateInString);
		System.out.println("first:" + first.getTime());
		System.out.println("current:" + current.getTime());
		System.out.println("end:" + end.getTime());
		if (current.getTime() > first.getTime() && current.getTime() < end.getTime()) {
			System.out.println("True");
		}
	}
}
