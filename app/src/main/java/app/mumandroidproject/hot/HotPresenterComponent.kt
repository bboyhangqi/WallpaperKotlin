package app.mumandroidproject.hot

import app.mumandroidproject.model.WallpaperModelModule
import dagger.Component

@Component(modules = arrayOf(WallpaperModelModule::class, HotViewModule::class))
interface HotPresenterComponent {

    fun inject(hotFragment: HotFragment)

}