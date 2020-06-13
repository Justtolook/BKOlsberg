<?php
/**
 * Created by PhpStorm.
 * User: christian
 * Date: 24.10.18
 * Time: 15:24
 */

require_once ($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

class Bedingung
{
    var $qualification;
    var $education;

    function getQualification() {return  $this->qualification;}
    function getQualificationID() {return $this->qualification->getID();}

    function getEducation() {return $this->education;}
    function getEducationID() {return $this->education->getID();}

    function __construct($qualification, $education)
    {
        $this->education = $education;
        $this->qualification = $qualification;
    }
}