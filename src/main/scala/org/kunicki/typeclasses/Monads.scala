package org.kunicki.typeclasses

import cats.Monad
import cats.implicits._

import scala.Numeric.Implicits._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.higherKinds

object Monads extends App {

  def addOptions[T: Numeric](a: Option[T], b: Option[T]): Option[T] =
    for {
      x <- a
      y <- b
    } yield x + y

  addOptions(Some(1), None)

  def addFutures[T: Numeric](a: Future[T], b: Future[T]): Future[T] =
    for {
      x <- a
      y <- b
    } yield x + y

  addFutures(Future.successful(1), Future(3))

  def add[T: Numeric, M[_] : Monad](a: M[T], b: M[T]): M[T] =
    for {
      x <- a
      y <- b
    } yield x + y

  // can't add(Some(1), Some(3)) since their type is Some[Int], but a monad instance only exists for Option[Int]
  add(1.some, 3.some)

  add(Future.successful(1), Future(3))
}
