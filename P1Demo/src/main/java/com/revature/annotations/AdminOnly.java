package com.revature.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//This is a custom annotation!
//We can use it to annotate any controller method that we want to be accessible only by managers
@Target(ElementType.METHOD) //This annotation can only be used over methods
@Retention(RetentionPolicy.RUNTIME) //This annotation will be available at runtime
public @interface AdminOnly {
    //No need for fields etc
}
