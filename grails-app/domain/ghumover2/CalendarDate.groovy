package ghumover2

import org.grails.databinding.BindingFormat
import org.joda.time.DateTime

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.sql.Timestamp

class CalendarDate
{
    @BindingFormat('dd-MM-yyyy')
        Date calendar_date
        Integer year
        Integer quarter
        Integer month
        Integer day_of_month
        Integer day_of_week
         String monthName
         String dayName
         Integer week_of_year
         Boolean isWeekday
         Boolean isHoliday
         Boolean isPayday
         String holiday_description
         static hasMany = [events:Event , holidays:Holiday]

     static constraints = {

         year(unique: ['day_of_month' , 'month'])

         year(nullable:true)
         quarter(nullable :true)
         month(nullable :true)
         day_of_month(nullable:true)
         day_of_week(nullable :true)
         monthName(nullable :true)
         dayName(nullable :true)
         week_of_year(nullable :true)
         isWeekday(nullable :true)
         isHoliday(nullable :true)
         isPayday(nullable :true)
         holiday_description(nullable :true)

     }

    static mapping = {
        version false
        calendar_date  sqlType: "DATE"
    }



  static void addYearlyHoliday(Integer month , Integer day ,  String description)
    {
        Holiday h = new Holiday(holiday_description:description).save()
        CalendarDate.findAllByMonthAndDay_of_month(month,day).each {
            it.isHoliday = true
            it.addToHolidays(h).save()

        }
    }


    static Integer getTotalWorkingDays(Integer month , Integer year)
        {

            Integer weekDays =  CalendarDate.findAllByMonthAndYearAndIsWeekday(month,year,true).size()
            Integer week_holidays =  CalendarDate.findAllByMonthAndYearAndIsHolidayAndIsWeekday(month,year,true,true).size()
            return weekDays - week_holidays

        }
    static Integer getTotalHolidays(Integer month , Integer year)
      {
          Integer holidays = CalendarDate.findAllByYearAndMonthAndIsHoliday(year,month,true).size()
          return  holidays

      }

    static CalendarDate today()
      {
          Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
          int d = localCalendar.get(Calendar.DATE);
          int m = localCalendar.get(Calendar.MONTH) + 1;
          int y = localCalendar.get(Calendar.YEAR);
           return  CalendarDate.findByDay_of_monthAndMonthAndYear(d,m,y)
      }

    static CalendarDate getDate(String date)
     {
         DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
         Calendar cal  = Calendar.getInstance();
         cal.setTime(df.parse(date));
         int d = cal.get(Calendar.DATE);
         int m = cal.get(Calendar.MONTH) + 1;
         int y = cal.get(Calendar.YEAR);
         return   CalendarDate.findByDay_of_monthAndMonthAndYear(d,m,y)

     }

    static getTotalDaysInMonth(Integer month,Integer year)
    {
        DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0, 0);
        int daysInMonth = dateTime.dayOfMonth().getMaximumValue();
        return daysInMonth

    }



    def ab()
    {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int m = localCalendar.get(Calendar.MONTH) + 1;
        render m

    }

}
