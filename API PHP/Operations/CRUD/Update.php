<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: PATCH");
    header("Content-Type: application/json;charset=UTF-8");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once("./../../DataBase/Database.php");

    $db=new Database();
    $con=$db->Connection();

    $data=json_decode(file_get_contents("php://input"));

    if($_SERVER["REQUEST_METHOD"]!="PATCH"):
        $db->Message(0,404,"Page Not Found !");
    elseif( !isset($data->id) 
        || !isset($data->username) 
            || !isset($data->email)
            || empty(trim($data->id))
            || empty(trim($data->username))
            || empty(trim($data->email))
        ):
        $db->Message(0,422,"Pleas Fill all The Required Fields !");
    else:
        $id=$data->id;
        $username=$data->username;
        $email=trim($data->email);
        if(!is_numeric($data->id)):
            $db->Message(0,422,"ID Must be Integer !");
        elseif(!filter_var($email,FILTER_VALIDATE_EMAIL)):
            $db->Message(0,422,"Invalid Email Format !");
        elseif(strlen($username) < 3):
            $db->Message(0,422,"Your User Name Must Be At Least 3 Characters !");
        else:
            try{
                if($db->SelectedByEmail($email)):
                    $db->Message(0,422,"This Email Already Exist !");
                elseif($db->UpdateUser($id,$username,$email)):
                    $db->Message(1,201,"User Updated Successfully .");
                endif;
            }catch(PDOEception $e){
                echo $e->getMessage(); 
                $db->Message(0,401,"".$e->getMessage());
            }
        endif;
    endif;

?>