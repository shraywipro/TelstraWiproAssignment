package com.assignment.telstra.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    /**
     * @desc method to get current date
     * @return String date
     */
    fun getCurrentDate(format: String): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat(format)
        return sdf.format(cal.time)
    }

    /**

     * @desc method to get current time
     * @return String time
     */
    fun getCurrentTimeIn12HrsFormat(): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("hh:mm aa")
        return sdf.format(cal.time)
    }

    /**

     * @desc method to get current time
     * @return String time
     */
    fun getCurrentTimeIn24HrsFormat(): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(cal.time)
    }

    fun getTimeDiffernecIn12HrsFormat(startTime: String, endTime: String){

    }

    fun getTimeDiffernecIn24HrsFormat(startTime: String, endTime: String): Int{
        //HH converts hour in 24 hours format (0-23), day calculation
        val format = SimpleDateFormat("HH:mm");

        var d1: Date? = null;
        var d2: Date? = null;

        try {
            d1 = format.parse(startTime);
            d2 = format.parse(endTime);

            //in milliseconds
            val diff = d2.time - d1.time
            val diffMinutes = diff / (60 * 1000) % 60;
            return diffMinutes.toInt()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return 0
    }

    fun getformattedDateFromString(inputFormat: String, outputFormat: String, inputDate: String): String {
        var inputFormat = inputFormat
        var outputFormat = outputFormat
        if (inputFormat == "") { // if inputFormat = "", set a default input format.
            inputFormat = "yyyy-MM-dd hh:mm:ss"
        }
        if (outputFormat == "") {
            outputFormat = "EEEE d 'de' MMMM 'del' yyyy" // if inputFormat = "", set a default output format.
        }
        var parsed: Date? = null
        var outputDate = ""

        val df_input = SimpleDateFormat(inputFormat, java.util.Locale.getDefault())
        val df_output = SimpleDateFormat(outputFormat, java.util.Locale.getDefault())

        try {
            parsed = df_input.parse(inputDate)
            outputDate = df_output.format(parsed)
        } catch (e: Exception) {
            AppLog.debugE("formattedDateFromString", "Exception in formateDateFromstring(): " + e.message)
        }
        return outputDate
    }

    fun getFormattedDate(time: Long, format: String): String{
        val cal = Calendar.getInstance()
        cal.timeInMillis=time
        val sdf=SimpleDateFormat(format)
        return sdf.format(cal.time)
    }

}