package MultipleTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class DownloadTests extends TestObject{
    @Test
    public void testDownload() throws InterruptedException {
        WebDriver webDriver = getDriver();
        webDriver.navigate().to("https://web.archive.org/web/20250112093337/http://demoqa.com/upload-download");
        WebElement downloadButton = webDriver.findElement(By.id("downloadButton"));
        downloadButton.click();

        String fileName = "sampleFile.jpeg";
        File file = new File(DOWNLOAD_DIR.concat(fileName));
        Assert.assertTrue(isFileDownloaded(file), "The file is not downloaded!");
    }

    private boolean isFileDownloaded(File file) throws InterruptedException{
        int waitTime = 20;
        int counter = 0;

        while (counter < waitTime){
            if (file.exists()){
                return  true;
            }
            Thread.sleep(1000);
            counter++;
        }
        return false;
    }
}
