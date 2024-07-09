package Tests;

import Pages.LoginPage;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.baseutils.BrowserManager;
import utils.javautils.BaseUtils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

public class AllTest extends BrowserManager {

    public AllTest() {
        System.out.println("AllTest constructor called.");
    }

//    @Test
//    public void Login() throws Exception {
//        System.out.println("Login test started.");
//
//        try {
//            browserRun();
//            LoginPage lp = PageFactory.initElements(BaseUtils.driver, LoginPage.class);
//
//            logStep("Enter username");
//            driver.findElement(By.id(":r0:")).sendKeys("Amol");
//
//            waitForUi(2);
//            logStep("Enter password");
//            driver.findElement(By.id(":r1:")).sendKeys("Amol@123");
//
//            logStep("Click on Submit Button");
//            driver.findElement(By.xpath("//button[@type='submit']")).click();
//
//            waitForUi(2);
//            Thread.sleep(3000);
//
//            logStep("Click on Mike button");
//            driver.findElement(By.xpath("//div[@class='MuiBox-root css-14j5rrt']")).click();
//
//            Thread.sleep(3000);
//
//            logStep("Get Text from UI");
//            String text = driver.findElement(By.xpath("//h4[@class='MuiTypography-root MuiTypography-h5 css-xilszg']")).getText();
//            System.out.println("Extracted text: " + text);
//
//            Thread.sleep(3000);
//
//            driver.findElement(By.className("game-action-button")).click();
//
//            // Create a voice manager
//            VoiceManager voiceManager = VoiceManager.getInstance();
//
//            // Select the voice
//            Voice voice = voiceManager.getVoice("kevin16");
//            if (voice == null) {
//                System.err.println("Cannot find a voice named kevin16.\nPlease specify a different voice.");
//                System.exit(1);
//            }
//
//            // Allocate the chosen voice
//            voice.allocate();
//
//            // Ensure the directory exists
//            String directoryPath = "src/main/java/Pages"; // Adjust this path as needed
//            String fileName = "output_audio"; // Adjust the file name as needed
//            String outputPath = directoryPath + "/" + fileName + ".wav";
//
//            // Create directory if it doesn't exist
//            File directory = new File(directoryPath);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // Create a SingleFileAudioPlayer
//            SingleFileAudioPlayer audioPlayer = new SingleFileAudioPlayer(outputPath, javax.sound.sampled.AudioFileFormat.Type.WAVE);
//            voice.setAudioPlayer(audioPlayer);
//
//            voice.speak(text);
//
//            audioPlayer.close();
//            // Deallocate the voice resources
//            voice.deallocate();
//
//            System.out.println("Audio file created successfully at: " + outputPath);
//
//            // Convert audio file to Base64
//            String base64Audio = convertWavToBase64(outputPath);
//            System.out.println("Base64 Audio: " + base64Audio);
//
//            // Save Base64 string to a file
//            String base64FilePath = directoryPath + "/Base64Audio.txt";
//            saveBase64ToFile(base64FilePath, base64Audio);
//            System.out.println("Base64 Audio saved successfully at: " + base64FilePath);
//
//            Thread.sleep(4000);
//            // Example click action
//            // driver.findElement(By.xpath("//button[text()='Example']")).click();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        System.out.println("Login test completed.");
//    }

//    public static void logStep(String message) {
//        System.out.println(message);
//    }


