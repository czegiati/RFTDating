package hu.unideb.RFTDatingSite.controller;

import hu.unideb.RFTDatingSite.Model.DateFunctions;
import hu.unideb.RFTDatingSite.Model.User;
import hu.unideb.RFTDatingSite.Model.forms.*;
import hu.unideb.RFTDatingSite.Model.validation.LoginValidation;
import hu.unideb.RFTDatingSite.repository.UserRepository;
import hu.unideb.RFTDatingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController
{
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return "redirect:/logedin";
        } else {
            return "login";
        }
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
        return "searcher";
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
      return "searcher";
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
      model.addAttribute("user",opw);
      System.out.println("OPW: "+opw);
        return "othersprofileview";
  }

}