package com.datagile.test.download.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class GazPage {
    public GazPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[@class='main-nav__item-link' and text()='Продукты' and @href='#div109']")
    WebElement products;


    @FindBy(xpath = "//a[contains(@href, '/produkty/upravlenie-ib/ankey-idm')]")
    WebElement ankey;

    @FindBy(xpath = "//div[contains(@class, 'ankeyidm__blocks')]")
    WebElement info;

    @FindBy(xpath = "//a[contains(@id, 'tab-materialy')]")
    WebElement materials;

    @FindBy(xpath = "//a[contains(@href, '/component/jdownloads/send/37-ankey-idm/219-ankey-idm-manual-user')]")
    WebElement guide;

}