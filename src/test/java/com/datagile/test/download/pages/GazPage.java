package com.datagile.test.download.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GazPage {
    public GazPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(xpath="//a[text()='Продукты']")
    WebElement products;


    @Getter
    @FindBy(xpath = "//a[contains(@href, '/produkty/upravlenie-ib/ankey-idm')]")
    WebElement ankey;

    @Getter
    @FindBy (xpath = "//div[contains(@class, 'ankeyidm__blocks')]")
    WebElement info;

    @Getter
    @FindBy(xpath = "//a[contains(@id, 'tab-materialy')]")
    WebElement materials;

    @Getter
    @FindBy(xpath = "//a[contains(@href, '/component/jdownloads/send/37-ankey-idm/219-ankey-idm-manual-user')]")
    WebElement guide;
}