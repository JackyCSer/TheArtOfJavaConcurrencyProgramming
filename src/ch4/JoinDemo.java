package ch4;

import java.util.concurrent.TimeUnit;

public class JoinDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		Thread previous = Thread.currentThread();
		for (int i = 0; i < 10; i++)
		{
			Thread thread = new Thread(new Runner(previous), String.valueOf(i));
			thread.start();
			previous = thread;
		}
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread() + " Terminate");
	}

	static class Runner implements Runnable
	{
		private Thread thread;

		public Runner(Thread thread)
		{
			this.thread = thread;
		}

		@Override
		public void run()
		{
			try
			{
				thread.join();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + " Terminate");
		}

	}
}
