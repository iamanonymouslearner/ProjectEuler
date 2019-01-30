object PE0016PowerDigitSum extends App
{
  def PowerdigitSum(exponent: Int): Int =
  {
    BigInt(2).pow(exponent).toString.map(_.toString).map(_.toInt).toList.sum
  }

}
