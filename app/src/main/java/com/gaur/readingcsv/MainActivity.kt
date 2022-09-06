package com.gaur.readingcsv

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readCSVFile()

    }

    private fun readCSVFile() {
        val textView = findViewById<TextView>(R.id.tvCsv)
        val bufferReader = BufferedReader(assets.open("cities.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT
        )
        val list= mutableListOf<Cities>()
        csvParser.forEach {
            it?.let {
              val cities=  Cities(
                    city=it.get(0),
                    lat=it.get(1),
                    lng = it.get(2),
                    country = it.get(3),
                    iso2 = it.get(4),
                    admin_name = it.get(5),
                    capital = it.get(6),
                    population=it.get(7),
                    population_proper = it.get(8)
                )
                list.add(cities)
            }
        }
        list.forEach {
            textView.append(
                "${it.city} ${it.iso2} ${it.admin_name}\n\n"
            )
        }

    }


}