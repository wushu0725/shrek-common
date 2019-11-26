package com.shrek.redis.starter.annotation;


import com.shrek.redis.starter.adapter.RedisAdapter;
import com.shrek.redis.starter.core.RedisEnvironment;
import com.shrek.redis.starter.handler.RedisClusterProcessor;
import com.shrek.redis.starter.handler.RedisSentinelProcessor;
import com.shrek.redis.starter.handler.RedisSingleProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * redis 配置初始化
 * spring  类继承ImportBeanDefinitionRegistrar , BeanFactoryAware
 * @author 吴署
 */
public class RedisConfigRegistrar implements ImportBeanDefinitionRegistrar , BeanFactoryAware{

    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        //得到启动类注解的@EnableRedis(value = RedisModeConstants.REDIS_CLUSTER)
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(
                EnableRedis.class.getName()));

        String redisMode = attributes.getString("value");

        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) this.beanFactory;
        // 通过BeanDefinitionBuilder创建bean定义,
        // 并注册到spring bean factory 工厂
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedisEnvironment.class);
        defaultListableBeanFactory.registerBeanDefinition(RedisEnvironment.class.getName(),beanDefinitionBuilder.getBeanDefinition());

        beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedisAdapter.class);
        defaultListableBeanFactory.registerBeanDefinition(RedisAdapter.class.getName(),beanDefinitionBuilder.getBeanDefinition());

        beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedisSingleProcessor.class);
        defaultListableBeanFactory.registerBeanDefinition(RedisSingleProcessor.class.getName(),beanDefinitionBuilder.getBeanDefinition());

        beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedisClusterProcessor.class);
        defaultListableBeanFactory.registerBeanDefinition(RedisClusterProcessor.class.getName(),beanDefinitionBuilder.getBeanDefinition());

        beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedisSentinelProcessor.class);
        defaultListableBeanFactory.registerBeanDefinition(RedisSentinelProcessor.class.getName(),beanDefinitionBuilder.getBeanDefinition());


        factoryInit(redisMode);
    }

    /**
     * 修改bean中RedisEnvironment的是否初始化属性
     * 和设置bean中RedisAdapter的redis客户端类型
     * @param redisMode
     */
    private void factoryInit(String redisMode) {
        RedisEnvironment redisEnvironment = (RedisEnvironment) this.beanFactory.getBean(RedisEnvironment.class.getName());
        redisEnvironment.setRedisConnectionFactoryIsInit(Boolean.TRUE);

        RedisAdapter redisAdapter = (RedisAdapter) this.beanFactory.getBean(RedisAdapter.class.getName());
        redisAdapter.setRedisMode(redisMode);
    }

    /**
     * 重写BeanFactoryAware的setBeanFactory，
     * 将spring的beanFactory赋给this.beanFactory
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }
}
