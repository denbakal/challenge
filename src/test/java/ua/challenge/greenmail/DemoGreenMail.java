package ua.challenge.greenmail;

import com.google.gson.Gson;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import lombok.*;
import org.junit.Rule;
import org.junit.Test;

import javax.mail.internet.MimeMessage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 13.11.2016.
 */
public class DemoGreenMail {
    @Rule
//    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP_IMAP);

    @Test
    public void testSend() {
        GreenMailUtil.sendTextEmailTest("to@challenge.com", "from@challenge.com", "Test sending", "Sample text");
        assertThat(GreenMailUtil.getBody(greenMail.getReceivedMessages()[0])).isEqualTo("Sample text");
    }

    @Test
    public void testReceive() {
        GreenMailUser user = greenMail.setUser("test@challenge.com", "test", "123456");
//        user.deliver();

        GreenMailUtil.sendTextEmailTest("to@challenge.com", "from@challenge.com", "Test sending", "Sample text");
        assertThat(greenMail.getReceivedMessages().length).isEqualTo(1);
    }

}
