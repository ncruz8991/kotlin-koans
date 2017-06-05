package vi_generics

import util.TODO
import java.util.*

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList<String>(), ArrayList<String>()) { !it.contains(" ") }
}

fun List<String>.partitionTo(left: ArrayList<String>, right: ArrayList<String>, predicate: (String) -> Boolean): Pair<List<String>, List<String>>
        = mutablePartitionMapper(left, right, predicate) { it.toList() }

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet<Char>(), HashSet<Char>()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}

fun Set<Char>.partitionTo(left: HashSet<Char>, right: HashSet<Char>, predicate: (Char) -> Boolean): Pair<Set<Char>, Set<Char>>
        = mutablePartitionMapper(left, right, predicate) { it.toSet() }

fun <M, R> Collection<M>.mutablePartitionMapper(left: MutableCollection<M>,
                                                right: MutableCollection<M>,
                                                predicate: (M) -> Boolean,
                                                mapper: (Collection<M>) -> R): Pair<R, R> {
    groupBy(predicate).forEach { (key, value) -> if (key) left.addAll(value) else right.addAll(value) }
    return mapper(left).to(mapper(right))
}
