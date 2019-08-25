package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaunchVanguardPage {
	ChromeDriver driver;
	WebDriverWait wait;
	By personalInvestor = By.xpath("//span[@class='icon icon-avatar_man']");

	public LaunchVanguardPage(ChromeDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void launchesVanguard() {
		driver.get("https://investor.vanguard.com/corporate-portal/");
		driver.manage().window().maximize();
	}

	public void navigateToPersonalInvestor() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(personalInvestor));
		driver.findElement(personalInvestor).click();
	}
}
