package newpackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

public class ZeroOriginalFsmTests {

	public static int addCounter = 0;

	public static void main(String[] args) throws Exception {
		
		// This path should be your local folder
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alper Silistre\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebElement element = null;
		
		String baseUrl = "http://localhost/iselta/";
		
		driver.get(baseUrl); 
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	
    	element = driver.findElement(By.id("lang_en"));
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	
    	element = driver.findElement(By.xpath("//*[@id=\"page_container\"]/div[4]/form/input[2]"));
    	element.sendKeys("heide");
    	element = driver.findElement(By.id("qlogin_pwd"));
    	element.sendKeys("heide");
    	element = driver.findElement(By.xpath("//*[@id=\"page_container\"]/div[4]/form/input[4]"));
    	element.click();
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		// This path should be a local text file
		//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\0 Original FSM generated test cases.txt"));
		//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\1 add with empty boxes FSM generated test cases.txt"));
		//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\2 update with empty boxs FSM generated test cases.txt"));
		//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\3 add with empty number of packages box FSM .txt"));
		//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\4 add with empty price box FSM generated test cases.txt"));
		//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\5 add with empty title box FSM generated test cases.txt"));
    	//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\6 update with empty title box FSM generated test cases.txt"));
    	//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\7 update with empty price box FSM generated test cases.txt"));
    	BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Alper Silistre\\Desktop\\5 Final Test cases\\8 update with empty number box FSM generated test cases.txt"));
		
		int counter = 1;
		Boolean isAdditionalRowsDeleted = false;
		
		String line;
		while((line = in.readLine()) != null)
		{
			System.out.print(line);
			char[] chars = line.toCharArray();
						
			Boolean testResult = false;
			
			WebElement input = null;
			
			input = driver.findElement(By.cssSelector("a[href*='?content=main']"));
			input.click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			input = driver.findElement(By.cssSelector("a[href*='?content=product']"));
			input.click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			input = driver.findElement(By.xpath("//*[@id=\"leftBox\"]/div/div/table[1]/tbody/tr/td[4]/input[2]"));
			input.click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			input = driver.findElement(By.cssSelector("input[name*='btn_gotoStep_5']"));
			input.click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			
			if(!isAdditionalRowsDeleted) {
				for(int i = 0; i < 9; i++) {
					deleteIfExist(driver, "//*[@id=\"leftBox\"]/div/div[2]/table[1]/tbody/tr[3]/td[8]/input");
				}
				isAdditionalRowsDeleted = true;
			}
			
			for(int i = 0; i < chars.length; i++) {
				testResult = ApplyStage(chars[i], driver, baseUrl, counter);
				if(!testResult) {
					System.out.println(" - F");
					break;
				}
			}
			
			if(testResult) {
				System.out.println(" - P");
			}
			
			System.out.println("----");
			counter++;
		}
		
		in.close();
    			   
		driver.close();
    }
	
