package lambdasinaction.chap9;

/**
 * 菱形继承问题
 *
 * @author: liuning11
 * @date: 2019-11-11
 */
public class Diamond {

    public static void main(String... args) {
        new D().hello();
    }

    static interface A {
        public default void hello() {
            System.out.println("Hello from A");
        }
    }

    static interface B extends A {
    }

    static interface C extends A {
    }

    static class D implements B, C {

    }
}
