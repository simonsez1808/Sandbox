package euler;
//You are given the following information, but you may prefer to do some research for yourself.

//1 Jan 1900 was a Monday.

//Thirty days has September,
//April, June and November.
//All the rest have thirty-one,
//Saving February alone,
//Which has twenty-eight, rain or shine.
//And on leap years, twenty-nine.
//A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
//How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

public class Problem0019 {
	static int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	static final String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyDate myDate = new MyDate();
		int count = 0;

		while (myDate.day <= 31 && myDate.month <= 12 && myDate.year <= 2000) {

			if (myDate.isSundayAnd1stOfTheMonth() && myDate.year > 1900) {
				count++;
			}
			
			myDate.increment();
		}
		
		System.out.println(count);
	}

}

class MyDate {
	int dayOfWeek;
	int day;
	int month;
	int year;

	MyDate() {
		dayOfWeek = 0;
		day = 1;
		month = 1;
		year = 1900;
	}

	private boolean isLeapYear() {
		if (year % 4 != 0) {
			return false;
		}

		if (year % 100 == 0 && year % 400 != 0) {
			return false;
		}

		return true;
	}

	public String toString() {
		return Problem0019.daysOfWeek[dayOfWeek] + ", " + day + "/" + month + "/" + year;
	}

	public boolean isSundayAnd1stOfTheMonth() {
		return dayOfWeek == 6 && day == 1;
	}

	void increment() {

		if (month == 1 && isLeapYear()) {
			Problem0019.months[1] = 29;
		} else {
			Problem0019.months[1] = 28;
		}

		dayOfWeek = (dayOfWeek + 1) % 7;
		day++;

		if (day > Problem0019.months[month - 1]) {
			day = 1;
			month++;
		}

		if (month > 12) {
			month = 1;
			year++;
		}
	}

}
