package model.entity.edit;

import model.entity.table.ArithmeticInlinePage;
import model.base.EntityBaseEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class ArithmeticInlineEditPage extends EntityBaseEditPage<ArithmeticInlinePage> {

    @FindBy(css = "input#f1")
    private WebElement f1;

    @FindBy(css = "input#f2")
    private WebElement f2;

    @FindBy(css = "input#sum")
    private WebElement sum;

    @FindBy(css = "input#sub")
    private WebElement subtraction;

    @FindBy(css = "input#mul")
    private WebElement multiplication;

    @FindBy(css = "input#div")
    private WebElement division;

    public ArithmeticInlineEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ArithmeticInlinePage createPage() {
        return new ArithmeticInlinePage(getDriver());
    }

    public ArithmeticInlineEditPage fillF1F2(String f1Value, String f2Value) {
        ProjectUtils.fill(getWait(), f1, f1Value);
        ProjectUtils.fill(getWait(), f2, f2Value);
        f2.click();
        return this;
    }

    public ArithmeticInlineEditPage waitSumToBe(String value) {
        getWait().until(f -> sum.getAttribute("value").equals(value));
        return this;
    }

    public ArithmeticInlineEditPage waitSubToBe(String value) {
        getWait().until(f -> subtraction.getAttribute("value").equals(value));
        return this;
    }

    public ArithmeticInlineEditPage waitMulToBe(String value) {
        getWait().until(f -> multiplication.getAttribute("value").equals(value));
        return this;
    }

    public ArithmeticInlineEditPage waitDivToBe(String value) {
        getWait().until(f -> division.getAttribute("value").equals(value));
        return this;
    }
}
