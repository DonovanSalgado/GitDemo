package practise.AppiumFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class ApiDemoTest extends base2 {

	@Test
	public void apiDemo() throws IOException, InterruptedException {
		service.start();
		// TODO Auto-generated method stub
		AndroidDriver driver = capabilities("apiDemo");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		// driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
		// driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference
		// dependencies']")).click();

		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("hello");
		// driver.findElements(By.className("android.widget.Button")).get(1);
		service.stop();
	}

}
