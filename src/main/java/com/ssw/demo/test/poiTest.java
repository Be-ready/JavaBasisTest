package main.java.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

/** poi测试（导出excel表）
 * 官网：http://poi.apache.org/components/spreadsheet/quick-guide.html
 * 导入相关jar包D:\apache-maven-3.5.4\repository\org\apache\poi\poi\4.0.0\poi-4.0.0.jar
 * 导入相关jar包D:\apache-maven-3.5.4\repository\org\apache\poi\poi\3.1.7\poi-3.1.7.jar
 * 注意：a.xls对应HSSFWorkbook, a.xlsx对应XSSFWorkbook
 *
 * @author wss
 * @created 2020/8/28 10:17
 * @since 1.0
 */
public class poiTest {

    // 注意：a.xls对应HSSFWorkbook, a.xlsx对应XSSFWorkbook
    private static Workbook wb = new HSSFWorkbook();
    private static OutputStream fileOut = null;
    /**
     * 测试新建空的excel文件
     */
    public static void createWorkbook() {
        {
            try {
                // 在当前文件夹新建一空的excel文件
                fileOut = new FileOutputStream("workbook.xls");
                wb.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createSheetName() {

        Sheet sheet1 = wb.createSheet("new sheet");     // sheet1的名称
        Sheet sheet2 = wb.createSheet("second sheet");  // sheet2的名称

        // sheet3的名称(显示为：0'Brien's sales)
        String safeName = WorkbookUtil.createSafeSheetName("[0'Brien's sales*?]");
        Sheet sheet3 = wb.createSheet(safeName);

        createWorkbook();
    }

    /**
     * 创建单元格（在第一行创建3个单元格）
     * 注释：行和单元格的起始下标从0开始
     */
    public static void createCells() {

        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(creationHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // 添加日期类型数据
        Row row1 = sheet.createRow(1);
        Cell cell0 = row1.createCell(0);
        cell0.setCellValue(new Date());  // 实际存入的数据类似：44071.4619753356（这种方式最好不用）

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:m"));
        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd hh:MM:ss"));

        Cell cell1 = row1.createCell(1);
        cell1.setCellValue(new Date());
        cell1.setCellStyle(cellStyle);  // 实际存入的数据类似：8/28/20  11:05

        Cell cell2 = row1.createCell(2);
        cell2.setCellValue(Calendar.getInstance());
        cell2.setCellStyle(cellStyle);  // 实际存入的数据类似：8/28/20  11:05

        Cell cell3 = row1.createCell(3);
        cell3.setCellValue(Calendar.getInstance());
        cell3.setCellStyle(cellStyle1);  // 实际存入的数据类似：2020-8-28  11:12:34

        try (OutputStream fileOut = new FileOutputStream("workbook3.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示各种对齐方式
     */
    public static void showAlignmentOptions() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);

//        createCell();
    }

    private static void createCell(Workbook wb, Row row, int column, HorizontalAlignment horizon, VerticalAlignment valig) {

        Cell c = row.createCell(column);
        c.setCellValue("Align It");
        CellStyle cs = wb.createCellStyle();
    }

    public static void main(String[] args) {

//        createWorkbook();
//        createSheetName();
        createCells();
    }
}
