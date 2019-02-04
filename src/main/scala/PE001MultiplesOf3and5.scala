object PE001MultiplesOf3and5 extends App
{
  def multiplesOf3and5(range : Int) : Int =
  {
     //Lessions learnt that when condition used in filter then you cannot use
    //_
     List.range(0,range).filter(num=>num%3==0 || num%5==0).sum

  }
}
