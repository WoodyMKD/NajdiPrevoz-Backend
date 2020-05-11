package tomatosolutions.najdiprevoz.utils.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tomatosolutions.najdiprevoz.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScraperThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(ScraperThread.class);

    @Override
    public void run() {
        logger.info("(JAVA) Scraping started...");
        String pythonLocation = Constants.PYTHON_LOCATION;
        String script = Constants.SCRIPT_LOCATION;
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
                logger.info("(PYTHON): " + line);
            process.waitFor();
            logger.info("(JAVA) Scraping finished...");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
