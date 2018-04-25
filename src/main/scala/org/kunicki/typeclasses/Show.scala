package org.kunicki.typeclasses

trait Show[T] {

  def show(t: T): String
}

object Show {

  implicit val showInt: Show[Int] = (i: Int) => s"int: $i"

  implicit val showString: Show[String] = (t: String) => s"""string: "$t""""

  def apply[T](implicit ev: Show[T]): Show[T] = ev
}

object ShowTest extends App {

  import Show._

  println(showInt.show(42))
  println(showString.show("foo"))

  println(Show[Int].show(42))
  println(Show[String].show("foo"))
}
