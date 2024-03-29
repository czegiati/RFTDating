package hu.unideb.RFTDatingSite.controller;


import hu.unideb.RFTDatingSite.Model.Sex;
import hu.unideb.RFTDatingSite.Model.SexualOrientation;
import hu.unideb.RFTDatingSite.Model.User;
import hu.unideb.RFTDatingSite.Model.forms.UserLoginForm;
import hu.unideb.RFTDatingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MyController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String register(Model model) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            model.addAttribute("User",new User());
            setRegistrationModel(model);
            return "register";
        } else {
            return "redirect:/logedin";
        }

    }

    @PostMapping("/register")
    public String save(@Valid @ModelAttribute("User") User user, BindingResult errors,Model model) {
        if(errors.hasErrors())
        {
            setRegistrationModel(model);
            return "register";
        }
        else
            {
            userService.createUser(user);
            return "redirect:/login";
        }
    }


    static void setRegistrationModel(Model model){
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
