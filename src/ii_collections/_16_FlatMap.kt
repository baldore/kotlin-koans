package ii_collections

fun example() {

    val result = listOf("abc", "12").flatMap { it.toList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

// Return all products ordered by customer
val Customer.orderedProducts: Set<Product> get() =
    this.orders
        .flatMap { it.products }
        .toSet()

// Return all products that were ordered by at least one customer
val Shop.allOrderedProducts: Set<Product> get() =
    this.customers
        .flatMap { it.orders }
        .flatMap { it.products }
        .toSet()
