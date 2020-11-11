package poly.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dto.PagingDTO;
import poly.dto.RestDTO;
import poly.dto.SelfCheckDTO;
import poly.service.IRestService;
import poly.util.CmmUtil;

@Controller
public class RestController {
	
	@Resource(name = "RestService")
	IRestService RestService;

	private Logger log = Logger.getLogger(this.getClass());
	
	
	
	@RequestMapping(value = "rest/rest.do", method = RequestMethod.GET)
	public String rest(HttpServletRequest request, HttpServletResponse reponse, ModelMap model,HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "rest start!");

		List<String> rList = RestService.getRestInfo();
		
	            
		if(rList == null) {
	          	rList = new ArrayList<>();
	    }
	      
	    
        //HashSet<RestDTO> pSet = new HashSet<>(rList);
        //ArrayList<RestDTO> r2List = new ArrayList<>(pSet);
	 

	
		model.addAttribute("rList",rList);
		
		rList = null;
		//pSet = null;
		//r2List = null;
		
		log.info("rest.do end!!");
		
		return "/rest/rest";
	
	}

	//페이징 컨트롤러
		@RequestMapping(value="rest/restPaging.do")
		public String boardList(PagingDTO pDTO, Model model
				, @RequestParam(value="nowPage", required=false)String nowPage
				, @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception{
			
			int total = RestService.countBoard();
			
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "20";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) { 
				cntPerPage = "20";
			}
			
			pDTO = new PagingDTO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			
			model.addAttribute("paging", pDTO);
			model.addAttribute("viewAll", RestService.selectBoard(pDTO));
			log.info(this.getClass());
			return "rest/restPaging";
		}
	
	@RequestMapping(value = "map/map.do", method = RequestMethod.GET)
	public String Map2Service(HttpServletRequest request, HttpServletResponse reponse, ModelMap model,HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "MAP start!");

		List<String> rList = RestService.getRestInfo();
		
	            
		if(rList == null) {
	          	rList = new ArrayList<>();
	    }
	    
		model.addAttribute("rList",rList);
		
		log.info("map.do end!!");
		
		rList = null;
	
		
		return "/map/map";
	
	}
	
	// 현재위치 찍기 https로 접속시 사용가능
	@RequestMapping(value="map/mapForGeolocation.do") 
	public String map3() { 
		
		log.info("mapForGeolocation 시작!!");
		
		return "/map/mapForGeolocation";
	}
	
	@RequestMapping(value="rest/SelfCheck.do")
	public String SC() throws Exception {
		
		log.info("SC start!");
		
		return "/rest/SelfCheck";
	}
	
	@RequestMapping(value="rest/selfCheck.do")
	public String selfCheck(HttpServletRequest request, HttpServletResponse response,
			ModelMap model
			, @RequestParam(value="f", required=false) String[] queryString) throws Exception{
		
		String air = CmmUtil.nvl(request.getParameter("air"));
		String distance = CmmUtil.nvl(request.getParameter("distance"));
		String time = CmmUtil.nvl(request.getParameter("time"));
		String scale = CmmUtil.nvl(request.getParameter("scale"));
		String activity = CmmUtil.nvl(request.getParameter("activity"));
		String manager = CmmUtil.nvl(request.getParameter("manager"));
		String hdisinfectant = CmmUtil.nvl(request.getParameter("hdisinfectant"));
		String mask = CmmUtil.nvl(request.getParameter("mask"));
		String announcement = CmmUtil.nvl(request.getParameter("announcement"));
		String disinfection = CmmUtil.nvl(request.getParameter("disinfection"));
		String namecheck = CmmUtil.nvl(request.getParameter("namecheck"));
		
		SelfCheckDTO pDTO = new SelfCheckDTO();
		
		pDTO.setAir(air);
		pDTO.setDistance(distance);
		pDTO.setTime(time);
		pDTO.setScale(scale);
		pDTO.setActivity(activity);
		pDTO.setManager(manager);
		pDTO.setHdisinfectant(hdisinfectant);
		pDTO.setMask(mask);
		pDTO.setAnnouncement(announcement);
		pDTO.setDisinfection(disinfection);
		pDTO.setNamecheck(namecheck);
		
		int res = RestService.selfCheck(pDTO);
		
		log.info(res);
		
		String msg = "";
	
		if(res == 1) {
			msg = "등록이 완료되었습니다.";
		}
		else{
			msg = "등록 실패";
		}
		
		model.addAttribute("msg", msg);
		
		return "/rest/Msg";
	}
	
	@RequestMapping(value = "rest/restDetail.do", method = RequestMethod.GET)
	public String rest_detail(HttpServletRequest request, HttpServletResponse reponse, ModelMap model,HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "restDetail start!");
		
		String safety_restrnt_no = request.getParameter("no");
		
		RestDTO pDTO = new RestDTO();
		pDTO.setSafety_restrnt_no(safety_restrnt_no);
		
		RestDTO rDTO = RestService.getRestInfoDetail(pDTO);
		
		//rList = null;
		//pSet = null;
		//r2List = null;
		
		
		
		model.addAttribute("rDTO", rDTO);
		
		log.info("restDetail.do end!!");
		
		return "/rest/restDetail";
	
	}
}
