function hightlight(passeddiv, passedbool)
{
    if (passedbool)
    {
        $("#" + passeddiv).css("background", "#999999");
    }else{
        $("#" + passeddiv).css("background", "none");
    }
}

function listSongs()
{
    for(i in MusicLibrary[0])
    {
        $("#col2").append("<DIV>" + MusicLibrary[0][i]['song'] + "</DIV>");
    }
}

function listArtists()
{
    for(i in MusicLibrary[1])
    {
        $("#col2").append("<DIV>" + MusicLibrary[1][i]['artist'] + "</DIV>");
    }
}