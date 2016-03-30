package ch4;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;



public class Priority
{
	private static volatile boolean notStart = true;
	private static volatile boolean notEnd = true;

	public static void main(String[] args) throws InterruptedException
	{
		List<Job> jobList = new ArrayList<Job>();
		for (int i = 0; i < 10; i++)
		{
			int p = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
			Job job = new Job(p);
			jobList.add(job);
			Thread thread = new Thread(job, "Thread-" + i);
			thread.setPriority(p);
			thread.start();
		}
		
		notStart = false;
		TimeUnit.SECONDS.sleep(10);
		notEnd = false;
		
		for (Job j : jobList)
		{
			System.out.println("Job Priority: " + j.priority + ", Count: " + j.jobCount);
		}
	}

	static class Job implements Runnable
	{
		private int priority;
		private int jobCount;

		public Job(int p)
		{
			priority = p;
		}

		@Override
		public void run()
		{
			while (notStart)
			{
				Thread.yield();
			}
			while (notEnd)
			{
				Thread.yield();
				jobCount++;
			}
		}

	}
}
