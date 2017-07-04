package com.winter_ing.jpcc.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SignAnnotation
{

    public TYPE TypeName() default TYPE.DEFAULTTYPE;

    public enum TYPE
    {
        HANDLER, DATATYPE, DEFAULTTYPE
    };
}
