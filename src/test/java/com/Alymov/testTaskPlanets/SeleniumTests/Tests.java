package com.Alymov.testTaskPlanets.SeleniumTests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Tests extends WebDriverSettings {

    @FindBy(xpath = "/html/body/table[1]/tbody/tr[2]/td[3]/form/input")
    public  WebElement MainPageBtnViewLord;

    public  void clickViewLordMain()
    {MainPageBtnViewLord.click();}

    @FindBy(xpath = "/html/body/table[1]/tbody/tr[2]/td[5]/form/input")
    public static WebElement MainPageBtnEditLord;

    public  void clickEditLordMain()
    {MainPageBtnEditLord.click();}

    @FindBy(xpath = "/html/body/form[1]/button")
    public WebElement MainPageBtnAddLord;

    public void clickAddLordMain()
    {MainPageBtnAddLord.click();}

    @FindBy(xpath = "/html/body/form[2]/button")
    public WebElement MainPageBtnTopTenYoungLords;

    public void clickViewYoungLordsMain()
    {MainPageBtnTopTenYoungLords.click();}

    @FindBy(xpath = "/html/body/form[3]/button")
    public WebElement MainPageBtnLazyLords;

    public void clickViewLazyLordsMain()
    {MainPageBtnLazyLords.click();}

    @FindBy(xpath = "/html/body/form[4]/button")
    public WebElement MainPageBtnAddPlanet;

    public void clickAddPlanetMain()
    {MainPageBtnAddPlanet.click();}

    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td[3]/form/input")
    public WebElement MainPageBtnEditPlanet;

    public void clickEditPlanetMain()
    {MainPageBtnEditPlanet.click();}

    @FindBy(xpath = "/html/body/a")
    public WebElement LordViewBtnBackToMenu;

    public void clickBackToMenuLord()
    {LordViewBtnBackToMenu.click();}

    @FindBy(xpath = "//*[@id=\"nameLord\"]")
    public WebElement AddLordInputName;

    @FindBy(xpath = "//*[@id=\"nameLord\"]")
    public WebElement EditLordInputName;

    @FindBy(xpath = "//*[@id=\"age\"]")
    public WebElement AddLordInputAge;

    @FindBy(xpath = "//*[@id=\"age\"]")
    public WebElement EditLordInputAge;

    @FindBy(xpath = "/html/body/form/input[3]")
    public WebElement AddLordBtnCreate;

    public void clickCreateAddLord()
    {AddLordBtnCreate.click();}

    @FindBy(xpath = "//*[@id=\"namePlanet\"]")
    public WebElement AddPlanetInputName;

    @FindBy(xpath = "//*[@id=\"namePlanet\"]")
    public WebElement EditPlanetInputName;

    @FindBy(xpath = "/html/body/form/input[2]")
    public WebElement AddPlanetBtnCreate;

    @FindBy(xpath = "/html/body/form/input[2]")
    public WebElement EditPlanetBtnSave;

    public void clickCreateAddPlanet()
    {AddPlanetBtnCreate.click();}

    public void clickEditSavePlanet()
    {EditPlanetBtnSave.click();}

    @FindBy(xpath = "/html/body/table[1]/tbody")
    public WebElement MainTable;

    @FindBy(xpath = "/html/body/table[2]")
    public WebElement MainPlanetsTable;



    @Test
    public void MainPageTitleTest() {

        driver.get("http://localhost:8080/lords_of_planets");
        String title = driver.getTitle();
        Assert.assertEquals("Lords and planets", title);
    }

    @Test
    public void LordsInfoTitleTest() {
        driver.get("http://localhost:8080/lords_of_planets");
        clickViewLordMain();
        String title = driver.getTitle();
        Assert.assertEquals("Info about lord",title);

    }


    @Test
    public void BackButtonTest() {
        driver.get("http://localhost:8080/lords_of_planets");
        clickViewLordMain();
        driver.navigate().back();

        String tempUrl = driver.getCurrentUrl();

        clickViewLordMain();
        clickBackToMenuLord();

        Assert.assertEquals(driver.getCurrentUrl(),tempUrl);

    }

    @Test
    public void AddLordTest() {
        driver.get("http://localhost:8080/lords_of_planets");
        clickAddLordMain();

        String tempName = "TestAddedLord";
        int tempAge = 444;

        AddLordInputName.clear();
        AddLordInputName.sendKeys(tempName);
        AddLordInputAge.clear();
        AddLordInputAge.sendKeys(String.valueOf(tempAge));
        clickCreateAddLord();

        Assert.assertTrue(MainTable.getText().contains(tempName));
        Assert.assertTrue(MainTable.getText().contains(String.valueOf(tempAge)));
    }

    @Test
    public void EditLordTest() {
        driver.get("http://localhost:8080/lords_of_planets");

        clickEditLordMain();

        String tempName = "TestEditedLord";
        int tempAge = 555;

        EditLordInputName.clear();
        EditLordInputName.sendKeys(tempName);
        EditLordInputAge.clear();
        EditLordInputAge.sendKeys(String.valueOf(tempAge));

        clickCreateAddLord();

        Assert.assertTrue(MainTable.getText().contains(tempName));
        Assert.assertTrue(MainTable.getText().contains(String.valueOf(tempAge)));

    }

    @Test
    public void TopYoungLordsTitleTest() {
        driver.get("http://localhost:8080/lords_of_planets");

        clickViewYoungLordsMain();
        String title = driver.getTitle();

        Assert.assertEquals(title,"Top 10 young Lords");
    }

    @Test
    public void LazyLordsTitleTest() {
        driver.get("http://localhost:8080/lords_of_planets");

        clickViewLazyLordsMain();
        String title = driver.getTitle();

        Assert.assertEquals(title,"List of lazy lords");
    }

    @Test
    public void AddPlanetTest() {
        driver.get("http://localhost:8080/lords_of_planets");

        String tempName = "TestAddedPlanet";
        clickAddPlanetMain();

        AddPlanetInputName.clear();
        AddPlanetInputName.sendKeys(tempName);

        clickCreateAddPlanet();

        Assert.assertTrue(MainPlanetsTable.getText().contains(tempName));
    }
    @Test
    public void EditPlanetTest() {
        driver.get("http://localhost:8080/lords_of_planets");

        String tempName = "TestEditedPlanet";
        clickEditPlanetMain();

        EditPlanetInputName.clear();
        EditPlanetInputName.sendKeys(tempName);

        clickEditSavePlanet();

        Assert.assertTrue(MainPlanetsTable.getText().contains(tempName));
    }

}
