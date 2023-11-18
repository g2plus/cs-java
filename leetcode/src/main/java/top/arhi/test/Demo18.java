package top.arhi.test;

import org.junit.Assert;
import org.junit.Test;
import top.arhi.util.SecureUtil;

public class Demo18 {
    @Test
    public void test() {
        String msg = "I Love You";
        String s1 = SecureUtil.encrypt(msg);
        System.out.println(s1);
        String s2 = SecureUtil.decrypt(s1);
        System.out.println(s2);
        Assert.assertEquals(msg, s2);
    }
}
