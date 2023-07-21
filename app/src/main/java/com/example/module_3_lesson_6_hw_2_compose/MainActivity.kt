package com.example.module_3_lesson_6_hw_2_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.module_3_lesson_6_hw_2_compose.model.App
import com.example.module_3_lesson_6_hw_2_compose.model.LightOfRoom
import com.example.module_3_lesson_6_hw_2_compose.ui.MyApp
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Module_3_Lesson_6_hw_2_ComposeTheme
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Module_3_Lesson_6_hw_2_ComposeTheme {
                MyApp()
            }
        }

        var db = App.instance.getDatabase()
        var lightOfRoomDao = db.getLightOfRoomDao()

        var lightOfRoom = LightOfRoom("Office", true)

        lightOfRoomDao.insert(lightOfRoom)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("MYLOG", "success") },
                { error -> Log.d("MYLOG", error.message.toString()) }
            )

        lightOfRoomDao.getAllLightOfRoom()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { lights ->
                lights.forEach { kek ->
                    Log.d("MYLOG", kek.room)
                    Log.d("MYLOG", kek.turnedOn.toString())

                }
                Log.d("MYLOG", "1 | ${lights.toString()}")
            }

        lightOfRoomDao.getAllTurnedOn()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { turnedOn ->
                Log.d("MYLOG", "2 | ${turnedOn.toString()}")
            }

        lightOfRoomDao.getByRoom("Kitchen")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { room ->
                Log.d("MYLOG", "3 | ${room.toString()}")
            }

        val office = lightOfRoomDao.getByRoom("Office")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { room ->
                Log.d("MYLOG", "4 | ${room.toString()}")
                Log.d("MYLOG", "4 | ${room.room} ${room.turnedOn}")
            }

    }
}