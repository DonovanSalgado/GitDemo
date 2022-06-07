package practise.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base2 {

	public static AppiumDriverLocalService service;
	public static AndroidDriver driver;
	public AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;
	}

	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(
				"src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}

	public static AndroidDriver capabilities(String appName) throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\dsalgadosalazar\\eclipse-workspace\\AppiumFramework\\src\\test\\java\\practise\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		prop.get(appName);



		DesiredCapabilities cap = new DesiredCapabilities();

		File appDir = new File("src/test/java");

		File app = new File(appDir, (String) prop.get(appName));

		String device = (String) prop.get("device");
		if (device.contains("Pixel 2 XL")) {
			try {
				startEmulator();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");// new step
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		

		return driver;
	}

	public void runCapabilities(String appName, Boolean cloud) throws IOException {
		if (cloud) {
			cloudCapabilities(appName);
		}
		{
			capabilities(appName);
		}

	}
	public static AndroidDriver cloudCapabilities(String appName) throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\dsalgadosalazar\\eclipse-workspace\\AppiumFramework\\src\\test\\java\\practise\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		prop.get(appName);

		DesiredCapabilities cap = new DesiredCapabilities();

		File appDir = new File("src/test/java");

		File app = new File(appDir, (String) prop.get(appName));

		String device = (String) prop.get("device");
		if (device.contains("Pixel 2 XL")) {
			try {
				startEmulator();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cap.setCapability("browserstack.user", "donovansalgado_KGPSWY");
		cap.setCapability("browserstack.key", "MsdNuEjZccBbdJeH2axA");

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");// new step
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		if (appName.equals("GeneralStoreApp")) {
			cap.setCapability("app", "bs://87a2982334dcd9269843f813e91c20055c685ff8");
		}
		cap.setCapability("device", "Samsung Galaxy S10e");
		cap.setCapability("os_version", "9.0");

		driver = new AndroidDriver(new URL("http://hub.browserstack.com/wd/hub"), cap);

		return driver;
	}
	// public static void getScreenshot(String s) throws IOException {
	// File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	// FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\" +
	// s + ".png"));
	// }

}
