package ch4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import javax.xml.bind.helpers.PrintConversionEventImpl;

import org.omg.CORBA.PRIVATE_MEMBER;

public class PipeDemo
{
	public static void main(String[] args) throws IOException
	{
		PipedWriter pw = new PipedWriter();
		PipedReader pr = new PipedReader();

		pw.connect(pr);

		Thread printThread = new Thread(new Print(pr), "PrintThread");

		printThread.start();
		int receive = 0;
		try
		{
			while ((receive = System.in.read()) != -1)
			{
				pw.write(receive);
			}
		} finally
		{
			pw.close();
		}

	}

	static class Print implements Runnable
	{

		private PipedReader pr;

		public Print(PipedReader pr)
		{
			this.pr = pr;
		}

		@Override
		public void run()
		{
			int receive = 0;
			try
			{
				while ((receive = pr.read()) != -1)
				{
					System.out.println((char) receive);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}

	}
}
