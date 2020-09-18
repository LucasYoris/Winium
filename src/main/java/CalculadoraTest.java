import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalculadoraTest {

    static DesktopOptions options;
    static WiniumDriverService service;
    static WiniumDriver driver;

    @BeforeTest
    public static void setupEnvironment(){
        options = new DesktopOptions();
        options.setApplicationPath("C:\\Windows\\System32\\calc.exe");
        File driverPath = new File("src\\main\\resources\\driver\\Winium.Desktop.Driver.exe");
        service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
        try {
            service.start();
        } catch (IOException e) {
            System.out.println("Exception while starting WINIUM service");
            e.printStackTrace();
        }
        driver = new WiniumDriver(service,options);
    }

    @Test
    public void calculatorTest() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("8")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("Multiplicar")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("9")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("Igual a")).click();
        Thread.sleep(1000);
        String results = driver.findElement(By.id("150")).getAttribute("Name");
        Thread.sleep(1000);
        System.out.println("Result is: "+ results);
        Thread.sleep(1000);
        driver.findElement(By.name("Cerrar")).click();
    }

    @AfterTest
    public void tearDown(){
        service.stop();
    }
}

