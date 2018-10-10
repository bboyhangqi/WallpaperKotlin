package app.mumandroidproject.category

import dagger.Component

@Component(modules = arrayOf(CategoryViewModule::class))
interface CategoryComponent {

    fun inject(categoryFragment: CategoryFragment)
}