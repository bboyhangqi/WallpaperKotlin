package app.mumandroidproject.model

import app.mumandroidproject.model.WallpaperModel
import dagger.Module
import dagger.Provides

@Module
class WallpaperModelModule {

    @Provides
    fun provideWallpaperModel(): WallpaperModel {
        return WallpaperModel.instance
    }


}