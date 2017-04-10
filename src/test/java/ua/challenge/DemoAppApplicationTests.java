package ua.challenge;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.db.entity.Activity;
import ua.challenge.service.ActivityService;
import ua.challenge.service.impl.ActivityServiceImpl;

import java.util.concurrent.Executor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoAppApplication.class)
@WebAppConfiguration
@EnableAsync
@Log4j2
public class DemoAppApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Bean
	public ActivityService activityService(){
		return new ActivityService() {
			@Override
			public void save(Activity activity) {
			}

			@Override
			@SneakyThrows
			@Async("taskExecutor")
			public void process(String actionName) {
				log.debug("Current action: " + actionName);
				Thread.sleep(2000);
			}
		};
	}

	@Bean(name = "taskExecutor")
	public Executor threadPoolTaskExecutor() {
		/*SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
		executor.setConcurrencyLimit(5);*/
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(4);
		return executor;
	}
}
