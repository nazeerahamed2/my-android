package com.example.activities

fun saveAndUpdateNewActivitiesToDB(activitiesModel: ActivitiesDbModel)
        = activitiesModel.saveAndUpdateToDB()

fun getActivitiesFromDb(): List<ActivitiesDbModel> = findAllFromDb()