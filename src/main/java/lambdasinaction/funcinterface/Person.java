package lambdasinaction.funcinterface;

/**
 * @program Java8InAction
 * @description:
 * @author: liuning11
 * @create: 2019/11/11
 */
public class Person {
    private String name;
    private int age;
    private String nation;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
