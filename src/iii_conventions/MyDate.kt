package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange: DateRange): Iterator<MyDate> {
    var current = dateRange.start

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val next = current
        current = current.nextDay()
        return next
    }
}

// Solution with operator function
operator fun DateRange.contains(d: MyDate): Boolean = start < d && d <= endInclusive

// Solution with ClosedRange interface
// class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>
