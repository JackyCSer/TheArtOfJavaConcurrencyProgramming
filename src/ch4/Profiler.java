/**
 * @file Profiler.java
 * @author Jacky
 * @date Mar 31, 2016 
 */
package ch4;

import java.util.concurrent.TimeUnit;

/**
 * @description TODO
 * 
 */
public class Profiler
{

	private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<Long>()
	{
		@Override
		protected Long initialValue()
		{
		
			return System.currentTimeMillis();
		}
	};

	public static final void begin()
	{

		TIME_THREAD_LOCAL.set(System.currentTimeMillis());
	}

	public static final long end()
	{
		return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		Profiler.begin();
		TimeUnit.SECONDS.sleep(1);
		long time = Profiler.end();
		System.out.println("Cost: " + time + " mills");

	}

}
