import org.scalatest.FunSuite

class PE016PowerDigitSumTest extends FunSuite
{
  test("Find 2 pow 1000")
  {
    assert(PE016PowerDigitSum.PowerdigitSum(1000)==1366)
  }

}
