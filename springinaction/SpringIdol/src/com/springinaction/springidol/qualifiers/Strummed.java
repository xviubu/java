package com.springinaction.springidol.qualifiers;

import java.lang.annotation.*;
//import org.springframework.beans.factory.annotation.Qualifier;
import javax.inject.Qualifier;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Strummed
{

}
