package top.arhi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.arhi.anno.CustomSerializer;
import top.arhi.rule.IdCardRule;
import top.arhi.rule.PasswordRule;
import top.arhi.rule.PhoneRule;
import top.arhi.rule.UserNameRule;

import java.io.Serializable;

/**
 * @Author
 * @Description
 * @Date 2021/3/1 14:01
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class DemoRespDTO implements Serializable {

    private static final long serialVersionUID = 1019466745376831818L;

    @CustomSerializer(UserNameRule.class)
    private String userName;

    @CustomSerializer(PhoneRule.class)
    private String phone;

    @CustomSerializer(IdCardRule.class)
    private String idCard;

    @CustomSerializer(PasswordRule.class)
    private String password;

    /**
     * 隐藏前面10个字符
     */
    @CustomSerializer(pattern = "\\S{10}(\\S*)", format = "**********$1")
    private String customValue;

}
