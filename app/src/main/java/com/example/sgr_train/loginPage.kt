package com.example.sgr_train

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.ProgressBar
import com.example.sgr_train.databinding.ActivityLoginPageBinding
import com.example.sgr_train.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection

import java.net.URL
lateinit var progressBar: ProgressBar
class loginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        title="SGRTicket"
        var view = binding.root

        setContentView(view)
        progressBar = binding.progress

        binding.login.setOnClickListener {
            val logInpage = Intent(applicationContext, alltickets::class.java)
            startActivity(logInpage)

            val login=MyAsyncTask(applicationContext)
            login.execute()
        }
        binding.signin.setOnClickListener {
            val signupPage = Intent(applicationContext, signupPage::class.java)
            startActivity(signupPage)

        }
    }

    companion object {
        class MyAsyncTask internal constructor(context: Context) : AsyncTask<String, String, String>() {
            lateinit var con: HttpURLConnection
            lateinit var resulta:String
            private val builder = Uri.Builder()
            private val cont:Context=context
            override fun onPreExecute() {
                super.onPreExecute()


                progressBar.isIndeterminate()
                progressBar.visibility= View.VISIBLE
                builder .appendQueryParameter("phone", "25556613148")
                builder .appendQueryParameter("password", "money")
                builder .appendQueryParameter("key", "0000")
                Log.e("pass 1", "started")
            }

            override fun doInBackground(vararg params: String?):  String? {
                try {

                    var query = builder.build().encodedQuery
                    val url: String = "http://sgrtrain.000webhostapp.com/sgrtickets/login.php"
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

                return null;
            }
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                progressBar.visibility= View.GONE
                var json_data = JSONObject(resulta)
                val code: Int = json_data.getInt("code")
                Log.e("data",code.toString())
                if (code == 1) {
                    //val com: JSONArray = json_data.getJSONArray("userdetails")
                    //val comObject = com[0] as JSONObject
                    //Log.e("data",""+comObject.optString("fname"))

                    val toMain = Intent(cont, alltickets::class.java)
                    toMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    cont.run {
                        startActivity(toMain)
                    }
                }
            }
        }

    }


}