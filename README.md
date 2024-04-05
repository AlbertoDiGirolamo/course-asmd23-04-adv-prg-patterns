# Task 1: TEST-OPERATE

## Different parameters

I tried to experiment with different ScalaCheck parameters and I found these possible modifications:
* `.withMinSuccessfulTests` : is the minimum number of correct tests required to pass. The standard value is 100.
* `.withMaxDiscardRatio` : is the maximum value of the ratio between failed tests and tests passed. This value represents the threshold for considering a test passed. The standard value is 5.
* `.withWorkers` : is the number of workers used for tests execution. The standard value is 1.
* `.withMinSize` : is the minimum size value used by ScalaCheck when generating a value. The standard value is 0.
* `.withMaxSize` : is the maximum size value used by ScalaCheck when generating a value. The standard value is 100.

## From ScalaCheck to ScalaTest

I created a file named `Task1ConvertToScalaTest` where I wrote the tests using ScalaTest format for the tests written inside `SequenceCheck`.

## Annotation

* ScalaTest and ScalaCheck have both forAll() methods
* ScalaCheck is a library specifically for generating and automatically verifying properties.
* ScalaCheck is based on the concept of properties: you define a property that should hold true for a certain class or function, and ScalaCheck automatically generates a series of test cases to check if the property holds.
* ScalaCheck is particularly useful for exhaustively checking properties over a large set of randomly generated data.

#Task 2: ADT-VERIFIER

1. Define an ADT for sequences where it is composed by type, constructors, operations and axioms.
  * constructors contain the principals concepts that make a Sequences (cons and nil)
  * operations are map/concat/filer/flatMap/foldLeft/reduce
  * axioms represent the rules that each operation defined should observe. This axioms are like a contract

2. Define operations of map/concat/filer/flatMap/foldLeft/reduce inside a trait SequenceADT
3. Realise two different implementation of these operations:
   * BasicSequenceADT: using cons/nil approach
   * ScalaListSequenceADT: using Scala List

The getCons method was implemented because it was necessary to use a sort of unapply method that allowed from a Sequence to obtain the head and tail that made up the list.

## Cons/Nil Implementation

A private enum define Cons Nil cases and then an opaque alias Sequence[A] allows to hide implementation details tolerating only the operation defined inside SequenceADT trait

## Scala List Implementatio

This implementation is realized using Scala List operation. In this case the opaque alias Sequence[A] consider the List[A] type.
