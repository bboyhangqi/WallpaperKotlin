package app.mumandroidproject.category

import dagger.Module
import dagger.Provides

@Module
class CategoryViewModule constructor(private var categoryView: CategoryView) {

    @Provides
    fun provideCategoryView():CategoryView{
        return categoryView
    }

}