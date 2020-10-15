package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class SafeController {
		
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
		public String business() {
			log.info(this.getClass());
			
			return "/safe/business";
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
