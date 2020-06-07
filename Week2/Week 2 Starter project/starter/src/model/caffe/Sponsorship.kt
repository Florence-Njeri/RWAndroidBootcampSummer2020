package model.caffe

/**
 * Sponsorships involve recurring donations which are attached to a specific cat and
 * corresponding shelter and continue until the cat is adopted by someone.
 *
 * a patron only need “favorite” the cat in the list of cats and their credit card is automatically charged.
 */
data class Sponsorship(
    val patronId: String,
    val catId: String
)