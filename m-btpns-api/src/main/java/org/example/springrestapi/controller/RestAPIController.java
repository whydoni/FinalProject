package org.example.springrestapi.controller;

import com.google.gson.Gson;
import org.example.database.entities.Nasabah;
import org.example.springrestapi.SpringbootDummyBankMain;
import org.example.springrestapi.rabbitmq.*;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class RestAPIController {
    public final RecvMqRestAPI restApiReceive = new RecvMqRestAPI();
    public final Logger logger = LoggerFactory.getLogger(SpringbootDummyBankMain.class);

    //--------------------------Get All Nasabah-------------------------------------
    @RequestMapping(value = "/nasabah/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllNsb() throws IOException, TimeoutException {
        try {
        SendMqRestAPI.getAll();
        restApiReceive.receiveFromDatabase();
        Thread.sleep(1000);
        return new ResponseEntity<>(restApiReceive.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("ERROR! on getmapping /nasabah : " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //--------------------------Create Mahasiswa-------------------------------------
    @RequestMapping(value = "/nasabah/", method = RequestMethod.POST)
    public ResponseEntity<?> createNsb(@RequestBody Nasabah nasabah) {
        try {
            SendMqRestAPI.addNasabah(new Gson().toJson(nasabah));
            restApiReceive.receiveFromDatabase();
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("ERROR on RestApiController -create :  " + e);
        }
        return new ResponseEntity<>("Success, data created! \n", HttpStatus.OK);
    }

    //--------------------------Update Data Nasabah-------------------------------------
    @RequestMapping(value = "/nasabah/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateNsb(@PathVariable("id") Long id, @RequestBody Nasabah nasabah) {
        nasabah.setId(id);
        try {
            SendMqRestAPI.updateNasabah(new Gson().toJson(nasabah));
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("ERROR on RestApiController -update :  " + e);
        }
        return new ResponseEntity<>("Success, data updated! ", HttpStatus.OK);
    }

    //--------------------------Find Data Nasabah by Username-------------------------------------
    @RequestMapping(value = "/nasabah/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> findNsb(@PathVariable("username") String username) {
        try {
            SendMqRestAPI.findDataById(username);
            restApiReceive.RecvDataUser();
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("ERROR on RestApiController -findbyid :  " + e);
        }
        return new ResponseEntity<>(restApiReceive.getDatamessage(), HttpStatus.OK);
    }


    //--------------------------Get Saldo Nasabah by Username-------------------------------------
    @RequestMapping(value = "/saldo/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getSaldo(@PathVariable("username") String username) {
        try {
            SendMqRestAPI.getSaldoNsb(username);
            return new ResponseEntity<>(restApiReceive.RecvSaldoUser(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Saldo");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }


    //--------------------------Delete Data Nasabah by Id-------------------------------------
    @RequestMapping(value = "/nasabah/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNsb(@PathVariable("id") Long id) {
        try {
            SendMqRestAPI.deleteNasabahById(Long.toString(id));
        }catch (Exception e){
            System.out.println("ERROR on RestApiController -deletebyid :  " + e);
        }
        return new ResponseEntity<>("Data deleted!!! ", HttpStatus.OK);
    }


    //--------------------------Do Login Nasabah-------------------------------------
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<?> loginNsb(@RequestBody Nasabah nasabah) {
        try {
            SendMqRestAPI.loginNasabah(new Gson().toJson(nasabah));
            return new ResponseEntity<>(restApiReceive.RecvLoginMsg(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Login, Please Check Username or Password!!!");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }


    //--------------------------Do Logout Nasabah-------------------------------------
    @RequestMapping(value = "/logout/", method = RequestMethod.GET)
    public ResponseEntity<?> logutNsb() {
        try {
            SendMqRestAPI.logoutNasabah();
            return new ResponseEntity<>(restApiReceive.RecvLogoutMsg(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Logout");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }

    }


}
