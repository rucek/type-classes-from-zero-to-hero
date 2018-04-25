package org.kunicki.typeclasses

trait Show[T] {

  def show(t: T): String
}

object Show {

  implicit val showInt: Show[Int] = (i: Int) => s"int: $i"

  implicit val showString: Show[String] = (t: String) => s"""string: "$t""""
}

object ShowTest extends App {

  import Show._

  println(showInt.show(42))
  println(showString.show("foo"))
}
