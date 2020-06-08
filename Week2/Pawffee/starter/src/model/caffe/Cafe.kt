package model.caffe

import model.animals.Cat
import model.people.Employee
import model.people.Person

class Cafe {


    // cafe related stuff

    /**
     * To simplify it, let's imitate a week-long cafe turnaround.
     *
     * Make sure to add your receipts to each set, with your data.
     * */
    private val receiptsByDay = mutableMapOf(
            "Monday" to mutableSetOf<Receipt>(),
            "Tuesday" to mutableSetOf<Receipt>(),
            "Wednesday" to mutableSetOf<Receipt>(),
            "Thursday" to mutableSetOf<Receipt>(),
            "Friday" to mutableSetOf<Receipt>(),
            "Saturday" to mutableSetOf<Receipt>(),
            "Sunday" to mutableSetOf<Receipt>()
    )

    // maybe as employees check in, you can add them to the list of working employees!
    private val employees = mutableSetOf<Employee>()
    private val customers = mutableSetOf<Person>()

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()
    private val sponsoredCats = mutableSetOf<String>()


    // TODO Add logic for checking in and checking out!
    fun checkInEmployee(employee: Employee) {

        employee.clockIn()
        employees.add(employee)

    }

    fun checkOutEmployee(employee: Employee) {
        employee.clockOut()
    }

    fun showNumberOfReceiptsForDay(day: String) {
        val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!

        println("On $day you made ${receiptsByDay.size} transactions!")
    }

    fun getReceipt(items: List<Product>, customerId: String): Receipt {
        // TODO return a receipt! Also make sure to check if customer is also an employee
        var customerName = ""
        customers.forEach {
            if (it.id == customerId) {
                customerName = "${it.firstName}  ${it.lastName}"
            }

        }
        var price = 0.0
        var id = ""
        items.forEach {
            price = it.price
            id = it.id
        }
        val quantity = items.size

        return Receipt(itemId = id, cost = price, quantity = quantity, customerName = customerName)
    }

    fun addSponsorship(catId: String, personId: String) {
        // TODO add the sponsorship
        sponsorships.add(Sponsorship(catId = catId, patronId = personId))
        sponsoredCats.add(catId)

    }

    fun addCustomers(person: Person) {
        customers.add(person)
    }

    fun getWorkingEmployees(): Set<Employee> = employees
    lateinit var adoptedCatsSet: Cat

    fun getAdoptedCats() {
        /**Return a set containing all the adopted cats from each person*/
        var cats = arrayListOf<String>()
        customers.forEach {
            it.cats.forEach {
                cats.add(it.name)
            }
        }
        println("Cats adopted today were: $cats")
    }

    fun getSponsoredCats(): Set<String> {
        /** Return a set containing all the sponsored cats*/

        return sponsoredCats
    }

//    fun getMostPopularCats(): Set<Cat> {
//        /**Cat with most sponsors*/
//
//
//    }
//
//    fun getTopSellingItems(): Set<Product> {
//        /** Items sold the most*/
//
//    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }
}