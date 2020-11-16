package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        
        WebDriver driver = new ChromeDriver();
        
/*
        // tehtäväpohjan skenaario:
        driver.get("http://localhost:4567");        
        sleep(2);        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();        
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));        
        sleep(2);
        element.submit();
        sleep(3);        
        driver.quit();
*/
        
/* 
        // skenaario: uuden käyttäjätunnuksen luominen
        driver.get("http://localhost:4567");        
        sleep(2);        
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click(); 
        sleep(2); 
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("teuvo");
        element = driver.findElement(By.name("password"));
        element.sendKeys("testiukko1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("testiukko1");
        element = driver.findElement(By.name("signup"));
        sleep(2);
        
        element.submit();
        sleep(3);

        driver.quit();
*/

/*
        // skenaario: epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        driver.get("http://localhost:4567");
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("teuvo");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaarin");
        element = driver.findElement(By.name("login"));
        sleep(2);

        element.submit();
        sleep(3);
        
        driver.quit();
*/        

        // skenaario: uuden käyttäjätunnuksen luominen vaihtuvalla tunnuksella
        driver.get("http://localhost:4567");
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);
        
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("teuvo"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("testiukko1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("testiukko1");
        element = driver.findElement(By.name("signup"));
        sleep(2);
        
        element.submit();
        sleep(3);
        
        driver.quit();
        

/*        
        // skenaario: uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        driver.get("http://localhost:4567");
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);
        
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("teuvo"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("testiukko1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("testiukko1");
        element = driver.findElement(By.name("signup"));
        sleep(2);
        
        element.submit();        
        sleep(2);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();  
        sleep(2);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();  
        sleep(3);
        
        driver.quit();
*/        
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
