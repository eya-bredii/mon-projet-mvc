package com.data.examen.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {
@GetMapping("login")
public String connexion() {
	return"login";
}
}
