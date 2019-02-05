package com.phil.wechat.message.model.template.response;

import com.phil.modules.result.ResultState;
import lombok.Getter;
import lombok.Setter;

/**
 * 模板消息 返回的结果
 *
 * @author phil
 * @date 2017年6月30日
 */
@Getter
@Setter
public class TemplateMessageResult extends ResultState {

    private static final long serialVersionUID = 6022663524488147165L;

    private String msgid; // 消息id(发送模板消息)
}
