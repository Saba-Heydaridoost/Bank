package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.*;

public class DateAndTime {

    public static LocalDate today = LocalDate.now();
    private LocalDate date;

    public DateAndTime (int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public static LocalDate getToday() {
        return today;
    }

    public static int howOld (Client client) {
        Period period = Period.between(client.dateOfBirth, getToday());
        int age = period.getYears();

        return age;
    }

    public static int daysOfMonth (int month) {
        if (month == 2) {
            return 28;
        }
        else if (month == 4 || month == 9 || month == 11) {
            return 30;
        }
        else {
            return 31;
        }
    }

    public static ZonedDateTime getTime () {
        return ZonedDateTime.now(ZoneId.of("Asia/Tehran"));
    }

    public static int howManyDays (Client client) {
        LocalDate exactDateOfLastInterest;
        if (getToday().getDayOfMonth() > client.getDateOfRegister().getDayOfMonth()) {
            exactDateOfLastInterest = LocalDate.of(getToday().getYear(),
                    getToday().getMonth(), client.getDateOfRegister().getDayOfMonth());
        }
        else {
            exactDateOfLastInterest = LocalDate.of(getToday().getYear(),
                    getToday().getMonthValue() - 1, client.getDateOfRegister().getDayOfMonth());
        }

        Period period = Period.between(client.getDateOfRegister(), exactDateOfLastInterest);
        int days = period.getDays();

        return days;
    }

    public static int howManyMonths (LocalDate date) {
        Period period = Period.between(date, getToday());
        int months = period.getMonths();

        return months;
    }
}
