package ru.itmo.fitp.mobile.iterators

import java.io.Serializable

interface NumberIterator: Serializable, Iterator<Int> {
    fun current(): Int
}