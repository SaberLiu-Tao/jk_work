package lt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements ApplicationContextAware  {
    private String name;
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user1 = (User) context.getBean("user1");
        System.out.println(user1.getName());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }
}
