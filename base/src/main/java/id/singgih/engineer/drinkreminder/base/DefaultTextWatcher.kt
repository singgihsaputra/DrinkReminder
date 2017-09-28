package id.singgih.engineer.drinkreminder.base

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by singgihrs on 9/28/17.
 */

open class DefaultTextWatcher : TextWatcher {

    override fun afterTextChanged(editable: Editable?) {
    }

    override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(charSquence: CharSequence?, start: Int, before: Int, count: Int) {
    }
}