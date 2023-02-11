#思考：创建Bean的逻辑已经独立出来了，因此我们可以采用多种方式来创建，如何做到灵活地配置和选择呢？
	##1. 采用策略模式， 我们都知道创建bean主要是采用反射的方式来实例化类，如何灵活采用简单的实例化方式和用cglib的实例化方式，可以采用策略模式
		a. 策略模式
	##2. 策略模式，同一时间内只能采用一种策略，就像插卡器一样。
		a. 需要一个策略的接口，声明这个策略主要用于实例化bean
			InstantiationStrategy
	##3. 两种策略方式，一种简单策略，一种cglib策略，实现同样的接口，都是采用BeanDefinition入参
		a. SimpleInstantiationStrategy
		a. CglibSubclassingInstantiationStrategy
	##4. 在负责创建逻辑的BeanFactory中，默认配置一种策略模式，并且提供更换策略的借口，实例化Bean时，只需要调用对应的策略接口，把逻辑再度细化下去，将实例化Bean的逻辑再度独立出去
		a. AbstractAutowireCapableBeanFactory