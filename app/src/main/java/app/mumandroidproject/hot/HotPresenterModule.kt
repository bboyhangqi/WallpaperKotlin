package app.mumandroidproject.hot

import app.mumandroidproject.model.WallpaperModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HotPresenterModule {

    @Provides
    @Singleton
    fun proHotPresenter(wallpaperModel: WallpaperModel): HotPresenter {
        return HotPresenter.getInstance(wallpaperModel)
    }


}