package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NUserInfoDTO;
import poly.dto.SafeDTO;
import poly.service.ISafeService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;



@Controller
public class SafeController {
	
	@Resource(name="SafeService")
	private ISafeService safeService;
		
		private Logger log = Logger.getLogger(this.getClass());
		
		/*
		 * ######################################
		 * 메인페이지 컨트롤러 시작!!
		 * ######################################
		 */
		
		
		/*
		 * #######################################
		 * 메인페이지 컨트롤러 끝!!
		 * #######################################
		 */
		
		/*
		 * #######################################
		 * 사업자 비사업자 선택 컨트롤러 시작!!
		 * #######################################
		 */
		
		@RequestMapping(value="safe/choice")
		public String choice() {
			log.info(this.getClass());
			
			return "/safe/choice";
		}
		/*
		 * #######################################
		 * 사업자 비사업자 선택 컨트롤러 끝!!
		 * #######################################
		 */
		
		/*
		 * ########################################
		 * 사업자 회원가입페이지 컨트롤러 시작!!
		 * ########################################
		 */
		@RequestMapping(value="safe/business")
		public String business(HttpServletRequest request, HttpServletResponse response,
				ModelMap model) throws Exception {
			
			log.info(this.getClass().getName() + "business start!");
			
			return "/safe/business";
		}
		
		@RequestMapping(value="safe/insertSafeUserInfo")
		public String insertUserInfo(HttpServletRequest request, HttpServletResponse response,
				ModelMap model) throws Exception {
			
			log.info(this.getClass().getName() + ".insertUserInfo start!");
			
			//회원가입 결과에 대한 메시지를 전달 할 변수
			String msg = "";
			
			//웹(회원정보 입력화면)에서 받는 정보를 저장 할 변수
			SafeDTO pDTO = null;
			
			try {
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!!
				 * 
				 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
				 */
				String user_id = CmmUtil.nvl(request.getParameter("user_id"));
				String user_name = CmmUtil.nvl(request.getParameter("user_name"));
				String password = CmmUtil.nvl(request.getParameter("password"));
				String email = CmmUtil.nvl(request.getParameter("email"));
				String addr1 = CmmUtil.nvl(request.getParameter("addr1"));
				String addr2 = CmmUtil.nvl(request.getParameter("addr2"));
				String storename = CmmUtil.nvl(request.getParameter("storename"));
				String bnumber = CmmUtil.nvl(request.getParameter("bnumber"));
				String pnumber = CmmUtil.nvl(request.getParameter("pnumber"));
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 끝!
				 * 
				 */
				
				/*
				 * 반드시 값을 받았으면 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
				 */
				log.info("user_id : " + user_id);
				log.info("user_name : " + user_name);
				log.info("password : " + password);
				log.info("email : " + email);
				log.info("addr1 : " + addr1);
				log.info("addr2 : " + addr2);
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!
				 * 
				 * 무조건 웹에서 받은 정보는 DTO에 저장해야 한다고 이해
				 */
				
				//웹(회원정보 입력화면)에서 받는 정보를 저장 할 변수를 메모리에 올리기
				pDTO = new SafeDTO();
				
				pDTO.setUser_id(user_id);
				pDTO.setUser_name(user_name);
				
				//비밀번호는 절대로 복호화되지 않도록 해서 알고리즘으로 암호화함
				pDTO.setPassword(EncryptUtil.encHashSHA256(password));
				
				//민감 정보인 이메일은 AES128-CBC로 암호화함
				pDTO.setEmail(EncryptUtil.encAES128CBC(email));
				pDTO.setAddr1(addr1);
				pDTO.setAddr2(addr2);
				pDTO.setStorename(storename);
				pDTO.setBnumber((bnumber));
				pDTO.setPnumber((pnumber));
				//민감 정보인 사업자번호는 AES128-CBC로 암호화함
				//pDTO.setBnumber(EncryptUtil.encHashSHA256(bnumber));
				//민감 정보인 핸드폰번호는 AES128-CBC로 암호화함
				//pDTO.setPnumber(EncryptUtil.encHashSHA256(pnumber));
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝! 
				 */
				
				/*
				 * 회원가입
				 */
				int res = safeService.insertSafeUserInfo(pDTO);
				
				if(res == 1) {
					msg = "회원가입 되었습니다.";
					
				//추후 회원가입 입력화면에서 ajax를 활용해서 아이디 중복, 이메일 중복을 체크하길 바람
				}else if(res == 2) {
					msg = "이미 가입된 이메일 주소입니다.";
				}else {
					msg = "오류로 인해 회원가입이 실패하였습니다.";
				}
				
			}catch(Exception e) {
				//저장이 실패되면 사용자에게 보여줄 메시지
				msg = "실패하였습니다 : " + e.toString();
				log.info(e.toString());
				e.printStackTrace();
				
			}finally {
				log.info(this.getClass().getName() + ".insertUserInfo end!");
				
				//외원가입 여부 결과 메시지 전달하기
				model.addAttribute("msg", msg);
				model.addAttribute("pDTO", pDTO);
				
				//변수 초기화(메모리 효율을 위해)
				pDTO = null;
			}
			
			return "/safe/Msg";
		}
		
