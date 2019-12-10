package userdemoscreens

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import apirelated.CallApi
import apirelated.Url
import apirelated.login_register_moel.LoginRegisterResponse
import com.anagha.dabbamen.R
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utilities.WebCall
import java.util.concurrent.TimeUnit

class SignUp : AppCompatActivity(), View.OnClickListener {
    var _context: Context = this@SignUp
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signupBT -> {

                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.activity_animation_right_to_left,
                    R.anim.right_to_left
                );

                /* if (!validations(
                         mUserNameLET.editText.toString(),
                         mUserEmailLET.editText.toString(),
                         mUserPhoneLET.editText.toString(),
                         mUserPasswordLET.editText.toString(),
                         mUserConfirmPasswordLET.editText.toString()
                     )
                 ) {
                     if (NetworkConnectionCheck.checkInternetConnection(_context)) {
                         userSignUp(
                             mUserNameLET.editText.toString(), mUserEmailLET.editText.toString(),
                             mUserPhoneLET.editText.toString(),
                             mUserConfirmPasswordLET.editText.toString()
                         );
                     } else {
                         WebCall(_context).DialogForWifi_Enable_CloseDialog(
                             _context.getString(R.string.internet_enable),
                             _context.getString(R.string.internet_enable_message),
                             R.drawable.warning_red
                         );
                     }
                 }*/
            }
            R.id.link_login -> {
                val intent = Intent(this, SignIn::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.activity_animation_right_to_left,
                    R.anim.right_to_left
                ); }
            R.id.link_terms_cnd -> {
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.activity_animation_right_to_left,
                    R.anim.right_to_left
                );
            }
        }
    }

    private fun validations(
        username: String,
        useremail: String,
        userphone: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (TextUtils.isEmpty(username)) {
            WebCall(_context).EmptyDialog("Required", "Enter UserName", R.drawable.warning_red);
            return true;
        } else if (TextUtils.isEmpty(useremail)) {
            WebCall(_context).EmptyDialog("Required", "Enter Email", R.drawable.warning_red);
            return true;
        } else if (!WebCall.isEmailValid(useremail)) {
            WebCall(_context).EmptyDialog("Required", "Enter Valid Email", R.drawable.warning_red);
            return true;
        } else if (TextUtils.isEmpty(userphone)) {
            WebCall(_context).EmptyDialog("Required", "Enter Phone No", R.drawable.warning_red);
            return true;
        } else if (TextUtils.isEmpty(password)) {
            WebCall(_context).EmptyDialog("Required", "Enter Password", R.drawable.warning_red);
            return true;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            WebCall(_context).EmptyDialog(
                "Required",
                "Enter Confirm Password",
                R.drawable.warning_red
            );
            return true;
        } else if (!password?.equals(confirmPassword)!!) {
            WebCall(_context).EmptyDialog(
                "Required",
                "Enter Confirm Password",
                R.drawable.warning_red
            );
            return true;
        }
        return false;
    }


    private fun userSignUp(
        username: String,
        useremail: String,
        usermobile: String,
        userpassword: String
    ) {
        val progressDialog = ProgressDialog(_context)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
        //var alMyAndroidOS = ArrayList<Data>()
        //alMyAndroidOS.clear()
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
        val call = service.registerPost(
            username, useremail, usermobile, userpassword, "3"
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

    /*  lateinit var mUserNameLET: TextInputLayout
      lateinit var mUserEmailLET: TextInputLayout
      lateinit var mUserPhoneLET: TextInputLayout
      lateinit var mUserPasswordLET: TextInputLayout
      lateinit var mUserConfirmPasswordLET: TextInputLayout*/
    lateinit var mSignUpBT: Button
    lateinit var mlinkSignInTV: TextView
    lateinit var mlinkTermsConditiaons: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        initUI()
        uiListener()
    }

    private fun uiListener() {
        mSignUpBT.setOnClickListener(this)
        mlinkSignInTV.setOnClickListener(this)
        mlinkTermsConditiaons.setOnClickListener(this)

    }

    private fun initUI() {
        /* this.mUserNameLET = findViewById<TextInputLayout>(R.id.usernameLT)
         this.mUserEmailLET = findViewById<TextInputLayout>(R.id.useremailLT)
         this.mUserPhoneLET = findViewById<TextInputLayout>(R.id.mobileLT)
         this.mUserPasswordLET = findViewById<TextInputLayout>(R.id.passwordLT)
         this.mUserConfirmPasswordLET = findViewById<TextInputLayout>(R.id.confirmPasswordLT)*/
        this.mSignUpBT = findViewById<Button>(R.id.signupBT)
        this.mlinkSignInTV = findViewById<TextView>(R.id.link_login)
        this.mlinkTermsConditiaons = findViewById<TextView>(R.id.link_terms_cnd)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.activity_animation_right_to_left,
            R.anim.right_to_left
        );

    }
}