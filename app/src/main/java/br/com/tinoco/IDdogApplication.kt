package br.com.tinoco

import android.app.Application
import br.com.tinoco.di.appModule
import br.com.tinoco.di.remoteDatasourceModule
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import io.paperdb.Paper
import org.koin.android.ext.android.startKoin

class IDdogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, remoteDatasourceModule))
        Fabric.with(this, Crashlytics())
        Paper.init(this)
    }
}