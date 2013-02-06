<DIV id='cats' style='width: 15%; height: 99%; border-width: 1px; border-style: solid; border-color: black; display: inline-block;'>
    <div tyle='margin:0px;'>Music</div>
</DIV>
<DIV id='music' style='width: 15%; height: 99%; border-width: 1px; border-style: solid; border-color: black; display: inline-block;'>
    
    <?PHP
        $music = database_helper::db_Music_Cat();
        
        foreach($music[0] as $song)
        {
            echo "<DIV id='SONG_" . $song['id'] . "'>" . $song['song'] . "</DIV>";
        }
    ?>
</DIV>