   /* @Test
    public void Login() throws Exception {
        System.out.println("Login test started.");

        try {
            browserRun();
            LoginPage lp = PageFactory.initElements(BaseUtils.driver, LoginPage.class);

            logStep("Enter username");
            driver.findElement(By.id(":r0:")).sendKeys("Amol");

            waitForUi(2);
            logStep("Enter password");
            driver.findElement(By.id(":r1:")).sendKeys("Amol@123");

            logStep("Click on Submit Button");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            waitForUi(2);
            Thread.sleep(3000);

            logStep("Click on Mike button");
            driver.findElement(By.xpath("//div[@class='MuiBox-root css-14j5rrt']")).click();

            Thread.sleep(3000);

            logStep("Get Text from UI");
            String text = driver.findElement(By.xpath("//h4[@class='MuiTypography-root MuiTypography-h5 css-xilszg']")).getText();
            System.out.println("Extracted text: " + text);

            Thread.sleep(3000);

            // Save audio file
            saveTextToAudio(text);

            // Click on game action button
            logStep("Click on Game Action Button");
            driver.findElement(By.className("game-action-button")).click();

            Thread.sleep(4000);
            driver.findElement(By.xpath("(//*[@xmlns='http://www.w3.org/2000/svg'])[2]")).click();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        System.out.println("Login test completed.");
    }

    private void saveTextToAudio(String text) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            // Create a voice manager
            VoiceManager voiceManager = VoiceManager.getInstance();

            // Select the voice
            Voice voice = voiceManager.getVoice("kevin16");
            if (voice == null) {
                System.err.println("Cannot find a voice named kevin16.\n" +
                        "Please specify a different voice.");
                System.exit(1);
            }

            // Allocate the chosen voice
            voice.allocate();

            // Ensure the directory exists
            String directoryPath = "src/main/java/Pages"; // Adjust this path as needed
            String fileName = "output_audio"; // Adjust the file name as needed
            String outputPath = directoryPath + "/" + fileName + ".wav";

            // Create directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create a SingleFileAudioPlayer
            SingleFileAudioPlayer audioPlayer = new SingleFileAudioPlayer(outputPath, javax.sound.sampled.AudioFileFormat.Type.WAVE);
            voice.setAudioPlayer(audioPlayer);

            voice.speak(text);

            audioPlayer.close();
            // Deallocate the voice resources
            voice.deallocate();

            System.out.println("Audio file created successfully at: " + outputPath);

            // Convert audio file to Base64
            String base64Audio = convertWavToBase64(outputPath);
            System.out.println("Base64 Audio: " + base64Audio);

            // Save Base64 string to a file
            String base64FilePath = directoryPath + "/Base64Audio.txt";
            saveBase64ToFile(base64FilePath, base64Audio);
            System.out.println("Base64 Audio saved successfully at: " + base64FilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String convertWavToBase64(String filePath) {
        String base64String = "";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            base64String = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64String;
    }

    private static void saveBase64ToFile(String filePath, String base64String) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/






    @Test
    public static void Login() throws Exception {
        browserRun();
        LoginPage lp = PageFactory.initElements(BaseUtils.driver,LoginPage.class);

        logStep("Enter username");
        driver.findElement(By.id(":r0:")).sendKeys("Amol");

        waitForUi(2);
        logStep("Enter password");
        driver.findElement(By.id(":r1:")).sendKeys("Amol@123");

        logStep("Click on the Login button");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        waitForUi(2);

        Thread.sleep(3000);

        logStep("Click on Start assessment button");

        driver.findElement(By.xpath("//div[@class='MuiBox-root css-14j5rrt']")).click();

        Thread.sleep(3000);

        logStep("Get Text from UI");
        assertTrue("Mike button is enables",driver.findElement(By.xpath("//h4[@class='MuiTypography-root MuiTypography-h5 css-xilszg']")).isDisplayed(),"Mike button is not enabled");

        String Text = driver.findElement(By.xpath("//h4[@class='MuiTypography-root MuiTypography-h5 css-xilszg']")).getText();

        logStep("Click on the Mike button");
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='MuiBox-root css-1l4w6pd']")));
        assertTrue("Mike button is enables",driver.findElement(By.xpath("//*[@class='MuiBox-root css-1l4w6pd']")).isDisplayed(),"Mike button is not enabled");

        element.click();

        logStep(Text);

//        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");



        AudioFormat[] formatsToTry = {
                new AudioFormat(44100.0f, 16, 2, true, false), // PCM_SIGNED 44100 Hz, 16 bit, stereo, little-endian
                new AudioFormat(16000.0f, 16, 1, true, true)   // PCM_SIGNED 16000 Hz, 16 bit, mono, big-endian
        };

        for (AudioFormat format : formatsToTry) {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            if (AudioSystem.isLineSupported(info)) {
                try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                    line.open(format);
                    line.start();
                    // Your audio processing code here
                    line.drain();
                    System.out.println("Using format: " + format);
                    break; // Exit loop if a supported format is found
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Format " + format + " is not supported.");
            }
        }






        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        logStep("Speak text in Mike");
        // Create a voice manager
        VoiceManager voiceManager = VoiceManager.getInstance();

        // Select the voice
        Voice voice = voiceManager.getVoice("kevin16");
        if (voice == null) {
            System.err.println("Cannot find a voice named kevin16.\n" +
                    "Please specify a different voice.");
            System.exit(1);
        }

        // Allocate the chosen voice
        voice.allocate();
        voice.speak(Text);

        Thread.sleep(4000);
        logStep("Click on Next Button");
        driver.findElement(By.xpath("//*[@class='MuiBox-root css-140ohgs']")).click();

        // Ensure the directory exists
        String directoryPath = "src/main/java/Pages"; // Adjust this path as needed
        String fileName = "output_audio"; // Adjust the file name as needed
        String outputPath = directoryPath + "/" + fileName + ".wav";

        // Create directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a SingleFileAudioPlayer
        SingleFileAudioPlayer audioPlayer = new SingleFileAudioPlayer(outputPath, javax.sound.sampled.AudioFileFormat.Type.WAVE);
        voice.setAudioPlayer(audioPlayer);

        voice.speak(Text);

        audioPlayer.close();
        // Deallocate the voice resources
        voice.deallocate();

        System.out.println("Audio file created successfully at: " + outputPath);

        // Convert audio file to Base64
        String base64Audio = convertWavToBase64(outputPath);
        System.out.println("Base64 Audio: " + base64Audio);

        // Save Base64 string to a file
        String base64FilePath = directoryPath + "/Base64Audio.txt";
        saveBase64ToFile(base64FilePath, base64Audio);
        System.out.println("Base64 Audio saved successfully at: " + base64FilePath);

//      driver.findElement(By.xpath("//*[@fill='url(#paint0_linear_400_1340)']")).sendKeys("https://all-dev-content-service.s3.ap-south-1.amazonaws.com/all-audio-files/en/44983c9c-48d4-4605-8e03-f7ae3932ad3e.wav");
        Thread.sleep(4000);
        driver.findElement(By.xpath("(//*[@xmlns='http://www.w3.org/2000/svg'])[2]")).click();

        String audioFilePath = "output.mp3";
//        convertTextToSpeech(Text,audioFilePath);

    }


    private static String convertWavToBase64(String filePath) {
        String base64String = "";
        try {
            File file = new File(filePath);
            byte[] bytes = Files.readAllBytes(file.toPath());
            base64String = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64String;
    }

    private static void saveBase64ToFile(String filePath, String base64String) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
