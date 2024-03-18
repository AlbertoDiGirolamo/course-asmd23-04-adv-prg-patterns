package scala.u04.datastructures

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Inspectors.forAll
import org.scalatest.funsuite.AnyFunSuite
import u04.datastructures.Sequences.*
import Sequence.*
import org.scalatest.matchers.should.Matchers.shouldBe

import scala.u04.datastructures.Task1SequenceCheck.smallInt
import scala.util.Random

class Task1ConvertToScalaTest extends AnyFunSuite:
  val testsNumber: Int = 100
  def smallInt : Int = Random().nextInt(10)
  def str : String = Random().nextString(1)
  var values: List[(Int, String)] = List()

  for (_ <- 0 to testsNumber) {
    values = (smallInt, str) :: values
  }

  test("of is a correct factory"):
    forAll(values): (i, s) =>
      of(i, s) shouldBe of(i, s).filter(e => e == s)

      forAll(values): (i, s) =>
        of(i, s).filter(e => e != s) shouldBe Nil()

      forAll(values): (i, s) =>
        Cons(s, of(i, s)) shouldBe of(i + 1, s)

      forAll(values.map((_, s) => s)): s =>
        of(0, s) shouldBe Nil()

    println("OK, passed " + testsNumber + " tests.")

