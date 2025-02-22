package stepDefinition;

import dev.failsafe.internal.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class ContactUs_Steps {

    private WebDriver driver;

    @Before
    public void setup() {
        // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/drivers/chrome.exe");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am on the WebDriverUniversity HomePage")
    public void i_am_on_the_web_driver_university_home_page() {
        driver.get("https://webdriveruniversity.com/");

    }

    @When("I click on Contact Us Link")
    public void i_click_on_contact_us_link() {
        driver.findElement(By.id("contact-us")).click();
    }

    @When("I enter a First Name")
    public void i_enter_a_first_name() throws InterruptedException {
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentID = it.next();
        String childID = it.next();
        driver.switchTo().window(childID);

        driver.findElement(By.name("first_name")).sendKeys("Marshal");

    }

    @When("I enter a Last Name")
    public void i_enter_a_last_name() {
        driver.findElement(By.name("last_name")).sendKeys("Mathers");
    }

    @When("I enter an email address")
    public void i_enter_an_email_address() {
        driver.findElement(By.name("email")).sendKeys("marhal@gmail.com");
    }

    @When("I enter my Comment")
    public void i_enter_my_comment() {
        driver.findElement(By.name("message")).sendKeys("Hello World, Nice to meet yaa ..");
    }

    @When("I click on Submit button")
    public void i_click_on_submit_button() {
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    @Then("I should be represented a successful submission of the form")
    public void i_should_be_represented_a_successful_submission_of_the_form() {
        String url = driver.getCurrentUrl();
        Assert.isTrue(url.contains("contact-form-thank-you.html"), "Incorrect URL ...");

        String message = driver.findElement(By.tagName("h1")).getText();
        Assert.isTrue(message.equals("Thank You for your Message!"), "Incorrect message ...");
    }

    @And("I click on Reset button")
    public void iClickOnResetButton() {
        driver.findElement(By.xpath("//input[@value='RESET']")).click();
    }

    @Then("my form should be empty once again")
    public void myFormShouldBeEmptyOnceAgain() {
        WebElement fName = driver.findElement(By.name("first_name"));
        String s = fName.getText();
        Assert.isTrue(s.equals(""), "No");
    }
}
