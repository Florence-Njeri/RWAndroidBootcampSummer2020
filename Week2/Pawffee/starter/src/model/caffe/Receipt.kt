package model.caffe

// TODO add data, such as ID, list of products, and maybe an optional set of cats adopted/sponsored!
data class Receipt (val itemId:String,
                    val cost:Double,
                    val customerName:String,
                    val quantity: Int
                    )
