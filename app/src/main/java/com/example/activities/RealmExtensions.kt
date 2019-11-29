package com.example.activities

import io.realm.Realm
import io.realm.RealmObject

fun <T : RealmObject> T.saveAndUpdateToDB() {
    getDefaultRealm().transaction { realm ->
        realm.insertOrUpdate(this)
    }
}

inline fun <reified T : RealmObject> findAllFromDb(): List<T> {
    getDefaultRealm().use { realm ->
        return realm.copyFromRealm<T>(realm.where(T::class.java).findAll())
    }
}

fun getDefaultRealm(): Realm = Realm.getDefaultInstance()

fun Realm.transaction(realm: (Realm) -> Unit) = use { executeTransaction { realm(this) } }
