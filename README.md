#重点！理解BeanFactoryPostProcessor和BeanPostProcessor
	#1. BeanFactoryPostProcessor，Bean工厂后置处理器，运行我们在实例化Bean之前，修改BeanDefinition的信息，注意时机是在Bean实例化之前，也就是修改Bean的定义信息
		常用的BeanFactoryPostProcessor
			PropertyPlaceholderConfigurer
				已废弃，能够将properties的配置配置到xml的占位符处，可以用在mybatis的设置处理上
			CustomEditorConfigurer
				能够实现类型的转换
					可以自己自定义处理器
	#2.BeanPostProcesser，Bean前后处理器，提供两个方法，能够在Bean实例化完，填充属性之后，在初始化之前和初始化之后，对Bean进行前置和后置处理。会在后续AOP中用到
		BeanPostProcesser
	#3. 通过以上方法，用户可以通过实现这两个接口来实现对BeanDefinition的修改和对Bean初始化前后的处理
		CustomerBeanPostProcessor
		CustomBeanFactoryPostProcessor
	#4.BeanPostProcessor是如何做到在BeanFactory中执行前后操作的呢?是在实例化Bean，填充了属性之后，调用初始化Bean，并在初始化的前后，调用现有的BeanPostProcessor对Bean进行维护处理
		AbstractAutowireCapableBeanFactory