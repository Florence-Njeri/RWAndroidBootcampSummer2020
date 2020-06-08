package helper

import model.animals.Cat
import model.caffe.Cafe
import model.caffe.Product
import model.people.Person
import model.shelter.Shelter

class CafeController {

    // cafe related things
    private val cafe = Cafe()

    // shelter related things // TODO make sure to fill in the data!
    private val shelters = mutableSetOf<Shelter>(Shelter("Alley Cat Allieses"), Shelter("Whiskers"))
    private val shelterToCat = mutableMapOf<Shelter, MutableSet<Cat>>()

    //Called when a patron wants to adopt a cat
    fun adoptCat(catName: String, person: Person) {
        shelterToCat[shelters.elementAt(0)] = mutableSetOf(
                Cat(name = "Kitty", gender = 'm', shelterId = 242),
                Cat(name = "Whiskers", gender = 'm', shelterId = 242),
                Cat(name = "Misty", gender = 'm', shelterId = 242),
                Cat(name = "Oreo", gender = 'm', shelterId = 246),
                Cat(name = "Sassy", gender = 'm', shelterId = 242))

        shelterToCat[shelters.elementAt(1)] = mutableSetOf(
                Cat(name = "Sammy", gender = 'm', shelterId = 246),
                Cat(name = "Princes", gender = 'm', shelterId = 242),
                Cat(name = "Pawee", gender = 'm', shelterId = 246))


        // check if cats exist, and retrieve its entry!
        val catInShelter = shelterToCat.entries.firstOrNull { (_, catsInShelter) ->
            catsInShelter.any { it.name == catName }

        }

        // you can adopt that cat!
        if (catInShelter != null) {
            val cat = catInShelter.value.first { cat -> cat.name == catName } // find the cat for that ID again

            // remove the cat from the shelter
            catInShelter.value.remove(cat)

            // add the cat to the person
            person.cats.add(cat)
        }
    }

    fun sellItems(items: List<Product>, customerId: String) {

        /**
         * Also make sure to print receipt information out & add the receipt to the list of receipts for the current day.
         * You can random the day from a List, or check from the Date object!
         * */
        val receipt = cafe.getReceipt(items, customerId)
        println("\n<<<-------------------------------Receipt----------------------------------------------->>>\n")
        println("Hey ${receipt.customerName} your order of:\n" +
                "Item id ${receipt.itemId} , ${receipt.quantity} items costs: $${receipt.cost} ")

        println("\n<<--------------------------------------------------------------------------------------->>>\n")
    }

    /**
     * First gets a list of all adopters, then queries shelters.
     *
     * */
    fun getNumberOfAdoptionsPerShelter(): Map<String, Int> {
        val allAdopters = cafe.getAdopters()
        //Iterate through this list and add a map of cats and shelters they were adopted from
        val adoptionsPerShelter = mutableMapOf<String, Int>()

        if (allAdopters.isEmpty()) return mapOf()
        for (adopter in allAdopters) {
            val catsCount = adopter.cats.size
            for (cat in adopter.cats) {
                val shelterList = shelters.filter { it.name == cat.name }
                shelterList.forEach { adoptionsPerShelter.put(it.name, catsCount) }
            }
        }
        //Print Shelter : No of cats adopted from that shelter
// TODO find which cats belong to which shelter, and create a map of Shelter name to number of adoptions
        return adoptionsPerShelter
    }

    fun getUnadoptedCats(): Set<Cat> {
        //Return a set of all unadopted cats
        /**
         * TODO: iterate through shelterToCat and add all the cats there to a set.
         */
        var unadoptedCats = setOf<Cat>()
        for ((shelter, cat) in shelterToCat) {
            unadoptedCats.plus(cat)

        }
        return unadoptedCats
    }


}