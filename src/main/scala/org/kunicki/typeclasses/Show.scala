package org.kunicki.typeclasses

trait Show[T] {

  def show(t: T): String
}