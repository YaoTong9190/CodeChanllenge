package com.henry.yelp;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class BusinessOpenHour {
    static  class TimeRange {
        /*
        Represents a time range (the time between a start time and an end time)

        Example usage:
            time_range = TimeRange('3-5')
            System.out.println(time_range.start)
            3.0
         */
        float start;
        float end;

        TimeRange(String rangeString){
            String[] rangeSplit = rangeString.split("-");
            this.start = Float.valueOf(rangeSplit[0]);
            this.end = Float.valueOf(rangeSplit[1]);
        }
    }

    /*
   Inputs:
   A time range to query for (as a TimeRange object)
   A business's open hours (as a List of TimeRanges)

   Output:
   The fraction OF THE QUERY TIME RANGE that the business is open.
   (In other words, the percentage of the query time range in which the business is open.)
   Return this number as a float. This function should NOT do any rounding.

   Examples of time ranges:
       (0, 24)        the whole day
       (9, 17)        9 AM to 5 PM
       (18, 23.75)    6 PM to 11:45 PM

   Examples of open hours:
       []                       closed the entire day
       [(0, 24)]                open the entire day
       [(9.5, 17)]              open from 9:30 AM to 5 PM
       [(11, 14), (18, 22)]     open from 11 AM to 2 PM, and from 6 PM to 10 PM

   Assume that the open hours time ranges are in order and non-overlapping.

   Furthermore, all time ranges (start, end) have the property 0 <= start < end <= 24.

   Examples:
   Query Time Range    Open Hours            Answer
   (4, 10)             [(0, 24)]             1.0
   (7, 11)             [(9, 17)]             0.5
   (0, 24)             [(0, 2), (20, 24)]    0.25
   (5, 22)             []                    0.0
   */
    public static float openHoursRatio(TimeRange queryTimeRange, List<TimeRange> openHours) {
        float start = queryTimeRange.start;
        float end = queryTimeRange.end;

        float overlap = 0.0f;
        for (TimeRange timeRange: openHours){
            float overlapStart = Math.max(start, timeRange.start);
            float overlapEnd = Math.min(end, timeRange.end);
            float value = overlapEnd - overlapStart;
            if (value>0){
                overlap+= value;
            }
        }

        return  overlap/(end-start);
    }

    public static void main(String[] args) {
//        TimeRange queryTimeRange = new TimeRange("4-10");
//        List<TimeRange> openHours = new ArrayList<>();
//        TimeRange range1 = new TimeRange("0-24");
//        openHours.add(range1);
//
//        System.out.println(openHoursRatio(queryTimeRange,openHours));

//        TimeRange queryTimeRange = new TimeRange("7-11");
//        List<TimeRange> openHours = new ArrayList<>();
//        TimeRange range1 = new TimeRange("9-17");
//        openHours.add(range1);
//
//        System.out.println(openHoursRatio(queryTimeRange,openHours));

//        TimeRange queryTimeRange = new TimeRange("0-24");
//        List<TimeRange> openHours = new ArrayList<>();
//        TimeRange range1 = new TimeRange("0-2");
//        TimeRange range2 = new TimeRange("20-24");
//        openHours.add(range1);
//        openHours.add(range2);
//
//        System.out.println(openHoursRatio(queryTimeRange,openHours));

        TimeRange queryTimeRange = new TimeRange("5-22");
        List<TimeRange> openHours = new ArrayList<>();

        System.out.println(openHoursRatio(queryTimeRange,openHours));
    }
}
