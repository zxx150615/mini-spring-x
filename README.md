#思考：FactoryBean又是一个啥？
	##1.FactoryBean作为一种特别的Bean。其实是在Bean的外面包上了一层Factory。并且还申明了作用域，是否是单例模式
	##2.在上面节点涉及这个操作呢？那就是当创建Bean逻辑的时候。
		如果Bean已经在单例模式中有了，则在之后，去FactoryBean中获取一下，如果是FactoryBean类型，还要再检查一下是否是单例模式，如果还是单例，就再从这个FactoryBean中获取即可。如果不是的话，则直接调用FactoryBean的getObject方法，直接获取FactoryBean自己定义的Bean创建方法