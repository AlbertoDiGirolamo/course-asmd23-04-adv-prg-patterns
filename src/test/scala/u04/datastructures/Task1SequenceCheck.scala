package scala.u04.datastructures

import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import u04.datastructures.Sequences.*
import Sequence.*
import org.scalacheck.Test.Parameters
import org.scalacheck.Test

object Task1SequenceCheck extends Properties("Sequence"):

  def smallInt(): Gen[Int] = Gen.choose(0,100)
  
  property("of is a correct factory") =
    forAll(smallInt(), arbitrary[String]): (i, s) =>
      of(i, s) == of(i, s).filter(e => e == s)
    &&
    forAll(smallInt(), arbitrary[String]): (i, s) =>
      of(i, s).filter(e => e != s) == Nil()
    &&
    forAll(smallInt(), arbitrary[String]): (i, s) =>
      Cons(s, of(i, s)) == of(i+1, s)
    &&
    forAll(arbitrary[String]): s =>
      of(0, s) == Nil()


  override def overrideParameters(p: Parameters): Parameters =
    p.withMinSuccessfulTests(50)
