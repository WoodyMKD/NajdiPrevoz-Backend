package tomatosolutions.najdiprevoz.utils.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FacebookPostsScheduler {
    private static final Logger logger = LoggerFactory.getLogger(FacebookPostsScheduler.class);

    @Scheduled(fixedDelay = 3600000)
    public void getFacebookPostsAsync() {
        Thread fbScraper = new ScraperThread();
        logger.info("Executing python script...");
        fbScraper.start();
    }
}
