import java.io.{File, FileOutputStream}
import java.util.{Calendar, Date}

import com.itextpdf.text._
import _root_.Utilities.Helper
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfPCell
import org.apache.poi.openxml4j.opc.OPCPackage
import com.itextpdf.text.PageSize
import com.itextpdf.text.BaseColor
import com.itextpdf.text.Font.FontFamily
import com.itextpdf.text.Element

object POLC001Excel extends App
{
     var success : Boolean = false
    //Get year, Month and day
    val now = Calendar.getInstance()
    var month = now.get(Calendar.MONTH)+1
    val CurrentDate = now.get(Calendar.YEAR)+"-"+month+"-"+now.get(Calendar.DATE)

    //Call the function to generate student records based on size
    print("How many Student Records needs to be generated :")
    val noOfRecords  = scala.io.StdIn.readInt()
    val StudentRecords = Helper.CreateStudentRecords(noOfRecords)
    println(s"No Of Student Records Generated : ${StudentRecords.keySet().size()}")

    //Excel
    val workbook = new XSSFWorkbook
    val spreadsheet = workbook.createSheet("Student Info")
    var rowid :Int = -1
    var cellid : Int = -1

    //Write all records to excel and close
    StudentRecords.keySet().forEach{key=>
       val row = spreadsheet.createRow {rowid += 1; rowid}
       cellid = -1
       StudentRecords.get(key).forEach { Value =>
         val cell = row.createCell {cellid += 1; cellid}
         cell.setCellValue(Value)
       }
       val out = new FileOutputStream(
         new File("/Users/lakshmi/IdeaProjects/ProjectEuler/StudentsRecords_"+CurrentDate+".xlsx"))
       workbook.write(out)
     }
     workbook.close()
     println("Writesheet.xlsx written successfully")

     //Open xlsx document and get the sheet 'Student Info'
     val input_document = OPCPackage.open(new File("/Users/lakshmi/IdeaProjects/ProjectEuler/StudentsRecords_"+CurrentDate+".xlsx"))
     val my_xls_workbook = new XSSFWorkbook(input_document)
     val my_worksheet = my_xls_workbook.getSheetAt(0)
     val rowIterator = my_worksheet.iterator

     //Create new pdf document and open
     var xls_to_pdf = new Document(PageSize.A4, 2f, 2f, 2f, 2f)
     PdfWriter.getInstance(xls_to_pdf,new FileOutputStream("StudentsRecords_"+CurrentDate+".pdf"))
     xls_to_pdf.open()

     //Create 5 columns as we know how many columns required.
     var my_table = new PdfPTable(8)
     println(my_table.getClass)

     var columnWidths = Array[Float](25f, 28f, 25f, 25f, 25f, 25f, 25f, 25f )
     my_table.setWidths(columnWidths)
     var font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)


     //Iterate all rows and add to table then finally add the table to pdf file pointer
     var table_cell = new PdfPCell
    var Status_table_cell = new PdfPCell
     while(rowIterator.hasNext)
     {
         val Currentrow = rowIterator.next()
         val cellIterator = Currentrow.cellIterator()
         while(cellIterator.hasNext)
         {
           val Currentcell = cellIterator.next
           table_cell = new PdfPCell(new Phrase(Currentcell.getStringCellValue,font))
           table_cell.setHorizontalAlignment(Element.ALIGN_CENTER)
           my_table.addCell(table_cell)
         }
     }

     xls_to_pdf.add(new Paragraph("STUDENT RECORDS",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLDITALIC)))
     xls_to_pdf.add(new Paragraph(Calendar.getInstance.getTime.toString,FontFactory.getFont(FontFactory.TIMES,10,Font.NORMAL)))
     xls_to_pdf.add(new Phrase("----------------------------------------------------------------------------------------------------------------------------------"))
     xls_to_pdf.add(Chunk.NEWLINE)
     xls_to_pdf.add(my_table)
     xls_to_pdf.close()
     input_document.close()
     println("pdf Created successfully for all students")
     StudentRecords.keySet().forEach(println)

     print("Enter Student Id :")
     val StudentId  = scala.io.StdIn.readInt()

     println(s"-------$StudentRecords")
     println(StudentRecords.containsKey(StudentId))
     println(StudentRecords.get(StudentId))
    //Create new pdf document and open
    xls_to_pdf = new Document(PageSize.A4, 2f, 2f, 2f, 2f)
    PdfWriter.getInstance(xls_to_pdf,new FileOutputStream(StudentId+".pdf"))
    xls_to_pdf.open()

    my_table = new PdfPTable(2)

     var index : Int = 0
     StudentRecords.get(StudentId).forEach {Value=>
       table_cell = new PdfPCell(new Phrase(Value,font))
       if(Value.equals("PASS"))
       {
         Status_table_cell = new PdfPCell(new Phrase(Value,font))
         Status_table_cell.setBackgroundColor(BaseColor.GREEN)
         success = true
       }
       else if(Value.equals("FAIL")){
         Status_table_cell = new PdfPCell(new Phrase(Value,font))
         Status_table_cell.setBackgroundColor(BaseColor.RED)
         success = true
       }
       if(success) {
         Status_table_cell.setHorizontalAlignment(Element.ALIGN_CENTER)
         my_table.addCell(StudentRecords.get(0).get(index))
         my_table.addCell(Status_table_cell)
       }
       else
       {
         table_cell.setHorizontalAlignment(Element.ALIGN_CENTER)
         my_table.addCell(StudentRecords.get(0).get(index))
         my_table.addCell(table_cell)
       }
       index +=1
     }


  xls_to_pdf.add(new Paragraph("STUDENT "+StudentId+" RECORD",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLDITALIC)))
  xls_to_pdf.add(new Paragraph(Calendar.getInstance.getTime.toString,FontFactory.getFont(FontFactory.TIMES,10,Font.NORMAL)))
  xls_to_pdf.add(new Phrase("----------------------------------------------------------------------------------------------------------------------------------"))
  xls_to_pdf.add(Chunk.NEWLINE)
  xls_to_pdf.add(my_table)
  xls_to_pdf.close()

  println("pdf Created successfully for all students")



}
