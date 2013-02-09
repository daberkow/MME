<?PHP include('./library.php'); ?>
<script src="./radio.js"></script>
<script src="./static/SOTC-DnDLists.js"></script>
<style>
    .drag, .list
    {
      width: 150px;
    }
    
    .drag
    {
      z-index: 100;
      position:absolute;
      opacity: .50;
      filter: alpha(opacity=50);
    }
    
    .list
    {
      position:relative;
      z-index: 1;
      opacity: 1;
      filter: alpha(opacity=100);
      top: 0px;
      left: 0px;
    }
</style>

<table style='height: 100%; width: 100%;' >
    <tr>
        <th>
            Categories
        </th>
        <th>
            
        </th>
        <th>
            
        </th>
        <th>
            
        </th>
        <th>
            Stations
        </th>
    </tr>
    <tr style='height: 100%; width: 100%;' >
        <td style='height: 100%; width: 20%;' >
            <DIV id='col1' style='width: 100%; height: 100%; border-width: 1px; border-style: solid; border-color: black;'>
                <div id='allMusic' style='border-radius: 14%;' onclick='listSongs();' onmouseover="hightlight('allMusic', true);" onmouseout="hightlight('allMusic', false);"><i class="icon-music"></i>Songs</div>
                <div id='allArtist' style='border-radius: 14%;' onclick='listArtists();' onmouseover="hightlight('allArtist', true);" onmouseout="hightlight('allArtist', false);"><i class="icon-user"></i>Artists</div>
            </DIV>
        </td>
        <td style='height: 100%; width: 20%;' >
            <DIV id='col2' style='height: 100%; width: 100%; border-width: 1px; border-style: solid; border-color: black;'>
                
            </DIV>
        </td>
        <td style='height: 100%; width: 20%;'>
            <DIV id='col3' style='height: 100%; width: 100%; border-width: 1px; border-style: solid; border-color: black;'>
                
            </DIV>
        </td>
        <td style='height: 100%; width: 20%;' >
            <DIV id='col4' style='height: 100%; width: 100%; border-width: 1px; border-style: solid; border-color: black;'>
                
            </DIV>
        </td>
        <td style='height: 100%; width: 20%;' >
            <DIV id='col5' style='height: 100%; width: 100%; border-width: 1px; border-style: solid; border-color: black;'>
                <div id='88Mhz' style='border-radius: 14%;' onclick='SelectStation("88Mhz");' onmouseover="hightlight('88Mhz', true);" onmouseout="hightlight('88Mhz', false);"></i>88.0Mhz</div>
            </DIV>
        </td>
    </tr>
</table>



