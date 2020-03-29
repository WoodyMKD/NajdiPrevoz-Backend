package tomatosolutions.najdiprevoz.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class FacebookPostsScheduler {

    @Scheduled(fixedDelay = 600000)
    public void getFacebookPostsAsync() {
        Thread fbScraper = new ScraperThread();
        System.out.println("Executing python script...");
        fbScraper.start();
    }
}
