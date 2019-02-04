import org.scalatest.FunSuite
class MultiplesOf3and5Test extends FunSuite
{
   test("Give valid Integer Value")
   {
      assert(PE001MultiplesOf3and5.multiplesOf3and5(1000) == 233168)
   }

}
