package model.people

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class Employee(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        val salary: Double,
        val socialSecurityNumber: String,
        val hireDate: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "" // TODO format the data in any way you want! :]
    }

    /**
     * Prints a time of clocking in, in a nice format.
     *
     * Hint: to get time, you can create a `Date` object. Use SimpleDateFormatter to format the time!
     * */
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDateTime = LocalDateTime.now()
    var currentTime = Calendar.getInstance().time

    fun clockIn() {
        val currentDate = sdf.format(currentTime)
        println("$firstName $lastName  checked in at " + currentDate)

    }

    // TODO same as above, but times for clocking out!
    fun clockOut() {
        val currentDate = sdf.format(currentTime)
        println("${firstName + lastName}  checked out at " + currentDate)
    }
}