import org.scalatest.FunSuite

class PE0016PowerDigitSumTest extends FunSuite
{
  test("Find 2 pow 1000")
  {
    assert(PE0016PowerDigitSum.PowerdigitSum(1000)==1366)
  }

}
