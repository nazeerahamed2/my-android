package com.example.activities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ActivitiesDbModel(var name: String? = null,
                      var value: Double? = 0.0,
                      var valueUnit: String? = null,
                      @PrimaryKey var date: String? = null): RealmObject()