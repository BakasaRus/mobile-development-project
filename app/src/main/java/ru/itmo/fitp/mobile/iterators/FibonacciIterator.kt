package ru.itmo.fitp.mobile.iterators

class FibonacciIterator: NumberIterator {
    private var terms = Pair(0, 1)

    private val iterator = iterator {
        while (true) {
            yield(terms.first)
            terms = Pair(terms.second, terms.first + terms.second)
            if (terms.second < 0) {
                terms = Pair(0, 1)
            }
        }
    }

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): Int {
        return iterator.next()
    }

    override fun current() = terms.first
}