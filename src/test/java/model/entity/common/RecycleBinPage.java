package model.entity.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import test.data.AppConstant;

import java.util.List;

public final class RecycleBinPage extends MainPage {

    @FindBy(className = "card-body")
    private WebElement body;

    @FindBy(css = "table tbody > tr")
    private List<WebElement> rows;

    @FindBy(xpath = "//div[contains(text(), 'Good job with housekeeping! Recycle bin is currently empty!')]")
    private WebElement notification;

    @FindBy(xpath = "//a[normalize-space()='restore as draft']")
    private WebElement linkRestoreAsDraft;

    public RecycleBinPage(WebDriver driver) {
        super(driver);
    }

    public int getRowCount() {
        if (body.getText().equals(AppConstant.EMPTY_RECYCLE_BIN_TEXT)) {
            return 0;
        } else {
            return rows.size();
        }
    }

    public String getDeletedEntityContent(int rowNumber) {
        return rows.get(rowNumber).findElement(By.tagName("td")).getText();
    }

    public String getDeletedEntityContent() {
        return rows.get(0).findElement(By.tagName("td")).getText();
    }

    public String getDeletedImportValue() {
        return rows.get(0).findElement(By.xpath("//td/a/span/b")).getText();
    }

    public String getCellValue( int rowNumber, int cellNumber) {
        return rows.get(rowNumber).findElement(By.xpath(String.format("//td[1]/a/span[%d]/b", cellNumber))).getText();
    }

    public RecycleBinPage clickDeletePermanently(int rowNumber){
        rows.get(rowNumber).findElement(By.xpath("//a[contains (text(), 'delete permanently')]")).click();
        return this;
    }

    public String getNotification(){
        return notification.getText();
    }

    public RecycleBinPage clickRestoreAsDraft() {
        ProjectUtils.click(getWait(), linkRestoreAsDraft);
        return this;
    }
}
