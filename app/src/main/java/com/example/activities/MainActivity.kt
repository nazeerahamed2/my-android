package com.example.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var isFabOpen = false
    private val listAdapter: ListAdapter by lazy { ListAdapter(this, ArrayList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setListeners()
        setupList()

    }

    private fun setListeners() {
        uiFabHome.setOnClickListener {
            if(!isFabOpen)
                showFabMenuWithAnimation()
            else closeFabMenuWithAnimation()
        }
        uiFabHydration.setOnClickListener {
            showDialogToGetInput(getString(R.string.str_name_hydration), getString(R.string.str_value_ml))
        }
        uiFabMeasure.setOnClickListener {
            showDialogToGetInput(getString(R.string.str_name_weight), getString(R.string.str_value_kg))
        }
        uiFabMeals.setOnClickListener {
            showDialogToGetInput(getString(R.string.str_name_meal), getString(R.string.str_value_cal))
        }
        uiFabWorkout.setOnClickListener {
            showDialogToGetInput(getString(R.string.str_name_workout), getString(R.string.str_value_mins))
        }
        uiFabSleep.setOnClickListener {
            showDialogToGetInput(getString(R.string.str_name_sleep), getString(R.string.str_value_mins))
        }
    }

    private fun setupList() {
        uiRvActivities.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = listAdapter
        }
        listAdapter.activitiesList = getActivitiesFromDb().toMutableList()
        listAdapter.notifyDataSetChanged()
    }

    @SuppressLint("InflateParams")
    private fun showDialogToGetInput(name: String, valueUnit: String) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle(getString(R.string.str_create_new))
        val dialogLayout = inflater.inflate(R.layout.dailog_new_activity, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.uiEtInput)
        builder.setView(dialogLayout)
        builder.setPositiveButton("ADD") { _, _ ->
            if (editText.text.trim().isNotBlank()) {
                saveAndUpdateNewActivitiesToDB(ActivitiesDbModel(
                    name = name,
                    value = editText.text.toString().toDouble(),
                    valueUnit = valueUnit,
                    date = System.currentTimeMillis().toString()))
                listAdapter.activitiesList = getActivitiesFromDb().toMutableList()
                listAdapter.notifyDataSetChanged()
            }
            if(name == getString(R.string.str_name_hydration))
                saveAndUpdateReminderTimeToDB(System.currentTimeMillis() + (60*60*1000))
        }
        builder.show()
    }

    private fun showFabMenuWithAnimation() {
        isFabOpen = true
        uiFabHydration.animate().translationY(-resources.getDimension(R.dimen.standard_55))
        uiFabMeasure.animate().translationY(-resources.getDimension(R.dimen.standard_105))
        uiFabMeals.animate().translationY(-resources.getDimension(R.dimen.standard_155))
        uiFabWorkout.animate().translationY(-resources.getDimension(R.dimen.standard_205))
        uiFabSleep.animate().translationY(-resources.getDimension(R.dimen.standard_255))
    }

    private fun closeFabMenuWithAnimation(){
        isFabOpen = false
        uiFabHydration.animate().translationY(0.toFloat())
        uiFabMeasure.animate().translationY(0.toFloat())
        uiFabMeals.animate().translationY(0.toFloat())
        uiFabWorkout.animate().translationY(0.toFloat())
        uiFabSleep.animate().translationY(0.toFloat())
    }

}
