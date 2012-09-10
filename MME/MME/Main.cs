using System;

namespace MME
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			//MySQL test
			Console.Write("Connecting to MySQL... ");
			mysql_interface MyQL = new mysql_interface(true);
			if (MyQL.mysql_isconnected(false))
			{
				Console.Write(" Successful" + Environment.NewLine);
			}else{
				Console.Write(" Fail" + Environment.NewLine);
			}
			
			//Settings Load
			Console.Write("Loading Settings... ");
			settings Settings_data = new settings();
			if (!Settings_data.load_settings())
			{
				Console.Write(" No settings found, creating...");
				if (Settings_data.create_settings())
				{
					Console.Write(" Successful" + Environment.NewLine);
				}else{
					Console.Write(" Fail" + Environment.NewLine);
				}
			}else{
				Console.Write(" Successful" + Environment.NewLine);
			}
			
			Console.Write("DB Integrity Check... ");
			music_library_manager Music_Manager = new music_library_manager();
			if(Music_Manager.Music_DB_file_check(MyQL))
			{
				Console.Write(" Successful" + Environment.NewLine);
			}else{
				Console.Write(" Fail" + Environment.NewLine);
			}
			
			Console.Write("DB Empty Playlist Table... ");
			if(Music_Manager.Drop_Playlist_Table(MyQL))
			{
				Console.Write(" Successful" + Environment.NewLine);
			}else{
				Console.Write(" Fail" + Environment.NewLine);
			}
			
			//Main Loop
			Console.Write("System Ready..." + Environment.NewLine);
			bool continue_loop = true;
			Core_player Plaz = new Core_player();
			Plaz.add_song("/music.mp3");
			while(continue_loop)
			{
				Console.WriteLine(Plaz.get_position() + "/" + Plaz.get_length());
				System.Threading.Thread.Sleep(1000);
			}
		}
	}
}