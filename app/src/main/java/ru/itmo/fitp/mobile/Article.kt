package ru.itmo.fitp.mobile

data class Article(val title: String, val description: String, val type: Category) {
    companion object {
        fun dummyArticles(): ArrayList<Article> {
            val result = ArrayList<Article>()
            result.add(Article("Hello World", "This is a test article", Category.PROGRAMMING))
            result.add(Article("Hello Universe", "Another article for testing purposes", Category.DATABASE))
            result.add(Article("Hello Earth", "Last one", Category.ANALYTICS))
            return result
        }
    }
}