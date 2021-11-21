package ru.itmo.fitp.mobile.iterators

class NaturalIterator: NumberIterator {
    private var term = 0

    private val iterator = iterator {
        while (true) {
            yield(term)
            term++
            if (term < 0)
                term = 0
        }
    }

    override fun next(): Int {
        return iterator.next()
    }

    override fun hasNext(): Boolean {
        return true
    }

    override fun current() = term
}