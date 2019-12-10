package userdemoscreens

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apirelated.CallApi
import apirelated.Url
import apirelated.login_register_moel.LoginRegisterResponse
import com.anagha.dabbamen.R
import com.google.android.material.textfield.TextInputLayout
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utilities.WebCall
import java.util.concurrent.TimeUnit


class SignIn : AppCompatActivity(), View.OnClickListener {
    var _context: Context = this@SignIn
    //lateinit var mUserEmailET: TextInputLayout
    //lateinit var mUserPasswordET: TextInputLayout
    lateinit var mSignInBT: Button
    lateinit var mlinkSignupTV: TextView
    lateinit var mlinkForgotPasswordTV: TextView
    lateinit var inputManager: InputMethodManager
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signInBT -> {

                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.activity_animation_right_to_left,
                    R.anim.right_to_left
                );

                /*  if (!validations(
                          mUserEmailET.editText.toString(),
                          mUserPasswordET.editText.toString()
                      )
                  ) {
                      if (NetworkConnectionCheck.checkInternetConnection(_context)) this.userSignIn(
                          mUserEmailET.editText.toString(),
                          mUserPasswordET.editText.toString()
                      ) else {
                          WebCall(_context).DialogForWifi_Enable_CloseDialog(
                              _context.getString(R.string.internet_enable),
                              _context.getString(R.string.internet_enable_message),
                              R.drawable.warning_red
                          );
                      }
                  }*/
                /*   inputManager = (InputMethodManager)
                   getSystemService(Context.INPUT_METHOD_SERVICE);
                   inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                       InputMethodManager.HIDE_NOT_ALWAYS);*/


            }
            R.id.link_signupTV -> {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.activity_animation_right_to_left,
                    R.anim.right_to_left
                );

            }
            R.id.link_forgot_passwordTV -> {
                val intent = Intent(this, ForgotPassword::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.activity_animation_right_to_left,
                    R.anim.right_to_left
                );
            }
        }
    }

    private fun userSignIn(
        username: String,
        password: String
    ) {

        val progressDialog = ProgressDialog(_context)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        //building retrofit object
        val retrofit = Retrofit.Builder()
            .baseUrl(Url.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Defining retrofit api service
        val service = retrofit.create(CallApi::class.java!!)
        //defining the call
        val call = service.loginPost(
            username, password, "3"
        )
        //calling the api
        call.enqueue(object : Callback<LoginRegisterResponse> {
            override fun onResponse(
                call: Call<LoginRegisterResponse>,
                response: Response<LoginRegisterResponse>
            ) {
                //hiding progress dialog
                progressDialog.dismiss()
                val itemsData = response.body()
                if (itemsData != null) {

                }
            }

            override fun onFailure(call: Call<LoginRegisterResponse>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(
                    _context!!.applicationContext,
                    "error :" + t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }


    private fun validations(emailUserId: String, password: String): Boolean {
        if (TextUtils.isEmpty(emailUserId)) {
            WebCall(_context).EmptyDialog("Required", "Enter Email", R.drawable.warning_red);
            return true;
        }
        if (!WebCall.isEmailValid(emailUserId)) {
            WebCall(_context).EmptyDialog("Required", "Enter Valid Email", R.drawable.warning_red);
            return true;
        } else if (TextUtils.isEmpty(password)) {
            WebCall(_context).EmptyDialog("Required", "Enter Password", R.drawable.warning_red);
            return true;
        }
        return false;

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        initUI()
        uiListener()
    }

    private fun uiListener() {
        mSignInBT.setOnClickListener(this)
        mlinkSignupTV.setOnClickListener(this)
        mlinkForgotPasswordTV.setOnClickListener(this)
    }

    private fun initUI() {
       // this.mUserEmailET = findViewById<TextInputLayout>(R.id.useremailLT)
       // this.mUserPasswordET = findViewById<TextInputLayout>(R.id.passwordLT)
        this.mSignInBT = findViewById<Button>(R.id.signInBT)
        this.mlinkSignupTV = findViewById<TextView>(R.id.link_signupTV)
        this.mlinkForgotPasswordTV = findViewById<TextView>(R.id.link_forgot_passwordTV)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.activity_animation_right_to_left,
            R.anim.right_to_left
        );
    }
}