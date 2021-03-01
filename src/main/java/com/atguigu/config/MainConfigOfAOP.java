package com.atguigu.config;

import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 *
 *
 * @author luzc
 * @date 2021/2/2 11:15
 * @desc
 *
 *      AOP:
 *          指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 *
 *      1：导入AOP模块：spring AOP spring-aspects
 *      2: 定义一个业务逻辑类（MathCalculator）；在业务逻辑运行的时候将日志打印（方法之前、方法运行结束之后、方法出现异常）
 *      3：定义一个日志切面类（LogAspects）：切面类里面的方法需要动态感知 MathCalculator.div 运行到哪里
 *          通知方法：
 *              前置通知：（@Before）logStart: 在目标方法（div）运行之前运行
 *              后置通知；(@After)logEnd:在目标方法（div）运行结束之后运行,无论方法正常结束还是异常结束
 *              返回通知：(@AfterReturning)logReturn:在目标方法（div）正常返回运行
 *              异常通知：(@AfterThrowing)logException:在目标方法（div）出现异常
 *              环绕通知：(@Around)动态代理，手动推进目标方法运行（joinPoint.procced()）,最底层的通知
 *      4: 给切面类的目标方法标书何时何地运行
 *      5: 将切面类和业务逻辑类（切入目标类）都加入到容器中
 *      6： 必须告诉spring哪个类是切面类（给切面类上加注解 @Aspect）
 *      7：需要给配置类 @EnableAspectJAutoProxy ，启用AspectJ 自动代理，开启基于注解的AOP模式
 *          在spring中有很多@Enablexxx,用于开启
 *
 *  总的来说三步：
 *      1 将业务逻辑组件和切面类都加入到容器中，告诉spring哪个是切面类（@Aspect)
 *      2 在切面类上的每一个通知方法上标注通知注解，告诉spring何时何地运行（切入点表达式）
 *      3 开启基于注解的aop模式：@EnableAspectJAutoProxy
 *
 *  AOP原理 核心思想【看给容器中注册了什么组件，这个组件什么时候工作，这个组件工作时候的功能】
 *      入手： @EnableAspectJAutoProxy
 *      {@Link @EnableAspectJAutoProxy}
 *          1 @  是什么
 *              @Import(AspectJAutoProxyRegistrar.class) 给容器中导入 AspectJAutoProxyRegistrar
 *                  利用AspectJAutoProxyRegistrar  自定义给容器中注册bean
 *                  internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *             给容器中 注册一个 AnnotationAwareAspectJAutoProxyCreator（翻译一下：注解装配模式的AspectJ切面自动代理创建器）
 *
 *         2 AnnotationAwareAspectJAutoProxyCreator
 *                  -》 AspectJAwareAdvisorAutoProxyCreator
 *                      -》 AbstractAdvisorAutoProxyCreator
 *                          -》 AbstractAutoProxyCreator
 *                              implements SmartInstantiationAwareBeanPostProcessor（后置处理器）, BeanFactoryAware（bean工厂）
 *                             关注后置处理器（在bean初始化完成前后做的事情）
 *                             BeanFactoryAware（自动装配）
 *
 *          AbstractAutoProxyCreator.setBeanFactory() Aware接口的逻辑
 *          AbstractAutoProxyCreator.postProcessBeforeInstantiation 后置处理器的逻辑
 *
 *          AbstractAdvisorAutoProxyCreator.setBeanFactory() ->initBeanFactory
 *
 *          AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 *
