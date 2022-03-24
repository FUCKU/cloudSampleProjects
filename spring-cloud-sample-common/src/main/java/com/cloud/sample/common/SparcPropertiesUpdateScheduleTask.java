package com.cloud.sample.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;

/**
 * ScheduleTask for update app properties
 */
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class SparcPropertiesUpdateScheduleTask {

    private final RefreshEndpoint refreshEndpoint;

    @Scheduled(cron = "0 */2 * * * ?")
    public void refreshContextPeriodically() {
        Collection<String> keys = refreshEndpoint.refresh();
    }


}