package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
		@RequestMapping(value="safe/main")
		public String main() {
			log.info(this.getClass());
			
			return "/safe/main";
		}
		
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
				log.info("storename");
				log.info("bnumber");
				log.info("pnumber");
				
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
				
				//회원가입 여부 결과 메시지 전달하기
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
		public String nonbusiness() {
			log.info(this.getClass());
			
			return "/safe/nonbusiness";
		}
		/*
		 * ########################################
		 * 개인회원 회원가입페이지 컨트롤러 끝!!
		 * ########################################
		 */
			
}
