package shop.mtcoding.blog.controller;

import java.net.PasswordAuthentication;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blog.controller.util.Script;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.model.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession session;

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password) {
        User principal = userRepository.findByUsernameAndPassword(username, password);
        if (principal == null) {
            return Script.back("로그인 실패");
        }
        session.setAttribute("principal", principal);
        return Script.href("/board/list");
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(String username, String password, String email) {
        int result = userRepository.insert(username, password, email);
        if (result != 1) {
            return Script.back("회원가입 실패");
        }
        return Script.href("/loginForm");
    }

    @GetMapping("/loginForm")
    public String logintForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }

        User user = userRepository.findById(principal.getId());
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("user/{id}/update")
    @ResponseBody
    public String update(@Param("id") int id, String username, String email) {

    }

}
