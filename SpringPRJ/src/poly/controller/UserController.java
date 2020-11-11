package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserDTO;
import poly.service.IUserService;
import poly.util.CmmUtil;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	
//	resource name ="service"가 있어야 메모리에 올라가져 있어서 service로 이동할수 있다.
	
	@Resource(name = "UserService")
	private IUserService userService;
	
	@RequestMapping(value="user/userLogin")
	public String userLogin() {
		log.info(this.getClass() + "user/userLogin start!!");
		log.info(this.getClass() + "user/userLogin end!!");
		return "/user/userLogin";
	}
	
	@RequestMapping(value="user/userLoginProc")
	public String userLoginProc(HttpServletRequest request, HttpSession session, Model model)throws Exception{
		log.info("user/userLoginProc start !!");
		//mapping ""은 jsp form에서  action 이랑 같아야함
		//request("") = jsp input name과 같아야함
		//cmmtil.nvl 은 util함수로 null갑을 공백으로 바꿔야한다.
		
		String id = CmmUtil.nvl(request.getParameter("id"));
		String pwd = CmmUtil.nvl(request.getParameter("pwd"));
		
		log.info("id : " + id);
		log.info("pwd : " + pwd);
		
		//UserDTO 사용을 위해 메로리에 올림
		UserDTO uDTO = new UserDTO();
		
		//UserDTO 셋팅
		uDTO.setId(id);
		uDTO.setPwd(pwd);
		
		String msg = "";
		String url = "";
		
		//user Service를 uDTO보내기
		uDTO = userService.getUserInfo(uDTO);
		
		if(uDTO != null) {
			log.info("uDTO.id : " + uDTO.getId());
			log.info("uDTO.name : " + uDTO.getName());
			msg = "로그인 성공";
			session.setAttribute("id", uDTO.getId());
			session.setAttribute("name", uDTO.getName());
		}else {
			msg= "로그인 실패";
		}
		
		url="/";
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		

		
		log.info("user/userLoginProc end !!");
		
		return "redirect";
		
}
	@RequestMapping(value="/user/userlogOut")
	public String logOut(HttpSession session, Model model)throws Exception{
		log.info(this.getClass() + "user/logOut start!!");
		
		String msg = "";
		String url = "";
		
		msg = "로그아웃 성공";
		
		session.invalidate();
		
		url = "/";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		log.info(this.getClass() + "user/logOut end!!");
		return "/redirect";
	}
}
