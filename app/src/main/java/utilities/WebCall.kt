package utilities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Patterns

import com.anagha.dabbamen.R

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by harsha on 4/9/2018.
 */

class WebCall(private val _context: Context) {


    fun DialogForWifi_Enable_CloseDialog(alertTitle: String, alertDescription: String, icon: Int) {
        val builder = AlertDialog.Builder(_context, R.style.AppCompatAlertDialogStyle)
        builder.setCancelable(false)
        builder.setTitle(alertTitle).setMessage(alertDescription).setIcon(icon)
            .setPositiveButton("Ok") { dialog, which -> }.show()
    }

    fun EmptyDialog(alertTitle: String, alertDescription: String, icon: Int) {
        val builder = AlertDialog.Builder(_context, R.style.AppCompatAlertDialogStyle)
        builder.setCancelable(false)
        builder.setTitle(alertTitle).setMessage(alertDescription).setIcon(icon)
            .setPositiveButton("Ok") { dialog, which -> }
            .show()
    }

    object Config {
        val DEVELOPER_MODE = false
    }

    companion object {
        val SharedPreference_Name = "astro_details"


        fun isEmailValid(email: String): Boolean {
            //abc@sad.com
            val pattern = Patterns.EMAIL_ADDRESS
            return pattern.matcher(email).matches()


            /*String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();*/
        }


        fun isPasswordValid(password: String): Boolean {
            var isPasswordValid = false
            //Minimum 6 characters at least 1 Alphabet, 1 Number and 1 Special Character:
            ///^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};:"\\|,.<>\/?]{6,16}$/
            // String passwordRegularExpression = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$";

            val expression = "((?=.*\\d)(?=.*[A-Za-z])(?=.*[0-9]).{6,20})"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(password)
            if (matcher.matches()) {
                isPasswordValid = true
            }
            return isPasswordValid
        }
    }

}
