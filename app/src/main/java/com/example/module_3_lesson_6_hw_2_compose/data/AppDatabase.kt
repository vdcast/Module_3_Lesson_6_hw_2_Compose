package com.example.module_3_lesson_6_hw_2_compose.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.RoomDao
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.RoomItem
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsDao
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [RoomItem::class, StatisticsItem::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
    abstract fun statisticsDao(): StatisticsDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        // migration
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE statistics (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "name TEXT NOT NULL, " +
                            "value REAL NOT NULL)"
                )
            }
        }

        // pre-filled table
        private val PREFILLED_DATA = listOf(
            StatisticsItem(1, "Current month", 0.0),
            StatisticsItem(2, "Previous month", 0.0),
            StatisticsItem(3, "Current year", 0.0)
        )
        private val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    PREFILLED_DATA.forEach {
                        Instance?.statisticsDao()?.insert(it)
                    }
                }
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "database")
                    .addCallback(callback)
                    .addMigrations(MIGRATION_2_3)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}