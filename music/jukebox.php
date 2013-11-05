<?PHP include('./library.php'); ?>



<table style='width: 100%; height:90%'>
    <?PHP
    for($j = 0; $j < 6; $j++)
    {
        echo "<tr style='height: 15%'>";
            for ($i = 0; $i < 8; $i++)
            {
                echo "<td id='r$j-c$i' style='width:10%; border-style:solid; border-width:1px;'></td>";
            }
        echo "</tr>";
    }
    ?>
</table>
<script>
    function find_album(passedVar)
    {
        for (z = 0; z < MusicLibrary[2].length; z++) {
            if (MusicLibrary[2][z][0] == passedVar) {
                return MusicLibrary[2][z][1];
            }
        }
        return "ERROR";
    }
    
    
    r=0;
    c=0;
    for (i = 0; i < MusicLibrary[0].length; i++) {
        $("#r" + r + "-c" + c).html("<h4>" + MusicLibrary[0][i][2] + "</h4><p style='font-weight:bold;'>" + find_album(MusicLibrary[0][i][3]) + "</p><p>ID: " + MusicLibrary[0][i][0] + "</p>");
        
        if (c==7) {
            r++;
            c=0;
        }else{
            c++;
        }
    }
</script>
