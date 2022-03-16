<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: POST");
    header("Content-Type: application/json;charset=UTF-8");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once("./../../DataBase/Database.php");

    $db=new Database();
    $con=$db->Connection();

    $data=json_decode(file_get_contents("php://input"));

    if($_SERVER["REQUEST_METHOD"]!="POST"):
        $db->Message(0,404,"Page Not Found !");
    elseif( !isset($data->username) 
            || !isset($data->email) 
            || !isset($data->password)
            || empty(trim($data->username))
            || empty(trim($data->email))
            || empty(trim($data->password))
        ):
        $db->Message(0,422,"Pleas Fill all The Required Fields !");
    else:
        $username=$data->username;
        $email=trim($data->email);
        $password=trim($data->password);
        if(!filter_var($email,FILTER_VALIDATE_EMAIL)):
            $db->Message(0,422,"Invalid Email Format !");
        elseif(strlen($password) < 8):
            $db->Message(0,422,"Your Password Must Be At Least 8 Characters !");
        elseif(strlen($username) < 3):
            $db->Message(0,422,"Your User Name Must Be At Least 3 Characters !");
        else:
            try{
                if($db->SelectedByEmail($email)):
                    $db->Message(0,422,"This Email Already Exist !");
                elseif($db->Register($username,$email,$password)):
                    $db->Message(1,201,"Successfull Register .");
                endif;
            }catch(PDOEception $e){
                echo $e->getMessage(); 
                $db->Message(0,401,"".$e->getMessage());
            }
        endif;
    endif;

?>