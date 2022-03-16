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

    if($_SERVER["REQUEST_METHOD"] != "POST"):
        $db->Message(0,404,"Page Not Found !");

    elseif( !isset($data->select) || empty(trim($data->select)) ):
        $db->Message(0,422,"The SelectBy is Required !");

    else:
        try{
            $select=$data->select;
        
            switch($select):

                case "ById":
                    echo json_encode(["success" => 1, "User" =>$db->SelectedByID(14)]);

                case "All":

                    if($stmt=$db->SelecteAll()):
                        $row = $stmt->fetchall(PDO::FETCH_ASSOC);
                        echo json_encode(["success" => 1, "Users" => $row]);

                    endif;

            endswitch;
            
        }catch(PDOEception $e){
            echo $e->getMessage(); 
            $db->Message(0,401,"".$e->getMessage());
            
        }

    endif;

?>