	public static boolean ApplyStage(char x, WebDriver driver, String baseUrl, int counter) throws Exception {
		
		WebElement input = null;
		Boolean isSaveButtonExist = false;
		Boolean isCancelButtonExist = false;
		Boolean isAddButtonExist = false;
		Boolean isTitleErrorExist = false;
		Boolean isPriceErrorExist = false;
		Boolean isNumberErrorExist = false;
		
		switch (x) {
	        case 'k':
	        	input = driver.findElement(By.xpath("//*[@id=\"leftBox\"]/div/div[2]/table[1]/tbody/tr[2]/td[7]/input"));
	        	input.click();   	
	        	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	        	isSaveButtonExist = existsElement(driver, "input[name*='btn_saveSpecial']");
	        	if(!isSaveButtonExist) {
	        		return false;
	        	}
                break;
	        case 'u': 
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Title]']"));
	        	input.clear();
	        	input.sendKeys("Test" + counter);
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                break;
	        case 'x': 
	        	Boolean isEnabled = isEnabled(driver, "input[name*='frm_arr_product[Specials][NumberOfPackages]']");
	        	if(isEnabled) {
	        		input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][NumberOfPackages]']"));	        			
	        	}
	        	else {
	        		input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][AvailableContingent]']"));
	        	}
	        	
	        	input.clear();
	        	input.sendKeys(Integer.toString(counter));
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                break;
	        case 'y': 
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Price]']"));
	        	input.clear();
	        	input.sendKeys(Integer.toString(counter));
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                break;
	        case 'z': 
	        	input = driver.findElement(By.cssSelector("textarea[name*='frm_arr_product[Specials][DescriptionNational]']"));
	        	input.clear();
	        	input.sendKeys("Test" + counter);
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                break;
	        case 'l':
	        	isSaveButtonExist = existsElement(driver, "input[name*='btn_saveSpecial']");
	        	isCancelButtonExist = existsElement(driver, "input[name*='btn_cancelSpecial']"); 
	        	if(!isSaveButtonExist || !isCancelButtonExist) {
	        		return false;
	        	}
	        	input = driver.findElement(By.cssSelector("input[name*='btn_saveSpecial']"));
	        	input.click();
	        	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	        	
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Title]']"));
	        	isTitleErrorExist = false;
	        	String a = input.getCssValue("background-color");
	        	String b = "rgba(153, 0, 0, 1)"; 
	        	if(a.equals(b)) {
	        		isTitleErrorExist = true;
	        	}
	        		        
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Price]']"));
	        	isPriceErrorExist = false;
	        	a = input.getCssValue("background-color");
	        	b = "rgba(153, 0, 0, 1)"; 
	        	if(a.equals(b)) {
	        		isPriceErrorExist = true;
	        	}
	        	
	        	isAddButtonExist = existsElement(driver, "input[name*='frm_arr_product[Specials][AvailableContingent]");
	        	if(isAddButtonExist) {
	        		input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][AvailableContingent]']"));
	        	}
	        	else {
	        		input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][NumberOfPackages]']"));
	        	}
	        	
	        	isNumberErrorExist = false;
	        	a = input.getCssValue("background-color");
	        	b = "rgba(153, 0, 0, 1)"; 
	        	if(a.equals(b)) {
	        		isNumberErrorExist = true;
	        	}
	        	
	        	if(isTitleErrorExist || isPriceErrorExist || isNumberErrorExist) {
	        		return false;
	        	}
                break;
	        case 'r':
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Title]']"));
	        	input.clear();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Price]']"));
	        	input.clear();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	input = driver.findElement(By.cssSelector("textarea[name*='frm_arr_product[Specials][DescriptionNational]']"));
	        	input.clear();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	Boolean isEnabledForClear = isEnabled(driver, "input[name*='frm_arr_product[Specials][NumberOfPackages]']");
	        	if(isEnabledForClear) {
	        		input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][NumberOfPackages]']"));	        			
	        	}
	        	else {
	        		input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][AvailableContingent]']"));
	        	}
	        	input.clear();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                break;
	        case 't':
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Title]']"));
	        	input.clear();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	break;
	        case 'p':
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Price]']"));
	        	input.clear();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	break;
	        case 'n':
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][AvailableContingent]']"));
	        	input.clear();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	break;
	        case 'v':
	        	isAddButtonExist = existsElement(driver, "input[name*='btn_addSpecial']");	        	 
	        	if(!isAddButtonExist) {
	        		return false;
	        	}
	        	
	        	if(addCounter > 7) {
	        		for(int i = 0; i < 8; i++) {
						deleteIfExist(driver, "//*[@id=\"leftBox\"]/div/div[2]/table[1]/tbody/tr[3]/td[8]/input");
					}
	        		addCounter = 0;
	        	}
	        	
	        	// Date pickers
	        	driver.findElement(By.xpath("//*[@id=\"leftBox\"]/div/div[2]/table[2]/tbody/tr[2]/td[2]/img[1]")).click();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	driver.findElement(By.xpath("//*[@id=\"datepicker\"]/table/tbody/tr[7]/td[1]")).click();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	driver.findElement(By.xpath("//*[@id=\"leftBox\"]/div/div[2]/table[2]/tbody/tr[2]/td[2]/img[2]")).click();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	driver.findElement(By.xpath("//*[@id=\"datepicker\"]/table/tbody/tr[7]/td[2]")).click();
	        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        	
	        	driver.findElement(By.cssSelector("input[name*='btn_addSpecial']")).click();
	        	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	        	
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Title]']"));
	        	isTitleErrorExist = false;
	        	String c = input.getCssValue("background-color");
	        	String d = "rgba(153, 0, 0, 1)"; 
	        	if(c.equals(d)) {
	        		isTitleErrorExist = true;
	        	}
	        		        
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][Price]']"));
	        	isPriceErrorExist = false;
	        	c = input.getCssValue("background-color");
	        	d = "rgba(153, 0, 0, 1)"; 
	        	if(c.equals(d)) {
	        		isPriceErrorExist = true;
	        	}
	        		        	
	        	input = driver.findElement(By.cssSelector("input[name*='frm_arr_product[Specials][NumberOfPackages]']"));
	        	isNumberErrorExist = false;
	        	c = input.getCssValue("background-color");
	        	d = "rgba(153, 0, 0, 1)"; 
	        	if(c.equals(d)) {
	        		isNumberErrorExist = true;
	        	}
	        	
	        	if(isTitleErrorExist || isPriceErrorExist || isNumberErrorExist) {
	        		return false;
	        	}
	        	addCounter++;
                break;
            default:
            	break;
        }
		
		return true;
	}
	
	private static boolean existsElement(WebDriver driver, String id) {
	    try {
	        driver.findElement(By.cssSelector(id));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	private static void deleteIfExist(WebDriver driver, String id) {
		WebElement input = null;		
		try {
			input = driver.findElement(By.xpath(id));
			input.click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.switchTo().alert().accept();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		} catch (NoSuchElementException e) {
	    }
	}
	
	private static boolean isEnabled(WebDriver driver, String id) {
		Boolean isEnabled;
        WebElement element = driver.findElement(By.cssSelector(id));
        isEnabled = element.isEnabled();	    
	    return isEnabled;
	}
}
