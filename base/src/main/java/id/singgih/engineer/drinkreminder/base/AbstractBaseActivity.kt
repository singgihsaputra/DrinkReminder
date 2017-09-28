package id.singgih.engineer.drinkreminder.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by singgihrs on 9/27/17.
 */

abstract class AbstractBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setup()

    }

    abstract fun getLayout(): Int

    abstract fun setup()
}

