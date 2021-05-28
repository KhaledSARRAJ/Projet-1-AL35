package fr.eql.projet01.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eql.projet01.dao.UtilisateurRepository;
import fr.eql.projet01.entity.Utilisateur;

@Controller
public class DefaultController {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
    //@GetMapping("/")
    
	/*public String home1() {
        return "/home";
    }*/

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("users", utilisateurRepository.findAll());
        return "user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    
    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
    
    @ModelAttribute("idSession")
	public String idSession(HttpSession session) {
		return session.getId();
	}
    	
    @RequestMapping("/session-end")
    public String finSession(Model model,HttpSession session) {
	    SecurityContextHolder.clearContext(); //RAZ context Spring security
	    session.invalidate();
        model.addAttribute("message", "session terminée");
        model.addAttribute("title","welcome");
        return "home"; 
    }
}