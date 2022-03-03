<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: POST");
    header("Content-Type: application/json;");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    include_once("./../DataBase/Database.php");

    $db=new Database();
    $con=$db->Connection();


    if($_SERVER["REQUEST_METHOD"]!="POST"):
        $db->Message(0,404,"Page Not Found !");
    elseif(!isset($_POST["email"]) || !isset($_POST["password"]) || empty($_POST["email"]) || empty($_POST["password"]) ):
        $db->Message(0,422,"Pleas Fill all The Required Fields !");
    else:
        $email=trim($_POST["email"]);
        $password=trim($_POST["password"]);
        
        if(!filter_var($email,FILTER_VALIDATE_EMAIL)):
            $db->Message(0,422,'Invalid Email Format !');
        elseif(strlen($password)<8):
            $db->Message(0,422,'Your Password Must Be At Least 8 Characters !');
        else:
            try{
                $stmt=$db->Login($email,$password);
                if($stmt->rowCount()):
                    $row=$stmt->fetch(PDO::FETCH_ASSOC);
                    echo json_encode(array(
                        'success'=>1,
                        'message'=>'You Have Successfully loggined In .',
                        'UserName'=>$row['UserName']
                    ));      
                else:
                    $db->Message(0,409,"Incorect Email Or Password !");
                endif;
            }catch(Exception $e){
                $db->Message(0,500,$e->getMessage());
            }
        endif;
    endif;

?>