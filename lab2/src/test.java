import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    static ArrayList<String> number = new ArrayList<>();
    static ArrayList<String> name = new ArrayList<>();
    static ArrayList<String> url = new ArrayList<>();
    public static void main(String args[]) {
        Workbook book;

        {
            try {
                book = Workbook.getWorkbook(new File("/Users/stefanht/Desktop/软件测试名单.xls"));
                Sheet sheet = book.getSheet(0);//index从0开始，0对应Sheet1
                int rows = sheet.getRows();
                int cols = sheet.getColumns();
                for(int k = 2; k < rows; k++){
                    Cell cell = sheet.getCell(1, k);
                    number.add(cell.getContents());
                }
                for(int k = 2; k < rows; k++){
                    Cell cell = sheet.getCell(2, k);
                    name.add(cell.getContents());
                }
                for(int k = 2; k < rows; k++){
                    Cell cell = sheet.getCell(3, k);
                    url.add(cell.getContents());

                }
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < url.size(); i++){
            System.out.print(number.get(i) + "   " + name.get(i) + "  " + url.get(i));
            System.out.println();
        }

    }

}
