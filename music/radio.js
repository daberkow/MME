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
    $("#col1").html("");
    for(i in MusicLibrary[0])
    {
        $("#col1").append("<DIV id='SONG_" + i + "' style=\"padding-left: 10px;\" onclick=\"selectedItem('SONG_" + i + "')\" onmouseover=\"hightlight('SONG_" + i + "', true);\" onmouseout=\"hightlight('SONG_" + i + "', false);\">" + MusicLibrary[0][i]['song'] + "</DIV>");
    }
}

function listArtists()
{
    $("#col1").html("");
    for(i in MusicLibrary[1])
    {
        $("#col1").append("<DIV id='ARTIST_" + i + "' style=\"padding-left: 10px;\" onmouseover=\"hightlight('ARTIST_" + i + "', true);\" onmouseout=\"hightlight('ARTIST_" + i + "', false);\">" + MusicLibrary[1][i]['artist'] + "</DIV>");
    }
}

function selectedItem(passedItemID)
{
    switch(passedItemID.substr(0,4))
    {
        case "SONG":
            SongID = parseInt(passedItemID.substr(5));
            $("#col3").html(buildPane(MusicLibrary[0][SongID]));
            break;
        case "ARTI":
            break;
        default:
            console.log("oops");
            break;
    }
}

function buildPane(passedArraySong)
{
    outputString = "";
    outputString += "<h3 style='text-align:center;'>" + passedArraySong['song'] + "</h3>";
    return outputString;
}

function changeOption()
{
    switch($("#musicSort").val())
    {
        case "music":
            break;
        case "artist":
            listArtists();
            break;
    }
}