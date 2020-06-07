package com.ypdaic.mymall.common.annotation;

import com.ypdaic.mymall.common.enums.NeedAuthEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
@Inherited
@Documented
public @interface NeedAuth {
    @AliasFor("needAuth")
    NeedAuthEnum value() default NeedAuthEnum.TOKEN;

    @AliasFor("value")
    NeedAuthEnum needAuth() default NeedAuthEnum.TOKEN;
}
