package test.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityReadonlyTest extends BaseTest {

    private static final List<String> EXPECTED_VALUES =
            Arrays.asList("", "", "", "0", "0.00", "", "", "", "", "", "menu");

    @Test
    public void verifyListIsEmptyTest() {
        WebDriver driver = getDriver();

        WebElement readonlyPage = driver.findElement(By.xpath("//p[contains(text(), 'Readonly')]"));
        ProjectUtils.click(driver, readonlyPage);
        driver.findElement(By.xpath("//a[@href='index.php?action=action_list&list_type=table&entity_id=6']")).click();
        Assert.assertEquals(driver.findElement(By.className("card-body")).getText(), "");
    }

    @Test
    public void verifyRowsAreEmptyTest() {
        WebDriver driver = getDriver();

        WebElement readonlePage = driver.findElement(By.xpath("//p[contains(text(), ' Readonly ')]"));
        ProjectUtils.click(driver, readonlePage);
        WebElement createNewRecordButton = driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]"));
        ProjectUtils.click(driver, createNewRecordButton);
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(), 'Save')][1]"));
        ProjectUtils.click(driver, saveButton);

        List<WebElement> rowsList = driver.findElements(By.xpath("//tbody/tr"));
        List<WebElement> rowValue = driver.findElements(By.xpath("//td"));

        Assert.assertEquals(rowsList.size(), 1);
        Assert.assertEquals(rowValue.stream().map(WebElement::getText).collect(Collectors.toList()), EXPECTED_VALUES);
    }

    @Ignore
    @Test
    public void inputTest() {

        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//p[contains(text(), 'Readonly')]")).click();
        driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")).click();

        WebElement inpString = driver.findElement(By.xpath("//input[@id='string']"));
        WebElement inpText = driver.findElement(By.xpath("//textarea[@id='text']"));
        WebElement inpDec = driver.findElement(By.xpath("//input[@id='decimal']"));
        WebElement inpInt = driver.findElement(By.xpath("//input[@id='int']"));
        WebElement inpDate = driver.findElement(By.xpath("//input[@id='date']"));
        WebElement inpDatetime = driver.findElement(By.xpath("//input[@id='datetime']"));
        WebElement btnAddEmbedRec = driver.findElement(By.xpath("//button[contains(text(),'+')]"));

        Assert.assertTrue(Boolean.parseBoolean(inpString.getAttribute("readonly")));
        Assert.assertTrue(Boolean.parseBoolean(inpText.getAttribute("readonly")));
        Assert.assertTrue(Boolean.parseBoolean(inpDec.getAttribute("readonly")));
        Assert.assertTrue(Boolean.parseBoolean(inpInt.getAttribute("readonly")));
        Assert.assertTrue(Boolean.parseBoolean(inpDate.getAttribute("readonly")));
        Assert.assertTrue(Boolean.parseBoolean(inpDatetime.getAttribute("readonly")));
        Assert.assertTrue(Boolean.parseBoolean(btnAddEmbedRec.getAttribute("disabled")));

        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        driver.findElement(By.xpath("//i[contains(text(),'format_line_spacing')]")).click();

        WebElement lastRow = driver.findElement(By.xpath("//tr[last()]"));
        Assert.assertEquals(lastRow.getText(),"String\n" +
                                                        "Text\n" +
                                                        "Int\n" +
                                                        "Decimal\n" +
                                                        "Date\n" +
                                                        "Datetime\n" +
                                                        "File\n" +
                                                        "File image\n" +
                                                        "User");
    }
}
