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
