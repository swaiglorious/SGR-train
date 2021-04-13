package com.example.sgr_train


import adapters.AllTicketsAdapters
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sgr_train.databinding.ActivityAllticketsBinding
import models.Ticket
import org.json.JSONArray
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
private lateinit var binding: ActivityAllticketsBinding
val tTicketDetails =  ArrayList<Ticket>()

class alltickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAllticketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tRecyclerView = binding.alltickets
        tRecyclerView.setHasFixedSize(true)
        tRecyclerView.layoutManager = LinearLayoutManager(this)

        MyAsyncTask(applicationContext).execute()
    }
    class MyAsyncTask internal constructor(context: Context) : AsyncTask<String, String, String>() {
        lateinit var con: HttpURLConnection
        lateinit var resulta:String
        private val builder = Uri.Builder()
        private val cont: Context =context
        val tRecyclerView = binding.alltickets
        override fun onPreExecute() {
            super.onPreExecute()


            progressBar.isIndeterminate=true
            progressBar.visibility= View.VISIBLE
           // builder .appendQueryParameter("phone", "25556613148")
           // builder .appendQueryParameter("password", "money")
            //builder .appendQueryParameter("key", "0000")
            Log.e("pass 1", "started")
        }

        override fun doInBackground(vararg params: String?):  String? {
            try {

                var query = builder.build().encodedQuery
                val url: String = "http://sgrtrain.000webhostapp.com/sgrtickets/alltickets.php"
                val obj = URL(url)
                con = obj.openConnection() as HttpURLConnection
                con.setRequestMethod("GET")
                con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)")
                con.setRequestProperty("Accept-Language", "UTF-8")
                con.setDoOutput(true)
                val outputStreamWriter = OutputStreamWriter(con.getOutputStream())
                outputStreamWriter.write(query)
                outputStreamWriter.flush()
                Log.e("pass 1", "connection success ")
            } catch (e: Exception) {
                Log.e("Fail 1", e.toString())
            }
            try {
                resulta = con.inputStream.bufferedReader().readText()
                Log.e("data", resulta)
            } catch (e: java.lang.Exception) {
                Log.e("Fail 2", e.toString())
            }

            return "";
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressBar.visibility= View.GONE
            var json_data = JSONArray(resulta)

            for (i in 0 until json_data.length()) {
                val jsonObject = json_data.getJSONObject(i)
                val name = jsonObject.optString("name")
                val ticketNumber = jsonObject.optString("ticket_number")
                val source = jsonObject.optString("source")
                val destination = jsonObject.optString("destination")
                tTicketDetails.add(Ticket(name, ticketNumber,source, destination))
            }
            tRecyclerView.adapter = AllTicketsAdapters(tTicketDetails)
            Log.e("data", json_data.toString())

            //val code: Int = json_.getInt("code")


        }
    }
}