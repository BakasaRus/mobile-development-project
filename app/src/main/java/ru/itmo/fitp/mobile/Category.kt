package ru.itmo.fitp.mobile

enum class Category {
    PROGRAMMING {
        override fun getIcon() = R.drawable.ic_round_developer_mode_24
    },

    DATABASE {
        override fun getIcon() = R.drawable.ic_round_table_rows_24
    },

    ANALYTICS {
        override fun getIcon() = R.drawable.ic_round_bar_chart_24
    };

    abstract fun getIcon(): Int
}