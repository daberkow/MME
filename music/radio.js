/***
 * 
 * @type Array|Array - This is where what songs are in which station is locally cached
 */
RADIO = new Array();
STATION = new Array();
/***
 * 
 * @param {type} passeddiv - Which song div should be highlighted
 * @param {type} passedbool - are we highlighting or removing the highlight
 * @returns {undefined}
 */
function hightlight(passeddiv, passedbool)
{
    if (passedbool)
    {
        $("#" + passeddiv).css("background", "#999999");
    }else{
        $("#" + passeddiv).css("background", "none");
    }
}

/***
 * This goes and lists all teh songs on teh page from the js db
 * 
 * @returns {undefined}
 */
function listSongs()
{
    $("#col1").html("");
    for(i in MusicLibrary[0])
    {
        $("#col1").append("<DIV id='SONG_" + i + "' style=\"padding-left: 10px;\" onmouseover=\"hightlight('SONG_" + i + "', true);\" onmouseout=\"hightlight('SONG_" + i + "', false);\">" + albumIDtoArtist(MusicLibrary[0][i]['album']) + " - " + MusicLibrary[0][i]['song'] + " <span id='access' class='glyphicon glyphicon-unchecked' style='float:right;' onclick='add_song(" + i + "," + MusicLibrary[0][i]['id'] + ")'></span> </DIV>");
    }
    loadstations();
    addButtons();
}

/***
 * 
 * @param {type} passedAlbumID - this is the mysql album ID
 * @returns {String} - this is the returned data of the artist name
 */
function albumIDtoArtist(passedAlbumID)
{
    SeekID = parseInt(passedAlbumID);
    FoundArtistID = -1;
    for(foundseeker = 0; foundseeker < MusicLibrary[2].length; foundseeker++)
    {
        if (parseInt(MusicLibrary[2][foundseeker]['id']) === SeekID)
        {
            FoundArtistID = parseInt(MusicLibrary[2][foundseeker]['artist']);
            break;
        }
    }
    for(foundseeker = 0; foundseeker < MusicLibrary[1].length; foundseeker++)
    {
        if (parseInt(MusicLibrary[1][foundseeker]['id']) === FoundArtistID)
        {
            return MusicLibrary[1][foundseeker]['artist'];
        }
    }
    return "";
}

/***
 * 
 * @param {type} passedAlbumID - This is the Mysql ID of a album, 
 * @returns {String} - that gets teh album name
 */
function albumIDtoAlbum(passedAlbumID)
{
    SeekID = parseInt(passedAlbumID);
    FoundArtistID = -1;
    for(foundseeker = 0; foundseeker < MusicLibrary[2].length; foundseeker++)
    {
        if (parseInt(MusicLibrary[2][foundseeker]['id']) === SeekID)
        {
            return MusicLibrary[2][foundseeker]['name'];
        }
    }
    return "";
}

/***
 * This goes to each song and adds the checkbox
 * 
 * @returns {undefined}
 */
function addButtons()
{
    for(i in MusicLibrary[0])
    {
        if(typeof(STATION[$("#stationID").val()]) !== "undefined")
        {
            if (STATION[$("#stationID").val()].indexOf(parseInt(i)) !== -1)
            {
                //song not found in station
                $("#SONG_" + i + " #access").attr("class", "glyphicon glyphicon-check");
            }
        }
    }
}


/***
 * 
 * @param {type} JSID - The javascript ID of the song
 * @param {type} SongID - this is the mysql ID for the song
 * @returns {undefined}
 */
