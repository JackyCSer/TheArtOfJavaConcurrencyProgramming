package ch4;

import java.util.concurrent.TimeUnit;

public class Shutdown
{
	public static void main(String[] args) throws InterruptedException
	{
		Thread t1 = new Thread(new Runner(), "t1");
		t1.start();
		TimeUnit.SECONDS.sleep(1);
		t1.interrupt();
		Runner r2 = new Runner();
		Thread t2 = new Thread(r2, "t2");
		t2.start();
		TimeUnit.SECONDS.sleep(1);
		r2.cancel();

	}

	private static class Runner implements Runnable
	{
		private long i;
		private volatile boolean on = true;

		@Override
		public void run()
		{
			while (on && !Thread.currentThread().isInterrupted())
			{
				i++;
			}

			System.out.println(Thread.currentThread().isInterrupted() + " i = " + i);

		}

		public void cancel()
		{
			on = false;
		}

	}
}
