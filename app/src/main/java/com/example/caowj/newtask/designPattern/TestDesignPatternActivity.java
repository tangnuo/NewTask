package com.example.caowj.newtask.designPattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.designPattern.builder.base.Builder2;
import com.example.caowj.newtask.designPattern.builder.subclass.TbBuilder2;
import com.example.caowj.newtask.designPattern.decorator.abstractComponent.House;
import com.example.caowj.newtask.designPattern.decorator.abstractComponent.mComponent.MyHouse;
import com.example.caowj.newtask.designPattern.decorator.abstractDecorator.mDecorator.DoorDecorator;
import com.example.caowj.newtask.designPattern.decorator.abstractDecorator.mDecorator.WindowDecorator;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.Factory1;
import com.example.caowj.newtask.designPattern.factory.abstractfactory.Factory2;
import com.example.caowj.newtask.designPattern.factory.factorymethod.FactoryInChina;
import com.example.caowj.newtask.designPattern.factory.factorymethod.FactoryInIndia;
import com.example.caowj.newtask.designPattern.factory.factorymethod.abstractMethod.FactoryMethod;
import com.example.caowj.newtask.designPattern.factory.simplefactory.SimpleFactory;
import com.example.caowj.newtask.designPattern.observer.iOberver.impl.Parent0;
import com.example.caowj.newtask.designPattern.observer.iOberver.impl.Parent1;
import com.example.caowj.newtask.designPattern.observer.iSubject.impl.Teacher;
import com.example.caowj.newtask.designPattern.strategy.Price;
import com.example.caowj.newtask.designPattern.strategy.mInterface.MemberStrategy;
import com.example.caowj.newtask.designPattern.strategy.mInterface.impl.AdvancedMemberStrategy;

/**
 * 设计模式简单Demo测试类
 * <br/>
 * 源码：https://github.com/binbinqq86/DesignPatten
 * <br/>
 * 博客：https://blog.csdn.net/binbinqq86/article/details/71023871
 */
public class TestDesignPatternActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_design_pattern);

        testStrategy();
    }


    /**
     * 建造者模式（builder）
     * <p>
     * https://blog.csdn.net/binbinqq86/article/details/79052236
     * </p>
     */
    private void testBuilder() {
//        写法二（推荐）：
        Builder2 builder2 = new TbBuilder2();
        String str = builder2.setAddress("china").setHost().setHouseNum(6).setStyle("田园").create().toString();
        Log.e(TAG, "test: " + str);
    }


    /**
     * 工厂模式（抽象工厂）
     */
    private void testAbstractFactory() {
        Factory1 factory1 = new Factory1();
        factory1.getCpu().printInfo();
        factory1.getMemory().printInfo();

        Factory2 factory2 = new Factory2();
        factory2.getCpu().printInfo();
        factory2.getMemory().printInfo();
    }

    /**
     * 工厂模式（工厂方法模式）
     * <p>
     * https://blog.csdn.net/binbinqq86/article/details/71775093
     * </p>
     */
    private void testFactoryMethod() {
        FactoryMethod factoryMethod = new FactoryInChina();
        factoryMethod.createPhone("mi2");

        factoryMethod = new FactoryInIndia();
        factoryMethod.createPhone("iphone8");
    }


    /**
     * 工厂模式（简单工厂）
     * <p>
     * https://blog.csdn.net/binbinqq86/article/details/71775093
     * </p>
     */
    private void testSimpleFactory() {
        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.createPhone("mi2");
    }


    /**
     * 装饰者模式
     * <p>
     * https://blog.csdn.net/binbinqq86/article/details/71487272
     * </p>
     */
    private void testDecorator() {
        //未装修的房子
        House house = new MyHouse();
        //装个门
        house = new WindowDecorator(house);
        //装个窗户
        house = new DoorDecorator(house);
        //装个空调、洗衣机...

        Log.e("Decorator:", house.getDescription());
    }


    /**
     * 策略模式
     * <p>
     * https://blog.csdn.net/zhangliangzi/article/details/52161211
     * </p>
     */
    private void testStrategy() {
        //选择并创建需要使用的策略对象
        MemberStrategy strategy = new AdvancedMemberStrategy();

        //TODO：（传入不同的会员对象，得到不同的折扣；后期新增会员类就可以实现功能扩展）
        Price price = new Price(strategy);
        //计算价格
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
    }


    /**
     * 观察者模式
     * <p>
     * https://blog.csdn.net/binbinqq86/article/details/71079609
     * </p>
     */
    private void testObserver() {
        Teacher teacher = new Teacher();

//        家长0关注了老师
        Parent0 p0 = new Parent0(teacher);
//        家长1关注了老师
        Parent1 p1 = new Parent1(teacher);

//        老师（被观察者）发送消息，所有关注过的家长都能收到消息
        teacher.sendMessage("通知：五一国际劳动节放假三天。。。");
    }
}
