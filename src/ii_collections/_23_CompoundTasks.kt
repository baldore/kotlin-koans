package ii_collections

// Return the set of customers who ordered the specified product
fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    return customers.filter {
        it.orders
            .flatMap { it.products }
            .toSet()
            .contains(product)
    }.toSet()
}

// Return the most expensive product among all delivered products
// (use the Order.isDelivered flag)
fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    val deliveredProducts = orders
        .filter { it.isDelivered }
        .flatMap { it.products }
        .toSet()

    if (deliveredProducts.isEmpty()) {
        return null
    }

    return deliveredProducts.reduce({ lastProduct, nextProduct ->
        if (lastProduct.price > nextProduct.price) lastProduct else nextProduct
    })
}

// Return the number of times the given product was ordered.
// Note: a customer may order the same product for several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    val allProducts = customers
        .flatMap { it.orders }
        .flatMap { it.products }
    return allProducts.count { it == product }
}
