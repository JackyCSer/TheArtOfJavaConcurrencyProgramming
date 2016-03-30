package ch4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThreadDemo
{

	public static void main(String[] args)
	{
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] infos = threadMXBean.dumpAllThreads(false, false);

		for (ThreadInfo threadInfo : infos)
		{
			System.out.println("[" + threadInfo.getThreadId() + "]"
					+ threadInfo.getThreadName());
		}
	}

}
