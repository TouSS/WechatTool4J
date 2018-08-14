package xx.wechat.tools.annotation;

import xx.wechat.tools.bean.message.Message;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageMapping {
    String name() default "";
    String type() default "";
    Class<?> clazz() default Message.class;
}
