import test.Mouse;
import test.MyAnotation;
import test.MyFiledAnotation;
import test.MyMethodAnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class Tes04 {
    public static void main(String[] args) throws Exception {
        Class<?> c2 = Class.forName("test.Mouse");
        // 1. 获取类注解
        // 1.1 获取所有注解
        Annotation[] annotations = c2.getAnnotations();
        for (int i = 0; i <annotations.length ; i++) {
            Annotation annotation = annotations[i];
            System.out.println(annotation);
            // 通过instanceof 来判断是否是自己需要的注解
            if (annotation instanceof MyAnotation) {
                MyAnotation my = (MyAnotation)annotation;
                // 获取自己注解的方法 我之前写过一个文章 redis锁防止重复点击 就用到了自定义注解
                System.out.println(my.value());
            }
        }
        // 1.2 获取指定注解
        MyAnotation myannotation = c2.getAnnotation(MyAnotation.class);
        System.out.println(myannotation.value());

        // 2. 获取方法注解
        // 使用getMethod 会报错 ---》 Exception in thread "main" java.lang.NoSuchMethodException: test.Mouse.takeAShower()
        // 上边说过了 getMethod不能获取private方法
        Method takeAShower = c2.getDeclaredMethod("takeAShower");
        // 2.1 获取所有注解
        Annotation[] MethodAnnotations = takeAShower.getAnnotations();
        for (int i = 0; i < MethodAnnotations.length; i++) {
            Annotation annotation = MethodAnnotations[i];
            System.out.println(annotation);
            // 通过instanceof 来判断是否是自己需要的注解
            if (annotation instanceof MyMethodAnotation) {
                MyMethodAnotation my = (MyMethodAnotation)annotation;
                System.out.println(my.value());
            }
        }
        // 2.2 指定注解获取
        MyMethodAnotation myAnnotation = takeAShower.getAnnotation(MyMethodAnotation.class);
        System.out.println(myAnnotation);

        // 3. 字段注解

        Field name = c2.getDeclaredField("name");
        // 3.1 获取指定字段全部注解
        Annotation[] FiledAnnotations = name.getAnnotations();
        for (int i = 0; i < FiledAnnotations.length; i++) {
            Annotation annotation = FiledAnnotations[i];
            System.out.println(annotation);
            // 通过instanceof 来判断是否是自己需要的注解
            if (annotation instanceof MyFiledAnotation) {
                MyFiledAnotation my = (MyFiledAnotation)annotation;
                System.out.println(my.value());
            }
        }
        // 3.2 获取指定类型注解
        MyFiledAnotation annotation = name.getAnnotation(MyFiledAnotation.class);
        System.out.println("获取指定注解："+annotation.value());

    }
}
