package tomatosolutions.najdiprevoz.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tomatosolutions.najdiprevoz.utils.security.JwtTokenProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class FacebookPostsScheduler {
    private static final Logger logger = LoggerFactory.getLogger(FacebookPostsScheduler.class);

    @Scheduled(fixedDelay = 600000)
    public void getFacebookPostsAsync() {
        Thread fbScraper = new ScraperThread();
        logger.info("Executing python script...");
        fbScraper.start();
    }
}
