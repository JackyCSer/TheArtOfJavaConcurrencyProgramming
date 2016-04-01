package ch4;

public class SynchronizedDemo
{
	public static void main(String[] args)
	{
		synchronized (SynchronizedDemo.class)
		{
			int i = 0;
		}
		f();
	}
	
	public static synchronized void f()
	{
		
	}
}
