package com.example.studyguardian

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class MyForegroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Lógica de la rutina en segundo plano
        var isRunning = true
        Log.d("MyForegroundService", "Iniciando la rutina en segundo plano...")
        thread {
            while (isRunning) {
                // Aquí colocas la rutina que deseas ejecutar repetidamente
                // cada vez que se cumpla el intervalo de tiempo
                val runningApps = getRunningApplications(this.applicationContext)
                for (appName in runningApps) {
                    Log.d("Running App", appName)
                    if (appName == "StudyGuardian") {
                        val tmp = Intent(this.applicationContext, OtherActivity::class.java)
                        tmp.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(tmp)
                        isRunning = false
                    }
                }
                // Imprimir en la consola
                println("Ejecutando rutina cada 1 segundo")

                // Pausar la ejecución durante 1 segundo
                Thread.sleep(1000)
            }
        }

        return START_STICKY // Para que el servicio se reinicie automáticamente si es cerrado por el sistema
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}