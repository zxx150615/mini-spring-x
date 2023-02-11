#思考：如何实现在一个Bean中注入另外一个Bean呢？
	#注入另外一个Bean，其实也是相当于Bean的一个属性，只是是一个Bean的引用，所以需要一个Bean引用的类，来代表另一个Bean的注入
		a. BeanReference
	#在创建Bean的逻辑里面，如果遇到属性属于BeanReference的话，那么先实例化这个Bean，（前提是这个Bean的定义信息已经注入到工厂类中）
		a. AbstractAutowireCapableBeanFactory