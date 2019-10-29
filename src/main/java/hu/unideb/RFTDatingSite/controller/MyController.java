package hu.unideb.RFTDatingSite.controller;


import hu.unideb.RFTDatingSite.Model.Sex;
import hu.unideb.RFTDatingSite.Model.SexualOrientation;
import hu.unideb.RFTDatingSite.Model.User;
import hu.unideb.RFTDatingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("message","I'm ugly, please format me :'(");
    return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("User",new User());
        setRegistrationModel(model);
        return "register";
    }

    @PostMapping("/register")
    public String save(@Valid @ModelAttribute("User") User user, BindingResult errors,Model model) {
        setRegistrationModel(model);
        if(errors.hasErrors())
        {
            return "register";
        }
        System.out.println(user);
        userService.createUser(user);
        return "redirect:/login";
    }



    private void setRegistrationModel(Model model){
        List<Sex> sexList=new ArrayList<>();
        for(Sex x: Sex.values())
        {
            sexList.add(x);
        }
        model.addAttribute("sexList", sexList);


        List<SexualOrientation> sexoList=new ArrayList<>();
        for(SexualOrientation x: SexualOrientation.values())
        {
            sexoList.add(x);
        }
        model.addAttribute("sexoList",sexoList);
    }

}
