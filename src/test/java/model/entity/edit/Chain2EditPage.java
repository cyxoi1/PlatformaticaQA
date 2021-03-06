package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.common.ErrorPage;
import model.entity.table.Chain2Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static runner.ProjectUtils.fill;

import java.util.ArrayList;
import java.util.List;

public class Chain2EditPage extends EntityBaseEditPage<Chain2Page> {

    @FindBy(id = "f1")
    private WebElement f1Field;

    @FindBy(id = "f10")
    private WebElement f10Field;

    @FindBy(css = "input[id*='f']")
    private List<WebElement> allFFields;

    public Chain2EditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Chain2Page createPage() {
        return new Chain2Page(getDriver());
    }

    public ErrorPage clickSaveButtonReturnError() {
        saveButton.click();
        return new ErrorPage(getDriver());
    }

    public List<String> getActualValues() {
        List<WebElement> fValues = allFFields;
        final List<String> actualValues = new ArrayList<>();
        for (WebElement fValue : fValues) {
            actualValues.add(fValue.getAttribute("value"));
        }

        return actualValues;
    }

    public Chain2EditPage inputF1Value(String f1Value) {
        fill(getWait(), f1Field, f1Value);
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(f10Field, "value"));
        return this;
    }

    public Chain2EditPage editF1Value(String f1Value, List<String> expectedValues) {
        String f10ExpectedValue = expectedValues.get(expectedValues.size() - 1);
        fill(getWait(), f1Field, f1Value);
        getWait().until(ExpectedConditions.attributeToBe(f1Field, "value", f1Value));
        getWait().until(ExpectedConditions.attributeToBe(f10Field, "value", f10ExpectedValue));
        return this;
    }

    public Chain2EditPage editValues(List<String> newValues) {
        for (int i = 0; i < newValues.size(); i ++) {
            WebElement fi = allFFields.get(i);
            fill(getWait(), fi, newValues.get(i));
        }
        return this;
    }
}
