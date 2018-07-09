package com.liren.design.pattern.Singleton;

/**
 * @author admin
 * 
 *         JDK 版本：JDK1.5 起
 * 
 *         是否 Lazy 初始化：是
 * 
 *         是否多线程安全：是
 * 
 *         实现难度：较复杂
 * 
 *         描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。 getInstance() 的性能对应用程序很关键。
 *
 */
public class Singleton4 {
	private volatile static Singleton4 singleton;

	private Singleton4() {
	}

	public static Singleton4 getSingleton() {
		if (singleton == null) {
			synchronized (Singleton4.class) {
				if (singleton == null) {
					singleton = new Singleton4();
				}
			}
		}
		return singleton;
	}
}