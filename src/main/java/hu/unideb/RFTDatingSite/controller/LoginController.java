package hu.unideb.RFTDatingSite.controller;

import hu.unideb.RFTDatingSite.Model.DateFunctions;
import hu.unideb.RFTDatingSite.Model.User;
import hu.unideb.RFTDatingSite.Model.forms.*;
import hu.unideb.RFTDatingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController
{
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return "redirect:/logedin";
        } else {
            UserLoginForm form=new UserLoginForm();
            model.addAttribute("login",form);
            return "login";
        }
    }

    public Map<String,String> getUsernameAndPassw(String data){
        String username=data.substring(0,data.indexOf('&'));
        String password=data.substring(data.indexOf('&')+1);
        username=username.substring(username.indexOf('=')+1);
        password=password.substring(password.indexOf('=')+1);
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return map;
    }

    @PostMapping("/login")
    public ResponseEntity<String> postlogin(Model model, HttpServletRequest req, @RequestBody String data){
        //RequestBody returns: paramname=paramvalue&paramname2=paramvalue2...
        String username=getUsernameAndPassw(data).get("username");
        String password=getUsernameAndPassw(data).get("password");
        if(userService.correctLogIn(username,password)) {
            try {
                req.login(username, password);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>("redirect:/logedin", HttpStatus.OK);
        }
        return new ResponseEntity<>("redirect:/login?error", HttpStatus.UNAUTHORIZED);
    }


  @RequestMapping("/logedin")
   public String logedin(Model model){
      String username;
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof UserDetails) {
         username = ((UserDetails)principal).getUsername();
      } else {
          username = principal.toString();
      }
      LogedInInfo info=new LogedInInfo();
      info.setUsername(username);
      model.addAttribute("logeininfo",info);
      return "logedin";

  }

  @GetMapping("/logedin/search")
    public String search(Model model){
        model.addAttribute("SearchFormObj",new SearchForm());
      ArrayList<SearchedUsersForm> list=new ArrayList<>();
      model.addAttribute("SearchUsersObj",list);
        return "searcher2";
  }

  @PostMapping("/logedin/search")
    public String searchPost(@ModelAttribute("SearchFormObj") SearchForm searchForm,Model model){
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user=userService.getUserByUsername(((UserDetails) principal).getUsername());
      ArrayList<SearchedUsersForm> list=new ArrayList<>();

      if(searchForm.getMin()==null) searchForm.setMin(18);
      if(searchForm.getMax()==null || searchForm.getMax()>= 120) searchForm.setMax(100);

      List<User> users=userService.getUsersInSearch(user,searchForm.getMin(),searchForm.getMax());
      if(users.contains(user)) users.remove(user);
      for(User u: users)
      {
          SearchedUsersForm f=new SearchedUsersForm();
          f.setAge(DateFunctions.yearsPassedSince(u.getBirthdate()));
          f.setSex(u.getSex());
          f.setSexualOrientation(u.getSexualOrientation());
          f.setUsername(u.getUsername());
          list.add(f);
      }
      model.addAttribute("SearchUsersObj",list);
      return "searcher2";
  }

  @GetMapping("/logedin/search/{username}")
    public String viewProfileOfOthers(@RequestParam(value="username",required = true)String username,Model model){
        User user= userService.getUserByUsername(username);
      OthersProfileView opw=new OthersProfileView();
      opw.setAge(DateFunctions.yearsPassedSince(user.getBirthdate()));
      opw.setBio(user.getBio());
      opw.setSex(user.getSex());
      opw.setUsername(user.getUsername());
      opw.setSo(user.getSexualOrientation());
      opw.setImage(user.getImage());
      opw.setPicture(user.getPicture());
      model.addAttribute("user",opw);
        return "othersprofileview";
  }

  @GetMapping("/logedin/profile")
  public String viewMyProfile(Model model){
        User u=userService.getUserByUsername(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("myuser",u);
        MyController.setRegistrationModel(model);
        model.addAttribute("myfile",new String());
        return "myprofileview";
  }

  @PostMapping("/logedin/profile")
    public String postView(Model model
          , @ModelAttribute("myuser")User user
          , @RequestParam("type")String param
          , @RequestParam(value = "file",required = false) MultipartFile file
          , @ModelAttribute("errorM")String errorM) throws IOException {
      User u=userService.getUserByUsername(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
      switch (param) {
          case "bio":
              u.setBio(user.getBio());
              userService.updateUserWithoutEncryption(u);
              break;
          case "birthdate":
              if(!u.getBirthdate().equals(user.getBirthdate())) {
                  u.setBirthdate(new java.sql.Date(new Date(user.getBirthdate().getTime()+(1000 * 60 * 60 * 24)).getTime()));
                  userService.updateUserWithoutEncryption(u);
              }
              break;
          case "password":
              System.out.println(user.getBio()+"   "+user.getPassword());
              if(user.getBio().equals(user.getPassword())) {
                  u.setPassword(user.getPassword());
                  userService.updateUser(u);
              }else {
                  model.addAttribute("myuser",u);
                  MyController.setRegistrationModel(model);
                  return "myprofileview";
              }
              break;
          case "picture":
              u.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
              u.setImage(file.getBytes());
              userService.updateUserWithoutEncryption(u);
              break;
          case "sex":
              u.setSex(user.getSex());
              userService.updateUserWithoutEncryption(u);
              break;
          case "name":
              u.setFull_name(user.getFull_name());
              userService.updateUserWithoutEncryption(u);
              break;
          case "email":
              if(userService.isEmail(user.getEmail()))
              {
                  model.addAttribute("myuser",u);
                  MyController.setRegistrationModel(model);
                  return "myprofileview";
              }
              break;
          case "sexualOrientation":
              u.setSexualOrientation(user.getSexualOrientation());
              userService.updateUserWithoutEncryption(u);
              break;
      }
      return "redirect:/logedin";
  }

}