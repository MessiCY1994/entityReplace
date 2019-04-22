package com.messiyang.modelreplace.util;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityComment{
    String value() default "";
}
