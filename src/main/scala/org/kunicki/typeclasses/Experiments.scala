package org.kunicki.typeclasses

object Experiments extends App {

  implicit class ShowString(s: String) {
    def show = s"""string: "$s"""""
  }

  println("foo".show)

  implicit class ShowInt(i: Int) {
    def show = s"int: $i"
  }

  println(1.show)

  implicit class ShowList[T](list: List[T]) {
    def show = list.map(???).mkString("List(", ", ", ")")
  }
}
