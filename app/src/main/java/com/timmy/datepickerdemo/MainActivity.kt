package com.timmy.datepickerdemo

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_show).setOnClickListener {
            DatePickerDialog(this@MainActivity).run {

                datePicker.setOnDateChangedListener { datePicker, year, monthOfYear, dayOfMonth ->
                    datePicker.minDate = 0L
                    datePicker.minDate = "$year/${monthOfYear + 1}/$dayOfMonth".toDate("yyyy/M/dd")?.time ?: 0L
                }

                setOnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    this@MainActivity.findViewById<TextView>(R.id.tv_result).text = "Selected Date is : " + "$year/${monthOfYear + 1}/$dayOfMonth"
                }

                show()
            }

        }
    }
}

fun String.toDate(format: String): Date? {
    try {
        return SimpleDateFormat(format, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}