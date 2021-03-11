package com.atguigu.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author luzc
 * @date 2021/3/9 16:37
 * @desc 声明式事务
 * 1：导入相关依赖
 * 数据源、数据库驱动、spring-jdbc模块
 * 2： 配置数据源、JDBCTemplate (spring 提供简化数据库操作的工具）操作数据
 * 3 给方法上标注 @Transactional 表示挡墙方法是一个事务方法
 * 4 @EnableTransactionManagement 开启基于注解的事务管理功能
 * 5 配置事务管理器来控制事务 加在容器中
 *  public PlatformTransactionManager transactionManager()
 *
 * 6 原理
 * 1） @EnableTransactionManagement 利用
 *          TransactionManagementConfigurationSelector 给容器中会导入组件
 *          导入两个组件
 *              AutoProxyRegistrar
 *                  给容器中 注册 InfrastructureAdvisorAutoProxyCreator
 *                      InfrastructureAdvisorAutoProxyCreator 的功能点：
 *                          利用后置处理器机制，在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用
 *                          类似AOP机制
 *              ProxyTransactionManagementConfiguration
 *                  1 给容器中添加事务增强器
 *                      1 事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource 解析事务注解
 *                      2 事务拦截器 TransactionInterceptor 保存了事务的属性信息，事务管理器
 *                          是一个 MethodInterceptor -> invoke -> invokeWithinTransaction 在目标方法执行的时候
 *                              执行拦截器链
 *                              事务拦截器连：
 *
 *                                  final TransactionAttribute txAttr = getTransactionAttributeSource().getTransactionAttribute(method, targetClass);
 *                                  1) 获取事务相关的属性
 *
 * 		                            final PlatformTransactionManager tm = determineTransactionManager(txAttr);
 * 		                            2）再获取 PlatformTransactionManager，如果事先没有添加指定任何 TransactionManager
 * 		                                String qualifier = txAttr.getQualifier();
 * 		                                最终会从容器中按照类型获取一个 PlatformTransactionManager
 *
 * 		                            final String joinpointIdentification = methodIdentification(method, targetClass, txAttr);
 * 		                            3） 执行目标方法
 * 		                                如果异常，获取到事物管理器，进行回滚操作
 * 		                                try {
                                        * 				retVal = invocation.proceedWithInvocation();
                                        *                        }
                                        * 			catch (Throwable ex) {
                                        * 				// target invocation exception
                                        * 				completeTransactionAfterThrowing(txInfo, ex);
                                        * 				throw ex;
                                        *            }
                                        * 			finally {
                                        * 				cleanupTransactionInfo(txInfo);
                                        *            }
 *                                      如果正常，利用事物管理器，提交事务
 *  *
 *
 *                  3
 *
 *
 */

@EnableTransactionManagement
@ComponentScan("com.atguigu.tx")
@Configuration
public class TxConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/gmall");
        return dataSource;
    }

    @Bean
    public String test() {
        return "abc";
    }

    // spring 对配置文件（configuration）特殊处理，方法如果是给容器中加组件，多次调用都只是从容器中找组件

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    // 注册事务管理器在容器中
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}
