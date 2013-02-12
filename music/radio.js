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
    //stopped here
    outputString = "";
    outputString += "<h4 style='text-align:center;'>" + passedArraySong['song'] + "</h4>";
    outputString += "<table class='table'>";
    outputString += "<tbody>";
    outputString += "<tr style='text-align:center;'><td>Artist:</td><td> " + (albumIDtoArtist(passedArraySong['album'])) + "</td></tr>";
    outputString += "<tr style='text-align:center;'><td>Album:</td><td> " + (albumIDtoAlbum(passedArraySong['album'])) + "</td></tr>";
    outputString += "<tr style='text-align:center;'><td>Plays:</td><td> " + passedArraySong['plays'] + "</td></tr>";
    outputString += "</tbody></table>";
    outputString += "<button style='margin-left: 10%'>Add to Selected Station</button>"
    return outputString;
}



function albumIDtoArtist(passedAlbumID)
{
    SeekID = parseInt(passedAlbumID);
    FoundArtistID = -1;
    for(foundseeker = 0; foundseeker < MusicLibrary[2].length; foundseeker++)
    {
        if (parseInt(MusicLibrary[2][foundseeker]['id']) == SeekID)
        {
            FoundArtistID = parseInt(MusicLibrary[2][foundseeker]['artist']);
            break;
        }
    }
    for(foundseeker = 0; foundseeker < MusicLibrary[1].length; foundseeker++)
    {
        if (parseInt(MusicLibrary[1][foundseeker]['id']) == FoundArtistID)
        {
            return MusicLibrary[1][foundseeker]['artist'];
        }
    }
    return "";
}

function albumIDtoAlbum(passedAlbumID)
{
    SeekID = parseInt(passedAlbumID);
    FoundArtistID = -1;
    for(foundseeker = 0; foundseeker < MusicLibrary[2].length; foundseeker++)
    {
        if (parseInt(MusicLibrary[2][foundseeker]['id']) == SeekID)
        {
            return MusicLibrary[2][foundseeker]['name'];
        }
    }
    return "";
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