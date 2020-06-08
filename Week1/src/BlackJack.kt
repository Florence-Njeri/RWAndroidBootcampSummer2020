fun main() {

    val deckOfCards = createDeck()
    println("<\n--------------------------------------------------DECK OF CARDS------------------------------------------------------------------------------------->\n")
    println(deckOfCards)

    val cardsDrawnFromDeck = dealHand(2, deckOfCards.toMutableList())
    println("\n<------------------------------------------------Cards Drawn from Deck:------------------------------------------------------------------------------>\n")
    println("Cards drawn: $cardsDrawnFromDeck")

    var total = 0

    for(card in cardsDrawnFromDeck) {
        total += evaluateHand(card)
    }

    printResult(total, cardsDrawnFromDeck)

}
fun createDeck(): MutableSet<Card> {
    /**
     * creates a collection of suits and a collection of pips
     * Use a nested for loop to create a card of each pip for each suit and place it in a MutableSet<Card>.
     * Return the MutableSet of cards.
     *
     * four suits of thirteen cards each: spades, hearts, clubs, and diamonds
     */

    val cards = mutableSetOf<Card>()
    val suits = setOf('\u2663', '\u2660', '\u2666', '\u2665')
    val pips = setOf("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2")

    suits.forEach { suit ->
        for (pip in pips) {
            //Add card in MutableSet<Card>
            cards.add(Card(pip, suit))
        }
    }

    return cards
}

fun dealHand(numOfCards: Int, deck: MutableList<Card>): List<Card> {
    /***
     * receive the MutableList<Card> deck as a parameter as well as the number of cards it should place in the hand.
     * deal two cards from the deck.
     *  return a collection containing the number of cards specified {use List}
     */

    val drawnCards = mutableListOf<Card>()
    for (i in 0 until numOfCards) {
        // pick two cards at random from a deck of 52 cards.
        //TODO: Limit uppercase eg number above 52 and check for negative values
        val card = deck.random()
        drawnCards.add(card)
        deck.remove(card)
    }

    return drawnCards
}

/**
 * Create a function that receives the hand as a parameter.
 * Utilize a decision statement such as an if statement or a when statement to figure out the pip value
 * of the card and add it’s value to a total. This function should return the total.
 */
fun evaluateHand(hand: Card): Int {

    return when (hand.pip) {
        "A","K","Q","J","10" -> 10
        "9" -> 9
        "8" -> 8
        "7" -> 7
        "6" -> 6
        "5" -> 5
        "4" -> 4
        "3" -> 3
        else -> 2
    }
}

/***
 * This function should receive the total and the hand as a parameter.
 * It should print the cards in the hand in a readable way, and then print the total of the hand.
 *
 */
fun printResult(total: Int, hand: List<Card>) {
    println("\n<---------------------------------------------Final result-------------------------------------------------------------------------------------------->\n")
    println("Your hand was:")
    hand.forEach() { card ->
        println("${card.pip}${card.suit}")
    }
    println("For a total of: $total")
    if (total == 21) {
        println("You win!!")
    } else if (total > 21) {
        println("You loose")
    }

}



