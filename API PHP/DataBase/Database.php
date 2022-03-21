<?php
    require_once(__DIR__.'/../vendor/autoload.php');
    $dotenv = Dotenv\Dotenv::createImmutable(dirname(__DIR__));
    $dotenv->load();

    class Database{
        private $con;
        public function Connection(){
            $this->con =null;
            try{
                $this->con = new PDO('mysql:host='.$_ENV["DB_HOST"].';dbname='.$_ENV["DB_Name"],$_ENV["DB_USER"],$_ENV["DB_PASSWORD"]);
                $this->con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                return $this->con;
            }catch(Exception $e){ 
                echo "Connection Erreur ! ".$e->getMessage();
                exit;
            }
        }

        public function Message($success,$status,$message)
        {
            echo json_encode(array(
                'success'=>$success,
                'status'=>$status,
                'message'=>$message
            ));
        }

        public function Login($email,$password)
        {
            $sql='SELECT * FROM users WHERE Email="'.$email.'"';
            $stmt=$this->con->prepare($sql);
            $stmt->execute();
            if($stmt->rowCount()):
                $row=$stmt->fetch(PDO::FETCH_ASSOC);

                $checkPass=password_verify($password,$row['Password']);

                if($checkPass):
                    return $row["UserName"];
                endif;
            endif;

            return null;
        }

        public function Register($username,$email,$password)
        {   
            $sql='INSERT INTO users (UserName,Email,Password) VALUES("'.$username.'","'.$email.'","'.password_hash($password,PASSWORD_DEFAULT).'");';
            $stmt=$this->con->prepare($sql);
            if($stmt->execute()):
                return true;
            endif;
            echo ''.$stmt->error;
            return false;
        }

        public function SelectedByEmail($email)
        {
            $sql='SELECT * FROM users WHERE Email="'.$email.'"';
            $stmt=$this->con->prepare($sql);
            $stmt->execute();
            if($stmt->rowCount()):
                return true;
            endif;
            return false;
        }

        public function UpdateUser($id,$username,$email){
            $sql='UPDATE users SET UserName="'.$username.'",Email="'.$email.'" WHERE ID='.$id;
            $stmt=$this->con->prepare($sql);
            if($stmt->execute()):
                return true;
            endif;
            echo ''.$stmt->error;
            return false;
        }

        public function DeleteUser($id){
            $sql='DELETE FROM users WHERE ID='.$id;
            $stmt=$this->con->prepare($sql);
            if($stmt->execute()):
                return true;
            endif;
            echo ''.$stmt->error;
            return false;
        }

        public function SelectedByID($id){
            $sql='SELECT UserName,Email FROM users WHERE ID='.$id.' LIMIT 1';
            $stmt=$this->con->prepare($sql);
            $stmt->execute();
            if($stmt->rowCount()):
                $row=$stmt->fetch(PDO::FETCH_ASSOC);
                return $row;
            endif;

            return false;
        }

        public function SelecteAll(){
            $sql='SELECT ID,UserName,Email FROM users';
            $stmt=$this->con->prepare($sql);
            $stmt->execute();
            if($stmt->rowCount()):
                return $stmt;
            endif;
            
            return false;
        }

    }
?>