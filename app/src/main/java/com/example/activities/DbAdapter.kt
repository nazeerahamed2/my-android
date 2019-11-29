package com.example.activities

fun saveAndUpdateNewActivitiesToDB(activitiesModel: ActivitiesDbModel)
        = activitiesModel.saveAndUpdateToDB()

fun getActivitiesFromDb(): List<ActivitiesDbModel> = findAllFromDb()

fun saveAndUpdateReminderTimeToDB(reminderTime: Long) = ReminderDbModel(reminderTime).saveAndUpdateToDB()

fun getReminderTimeFromDb() : Long = findFirstFromDB<ReminderDbModel>()?.reminderTime ?: 0

