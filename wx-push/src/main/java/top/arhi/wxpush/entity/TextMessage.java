package top.arhi.wxpush.entity;


/**
 * @Description:
 * @Author: lst
 * @Date 2020-08-19
 * 1、文本消息实体，这是用户给公众号发消息，然后微信后台给服务器响应的数据实体结构
 * <xml>
 * <ToUserName>oANl56cC7d7JP88la43243WaA</ToUserName>
 * <FromUserName>gh_a1821534134</FromUserName>
 * <CreateTime>1597979297984</CreateTime>
 * <MsgType>text</MsgType>
 * <Content>你</Content>
 * </xml>
 */
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;

    private String Content;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}