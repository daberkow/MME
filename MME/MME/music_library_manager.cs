using System;
using System.Collections.Generic;

namespace MME
{
	public class music_library_manager
	{
		public music_library_manager ()
		{
		}
		
		public bool Music_DB_file_check(mysql_interface passed_myql)
		{
			if (passed_myql.mysql_isconnected(true))
			{
				List< List<string> > Data_4_check = passed_myql.mysql_query("SELECT * FROM `Songs`;");
				foreach (List<string> row in Data_4_check)
				{
					if (row.Count == 7)
					{
						System.IO.FileInfo Test_File = new System.IO.FileInfo(row[4]);
						if (!Test_File.Exists)
						{
							return false;
						}
					}
				}
				return true;
			}else{
				//We couldnt get into database
				return false;
			}
		}
		
		public bool Drop_Playlist_Table(mysql_interface passed_myql)
		{
			return passed_myql.clear_playlist();
		}
	}
}

