package com.jojo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestTask {

    private static Logger log = LoggerFactory.getLogger(TestTask.class);

    @Scheduled(fixedRate = 5000)
    public void runPerFiveSeconds() {
        log.info("fix");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void runCron() {
        log.info("cron");
    }
}
