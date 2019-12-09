package userdemoscreens

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.anagha.dabbamen.R
import com.google.android.material.textfield.TextInputLayout

class ChangePassword : AppCompatActivity(), View.OnClickListener {
    var _context: Context = this@ChangePassword


    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    lateinit var mUserPasswordLET: TextInputLayout
    lateinit var mUserNewPasswordLET: TextInputLayout
    lateinit var mUserConfirmNewPasswordLET: TextInputLayout
    lateinit var mChangePasswordBT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changepassword)
        initUI()
        uiListener()
    }

    private fun uiListener() {
        mChangePasswordBT.setOnClickListener(this)
    }

    private fun initUI() {
        this.mUserPasswordLET = findViewById<TextInputLayout>(R.id.passwordLT)
        this.mUserNewPasswordLET = findViewById<TextInputLayout>(R.id.newpasswordLT)
        this.mUserConfirmNewPasswordLET = findViewById<TextInputLayout>(R.id.cnfnewpasswordLT)
        this.mChangePasswordBT = findViewById<Button>(R.id.changePassBT)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.activity_animation_right_to_left,
            R.anim.right_to_left
        );

    }
}