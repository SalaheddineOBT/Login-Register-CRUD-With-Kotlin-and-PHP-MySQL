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
            $sql='SELECT UserName FROM users WHERE Email="'.$email.'" AND Password="'.$password.'";';
            $stmt=$this->con->prepare($sql);
            $stmt->execute();
            return $stmt;
        }

        public function Register($username,$email,$password)
        {   
            $sql='INSERT INTO users (UserName,Email,Password) VALUES("'.$username.'","'.$email.'","'.$password.'");';
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

    }

?>