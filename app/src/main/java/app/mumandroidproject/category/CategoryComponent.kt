package app.mumandroidproject.category

import dagger.Component

@Component
interface CategoryComponent {

    fun inject(categoryFragment: CategoryFragment)
}