package com.example.stadiumcommentapp.ui.login

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stadiumcommentapp.MainActivity
import com.example.stadiumcommentapp.R
import com.example.stadiumcommentapp.databinding.ActivityLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        naverLogin()
    }

    fun naverLogin() {
        var mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(this, CLIENT_ID, CLIENT_SECRET, CLIENT_NAME)

        val mOAuthLoginHandler: OAuthLoginHandler = @SuppressLint("HandlerLeak")
        object : OAuthLoginHandler() {
            override fun run(success: Boolean) {
                if (success) {
                    val accessToken = mOAuthLoginInstance.getAccessToken(applicationContext)

                    Thread {
                        val header = "Bearer $accessToken"
                        val requestHeaders: MutableMap<String, String> = HashMap()
                        requestHeaders["Authorization"] = header

                        val apiUrl = "https://openapi.naver.com/v1/nid/me"
                        val responseBody: String =
                            NaverLogin().get(apiUrl, requestHeaders).toString()
                        Log.d(TAG, responseBody)
                        naverLoginParser(responseBody)
                    }.start()

                    Log.d("RESULT", "start login thread!")
                } else {
                    val errorCode = mOAuthLoginInstance.getLastErrorCode(applicationContext).code
                    val errorDesc = mOAuthLoginInstance.getLastErrorDesc(applicationContext)

                    Toast.makeText(
                        baseContext,
                        "errorCode: {$errorCode}, errorDesc: {$errorDesc}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.naverLoginBtn.setOAuthLoginHandler(mOAuthLoginHandler)
    }

    fun naverLoginParser(msg: String) {
        Log.d("PARSING", "message parsing: {$msg}")

        val jObject = JSONObject(msg)

        val resultCode = jObject.get("resultcode").toString()
        val message = jObject.get("message").toString()
        val resultJson = jObject.get("response") as JSONObject

        if (resultCode == "00") {
            if (message == "success") {
                val id = resultJson.get("id").toString()
                val email = resultJson.get("email").toString()
                val name = resultJson.get("name").toString()
                val profile = resultJson.get("profile_image").toString()

                Log.d(TAG, id)
                Log.d(TAG, email)
                Log.d(TAG, name)
                Log.d(TAG, profile)

                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "네이버 로그인에 성공했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "네이버 로그인에 실패했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            runOnUiThread {
                Toast.makeText(applicationContext, "네이버 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        val CLIENT_ID = "csUipOBxXoQLhzBS3PJz"
        val CLIENT_SECRET = "BZMPLjHRkZ"
        val CLIENT_NAME = "왓유씨_what you see"
    }
}