using System;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;

namespace MME
{
	public class mysql_interface
	{
		private MySqlConnection internal_connection = new MySqlConnection();
		
		public mysql_interface (bool passed_use_default)
		{// if false, then manual credentials needed
			if (passed_use_default)
			{
				mysql_connect();
			}
		}
		
		~mysql_interface()
		{
			mysql_disconnect();
		}
		
		public bool mysql_isconnected(bool passed_try_connection)
		{
			if (internal_connection.State == System.Data.ConnectionState.Open)
			{
				return true;
			}
			else
			{
				if (passed_try_connection)
				{
					mysql_connect();
					if (internal_connection.State == System.Data.ConnectionState.Open)
					{ // was going to do a recursion but that may never end
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
		}
		
		private bool mysql_connect()
		{
			string db_username = "MME";
			string db_password = "WWhvcuxK4ncCK8bw";
			string db_db = "MME";
			string MyConString = "SERVER=localhost;" +
                    "DATABASE=" + db_db + ";" +
                    "UID=" + db_username + ";" +
                    "PASSWORD=" + db_password + ";";
            internal_connection.ConnectionString = MyConString;
			internal_connection.Open();
			if (mysql_isconnected(true))
			{
				return true;
			}else{
				return false;
			}
		}
		
		private bool mysql_connect(string passed_user, string passed_pass, string passed_db)
		{

			string MyConString = "SERVER=localhost;" +
                    "DATABASE=" + passed_db + ";" +
                    "UID=" + passed_user + ";" +
                    "PASSWORD=" + passed_pass + ";";
            internal_connection.ConnectionString = MyConString;
			internal_connection.Open();
			if (mysql_isconnected(true))
			{
				return true;
			}else{
				return false;
			}
		}
		
		private bool mysql_disconnect()
		{
			if (mysql_isconnected(false))
			{
				internal_connection.Close();
				if (internal_connection.State == System.Data.ConnectionState.Open)
				{ // was going to do a recursion but that may never end
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		
		}
		
		public bool mysql_nonquery(string passed_command)
        {
			try
            {
				MySqlCommand command = new MySqlCommand(passed_command);
				if (mysql_isconnected(true))
				{
					command.Connection = internal_connection;
				}else{
					return false;
				}
				
                command.ExecuteNonQuery();
                return true;
            }
            catch
            {
                return false;
            }
        }
		
		public List< List<string> > mysql_query(string passed_command)
		{
			List< List<string> > return_data = new List<List<string>>();
            List<string> return_row = new List<string>();
			return_data.Add(return_row);
            MySqlCommand command = new MySqlCommand(passed_command);
			MySqlDataReader Reader;
            try
            {
                command.Connection = internal_connection;
                Reader = command.ExecuteReader();
				
				while (Reader.Read())
				{
					List<string> temp_list = new List<string>();
					for (int i= 0; i<Reader.FieldCount; i++)
					{
						temp_list.Add(Reader.GetValue(i).ToString());
					}
					return_data.Add(temp_list);
				}
            }
            catch
            {
				return_data[0].Add("ERROR");
            }
            
            return return_data;
		}
		
		public bool clear_playlist()
		{
			bool status = false;
			
			string Mycommand = "TRUNCATE TABLE `Songs`;";
			
            string db_username = "MME_Priv";
			string db_password = "FS3UeXNU7ThMV4cv";
			string db_db = "MME";
			string MyConString = "SERVER=localhost;" +
                    "DATABASE=" + db_db + ";" +
                    "UID=" + db_username + ";" +
                    "PASSWORD=" + db_password + ";";
            MySqlConnection connection = new MySqlConnection(MyConString);
            MySqlCommand command = new MySqlCommand(Mycommand);

            try
            {
                connection.Open();
                command.Connection = connection;
                command.ExecuteNonQuery();
                status = true;
            }
            catch
            {
                status = false;
            }
            finally
            {
                connection.Close();
            }
            
            return status;
		}
	}
}

