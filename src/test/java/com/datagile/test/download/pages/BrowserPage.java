package com.datagile.test.download.pages;

import com.datagile.test.util.ConfProperties;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserPage {


    public BrowserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(xpath = "//form[contains(@action, 'https://yandex.ru/search/')]")
    private WebElement searchForm;

    @Getter
    @FindBy(xpath = "//iframe[contains(@id, 'ya-search-iframe-283dku')]")
    private WebElement searchFrame;

    public void fillOutFormAndSubmit(String text, WebDriverWait wait) {
        WebElement textArea = getSearchForm().findElement(By
                .xpath("//input[contains(@name, 'text')]"));

        wait.until(ExpectedConditions.visibilityOf(textArea));

        textArea.sendKeys(ConfProperties.getProperty("request.text"));

        WebElement submit = getSearchForm().findElement(By
                .xpath("//button[contains(@type, 'submit')]"));

        submit.click();
    }

    @Getter
    @FindBy(xpath = "//a[contains(@href, 'https://www.gaz-is.ru/')]")
    private WebElement searchResult;

}
