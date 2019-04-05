package com.jpmorgan.services;

import java.sql.Timestamp;
import java.util.Calendar;

public class CalenderService {

	public static Timestamp addDays(Timestamp input, int days) {
		Calendar inputC = Calendar.getInstance();
		inputC.setTimeInMillis(input.getTime());
		inputC.add(Calendar.DAY_OF_MONTH, days);
		Timestamp output = new Timestamp(inputC.getTimeInMillis());
		return output;
	}

	/**
	 * Calculates date of first Day in the week for input
	 */
	public static Timestamp calculateFirstDayOfWeek(Timestamp input) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(input);
		input = addDays(input, calculateDiffToFirstDayInWeek(cal.get(Calendar.DAY_OF_WEEK)));
		return input;
	}

	/**
	 * Wie viele tage bis Montag abziehen?
	 */
	private static int calculateDiffToFirstDayInWeek(int dayOfWeek) {
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return -(dayOfWeek - 2);
		}
	}

}
