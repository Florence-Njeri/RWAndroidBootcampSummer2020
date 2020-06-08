import helper.CafeController
import model.animals.Cat
import model.caffe.Cafe
import model.caffe.Product
import model.people.Employee
import model.people.Patron
import model.shelter.Shelter

/**
 *Build a financial reporting system to help manage the cafe finances.
 */
fun main() {
    var cafe = Cafe()
    val cafeController = CafeController() // print out the data here using CafeController functions

    /**
     * Scenario: Customer comes into cafe a little bit stressed and wants to play with the cats.
     * Loves cat(s) so much that they decide to adopt it(them) or sponsor them.{Patron)
     */


    val florence = Patron(firstName = "Florence", lastName = "Njeri", email = "flo@mail.com", phoneNumber = "0701234567")
    val norbert = Patron(firstName = "Norbert", lastName = "Someone", email = "nor@mail.com", phoneNumber = "0708234567")
    val marty = Employee(firstName = "Marty", lastName = "Someone", email = "marty@mail.com", phoneNumber = "070856467", salary = 2400.78, socialSecurityNumber = "123", hireDate = "2018-09-07")
    val luca = Employee(firstName = "Luca", lastName = "Kamau", email = "luca@mail.com", phoneNumber = "0709546467", salary = 4400.78, socialSecurityNumber = "124", hireDate = "2019-01-03")

    println("\n<--------------------------------Morning Register------------------------------------>\n")
    //Employees Checkin
    cafe.checkInEmployee(marty)
    cafe.checkInEmployee(luca)

    println("\n<------------------------------------------------------------------------------------->\n")

    //Customers
    cafe.addCustomers(florence)
    cafe.addCustomers(norbert)
    cafe.addCustomers(marty)
    cafe.addCustomers(luca)
    //Cats get sponsored
    cafe.addSponsorship("Sassy", florence.id)
    cafe.addSponsorship("Misty", florence.id)
    cafe.addSponsorship("Oreo", norbert.id)
    cafe.addSponsorship("Misty", norbert.id)

    //Adopt Cats
    cafeController.adoptCat("Pawee", florence)
    cafeController.adoptCat("Sassy", marty)

    //Buy coffee and get receipt for item bought
    cafeController.sellItems(listOf(Product(price = 2.50)), florence.id)

    println("<\n--------------------------------Evening Register------------------------------------>")

    println("No. of employees: ${cafe.getWorkingEmployees()}\n")
    cafe.getAdoptedCats()//Get the adopted cats
    println("Cats sponsored today: ${cafe.getSponsoredCats()}\n")
    println("Today's adopters were: ${cafe.getAdopters()}\n")

    //Adoptions per Shelter
    println("No. of cats adopted: ${cafeController.getNumberOfAdoptionsPerShelter()}")
    println("Unadopted cats: ${cafeController.getUnadoptedCats()}")

    //Employees Checking out
    cafe.checkOutEmployee(marty)
    cafe.checkOutEmployee(luca)

    println("<--------------------------------------------------------------------------------------->\n")
}