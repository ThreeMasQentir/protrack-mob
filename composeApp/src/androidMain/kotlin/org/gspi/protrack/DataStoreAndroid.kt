package org.gspi.protrack

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.gspi.protrack.core.DATASTORE_FILE_NAME
import org.gspi.protrack.core.createDataStore

fun createDataStore(context: Context): DataStore<Preferences> {
    return createDataStore {
        context.filesDir.resolve(DATASTORE_FILE_NAME).absolutePath
    }
}