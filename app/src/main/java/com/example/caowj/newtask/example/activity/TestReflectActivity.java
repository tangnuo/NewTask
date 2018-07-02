package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.Student;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * java 反射机制
 * <p>
 * https://blog.csdn.net/sinat_38259539/article/details/71799078
 * </p>
 *
 * @author Caowj
 * @Date 2018/4/27 10:30
 */
public class TestReflectActivity extends AppCompatActivity {

    private final String STUDENT_URL = "com.example.caowj.newtask.example.bean.Student";
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btn10)
    Button btn10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_reflect);
        ButterKnife.bind(this);
        btn1.setText("获取Class对象的三种方式");
        btn2.setText("获取构造方法并使用");
        btn3.setText("获取成员变量并调用");
        btn4.setText("获取成员方法并调用");
        btn5.setText("获取实体类的main方法");
        btn6.setText("利用反射和配置文件");
        btn7.setText("通过反射越过泛型检查");
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                getClassObj();
                break;
            case R.id.btn2:
                try {
                    getClassConstructors();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn3:
                try {
                    getClassFields();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn4:
                try {
                    getClassMethods();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn5:
                getClassMainMethod();
                break;
            case R.id.btn6:
                try {
                    getPropertiesFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn7:
                try {
                    jumpCheck();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn8:
                break;
            case R.id.btn9:
                break;
            case R.id.btn10:
                break;
        }
    }

    /**
     * 通过反射越过泛型检查
     *
     * @throws Exception
     */
    private void jumpCheck() throws Exception {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");

        //  strList.add(100);
        //获取ArrayList的Class对象，反向的调用add()方法，添加数据
        Class listClass = strList.getClass(); //得到 strList 对象的字节码 对象
        //获取add()方法
        Method m = listClass.getMethod("add", Object.class);
        //调用add()方法
        m.invoke(strList, 100);

        //遍历集合
        for (Object obj : strList) {
            System.out.println(obj);
        }
    }


    //此方法接收一个key，在配置文件中获取相应的value
    public String getValue(String key) throws IOException {
        Properties pro = new Properties();//获取配置文件的对象
        InputStream is = this.getAssets().open("pro.txt");
//        FileReader in = new FileReader(" pro.txt");//获取输入流
        pro.load(is);//将流加载到配置文件对象中
        is.close();
        return pro.getProperty(key);//返回根据key获取的value值
    }

    /**
     * 我们利用反射和配置文件，可以使：应用程序更新时，对源码无需进行任何修改
     * 我们只需要将新类发送给客户端，并修改配置文件即可
     */
    private void getPropertiesFile() throws Exception {

        //通过反射获取Class对象
        Class stuClass = Class.forName(getValue("className"));//"cn.fanshe.Student"
        //2获取show()方法
        Method m = stuClass.getMethod(getValue("methodName"));//show
        //3.调用show()方法
        m.invoke(stuClass.getConstructor().newInstance());
    }

    /**
     * 获取实体类的main方法（不是测试类）
     */
    private void getClassMainMethod() {
        try {
            //1、获取Student对象的字节码
            Class clazz = Class.forName(STUDENT_URL);

            //2、获取main方法
            Method methodMain = clazz.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型，
            //3、调用main方法
//             methodMain.invoke(null, new String[]{"a","b","c"});
            //第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
            //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
            methodMain.invoke(null, (Object) new String[]{"a", "b", "c"});//方式一
            // methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取成员方法并调用：
     * <p>
     * 1.批量的：
     * public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
     * public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
     * 2.获取单个的：
     * public Method getMethod(String name,Class<?>... parameterTypes):
     * 参数：
     * name : 方法名；
     * Class ... : 形参的Class类型对象
     * public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
     * <p>
     * 调用方法：
     * Method --> public Object invoke(Object obj,Object... args):
     * 参数说明：
     * obj : 要调用方法的对象；
     * args:调用方式时所传递的实参；
     * <p>
     * ):
     */
    private void getClassMethods() throws Exception {
        //1.获取Class对象
        Class stuClass = Class.forName(STUDENT_URL);
        //2.获取所有公有方法
        System.out.println("***************获取所有的”公有“方法*******************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }

        System.out.println("***************获取所有的方法，包括私有的*******************");
        methodArray = stuClass.getDeclaredMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }

        System.out.println("***************获取公有的show1()方法*******************");
        Method m = stuClass.getMethod("show1", String.class);
        System.out.println(m);
        //实例化一个Student对象
        Object obj = stuClass.getConstructor().newInstance();
        m.invoke(obj, "刘德华");

        System.out.println("***************获取私有的show4()方法******************");
        m = stuClass.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);//解除私有限定
        Object result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);
    }

    /**
     * 获取成员变量并调用：
     * <p>
     * 1.批量的
     * 1).Field[] getFields():获取所有的"公有字段"
     * 2).Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有；
     * 2.获取单个的：
     * 1).public Field getField(String fieldName):获取某个"公有的"字段；
     * 2).public Field getDeclaredField(String fieldName):获取某个字段(可以是私有的)
     * <p>
     * 设置字段的值：
     * Field --> public void set(Object obj,Object value):
     * 参数说明：
     * 1.obj:要设置的字段所在的对象；
     * 2.value:要为字段设置的值；
     */
    private void getClassFields() throws Exception {
        //1.获取Class对象
        Class stuClass = Class.forName(STUDENT_URL);
        //2.获取字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = stuClass.getFields();
        for (Field f : fieldArray) {
            System.out.println(f);
        }


        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = stuClass.getDeclaredFields();
        for (Field f : fieldArray) {
            System.out.println(f);
        }


        System.out.println("*************获取公有字段**并调用***********************************");
        Field f = stuClass.getField("name");
        System.out.println(f);
        //获取一个对象
        Object obj = stuClass.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
        //为字段设置值
        f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        Student stu = (Student) obj;
        System.out.println("验证姓名：" + stu.name);


        System.out.println("**************获取私有字段****并调用********************************");
        f = stuClass.getDeclaredField("phoneNum");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obj, "18888889999");
        System.out.println("验证电话：" + stu);

    }


    /**
     * 获取Class对象的三种方式
     * 1 Object ——> getClass();
     * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
     * 3 通过Class类的静态方法：forName（String  className）(常用)
     */
    private void getClassObj() {
        //第一种方式获取Class对象
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName(STUDENT_URL);//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
     * <p>
     * 1.获取构造方法：
     * 1).批量的方法：
     * public Constructor[] getConstructors()：所有"公有的"构造方法
     * public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
     * <p>
     * 2).获取单个的方法，并调用：
     * public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
     * public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
     * <p>
     * 调用构造方法：
     * Constructor-->newInstance(Object... initargs)
     */
    private void getClassConstructors() throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName(STUDENT_URL);

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }


        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
//        Constructor con = clazz.getConstructor(null);
        Constructor con = clazz.getConstructor(new Class[0]);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
        Object obj = con.newInstance();
        //  System.out.println("obj = " + obj);
        //  Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');
    }


}
