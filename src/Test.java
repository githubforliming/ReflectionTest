import test.Mouse;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        // 方式1 通过对象获取
        Mouse mouse = new Mouse();
        Class<?> c1 = mouse.getClass();
        // 方式2 通过forName
        Class<?> c2 = Class.forName("test.Mouse");
        // 方式3 直接通过类获取
        Class<Mouse> c3 = Mouse.class;

        System.out.println("c1 == c2 :" + (c1 == c2));
        System.out.println("c2 == c3 :" + (c2 == c3));
        
        // 输出：
        // c1 == c2 :true
        // c2 == c3 :true
    }
}
