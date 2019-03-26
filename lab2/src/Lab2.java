

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import jxl.*;
import jxl.read.biff.BiffException;

import java.io.*;
import jxl.Workbook;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(Parameterized.class)
public class Lab2 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private String names;
    private String numbers;
    private String urls;
    static ArrayList<String> number = new ArrayList<>();
    static ArrayList<String> name = new ArrayList<>();
    static ArrayList<String> url = new ArrayList<>();

    public Lab2(String numbers, String names, String urls){
        this.numbers = numbers;
        this.names = names;
        this.urls = urls;

    }

    public String getPassword(String number){
        return number.substring(4);
    }

    static{
        try {
            Workbook book = Workbook.getWorkbook(new File("/Users/stefanht/Desktop/软件测试名单.xls"));
            //获得第一个工作表对象
            Sheet sheet = book.getSheet(0);//index从0开始，0对应Sheet1
            //Sheet sheet = book.getSheet(0);

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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData(){

        Object[][] list = new Object[number.size()][3];
        for(int i = 0; i < number.size(); i++) {
            list[i][0] = number.get(i);
            list[i][1] = name.get(i);
            list[i][2] = url.get(i);
        }
        return Arrays.asList(list);

    }


    @Before
    public void setUp() throws Exception {
        String driverPath = System.getProperty("user.dir") + "/src/resources/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
//        baseUrl = "http://121.193.130.195:8800/login";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLab2() throws Exception {
        driver.get("http://121.193.130.195:8800/login");
        driver.findElement(By.name("id")).click();
        driver.findElement(By.name("id")).clear();
        driver.findElement(By.name("id")).sendKeys(numbers);
        driver.findElement(By.id("login_form")).click();
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(getPassword(numbers));
        driver.findElement(By.id("login_form")).click();
        driver.findElement(By.id("btn_login")).click();
        assertEquals(numbers, driver.findElement(By.id("student-id")).getText());
        assertEquals(names,driver.findElement(By.id("student-name")).getText());
        assertEquals(urls,driver.findElement(By.id("student-git")).getText());
        System.out.println(names);
        driver.findElement(By.id("btn_logout")).click();
        driver.findElement(By.id("btn_return")).click();


    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

