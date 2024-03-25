package scala.u04.task2

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}

import scala.u04.task2.Sequences.{BasicSequenceADT, ScalaListSequenceADT, SequenceADT}

object SequenceCheck extends Properties("Sequence"):

  sequencesTestAxioms(BasicSequenceADT)
  sequencesTestAxioms(ScalaListSequenceADT)

  def sequencesTestAxioms(sequenceADT: SequenceADT): Unit =
    import sequenceADT.*

    def sequenceGen(): Gen[Sequence[Int]] = for
      b <- Gen.prob(0.7)
      size <- Gen.choose(0, 100) // Use the Gen.choose method to determine the size of the sequence
      elements <- Gen.listOfN(size, Gen.choose(0, 100)) // Generate a list of random integers
    yield if b then nil() else elements.foldRight[Sequence[Int]](nil())((e, acc) => cons(e, acc)) // Convert the list to a sequence

    //Pick a random mapper between the three
    def mapperGen(): Gen[Int => Int] = Gen.oneOf[Int => Int](_ + 1, _ * 2, x => x * x)

    def flatMapGen(): Gen[Int => Sequence[String]] = Gen.oneOf[Int => Sequence[String]](_ => cons("a", cons("b", nil())), _ => cons("dummy", nil()), _ => cons("x", cons("z", cons("c", nil()))))

    def predicateGen(): Gen[Int => Boolean] = Gen.oneOf[Int => Boolean](_ % 2 == 0, _ % 2 != 0)

    def operatorGen(): Gen[(Int, Int) => Int] = Gen.oneOf[(Int, Int) => Int](_ + _, _ - _, _ * _)

    property("map axiom") =
      forAll(sequenceGen(), mapperGen()): (s, m) =>
        (getCons(s), m) match
        case(None, _) => map(s, m) == nil()
        case(Some(h, t), m) => map(s, m) == cons(m(h), map(t, m))

    property("concat axiom") =
      forAll(sequenceGen(), sequenceGen()): (s1, s2) =>
        (getCons(s1), getCons(s2)) match
          case(None, None) => concat(s1, s2) == nil()
          case(Some((h1, t1)), None) => concat(s1, s2) == cons(h1, t1)
          case(None, Some((h2, t2))) => concat(s1, s2) == cons(h2, t2)
          case(Some((h1, t1)), Some((h2, t2))) => concat(s1, s2) == cons(h1, concat(t1, s2))

    property("filter axiom") =
      forAll(sequenceGen(), predicateGen()): (s, b) =>
        (getCons(s), b) match
          case(None, _) => filter(s, b) == nil()
          case(Some(h, t), b) => if b(h) then filter(cons(h, t), b) == cons(h, filter(t, b)) else filter(cons(h, t), b) == filter(t, b)

    property("flatmap axiom") =
      forAll(sequenceGen(), flatMapGen()): (s, m) =>
        (getCons(s), m) match
          case(None, _) => flatMap(s, m) == nil()
          case(Some(h, t), m) => flatMap(cons(h, t), m) == concat(m(h), flatMap(t, m))

    property("foldLeft axiom") =
      forAll(sequenceGen(), Gen.choose(1, 10), operatorGen()): (s, z, op) =>
        (getCons(s), z, op) match
          case(None, z, op) => foldLeft(s, z, op) == z
          case(Some(h, t), z, op) => foldLeft(cons(h, t), z, op) == foldLeft(t, op(z, h), op)

   /* property("reduce axiom") =
      forAll(sequenceGen(), operatorGen()): (s, op) =>
        (s, op) match
          case (nil, _) => throw new UnsupportedOperationException("Cannot reduce an empty sequence")
          //case(cons(h, t), op) => reduce(cons(h, t), op) == Some(foldLeft(t, h, op))


    */