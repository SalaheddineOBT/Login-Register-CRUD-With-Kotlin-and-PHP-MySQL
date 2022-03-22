<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: DELETE");
    header("Content-Type: application/json;charset=UTF-8");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once("./../../DataBase/Database.php");

    $db=new Database();
    $con=$db->Connection();

    $data=json_decode(file_get_contents("php://input"));

    if($_SERVER["REQUEST_METHOD"]!="DELETE"):
        $db->Message(0,404,"Page Not Found !");
    elseif( !isset($_REQUEST['id']) || empty($_REQUEST['id']) ):
        $db->Message(0,422,"ID is Required !!");
    else:
        $id=$_REQUEST['id'];

        if(!is_numeric($id)):
            $db->Message(0,422,"ID Must be Integer !");
        else:
            try{
                if($db->DeleteUser($id)):
                    $db->Message(1,201,"User Deleted Successfully .");
                endif;
            }catch(PDOEception $e){
                echo $e->getMessage(); 
                $db->Message(0,401,"".$e->getMessage());
            }
        endif;
    endif;

?>