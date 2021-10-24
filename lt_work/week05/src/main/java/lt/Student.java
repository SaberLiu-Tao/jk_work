package lt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.aop.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Local {


    @Autowired
    private User user;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object student1 =  context.getBean("student1");
        Local local = context.getBean(Local.class);
        System.out.println("local 代理类型是"+local.getClass());
        local.InStudy();

    }

    @Override
    public void InStudy() {
        System.out.println("do something");
    }
}
