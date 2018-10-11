package app.mumandroidproject.hot

import app.mumandroidproject.model.WallpaperModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(WallpaperModelModule::class, HotPresenterModule::class))
interface HotPresenterComponent {

    fun inject(hotFragment: HotFragment)

}