package practise.AppiumFramework;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class ecommerce_tc_4 extends base2 {

	@Test
	public void totalValidation() throws IOException {
	// TODO Auto-generated method stubd
	service = startServer();
		AndroidDriver driver = capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");

		driver.hideKeyboard();

		driver.findElement(By.xpath("//*[@text='Female']")).click();

		driver.findElement(By.id("android:id/text1")).click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));").click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		((WebElement) driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0)).click();

		((WebElement) driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0)).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum = 0;
		for (int i = 0; i < count; i++) {
			String price1 = ((WebElement) driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"))
					.get(i)).getText();
			double amount = getAmount(price1);

			sum = sum + amount;
		}
		// String price1 = ((WebElement)
		// driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0)).getText();
		// double price1val = getAmount(price1);
		// String price2 = ((WebElement)
		// driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1)).getText();
		// double price2val = getAmount(price2);

		// double tot = price1val + price2val;

		System.out.println(sum + " sum of products");

		String total = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double totalValue = getAmount(total);

		System.out.println(totalValue + " Total value of products ");
		Assert.assertEquals(sum, totalValue);
		
		//Gestures
		
		WebElement check = driver.findElement(By.className("android.widget.CheckBox"));
	
		TouchAction t = new TouchAction(driver);
		
		t.tap(tapOptions().withElement(element(check))).perform();
		
		WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

		service.stop();
	}



	public static double getAmount(String value) {
		value = value.substring(1);
		double price2int = Double.parseDouble(value);

		return price2int;

	}

}
