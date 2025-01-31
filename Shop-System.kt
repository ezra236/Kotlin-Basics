open class Shop {

    var names = mutableMapOf("Ezra" to "123", "Monica" to "12q")
    var admin = mutableListOf(Pair("Ezra", "123"))
    var products = mutableMapOf("Apple" to "15", "Banana" to "20")

    fun see(names: MutableMap<String, String>, x: String, y: String): Boolean {
        return if (names[x] == y) {
            println("Successfully Logged In")
            true
        } else {
            println("Intruder")
            false
        }
    }

    fun chec(admin: MutableList<Pair<String, String>>, d: String, t: String): Boolean {
        return if (admin.any { it.first == d && it.second == t }) {
            println("Successfully Logged In")
            true
        } else {
            println("Intruder")
            false
        }
    }

    fun modify() {
        println("Enter name of product to be Added:")
        val item = readLine()!!.toString()

        println("Enter the quantity of product to be Added:")
        val measure = readLine()!!.toString()

        products[item] = measure
        println("Product Added")
    }

    fun show() {
        println("Available Products:")
        for ((name, price) in products) {
            println("$name: $price")
        }
    }

    fun exit() {
        println("Exiting the program.")
        System.exit(0)
    }

    fun purchase(): Float {
        var totalPrice = 0f  // Initialize the total price as a float
        while (true) {
            println("Enter the product to Purchase:")
            val productName = readLine()!!.toString()

            // Ensure that we handle invalid input by checking if the product exists
            val price = products[productName]?.toFloatOrNull()

            if (price != null) {
                totalPrice += price  // Add the price to the total
            } else {
                println("Product not found!")
            }

            println("End Shopping ('Yes' or 'No')")
            val situation = readLine()!!.toString()

            when (situation) {
                "Yes" -> continue  // Continue shopping
                "No" -> break  // End shopping and exit the loop
                else -> println("A valid action should be entered!")  // Invalid input
            }
        }
        return totalPrice  // Return the total price accumulated
    }

}

class User : Shop() {

    fun validation(): Boolean {
        println("Enter Your Name:")
        val validate = readLine()!!.toString()

        println("Enter Your Password:")
        val password = readLine()!!.toString()

        return see(names, validate, password)
    }

}

class ProductManagement : Shop() {

    fun credentials(): Boolean {
        println("Enter your Username:")
        val nameAdmin = readLine()!!.toString()

        println("Enter your password:")
        val passAdmin = readLine()!!.toString()

        return chec(admin, nameAdmin, passAdmin).also {
            if (it) modify() // Only allow modification if login is successful
        }
    }
}

class Checkout : Shop() {

    fun display() {
        show()
    }

    fun action() {
        println("Enter what you would like to do: ('a' for admin, 'b' for Available Products, 'c' for log-in and 'd' for Purchase, 'e' for exit)")
        val action1 = readLine()!!.toString()

        when (action1) {
            "a" -> ProductManagement().credentials()
            "b" -> display()
            "c" -> User().validation()
            "d" -> {
                val total = purchase()
                println("Total Price: $total")
            }
            "e" -> exit()
            else -> println("Invalid choice!")
        }
    }

}

fun main() {
    println("WELCOME")

    val myobj = Checkout()
    myobj.action()
}