		/*
		 * ########################################
		 * 사업자 회원가입페이지 컨트롤러 끝!!
		 * ########################################
		 */
		
		/*
		 * ########################################
		 * 개인회원 회원가입페이지  컨트롤러 시작!!
		 * ########################################
		 */
		
		@RequestMapping(value="safe/nonbusiness")
		public String nonbusiness(HttpServletRequest request, HttpServletResponse response,
				ModelMap model) throws Exception {
			
			log.info(this.getClass().getName() + "nonbusiness start!");
			
			return "/safe/nonbusiness";
		}
		
		@RequestMapping(value="safe/insertNUserInfo")
		public String insertNUserInfo(HttpServletRequest request, HttpServletResponse response,
				ModelMap model) throws Exception {
			
			log.info(this.getClass().getName() + ".insertUserInfo start!");
			
			//회원가입 결과에 대한 메시지를 전달 할 변수
			String msg = "";
			
			//웹(회원정보 입력화면)에서 받는 정보를 저장 할 변수
			NUserInfoDTO NDTO = null;
			
			try {
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!!
				 * 
				 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
				 */
				String user_id = CmmUtil.nvl(request.getParameter("user_id"));
				String user_name = CmmUtil.nvl(request.getParameter("user_name"));
				String password = CmmUtil.nvl(request.getParameter("password"));
				String email = CmmUtil.nvl(request.getParameter("email"));
				String addr1 = CmmUtil.nvl(request.getParameter("addr1"));
				String addr2 = CmmUtil.nvl(request.getParameter("addr2"));
				String pnumber = CmmUtil.nvl(request.getParameter("pnumber"));
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 끝!
				 * 
				 */
				
				/*
				 * 반드시 값을 받았으면 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
				 */
				log.info("user_id : " + user_id);
				log.info("user_name : " + user_name);
				log.info("password : " + password);
				log.info("email : " + email);
				log.info("addr1 : " + addr1);
				log.info("addr2 : " + addr2);
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!
				 * 
				 * 무조건 웹에서 받은 정보는 DTO에 저장해야 한다고 이해
				 */
				
				//웹(회원정보 입력화면)에서 받는 정보를 저장 할 변수를 메모리에 올리기
				NDTO = new NUserInfoDTO();
				
				NDTO.setUser_id(user_id);
				NDTO.setUser_name(user_name);
				
				//비밀번호는 절대로 복호화되지 않도록 해서 알고리즘으로 암호화함
				NDTO.setPassword(EncryptUtil.encHashSHA256(password));
				
				//민감 정보인 이메일은 AES128-CBC로 암호화함
				NDTO.setEmail(EncryptUtil.encAES128CBC(email));
				NDTO.setAddr1(addr1);
				NDTO.setAddr2(addr2);
				NDTO.setPnumber((pnumber));
				//민감 정보인 사업자번호는 AES128-CBC로 암호화함
				//pDTO.setBnumber(EncryptUtil.encHashSHA256(bnumber));
				//민감 정보인 핸드폰번호는 AES128-CBC로 암호화함
				//pDTO.setPnumber(EncryptUtil.encHashSHA256(pnumber));
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝! 
				 */
				
				/*
				 * 회원가입
				 */
				int res = safeService.insertNUserInfo(NDTO);
				
				if(res == 1) {
					msg = "회원가입 되었습니다.";
					
				//추후 회원가입 입력화면에서 ajax를 활용해서 아이디 중복, 이메일 중복을 체크하길 바람
				}else if(res == 2) {
					msg = "이미 가입된 이메일 주소입니다.";
				}else {
					msg = "오류로 인해 회원가입이 실패하였습니다.";
				}
				
			}catch(Exception e) {
				//저장이 실패되면 사용자에게 보여줄 메시지
				msg = "실패하였습니다 : " + e.toString();
				log.info(e.toString());
				e.printStackTrace();
				
			}finally {
				log.info(this.getClass().getName() + ".insertUserInfo end!");
				
				//외원가입 여부 결과 메시지 전달하기
				model.addAttribute("msg", msg);
				model.addAttribute("pDTO", NDTO);
				
				//변수 초기화(메모리 효율을 위해)
				NDTO = null;
			}
			
			return "/safe/Mesg";
		}
		
