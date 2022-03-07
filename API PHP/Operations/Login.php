<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: POST");
    header("Content-Type: application/json;");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once("./../DataBase/Database.php");

    $db=new Database();
    $con=$db->Connection();

    $data=json_decode(file_get_contents("php://input"));

    if($_SERVER["REQUEST_METHOD"]!="POST"):
        $db->Message(0,404,"Page Not Found !");
    elseif(!isset($data->email) 
            || !isset($data->password)
            || empty(trim($data->email))
            || empty($data->password)):
        $db->Message(0,422,"Pleas Fill all The Required Fields !");
    else:
        $email=trim($data->email);
        $password=trim($data->password);
        
        if(!filter_var($email,FILTER_VALIDATE_EMAIL)):
            $db->Message(0,422,"Invalid Email Format !");
        elseif(strlen($password)<8):
             $db->Message(0,422,"Your Password Must Be At Least 8 Characters !");
        else:
            try{
                $stmt=$db->Login($email,$password);
                if($stmt):
                    echo json_encode(array(
                        "success"=>1,
                        "status"=>201,
                        "message"=>"You Have Logined Successfully .",
                        "user"=>$stmt));
                else:
                    $db->Message(0,422,"Incorect Email Or Password !");
                endif;
            }catch(Exception $e){
                $db->Message(0,422,"".$e->getMessage());
            }
        endif;
    endif;

?>