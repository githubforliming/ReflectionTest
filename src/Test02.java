import test.MyFiledAnotation;
import test.MyMethodAnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class<?> c2 = Class.forName("test.Mouse");

        // 1. 类名
        System.out.println("包名.类名"+c2.getName());
        System.out.println("类名"+c2.getSimpleName());
        System.out.println("包名.类名"+c2.getTypeName());
        System.out.println("包名.类名"+c2.getCanonicalName());

        // 2. 获取所有属性 包括private
        // c2.getFields() 不能获取private的属性
        Field[] declaredFields = c2.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field filed = declaredFields[i];
            System.out.println("字段名称："+filed.getName());
            Annotation[] annotations = filed.getAnnotations();
            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];
                System.out.println(filed.getName()+"的注解:"+annotation);
                if (annotation instanceof MyFiledAnotation) {
                    MyFiledAnotation my = (MyFiledAnotation) annotation;
                    System.out.println("可以根据注解获取自定义相关内容："+my.value());
                }
            }
        }
        //
        // 2.2  通过指定字段名获取
        // 抛异常：java.lang.NoSuchFieldException  getField不能获取private属性
        try {
            Field name01 = c2.getField("password");
        } catch (Exception e) {
            System.out.println("异常："+e);
        }
        // 2.3 getDeclaredField可以获取private的属性
        Field name02 = c2.getDeclaredField("password");
        System.out.println("getField可以获取private属性："+name02);

        // 3. 获取所有方法
        // getMethods 获取不到private的方法,getDeclaredMethods可以
        Method[] methods = c2.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String name = method.getName();
            // 无关的方法wait equals hashCode toString getClass notify notifyAll 等
            if (!"say".equals(name) && !"run".equals(name) && !"takeAShower".equals(name)) {
                continue;
            }
            System.out.println("方法名："+method.getName());
            Annotation[] annotations = method.getAnnotations();
            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];
                System.out.println(name+"注解："+annotation);
                if(annotation instanceof MyMethodAnotation) {
                    MyMethodAnotation my = (MyMethodAnotation) annotation;
                    System.out.println("可以根据注解获取自定义相关内容:"+my.value());
                }
            }
        }
        // 3.2 获取指定方法  getDeclaredMethod(方法名，可变长度参数列表)
        // 与获取Filed一样 getMethod(方法名，可变长度参数列表) 也不能获取
        Method say = c2.getDeclaredMethod("say", String.class);
        Method run = c2.getDeclaredMethod("run", null);
        System.out.println("say:"+say);
        System.out.println("run:"+run);

        // 4. 特殊的方法 -》 构造方法
        // 同理getConstructors不能获取private的构造方法
        Constructor<?>[] constructors = c2.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            Constructor<?> c = constructors[i];
            System.out.println(c);
        }
        // 4.2 根据构造函数参数获取构造函数 一般直接用DeclaredXXX
        Constructor<?> constructor = c2.getDeclaredConstructor(String.class);
        System.out.println("获取参数是String的构造函数："+constructor);
    }
}
