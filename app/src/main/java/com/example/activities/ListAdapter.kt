package com.example.activities

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.model_track_itesm.view.*

class ListAdapter(context: Context, var activitiesList: MutableList<ActivitiesDbModel>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var isDialogShown: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(parent.inflate(R.layout.model_track_itesm))

    override fun getItemCount(): Int = activitiesList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val activityItem = activitiesList[position]
        with(holder.itemView) {
            uiTvName.text = activityItem.name
            uiTvDate.text = dateTimeFormat(activityItem.date?.toLong())
            uiTvValue.text = activityItem.value.toString()
            uiTvUnit.text = activityItem.valueUnit
            when (activityItem.name) {
                context.getString(R.string.str_name_hydration) -> {
                    uiIvIcon.setImageResource(R.drawable.ic_hydration)
                    uiIvIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorBlue))
                }
                context.getString(R.string.str_name_weight) -> {
                    uiIvIcon.setImageResource(R.drawable.ic_measurements)
                    uiIvIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorRose))
                }
                context.getString(R.string.str_name_meal) -> {
                    uiIvIcon.setImageResource(R.drawable.ic_meal)
                    uiIvIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorYellow))
                }
                context.getString(R.string.str_name_workout) -> {
                    uiIvIcon.setImageResource(R.drawable.ic_workout)
                    uiIvIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorBlue))
                }
                context.getString(R.string.str_name_sleep) -> {
                    uiIvIcon.setImageResource(R.drawable.ic_sleep)
                    uiIvIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorBlue))
                }
            }
            if (activityItem.name == context.getString(R.string.str_name_hydration)) {
                    if (getReminderTimeFromDb() != 0.toLong() && System.currentTimeMillis() > getReminderTimeFromDb())
                        if(!isDialogShown) showReminderDialog(context)
                }
        }
    }

    private fun showReminderDialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage(context.getString(R.string.str_remind_hydration))
            .setCancelable(false)
            .setPositiveButton("Ok") { dialog, id ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        alert.setTitle(context.getString(R.string.str_reminder))
        alert.show()
        isDialogShown = true
    }

    inner class ListViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}