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
            <select id='musicSort' onchange='changeOption();'>
                <option value='music'><i class="icon-music"></i>Songs</option>
                <option value='artist'><i class="icon-user"></i>Artists</option>
            </select>
        </th>
        <th></th>
        <th style='margin:auto;'>
            Station
        </th>
        <th>
            <select>
                <option value="89">89Mhz</option>
                <option value="89">91Mhz</option>
                <option value="89">93Mhz</option>
                <option value="89">95Mhz</option>
                <option value="89">98Mhz</option>
            </select>
        </th>
    </tr>
    <tr style='height: 100%; width: 100%;' >
        <td style='height: 100%; width: 40%;' >
            <DIV id='col1' style='width: 100%; height: 100%; border-width: 1px; border-style: solid; border-color: black;border-right-style: dashed;'>
            </DIV>
        </td>
        <td style='height: 100%; width: 20%;'>
            <DIV id='col3' style='height: 100%; width: 100%; border-width: 1px; border-style: solid; border-color: black;border-left-style: dashed;'>
                
            </DIV>
        </td>
        <td style='height: 100%; width: 20%;' >
            <DIV style='height:20%; text-align:center;'>
                Stations Settings:
            </DIV>
            <DIV id='col4' style='height: 80%; width: 100%; border-width: 1px; border-style: solid; border-color: black; border-right-style: dashed;'>
                
            </DIV>
        </td>
        <td style='height: 100%; width: 20%;' >
            <DIV style='height:20%'>
                Commercialss
            </DIV>
            <DIV id='list' style='height: 80%; width: 100%; border-width: 1px; border-style: solid; border-color: black; border-left-style: dashed;'>
               
            </DIV>
        </td>
    </tr>
</table>
<script>
    listSongs();
    load();
    new dragObject("88Mhz", null, null, null, itemDragBegin, itemMoved, itemDragEnd, false);
    new dragObject("91Mhz", null, null, null, itemDragBegin, itemMoved, itemDragEnd, false);
    new dragObject("93Mhz", null, null, null, itemDragBegin, itemMoved, itemDragEnd, false);
    new dragObject("95Mhz", null, null, null, itemDragBegin, itemMoved, itemDragEnd, false);
</script>


