package id.singgih.engineer.drinkreminder.feature

import android.text.Editable
import android.view.View
import android.widget.Toast
import id.singgih.engineer.drinkreminder.base.AbstractBaseActivity
import id.singgih.engineer.drinkreminder.base.DefaultTextWatcher
import id.singgih.engineer.drinkreminder.feature.data.DataPreferences
import kotlinx.android.synthetic.main.activity_drink_reminder.*

class DrinkReminderActivity : AbstractBaseActivity() {

    private var drinkingTimes = 0

    private var drinkingTimeTemps = drinkingTimes

    private lateinit var dataPreferences: DataPreferences

    private lateinit var reminderHelper: ReminderHelper

    override fun getLayout(): Int = R.layout.activity_drink_reminder

    override fun setup() {
        listenInput()
        initData()
        reminderHelper = ReminderHelper(this)
    }

    private fun initData() {
        dataPreferences = DataPreferences(this)
        drinkingTimes = dataPreferences.drinkingTimes
        drinkingTimeTemps = drinkingTimes
        setDrinkingTimes()
    }

    private fun listenInput() {
        iv_minus.setOnClickListener { decreaseTime() }
        iv_plus.setOnClickListener { increaseTime() }
        btn_set_timer.setOnClickListener { applyCalculatedTimer() }
        btn_remove_timer.setOnClickListener { removeTimer() }
        btn_cancel.setOnClickListener { cancel() }
        tv_drinking_times.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(editable: Editable?) {
                if (drinkingTimeTemps == 0) {
                    iv_minus.isClickable = false
                    iv_minus.setImageResource(R.drawable.ic_minus_disabled)
                    btn_set_timer.visibility = View.GONE
                    btn_remove_timer.visibility = View.VISIBLE
                } else {
                    iv_minus.isClickable = true
                    iv_minus.setImageResource(R.drawable.ic_minus)
                    btn_set_timer.visibility = View.VISIBLE
                    btn_remove_timer.visibility = View.GONE
                }
            }
        })
    }

    private fun decreaseTime() {
        drinkingTimeTemps--
        setDrinkingTimes()
    }

    private fun increaseTime() {
        drinkingTimeTemps++
        setDrinkingTimes()
    }

    private fun setDrinkingTimes() {
        tv_drinking_times.text = String.format(getString(R.string.drinking_times), drinkingTimeTemps)
        if (drinkingTimes != drinkingTimeTemps) {
            ll_action_container.visibility = View.VISIBLE
        }else{
            ll_action_container.visibility = View.GONE
        }
    }

    private fun applyCalculatedTimer() {
        refreshTimer()
        reminderHelper.createNotification()
    }

    private fun removeTimer() {
        refreshTimer()
    }

    private fun refreshTimer() {
        drinkingTimes = drinkingTimeTemps
        dataPreferences.drinkingTimes = drinkingTimes
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
        ll_action_container.visibility = View.GONE
    }

    private fun cancel() {
        drinkingTimeTemps = drinkingTimes
        setDrinkingTimes()
    }
}
