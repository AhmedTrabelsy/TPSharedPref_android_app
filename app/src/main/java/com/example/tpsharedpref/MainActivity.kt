package com.example.tpsharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var password: TextView
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        val saver: SharedPreference = SharedPreference(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = this.findViewById(R.id.username)
        password = this.findViewById(R.id.password)
        checkBox = this.findViewById(R.id.rememberMeCheckBox)
        username.text = saver.getValueString("Username")
        password.text = saver.getValueString("Password")
        val status = saver.getStatus("CheckBox")
        if(status){
            login()
        }
    }

    override fun onResume() {
        super.onResume()
        val saver: SharedPreference = SharedPreference(this)
        username.text = ""
        password.text = ""
        checkBox.isChecked = false
        saver.clearSharedPreference()
    }

    fun loginAction (view: View) {
        val saver: SharedPreference = SharedPreference(this)
        val status = saver.getStatus("CheckBox")

        if(checkBox.isChecked){
            saver.save("Username", username.text.toString())
            saver.save("Password", username.text.toString())
            saver.save("CheckBox", checkBox.isChecked)
            login()
        }
        else{
            saver.clearSharedPreference()
            login()
        }
    }

    private fun login(){
        var goHome = Intent(this, HomePage::class.java)
        goHome.putExtra("username", username.text.toString())
        startActivity(goHome)
    }



    class SharedPreference(private val context: Context) {
        private val prefsName = "Mon Fishier Shared"
        private val sharedPref: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

        fun save(keyName: String, text:String){
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putString(keyName, text)
            editor.apply()
        }
        fun save(keyName: String, value:Int){
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putInt(keyName, value)
            editor.apply()
        }
        fun save(keyName: String, status:Boolean){
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putBoolean(keyName, status)
            editor.apply()
        }

        fun getValueString(keyName: String): String? {
            return sharedPref.getString(keyName, null)
        }

        fun getValueInt(keyName: String): Int{
            return sharedPref.getInt(keyName, 0)
        }

        fun getStatus(keyName: String): Boolean{
            return sharedPref.getBoolean(keyName, false)
        }

        fun clearSharedPreference(){
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.clear()
            editor.apply()
        }

        fun removeValue(keyName: String){
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.remove(keyName)
            editor.apply()
        }

    }


}