package cn.targetpath.springbatch.itemReaderFromDB;

/**
 * 用户实体
 *
 * @author DengBo_Zhong
 * @Date 2020/9/7 23:28
 * @Version V1.0
 */
public class User {
    private int id;
    private String name;
    private String pwd;
    private int age;

    public User() {
    }

    public User(int id, String name, String pwd, int age) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd(String string) {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge(int anInt) {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                '}';
    }
}
