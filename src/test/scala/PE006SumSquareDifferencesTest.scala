import org.scalatest.FunSuite

class PE006SumSquareDifferencesTest extends FunSuite
{
  test("find the diff in the range for 100")
  {
    assert(PE006SumSquareDifferences.SumSquareDifferences(100)==25164150)
  }

}
