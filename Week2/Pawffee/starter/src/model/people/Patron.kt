package model.people

/**A patron is someone who gives financial assitance to a cause.
 * A patron can: order food and drinks and interact with the cats
 * adoption fees and paperwork which is routed through the appropriate shelter, or
 * can still sign up to sponsor a feline.
 *
 */
class Patron(
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "" // TODO format the data in any way you want! :]
    }


}