package ua.challenge.spring.async;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.challenge.DemoAppApplicationTests;
import ua.challenge.service.ActivityService;

/**
 * Created by d.bakal on 10.04.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoAppApplicationTests.class})
public class ThreadPoolTaskExecutorTest {
    @Autowired
    private ActivityService activityService;

    @Test
    @SneakyThrows
    public void asyncExecution() {
        activityService.process("test");
        activityService.process("test2");
        activityService.process("test3");
        activityService.process("test4");
        activityService.process("test5");
        activityService.process("test6");
        activityService.process("test7");
        activityService.process("test8");
        activityService.process("test9");
        activityService.process("test10");
        activityService.process("test11");
        activityService.process("test12");
        activityService.process("test14");
        activityService.process("test15");
        Thread.sleep(10000);
    }
}
