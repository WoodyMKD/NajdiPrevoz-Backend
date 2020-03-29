package tomatosolutions.najdiprevoz.schedulers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScraperThread extends Thread {
    @Override
    public void run() {
        System.out.println("(JAVA) Scraping started...");
        String pythonLocation = "\"C:\\Users\\Woody's PC\\AppData\\Local\\Programs\\Python\\Python38-32\\python.exe\"";
        String script = "\"C:\\Users\\Woody's PC\\Desktop\\facebook-scraper-selenium-master\\scraper.py\"";
        String arg1 = "-g 125093080970970";
        String arg2 = "-d 3";
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    pythonLocation,
                    script,
                    arg1,
                    arg2
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println("(PYTHON): " + line);
            process.waitFor();
            System.out.println("(JAVA) Scraping finished...");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
