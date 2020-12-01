/**
 * [a, b].cartesianProduct([c,d]) == [[a, c],[a, d], [b, c],[b, d]]
 */
fun <T, U> List<T>.cartesianProduct(list: List<U>) =
    this.flatMap { first -> list.map { second -> first to second } }

/**
 * [a, b].cartesianProduct() == [[a,a], [a,b], [b,a], [b,b]]
 */
fun <T> List<T>.cartesianProduct() = this.cartesianProduct(this)