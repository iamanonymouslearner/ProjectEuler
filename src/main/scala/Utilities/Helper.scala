package Utilities

import java.io.FileOutputStream

import com.google.common.collect.{ArrayListMultimap, ListMultimap}
import com.itextpdf.text.Font.FontFamily
import com.itextpdf.text.pdf.{PdfPTable, PdfWriter}
import com.itextpdf.text.{BaseColor, Document, Font, PageSize}

import scala.util.Random

object Helper
{
  /* Purpose of this function is to get the random number based on min and max */
  def GetRandomNumber(minInclusive : Int, maxExclusive : Int) : Int =
  {
    val rand = new Random()
    minInclusive + rand.nextInt((maxExclusive-minInclusive)+1)
  }
  /* Purpose of this function is to get the random String based on length*/
  def GetRandomString(length : Int) : String =
  {
    Random.alphanumeric.take(10).mkString
  }


  /* Purpose of this function is to create n student records based on
   * parameter. */
  def CreateStudentRecords(NoOfRecords : Int) : ListMultimap[Int,String] =
  {
    val Students : ListMultimap[Int, String] = ArrayListMultimap.create()
    var i : Int = 0

    //Excel Header information
    Students.put(0,"STUDENT ID")
    Students.put(0,"STUDENT NAME")
    Students.put(0,"PHYSICS MARKS")
    Students.put(0,"CHEMISTRY MARKS")
    Students.put(0,"MATHS MARKS")
    Students.put(0,"TOTAL")
    Students.put(0,"AVERAGE")
    Students.put(0,"STATUS")

    while(i < NoOfRecords)
    {
      val StudentId = GetRandomNumber(1000, 2000)
      if(!Students.containsKey(StudentId))
      {
        val physicsMarks   = GetRandomNumber(50, 60)
        val ChemistryMarks = GetRandomNumber(70, 90)
        val MathsMarks     = GetRandomNumber(90, 100)

        Students.put(StudentId, StudentId.toString)
        Students.put(StudentId, GetRandomString(5))
        Students.put(StudentId, GetRandomNumber(50, 60).toString)
        Students.put(StudentId, GetRandomNumber(70, 90).toString)
        Students.put(StudentId, GetRandomNumber(20, 50).toString)

        val total = physicsMarks+ChemistryMarks+MathsMarks
        Students.put(StudentId,total.toString)
        Students.put(StudentId,(total/2).toString)
        if(total/2.toFloat < 115)
          Students.put(StudentId,"FAIL")
        else
          Students.put(StudentId,"PASS")

        i += 1
      }
    }
    Students
  }

  def CreateNewPdfAndOpen(FileName : String) : Boolean = {
    try
    {
      val xls_to_pdf = new Document(PageSize.A4, 2f, 2f, 2f, 2f)
      PdfWriter.getInstance(xls_to_pdf, new FileOutputStream(FileName))
      xls_to_pdf.open()
    }
    catch
    {
      case ex : Exception => println("exception caught: " + ex)
      return false
    }
    true
  }

 /* def SetPdfTableProperties() : com.itextpdf.text.pdf.PdfPTable =
  {
    val table = new PdfPTable(8)
    val columnWidths = Array[Float](25f, 28f, 25f, 25f, 25f, 25f, 25f, 25f )
    table.setWidths(columnWidths)
    //val font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)
  }
  table*/

}
