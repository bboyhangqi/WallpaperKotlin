package app.mumandroidproject.hot

import app.mumandroidproject.hot.HotView
import dagger.Module
import dagger.Provides


@Module
class HotViewModule constructor(private val hotView: HotView) {

    @Provides
    fun getHotView(): HotView {
        return hotView
    }

}