package java.lang;

/**
 * @Title: String.java
 * @Package java.lang
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class String {

    static {
        System.out.println("My String.static initializer");
    }

    private static int i = 1;

    private int x;

    private Object obj = new Object();

    public String(){
        x = 10;
    }

    public int getValue(){
        this.obj.hashCode();
        return 1;
    }

    // 类加载的三个阶段：
    // 1.加载:查找并加载类的二进制数据
    // 2.连接：
    //   1).验证:确保被加载类的正确性。
    //   2).准备:为类的静态变量分配内存，并将其初始化为默认值
    //   3).解析:把类中的符号引用转换为直接引用
    // 3.初始化:为类的静态变量赋予正确的初始值
    //========================================================
    // Java程序对类的使用方式
    // 主动使用  被动使用
    // 所有的Java虚拟机实现必须在每个类或者接口被Java程序首次主动使用时才初始化它们，当然JVM有可能根据程序的上下文推断出接下来可能初始化谁。
    // 主动使用分类：
    //    1.new 直接使用
    //    2.访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
    //    3.调用静态方法.
    //    4.反射某个类
    //    5.初始化一个子类
    //    6.main函数 启动类

    //============================================================
    // javap -v String(class名字)  javap是 Java class文件分解器
    // 魔术因子

}
