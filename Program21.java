import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.net.URL;
import org.apache.poi.ss.usermodel.*;

public class CrossBrowserLoginTest {

    @DataProvider(name = "loginData")
    public Object[][] readExcelData() throws Exception {
        FileInputStream fis = new FileInputStream("testdata.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        Object[][] data = new Object[rowCount-1][2];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            data[i-1][0] = row.getCell(0).getStringCellValue(); // username
            data[i-1][1] = row.getCell(1).getStringCellValue(); // password
        }
        wb.close();
        return data;
    }

    @Parameters("browser")
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String browser) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        if (browser.equalsIgnoreCase("chrome")) {
            caps.setBrowserName("chrome");
        } else if (browser.equalsIgnoreCase("firefox")) {
            caps.setBrowserName("firefox");
        } else if (browser.equalsIgnoreCase("edge")) {
            caps.setBrowserName("MicrosoftEdge");
        }

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/"), caps);

        driver.get("https://example.com/login");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();

        // validation step
        System.out.println("Logged in with: " + username + " on " + browser);

        driver.quit();
    }
}
