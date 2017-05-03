package iii_conventions

import kotlin.comparisons.compareBy

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

// _25_Comparison
operator fun MyDate.compareTo(date2: MyDate): Int =
        compareBy<MyDate>({ it.year }, { it.month }, { it.dayOfMonth }).compare(this, date2)

// _26_InRange
operator fun DateRange.contains(date: MyDate): Boolean = date >= start && date <= endInclusive

// _27_RangeTo
operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

// _28_ForLoop
operator fun DateRange.iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
    private var currentDate = start

    override fun hasNext(): Boolean = currentDate <= endInclusive

    override fun next(): MyDate {
        val previousDate = currentDate
        currentDate = currentDate.nextDay()
        return previousDate
    }
}

// _29_OperatorsOverloading
class RepeatedTimeInterval(val timeInterval: TimeInterval, val count: Int)

operator fun MyDate.plus(other: TimeInterval): MyDate = addTimeIntervals(other, 1)

operator fun MyDate.plus(other: RepeatedTimeInterval): MyDate = addTimeIntervals(other.timeInterval, other.count)

operator fun TimeInterval.times(i: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, i)

// _30_DestructuringDeclarations