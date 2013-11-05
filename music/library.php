<?PHP
    $music = database_helper::db_Music_Cat();
    echo "<script> MusicLibrary = JSON.parse('" . json_encode($music) . "'); </script>";
?>