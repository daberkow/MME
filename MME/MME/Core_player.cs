using System;

namespace MME
{
	public class Core_player
	{
		System.Diagnostics.Process MusicPlayer = new System.Diagnostics.Process();
		System.Diagnostics.Process Telnet_Client = new System.Diagnostics.Process();
		
		public Core_player ()
		{
			Console.Write("Starting VLC Core...");
			MusicPlayer.StartInfo.UseShellExecute = false;
			MusicPlayer.StartInfo.RedirectStandardInput = true;
			MusicPlayer.StartInfo.RedirectStandardOutput = true;
			MusicPlayer.StartInfo.RedirectStandardError = true;
			MusicPlayer.StartInfo.FileName = "./VLC/VLC";
			MusicPlayer.StartInfo.Arguments = "-I telnet --telnet-password test";
			
			MusicPlayer.Start();
			
			//If VLC is already running you will get a error trying to open another socket,
			//Either kill other process or start multiple on other port, I kill
			for (int i = 0; i < 10; i++)
			{
				if (!MusicPlayer.StandardError.EndOfStream && MusicPlayer.StandardError.ReadLine().Contains("socket bind error"))
				{
					Console.Write(" Already Running killing...");
					System.Diagnostics.Process Saver = new System.Diagnostics.Process();
					Saver.StartInfo.UseShellExecute = true;
					//Telnet_Client.StartInfo.RedirectStandardInput = false;
					//Telnet_Client.StartInfo.RedirectStandardOutput = false;
					Saver.StartInfo.FileName = "killall";
					Saver.StartInfo.Arguments = "VLC";
					Saver.Start();
					System.Threading.Thread.Sleep(1000);
					MusicPlayer.Start();
					break;
				}
			}
			Console.Write("Core Started" + Environment.NewLine);
			
			System.Threading.Thread.Sleep(1000);
			
			Console.Write("Starting Telnet Client...");
			Telnet_Client.StartInfo.UseShellExecute = false;
			Telnet_Client.StartInfo.RedirectStandardInput = true;
			Telnet_Client.StartInfo.RedirectStandardOutput = true;
			Telnet_Client.StartInfo.FileName = "telnet";
			Telnet_Client.StartInfo.Arguments = "localhost 4212";
			
			Telnet_Client.Start();
			
			System.Threading.Thread.Sleep(1000);
			
			Telnet_Client.StandardInput.WriteLine("test");
			Console.Write(" Complete" + Environment.NewLine);
		}
		
		public void add_song(string file_location)
		{
			Telnet_Client.StandardInput.WriteLine("add " + file_location);
		}
		
		public void flush_terminal()
		{
			Telnet_Client.StandardInput.Flush();
			
		}
		
		public string get_position()
		{
			flush_terminal();
			
			Telnet_Client.StandardInput.WriteLine("get_time");
			if (!Telnet_Client.StandardOutput.EndOfStream)
				return Telnet_Client.StandardOutput.ReadLine();
			else
				System.Threading.Thread.Sleep(500);
			
			if (!Telnet_Client.StandardOutput.EndOfStream)
				return Telnet_Client.StandardOutput.ReadLine();
			else
				return "";
		}
		
		public string get_length()
		{
			flush_terminal();
			
			Telnet_Client.StandardInput.WriteLine("get_length");
			if (!Telnet_Client.StandardOutput.EndOfStream)
				return Telnet_Client.StandardOutput.ReadLine();
			else
				System.Threading.Thread.Sleep(500);
			
			if (!Telnet_Client.StandardOutput.EndOfStream)
				return Telnet_Client.StandardOutput.ReadLine();
			else
				return "";
		}
		
	}
}

