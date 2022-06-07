package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Preferencess {



	public Preferencess(AppiumDriver<MobileElement> driverPrevious) {

		new AppiumFieldDecorator(driverPrevious);
		
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
	public WebElement Dependencies;

	@AndroidFindBy(className = "android.widget.Button")
	public List<WebElement> button;
}
