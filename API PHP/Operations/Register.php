<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: POST");
    header("Content-Type: application/json;charset=UTF-8");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once("./../DataBase/Database.php");

    $db=new Database();
    $con=$db->Connection();

    $data=json_decode(file_get_contents("php://input"));

    if($_SERVER["REQUEST_METHOD"]!="POST"):
        $db->Message(0,404,"Page Not Found !");
    elseif(!isset($_POST["username"]) || !isset($_POST["email"]) || !isset($_POST["password"]) || empty($_POST["username"]) || empty($_POST["email"]) || empty($_POST["password"]) ):
        echo 422; //Pleas Fill all The Required Fields !
    else:
        $username=$_POST["username"];
        $email=trim($_POST["email"]);
        $password=trim($_POST["password"]);
        if(!filter_var($email,FILTER_VALIDATE_EMAIL)):
            echo 401; //Invalid Email Format !
        elseif(strlen($password) < 8):
            echo 403; //Your Password Must Be At Least 8 Characters !
        elseif(strlen($username) < 3):
            echo 405;//Your User Name Must Be At Least 3 Characters Long !
        else:
            try{
                if($db->SelectedByEmail($email)):
                    echo 408; //This Email Already Exist !
                elseif($db->Register($username,$email,$password)):
                    echo 201; //Successfull Register .
                endif;
            }catch(PDOEception $e){
                echo $e->getMessage(); 
            }
        endif;
    endif;

?>