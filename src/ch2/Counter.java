/**
 * @file Counter.java
 * @author Jacky
 * @date Mar 29, 2016 
 */
package ch2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter
{
	private AtomicInteger ai = new AtomicInteger(0);
	private int i = 0;

	private void safeCount()
	{
		while (true)
		{
			int i = ai.get();
			boolean success = ai.compareAndSet(i, ++i);
			if (success)
			{
				break;
			} 
	 	}
	}

	private void count()
	{
		i++;
	}

	public static void main(String[] args)
	{
		final Counter casCounter = new Counter();
		List<Thread> threads = new ArrayList<Thread>(500);
		long start = System.currentTimeMillis();

		for (int j = 0; j < 100; j++)
		{
			Thread thread = new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					for (int k = 0; k < 10_000; k++)
					{
						casCounter.safeCount();
						casCounter.count();
					}
				}
			});

			threads.add(thread);
		}
		for (Thread t : threads)
		{
			t.start();
		}

		for (Thread t : threads)
		{
			try
			{
				t.join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		System.out.println("i   = " + casCounter.i);
		System.out.println("ai = " + casCounter.ai.get());
		System.out.println("Time Used = " + (System.currentTimeMillis() - start) + " ms");

	}

}
