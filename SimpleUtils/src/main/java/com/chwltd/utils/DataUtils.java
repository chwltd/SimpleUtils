package com.chwltd.utils;

import android.annotation.SuppressLint;
import android.icu.util.Calendar;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    @SuppressLint("SimpleDateFormat")
    public static String processTime(String inputTime) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = inputFormat.parse(inputTime);

            SimpleDateFormat outputFormat;
            String output;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(new Date());

            if (calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                    calendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)) {
                outputFormat = new SimpleDateFormat("HH:mm");
                output = outputFormat.format(date);
            } else if (calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                    calendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR) - 1) {
                outputFormat = new SimpleDateFormat("昨天 HH:mm");
                output = outputFormat.format(date);
            } else if (calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                    currentCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR) <= 7) {
                outputFormat = new SimpleDateFormat("EEEE");
                output = outputFormat.format(date);
            } else if (calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) {
                outputFormat = new SimpleDateFormat("MM/dd");
                output = outputFormat.format(date);
            } else {
                outputFormat = new SimpleDateFormat("yyyy/MM/dd");
                output = outputFormat.format(date);
            }

            return output;
        } catch (ParseException e) {
            e.printStackTrace();
            return inputTime;
        }
    }



    public static boolean isJson(String jsonString) {
        try (JsonReader jsonReader = new JsonReader(new StringReader(jsonString))) {
            JsonToken token = jsonReader.peek();
            return token != JsonToken.END_DOCUMENT;
        } catch (Exception e) {
            return false;
        }
    }
}
