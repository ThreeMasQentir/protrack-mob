package org.gspi.protrack.common.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class UserPreferences(private val dataStore: DataStore<Preferences>) {

    private val tokenFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    private val nameFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[NAME_KEY]
    }

    private val phoneFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PHONE_KEY]
    }

    private val listRoleFlow: Flow<List<String>?> = dataStore.data.map { preferences ->
        preferences[LIST_ROLE_KEY]?.let { json ->
            json.split(",").map { it.trim() }
        }
    }

    // Getters
    fun getToken(): String? = runBlocking { tokenFlow.firstOrNull() }
    fun getName(): String? = runBlocking { nameFlow.firstOrNull() }
    fun getPhone(): String? = runBlocking { phoneFlow.firstOrNull() }
    fun getListRole(): List<String>? = runBlocking { listRoleFlow.firstOrNull() }

    // Save methods
    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun saveName(name: String) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
        }
    }

    suspend fun savePhone(phone: String) {
        dataStore.edit { preferences ->
            preferences[PHONE_KEY] = phone
        }
    }

    suspend fun saveListRole(listRole: List<String>) {
        dataStore.edit { preferences ->
            preferences[LIST_ROLE_KEY] = listRole.joinToString(",")
        }
    }

    // Clear methods
    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    suspend fun clearName() {
        dataStore.edit { preferences ->
            preferences.remove(NAME_KEY)
        }
    }

    suspend fun clearPhone() {
        dataStore.edit { preferences ->
            preferences.remove(PHONE_KEY)
        }
    }

    suspend fun clearListRole() {
        dataStore.edit { preferences ->
            preferences.remove(LIST_ROLE_KEY)
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val NAME_KEY = stringPreferencesKey("name")
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val LIST_ROLE_KEY = stringPreferencesKey("list_role") // Stored as a comma-separated string
    }
}