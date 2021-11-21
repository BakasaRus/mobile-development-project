package ru.itmo.fitp.mobile.iterators

import kotlin.random.Random

class CollatzIterator : NumberIterator {
    private var term = Random(System.currentTimeMillis()).nextInt() % 500 + 500

    private val iterator = iterator {
        while (true) {
            yield(term)
            term = if (term % 2 == 0) {
                term / 2
            } else {
                term * 3 + 1
            }
        }
    }

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): Int {
        return iterator.next()
    }

    override fun current() = term
}