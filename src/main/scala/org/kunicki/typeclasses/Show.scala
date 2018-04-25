package org.kunicki.typeclasses

trait Show[T] {

  def show(t: T): String
}

object Show {

  implicit val showInt: Show[Int] = (i: Int) => s"int: $i"

  implicit val showString: Show[String] = (t: String) => s"""string: "$t""""

//  def apply[T](implicit ev: Show[T]): Show[T] = ev
  def apply[T: Show]: Show[T] = implicitly[Show[T]]

  implicit def showList[T](implicit ev: Show[T]): Show[List[T]] = (list: List[T]) =>
    list.map(ev.show).mkString("list(", ", ", ")")

  implicit class ShowSyntax[T: Show](t: T) {

    def show: String = implicitly[Show[T]].show(t)
  }
}

case class Wrapper[T](value: T)

object Wrapper {

  import Show._

  implicit def showWrapper[T: Show]: Show[Wrapper[T]] = (wrapper: Wrapper[T]) =>
    s"wrapped: ${wrapper.value.show}"
}

object ShowTest extends App {

  import Show._

  println(showInt.show(42))
  println(showString.show("foo"))

  println(Show[Int].show(42))
  println(Show[String].show("foo"))
  println(Show[List[Int]].show(List(1, 2, 3)))

  println(42.show)
  println("foo".show)
  println((1 :: 2 :: 3 :: Nil).show)

  println(Wrapper(1 :: 2 :: 3 :: Nil).show)
}
