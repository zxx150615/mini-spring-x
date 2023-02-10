思考：Bean是如何通过定义bean，并且能够生产bean
	1，通过单例模式生产bean。所以需要一个存放单例bean的Map。如果已经有了单例了，就不再去创建，而是直接用
		BeanFactory(interface)
		一个单例注册器接口
			SingletonBeanRegistry
		一个默认的单例注册器，实现接口
			DefaultSingletonBeanRegistry
		单例模式精髓
			如果已经创建，就直接用已经存在的，没有再去获取新的
	2，那么如果一个bean确实没有呢，那就需要去创建，如何去创建呢？那么就需要一个能够定义bean信息的类
		需要一个定义bean信息的类，存储bean的各种信息，我们称为beanDefinition
			BeanDefinition
		需要一个beanDefinition注册器接口，将bean的定义信息给存储起来
			BeanDefinitionRegistry
	3.以上就是作为一个beanfactory的基本功能，获取bean时，先从单例注册器中寻找，没有的话，就根据bean的定义信息生成一个bean，并注册到单例注册器中去
		需要一个beanFactroy抽象实现类，可以实现beanFactory的最基本功能，那就是获取bean，同时定义创建bean，以及获取bean定义信息的抽象方法，具体实现，交由子类来实现。这样具体怎么创建，我不需要关系，我只要调用就可以了。好精妙呀
			AbstractBeanFactory
				负责Bean的获取逻辑
	4.这个时候，我们就可以分别实现这个抽象工厂里面的方式，而且可以分步骤进行，再把bean的创建逻辑和获取Bean定义给分开，先要获取Bean定义，才能创建，所以要先让Bean创建的逻辑卸载前面
		需要一个根据Bean的定义信息，通过反射的方式，来创建Bean，怎么获取Bean定义信息我不关心
			AbstractAutowireCapableBeanFactory
				负责Bean的创建逻辑
	5. 这里负责了bean的定义信息的获取逻辑，那么该怎么获取呢，那就是从beanDefinition注册器中获取，所以我们要再实现beanDefinition类，这样就可以从中获取Bean定义信息，并一步步地往上传递
		一个实现了BeanDefinition注册器，而且还继承了BeanFactory的类，这个类也是对外的BeanFactory
			DefaultListableBeanFactory
				负责Bean的获取定义信息逻辑
	6.最终，我们得到的效果就是，通过往这个BeanFactory中注入一个Bean定义信息，并最终会返回一个单例的Bean。这样的一个效果