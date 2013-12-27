<?PHP 
    include('./library.php');
    $station = database_helper::db_Radio_Cat();
    echo "<script> Radio = JSON.parse('" . json_encode($station) . "'); </script>";
?>

<script src="./radio.js"></script>

<div style='height: 100%; width: 100%; position:relative;'>
    <div style='height:5%; width:30%; margin: auto;'>
        <h3>Manage Station:
        <select id='stationID' onchange='changeStation();'>
            <option value="0">89Mhz</option>
            <option value="1">91Mhz</option>
            <option value="2">93Mhz</option>
            <option value="3">95Mhz</option>
            <option value="4">98Mhz</option>
        </select></h3>
    </div>
    <div style='height: 6%; width: 100%;'>
        <div style='height: 100%; width: 49%; display: inline-block;float:left;'>
            <h2 style='margin: 0px;'>Music Library</h2>
        </div>
        <div style='height: 100%; width: 49%; display: inline-block; float:right; text-align: right;'>
            <h2 style='margin:0px;' >Station Lineup</h2>
        </div>
    </div>
    <div style='height: 80%; width: 100%;'>
        <div id='col1' style='height: 100%; width: 48%; display: inline-block; border-style: solid; float:left;'>
  
        </div>
        <div id='col2' style='height: 100%; width: 48%; display: inline-block; border-style: solid; float:right;'>
            
        </div>
    </div>
</div>


<script>
    listSongs();
</script>


