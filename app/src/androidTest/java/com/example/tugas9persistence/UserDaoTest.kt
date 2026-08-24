package com.example.tugas9persistence

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tugas9persistence.data.AppDatabase
import com.example.tugas9persistence.data.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var database: AppDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertAndReadUser() = runBlocking {
        val user = User(name = "Galih", email = "galih@example.com")
        database.userDao().insertUser(user)
        val users = database.userDao().getAllUsers().first()
        assertEquals(1, users.size)
        assertEquals("Galih", users.first().name)
        assertEquals("galih@example.com", users.first().email)
    }
}
