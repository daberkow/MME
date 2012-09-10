using System;
using Microsoft.Xna.Framework.Media;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;

namespace xna_mme
{
	class MainClass
	{
		public static void Main (string[] args)
		{

			Microsoft.Xna.Framework.Game dan = new Microsoft.Xna.Framework.Game();
			Song test = dan.Content.Load<Song>("/music.mp3");
			MediaPlayer.Play(test);

			Console.WriteLine ("Hello World!");
		}
	}
}
