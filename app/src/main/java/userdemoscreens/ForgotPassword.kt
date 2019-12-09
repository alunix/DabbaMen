package userdemoscreens

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anagha.dabbamen.R
import com.google.android.material.textfield.TextInputLayout

class ForgotPassword : AppCompatActivity(), View.OnClickListener {

    var _context: Context = this@ForgotPassword

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




    lateinit var mUserEmailLET: TextInputLayout
    lateinit var mForgotPasswordBT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpassword)
        initUI()
        uiListener()
    }

    private fun uiListener() {
        mForgotPasswordBT.setOnClickListener(this)


    }

    private fun initUI() {

        this.mUserEmailLET = findViewById<TextInputLayout>(R.id.useremailLT)
        this.mForgotPasswordBT = findViewById<Button>(R.id.forgotpasswordBT)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.activity_animation_right_to_left,
            R.anim.right_to_left
        );

    }
}