		/*
		 * ########################################
		 * 로그인 컨트롤러 시작!!
		 * ########################################
		 */
		
		
		// 로그인 처리 및 결과 알려주는 화면으로 이동
		@RequestMapping(value="safe/getNUserLoginCheck")
		public String getNUserLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) throws Exception {
			log.info(this.getClass().getName() + ".getNUserLoginCheck start!");
			
			// 로그인 처리 결과를 저장할 변수(로그인 성공 : 1, 아이디, 비밀번호 불일치로 인한 실패 : 0, 시스템 에러 : 2)
			int res = 0;
			
			//웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
			NUserInfoDTO pDTO = null;
			
			try {
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작
				 * 
				 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
				 */
				String user_id = CmmUtil.nvl(request.getParameter("user_id"));
				String password = CmmUtil.nvl(request.getParameter("password"));
				// String 변수에 저장 끝
				
				// 로그 필수
				log.info("user_id : " + user_id);
				log.info("password : " + password);
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!
				 * 
				 * 무조건 웹에서 받은 정보는 DTO에 저장해야 한다고 이해
				 */
				
				//웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
				pDTO = new NUserInfoDTO();
				
				pDTO.setUser_id(user_id);
				
				//비밀번호는 절대 복호화되지 않도록 해시 알고리즘으로 암호화함
				pDTO.setPassword(EncryptUtil.encHashSHA256(password));
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝!!
				 * 
				 * 무조건 웹에서 받은 정보는 DTO에 저장해야 한다고 이해
				 */
				
				//로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 userInfoService 호출하기
				res = safeService.getNUserLoginCheck(pDTO);
				
				/*
				 * 로그인을 성공했다면, 회원아이디 정보를 session에 저장함
				 * 
				 * 세션은 톰캣(was)의 메모리에 존재하며, 웹사이트에 접속한 사람(연결된 객체)마다 메모리에 값을 올린다.
				 * 
				 * 예 ) 톰캣에 100명의 사용자가 로그인했다면 사용자 각각 회원아이디를 메모리에 저장하며
				 * 메모리에 저장된 객체의 수는 100개이다.
				 * 따라서 과도한 세션은 톰캣의 메모리 부하를 발생시켜 서버가 다운되는 현상이 있을 수 있기때문에
				 * 최소한으로 사용하는 것을 권장한다.
				 * 
				 * 스프링에서 세션은 사용하기 위해서는 함수명의 파라미터에 HttpSession session이 존재해야 한다.
				 * 세션은 톰캣의 메모리에 저장되기 때문에 url마다 전달하는게 필요하지 않고
				 * 그냥 메모리에서 부르면 되기 때문에 jsp, controller에서 쉽게 불러서 쓸 수 있다
				 */
				if(res == 1) { //로그인 성공
					/*
					 * 세션에 회원아이디 저장하기, 추후 로그인여부를 체크하기 위해 세션에 값이 존재하는지 체크한다
					 * 일반적으로 세션에 저장되는 키는 대문자로 입력하며 앞에 SS를 붙인다
					 * 
					 * Session 단어에서 SS를 가져온 것이다.
					 */
					session.setAttribute("SS_USER_ID", user_id);
				}
				
			}catch(Exception e) {
				//저장이 실패되면 사용자에게 보여줄 메시지
				res = 2;
				log.info(e.toString());
				e.printStackTrace();
			}finally {
				log.info(this.getClass().getName() + ".insertSafeUserInfo end!");
				
				/*
				 * 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용
				 * 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이 있어
				 * 문자 유형(String)으로 강제 형변환하여 jsp에 전달한다.
				 */
				model.addAttribute("res", String.valueOf(res));
				
				//변수 초기화(메모리 효율화 시키기 위해)
				pDTO = null;
				
			}
			
			return "/safe/LoginResult";
		}
		/*
		 * ########################################
		 * 개인회원 회원가입페이지 컨트롤러 끝!!
		 * ########################################
		 */
		
		
		
		/*
		 * ########################################
		 * 로그인 컨트롤러 시작!!
		 * ########################################
		 */
		
		
		// 로그인 처리 및 결과 알려주는 화면으로 이동
		@RequestMapping(value="safe/getUserSafeLoginCheck")
		public String getUserSafeLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) throws Exception {
			log.info(this.getClass().getName() + ".getUserSafeLoginCheck start!");
			
			// 로그인 처리 결과를 저장할 변수(로그인 성공 : 1, 아이디, 비밀번호 불일치로 인한 실패 : 0, 시스템 에러 : 2)
			int res = 0;
			
			//웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
			SafeDTO pDTO = null;
					
			try {
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작
				 * 
				 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
				 */
				String user_id = CmmUtil.nvl(request.getParameter("user_id"));
				String password = CmmUtil.nvl(request.getParameter("password"));
				// String 변수에 저장 끝
				System.out.println();
				
				// 로그 필수
				log.info("user_id : " + user_id);
				log.info("password : " + password);
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!
				 * 
				 * 무조건 웹에서 받은 정보는 DTO에 저장해야 한다고 이해
				 */
				
				//웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
				pDTO = new SafeDTO();
				
				pDTO.setUser_id(user_id);
				//비밀번호는 절대 복호화되지 않도록 해시 알고리즘으로 암호화함
				pDTO.setPassword(EncryptUtil.encHashSHA256(password));
				
				/*
				 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝!!
				 * 
				 * 무조건 웹에서 받은 정보는 DTO에 저장해야 한다고 이해
				 */
				
				//로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 userInfoService 호출하기
				res = safeService.getUserSafeLoginCheck(pDTO);
				
				/*
				 * 로그인을 성공했다면, 회원아이디 정보를 session에 저장함
				 * 
				 * 세션은 톰캣(was)의 메모리에 존재하며, 웹사이트에 접속한 사람(연결된 객체)마다 메모리에 값을 올린다.
				 * 
				 * 예 ) 톰캣에 100명의 사용자가 로그인했다면 사용자 각각 회원아이디를 메모리에 저장하며
				 * 메모리에 저장된 객체의 수는 100개이다.
				 * 따라서 과도한 세션은 톰캣의 메모리 부하를 발생시켜 서버가 다운되는 현상이 있을 수 있기때문에
				 * 최소한으로 사용하는 것을 권장한다.
				 * 
				 * 스프링에서 세션은 사용하기 위해서는 함수명의 파라미터에 HttpSession session이 존재해야 한다.
				 * 세션은 톰캣의 메모리에 저장되기 때문에 url마다 전달하는게 필요하지 않고
				 * 그냥 메모리에서 부르면 되기 때문에 jsp, controller에서 쉽게 불러서 쓸 수 있다
				 */
				if(res == 1) { //로그인 성공
					/*
					 * 세션에 회원아이디 저장하기, 추후 로그인여부를 체크하기 위해 세션에 값이 존재하는지 체크한다
					 * 일반적으로 세션에 저장되는 키는 대문자로 입력하며 앞에 SS를 붙인다
					 * 
					 * Session 단어에서 SS를 가져온 것이다.
					 */
					session.setAttribute("SS_USER_ID", user_id);
				}
				
			}catch(Exception e) {
				//저장이 실패되면 사용자에게 보여줄 메시지
				res = 2;
				log.info(e.toString());
				e.printStackTrace();
			}finally {
				log.info(this.getClass().getName() + ".insertSafeUserInfo end!");
				
				/*
				 * 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용
				 * 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이 있어
				 * 문자 유형(String)으로 강제 형변환하여 jsp에 전달한다.
				 */
				model.addAttribute("res", String.valueOf(res));
				
				//변수 초기화(메모리 효율화 시키기 위해)
				pDTO = null;
				
			}
			
			return "/index";
		}
		
		/*
		 * ########################################
		 * 로그인 컨트롤러 끝!!
		 * ########################################
		 */
		
		
		
		@RequestMapping(value="safe/Login")
		public String Login() {
			log.info(this.getClass());
			
			return "/safe/Login";
		}
		
		
		
			
}
