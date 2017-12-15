package ru.talismanov

object PatternMatching {

  def main(args: Array[String]): Unit = {
    val customer = Customer("Martin", "Odersky")

    println(isCustomer(customer))

    println(isCustomer("Martin Odersky"))

  }

  def isCustomer(someValue: Any) : Boolean = {
    someValue match {
      case cust: Customer => true
      case _ => false
    }
  }
}