*  流程：
 *      1 传入配置类、创建ioc容器
 *      2 注册配置类，调用refresh() 刷新容器（初始化容器）
 *      3 registerBeanPostProcessors(beanFactory); 注册bean的后置处理器来方便拦截bean的创建
 *          1 先获取ioc容器中已经定义了的需要创建对象的所有BeanPostProcessor
 *          2 给容器中加别的BeanPostProcessor
 *          3 优先注册实现了 PriorityOrdered 接口的 processor
 *          4 再给容器中注册 Ordered 接口的 processor
 *          5 注册没实现优先级接口的BeanPostProcessor
 *          6 注册 BeanPostProcessor 实际上就是创建 BeanPostProcessor 保存在容器中
 *              创建 org.springframework.aop.config.internalAutoProxyCreator 的 BeanPostProcessor 【AnnotationAwareAspectJAutoProxyCreator】
 *              1 创建bean的实例
 *              2 populateBean ： 给bean的各种属性赋值
 *              3 initializeBean 初始化bean
 *                  1 invokeAwareMethods（）： 处理Aware 接口的方法回调
 *                  2 applyBeanPostProcessorsBeforeInitialization() 应用后置处理器的 postProcessBeforeInitialization（）
 *                  3 invokeInitMethods（） 执行自定义的初始化方法
 *                  4 applyBeanPostProcessorsAfterInitialization 执行后置处理器的 postProcessAfterInitialization（）
 *              4 BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator) 创建成功 同时创建 aspectJAdvisorsBuilder
 *          7 把BeanPostProcessor 注册到 BeanFactory 中
 *              beanFactory.addBeanPostProcessor(postProcessor)
 *
 *================以上是创建和注册 AnnotationAwareAspectJAutoProxyCreator 的过程=================
 *              AnnotationAwareAspectJAutoProxyCreator  =》 InstantiationAwareBeanPostProcessor
 *       4 finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作，创建剩下的单实例bean
 *              1 遍历获取容器中所有的bean，依次创建对象 getBean(beanName);
 *                  getBean -》 doGetBean() -》getSingleton()
 *              2 创建bean
 *                  【AnnotationAwareAspectJAutoProxyCreator 会在所有bean创建之前有个拦截 InstantiationAwareBeanPostProcessor】
 *                  1 先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的；直接使用，否则再创建
 *                      只要创建好的bean都会被缓存起来
 *                  2  createBean（）；创建 bean AnnotationAwareAspectJAutoProxyCreator 会在任何bean创建之前尝试返回bean的实例
 *                        【BeanPostProcessor 是 Bean对象创建完成初始化前后调用的】
 *                        【InstantiationAwareBeanPostProcessor 是创建bean实例之前先尝试用后置处理器返回对象】
 *
