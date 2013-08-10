package Hello

object HelloWorld {

  def main(args: Array[String]): Unit = {
    var x = 2;
    if (x == 2) {
      println("fred")
    }
  }
  
  def isPrime(number: Long) : Boolean = {

    if (number == 2)
      true
    else
      false
 
  }
  

}