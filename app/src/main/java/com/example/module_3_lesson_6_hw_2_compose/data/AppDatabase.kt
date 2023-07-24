package com.example.module_3_lesson_6_hw_2_compose.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning.AirConditioningDao
import com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning.AirConditioningItem
import com.example.module_3_lesson_6_hw_2_compose.data.kitchen.KitchenDao
import com.example.module_3_lesson_6_hw_2_compose.data.kitchen.KitchenItem
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.RoomDao
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.RoomItem
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsDao
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsItem
import com.example.module_3_lesson_6_hw_2_compose.data.tasks.TaskDao
import com.example.module_3_lesson_6_hw_2_compose.data.tasks.TaskItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [RoomItem::class, StatisticsItem::class, KitchenItem::class, TaskItem::class,
        AirConditioningItem::class],
    version = 7,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
    abstract fun statisticsDao(): StatisticsDao
    abstract fun kitchenDao(): KitchenDao
    abstract fun taskDao(): TaskDao
    abstract fun acDao(): AirConditioningDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        // migration_2_3
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
        // migration_3_4
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE kitchen (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "name TEXT NOT NULL, " +
                            "time INTEGER NOT NULL, " +
                            "cookingStatus INTEGER NOT NULL DEFAULT 0)"
                )
            }
        }
        // migration_5_6
        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE tasks (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "taskTitle TEXT NOT NULL, " +
                            "taskText TEXT NOT NULL)"
                )
            }
        }
        // migration_6_7
        private val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE air_conditioning (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "name TEXT NOT NULL, " +
                            "currentTemperature INTEGER NOT NULL, " +
                            "stepTemperature INTEGER NOT NULL)"
                )
            }
        }

        // pre-filled table for Statistics
        private val PREFILLED_DATA = listOf(
            StatisticsItem(1, "Current month", 0.0),
            StatisticsItem(2, "Previous month", 0.0),
            StatisticsItem(3, "Current year", 0.0)
        )
        //pre-filled table for Kitchen
        private val PREFILLED_KITCHEN = KitchenItem(1, "Kitchen", 0, 0, false)
        private val PREFILLED_AC = AirConditioningItem(1, "Office", 0, 1)

        private val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    PREFILLED_DATA.forEach {
                        Instance?.statisticsDao()?.insert(it)
                    }
                    Instance?.kitchenDao()?.insert(PREFILLED_KITCHEN)
                    Instance?.acDao()?.insert(PREFILLED_AC)
                }
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "database")
                    .addCallback(callback)
                    .addMigrations(MIGRATION_2_3, MIGRATION_3_4, MIGRATION_5_6, MIGRATION_6_7)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}