*                         1 resolveBeforeInstantiation(beanName, mbdToUse);
*                         希望后置处理器在此能返回一个代理对象，如果能返回代理对象就使用，如果不能就继续
*                              1 后置处理器先尝试返回对象
*                                  // 拿到所有的后置处理器，如果是 InstantiationAwareBeanPostProcessor 就执行 postProcessBeforeInstantiation
*                                  bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
*                                  if (bean != null) {
* 						                    bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
*                                        }
 *
 *                         2 doCreateBean(beanName, mbdToUse, args); 真正的去创建一个bean实例 和 3.6 流程一样
 *                  3
 *      AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】作用：
 *          1 在每一个bean创建之前调用，调用 postProcessBeforeInstantiation
 *              关心 mathCalculator 和 logAspect 的创建
 *                  1 判断当前bean是否在adviseBeans中，（保存了所有需要增强的bean）
 *                  2 判断当前bean 是否是基础类型的 Advice.class.
 * 				                               Pointcut.class
 * 				                               Advisor.class
 * 				                               AopInfrastructureBean
 * 				                               或者是否是切面 hasAspectAnnotation(clazz)
 * 				    3 是否需要跳过
 * 				        1 获取候选的增强器（切里面的通知方法 【List<Advisor> candidateAdvisors】）
 * 				            每一个封装的通知方法的增强器 InstantiationModelAwarePointcutAdvisor
 * 				            判断每一个增强器是否是 AspectJPointcutAdvisor
 * 				        2 永远返回false
 * 		    2 创建对象
 * 		        postProcessAfterInitialization:
 * 		            return wrapIfNecessary(bean, beanName, cacheKey); // 包装如果需要的情况下
 *                  1 获取当前bean的所有增强器 Object[] specificInterceptors
 *                      1 找到候选的所有的增强器（找哪些通知方法是需要切入当前bean通知方法）
 *                      2 获取到能在bean使用的增强器
 *                      3 给增强器排序
 *                   2 保存当前bean在advisedBeans，表示当前bean已经被增强处理了
 *                   3 如果当前bean需要增强，创建当前 bean 代理对象 createProxy
 *                      1 获取所有增强器（通知方法）
 *                      2 保存在proxyFact
 *                      3 创建代理对象
 *                          JdkDynamicAopProxy jdk：动态代理 有创建接口的
 *                          ObjenesisCglibAopProxy cglib：动态代理 没有创建接口的
 *                   4 给容器中返回当前组件使用了cglib增强的对象
 *                   5 容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 *
 *          3 目标方法的执行
 *              容器中保存了组件的代理对象（cglib增强后的对象），这个对象保存了详细信息（比如增强器，比如目标对象）
 *              1 CglibAopProxy.intercept() ； 拦截目标方法的执行
 *              2 根据 ProxyFactory（目标方法） 对象 将要执行的目标方法获取拦截器链
 *                  List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *                      1 List<Object> interceptorList 保存所有拦截器
 *                          一个默认的 ExposeInvocationInterceptor 和 4个 增强器
 *                      2 遍历所有的增强器，转为一个 interceptor
 *                          registry.getInterceptors(advisor);
 *                      3 将增强器转为 List<MethodInterceptor>
 *                           如果是 MethodInterceptor 直接加入到集合中
 *                           如果不是 使用 AdvisorAdapter 将增强器转为 MethodInterceptor
 *                           转换完成返回 MethodInterceptor 数组
 *
 *              3 如果没有拦截器链，直接执行目标方法
 *                  拦截器链（每一个通知方法又被包装为方法拦截器，利用 MethodInterceptor 机制）
 *              4 如果有拦截器链，把需要执行的目标对象，目标方法，拦截器链等信息传入创建一个 CglibMethodInvocation 对象，
 *                  并调用 Object retVal = CglibMethodInvocation proceed 方法
 *              5 拦截器链的 触发过程。
 *                  1 如果没有拦截器执行目标方法，或者拦截器的索引和拦截器数组 -1 大小一样（指定到了最后一个拦截器）执行目标方法
 *                  2 链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后 再来执行
 *                      拦截器链的机制，保证通知方法与目标方法的执行顺序
 *  总结：
 *      1 @EnableAspectJAutoProxy 开启aop功能
 *      2 @EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
 *      3 AnnotationAwareAspectJAutoProxyCreator 是一个后置处理器
 *      4 容器的创建流程：
 *          1 registerBeanPostProcessors（） 注册后置处理器；创建 AnnotationAwareAspectJAutoProxyCreator 对象
 *          2 finishBeanFactoryInitialization（） 初始化剩下的单实例bean
 *              1 创建业务逻辑组件和切面组件
 *              2 AnnotationAwareAspectJAutoProxyCreator 拦截组件的创建过程
 *              3 组件创建完之后 postProcessAfterInitialization 判断组件是否需要增强
 *                  是： 切面的通知方法，包装成增强器（Advisor）；给业务逻辑组件创建一个代理对象（cglib）
 *       5 执行目标方法
 *          1 代理对象执行目标方法
 *          2 CglibAopProxy.intercept()
 *              1 得到目标方法的拦截器链（增强器包装成拦截器 MethodInterceptor ）
 *              2 利用拦截器的链式机制 依次进入每一个拦截器进行执行
 *              3 效果：
 *                  正常执行： 前置通知 -》 目标方法 -》 后置通知 -》返回通知
 *                  出现异常： 前置通知 -》 目标方法 -》 后置通知 -》异常通知
 *
 *
 *
 *
 *
 *
 *
 */
// 开启基于AspectJ的自动代理，就是新增一个BeanPostProcessor接口，在该接口中完成代理
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    // 业务逻辑类加入容器中
    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    // 切面类加入容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
