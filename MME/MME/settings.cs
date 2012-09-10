using System;

namespace MME
{
	public class settings
	{
		private string music_folder = "";
		
		public settings ()
		{
		}
		
		public string get_music_folder()
		{
			return music_folder;
		}
		
		public bool load_settings()
		{
			System.IO.FileInfo Settings_file = new System.IO.FileInfo(System.Environment.CurrentDirectory + "settings.ini");
			if (Settings_file.Exists)
			{
				using (System.IO.StreamReader Reader = Settings_file.OpenText())
				{
					try
					{
						string temp = "";
						while (!Reader.EndOfStream)
						{
							temp = Reader.ReadLine();
							if (temp.StartsWith("music folder".ToLower()) && temp.Split('=').Length > 1)
							{
								music_folder = temp.Split('=')[1];
							}
						}
						return true;
					}catch{
						return false;
					}
				}
			}else{
				return false;
			}
		}
		
		public bool create_settings()
		{
			System.IO.FileInfo Settings_file = new System.IO.FileInfo(System.Environment.CurrentDirectory + "settings.ini");
			using (System.IO.FileStream Streamy = Settings_file.OpenWrite())
			{
				System.IO.StreamWriter writer = new System.IO.StreamWriter(Streamy);
				try 
				{
					System.IO.DirectoryInfo Songs = new System.IO.DirectoryInfo(System.Environment.CurrentDirectory.ToString());
					music_folder = Songs.CreateSubdirectory("music").FullName;
				}catch{
					return false;
				}
				try
				{
					writer.WriteLine("music folder=" + music_folder);
					return true;
				}catch{
					return false;
				}
			}
		}
	}
}