function add_song(JSID, SongID)
{
    if (putinstation($("#stationID").val(), JSID) === 1)
    {
        //song was not checked and has been added to the local DB
        $("#SONG_" + JSID + " #access").attr("class", "glyphicon glyphicon-check");
        $("#col2").append("<DIV id='A_SONG_" + JSID + "' style=\"padding-left: 10px;\" onmouseover=\"hightlight('SONG_" + i + "', true);\" onmouseout=\"hightlight('SONG_" + i + "', false);\">" + albumIDtoArtist(MusicLibrary[0][JSID]['album']) + " - " + MusicLibrary[0][JSID]['song'] + "</DIV>");
    }else{
        //song already there we need to remove
        $("#A_SONG_" + JSID).remove();
        $("#SONG_" + JSID + " #access").attr("class", "glyphicon glyphicon-unchecked");
    }
    $.ajax({
        type: 'POST',
        url: "./ajax.php",
        data: {Task_ID: "addtoRadio", Song_ID: SongID, Station_ID: $("#stationID").val(), Adding: STATION[$("#stationID").val()].indexOf(JSID)},
        success: function(data) {
                console.log(data);
        },
        error: function(data) {
                console.log("ERROR: " + data);
        }
    });
}

/***
 * 
 * @param {type} station // a string of the nubmer for the station
 * @param {type} songid // the javascript ID of the song
 * @returns {Number} // 0 is song was active and it has been removed, 1 is the song didnt exist or was false; then has been made true
 */
function old_putinstation(station, songid)
{
    if (typeof(RADIO[station]) === "undefined")
    {
        RADIO[station] = new Array();
        RADIO[station][songid] = true;
        nextup(station, songid);
        return 1;
    }else{
        if (typeof(RADIO[station][songid]) !== "undefined")
        {
            //here the station exists, and the song, check for song is checked
            if (RADIO[station][songid])
            {
                //song already exists and is checked, reverse
                RADIO[station][songid] = false;
                removefromlist(station, songid);
                return 0;
            }else{
                RADIO[station][songid] = true;
                nextup(station, songid);
                return 1;
            }
        }else{
            //Here the radio station was defined, but the song was not
            RADIO[station][songid] = true;
            nextup(station, songid);
            return 1;
        }
    }
}

function nextup(station, songid)
{
    if (typeof(STATION[station]) === "undefined")
    {
        //Station is not yet defined
        STATION[station] = new Array();
        STATION[station][0] = songid;
    }else{
        STATION[station].push(songid);
    }
}

function removefromlist(station, songid)
{
    var index = STATION[station].indexOf(songid);
    if (index > -1) {
        STATION[station].splice(index, 1);
    }
}

/***
 * Function rewrite, make it hotter, faster, better, stronger
 * 
 * @param {type} station - station id
 * @param {type} songid - javascript id
 * @returns {Number} - 0 is song was active and it has been removed, 1 is the song didnt exist or was false; then has been made true
 */
function putinstation(station, songid)
{
    if (typeof(STATION[station]) === "undefined")
    {
        //Station is not yet defined
        STATION[station] = new Array();
        STATION[station][0] = songid;
        return 1;
    }else{
        if (STATION[station].indexOf(songid) === -1)
        {
            STATION[station].push(songid);
            return 1;
        }else{
            STATION[station].splice(STATION[station].indexOf(songid), 1);
            return 0;
        }
    }
}

function changeStation()
{
    $("#col2").html("");
    $(".glyphicon").attr("class", "glyphicon glyphicon-unchecked");
    addButtons();
    for(i in STATION[$("#stationID").val()])
    {
        $("#col2").append("<DIV id='A_SONG_" + STATION[$("#stationID").val()][i] + "' style=\"padding-left: 10px;\" onmouseover=\"hightlight('SONG_" + STATION[$("#stationID").val()][i] + "', true);\" onmouseout=\"hightlight('SONG_" + i + "', false);\">" + albumIDtoArtist(MusicLibrary[0][STATION[$("#stationID").val()][i]]['album']) + " - " + MusicLibrary[0][STATION[$("#stationID").val()][i]]['song'] + "</DIV>");
    }
}

function loadstations()
{
    //Radio is a 2d array with Radio[sequentialnumber][channel: "0", id: "20", order: "1", songID: "22", status: "1", 
    for(i in Radio)
    {
        putinstation(Radio[i]['channel'], SQLID2JSID(Radio[i]['songID']));
    }
    changeStation();
}

function SQLID2JSID(mysqlID)
{
    for (i in MusicLibrary[0])
    {
        if (MusicLibrary[0][i]['id'] == mysqlID)
        {
            return parseInt(i);
        }
    }
}