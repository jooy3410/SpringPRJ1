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
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Index(HttpServletRequest request, HttpServletResponse reponse, ModelMap model, HttpSession session) throws Exception {
		log.info(getClass().getName() + "index start");
		
		int res = RestService.countBoard();
		
		int num = RestService.countSC();
		
		model.addAttribute("res", String.valueOf(res));
		model.addAttribute("num", String.valueOf(num));
		
		return "/index"; 
	}
	
	//초기 게시판 리스트
	@RequestMapping(value = "rest/rest.do", method = RequestMethod.GET)
	public String rest(HttpServletRequest request, HttpServletResponse reponse, ModelMap model, HttpSession session) throws Exception {
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
		
		return "/rest/rest2";
	
	}

	    //페이징 처리 안심식당 리스트
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
		
		//상호명 찾기, Search 기능
		@RequestMapping(value = "rest/restSearch", method=RequestMethod.POST)
		@ResponseBody
		public List<RestDTO> restSearchList(HttpServletRequest request)throws Exception{
			//컨트롤러로 들어옴
			log.info(this.getClass() + ".rest/restSearchList start!!");
			
			//jsp 보낸값을 받는거
			String bizplc_nm = CmmUtil.nvl(request.getParameter("name"));
			//cmmutil .src > util > cmmutil 함수 값이 널이면 ""으로 바꿔주는 것
			//ajax data {}, form name, ?title=값
			log.info("name: " +bizplc_nm);
			
			RestDTO eDTO = new RestDTO();
			eDTO.setBizplc_nm(bizplc_nm);
			
			List<RestDTO> eList = RestService.getRestSearchList(eDTO);
			
			log.info("eList size : " + eList.size());

			log.info(this.getClass() + ".rest/restSearchList end!!");
			
			return eList;
		}
	
	//상세페이지 내 지도 마커찍기
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
	
	//자가점검표 
	@RequestMapping(value="rest/SelfCheck.do")
	public String selfcheck(){
		return "rest/SelfCheck";
	}

	
	@RequestMapping(value="rest/SelfCheckdetail.do")
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
	
	//안심식당 상세페이지
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
	
	//자가점검표 리스트
	@RequestMapping(value="rest/selfCheckList.do")
	public String SCList(PagingDTO pDTO, Model model
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
		return "rest/selfCheckList";
	}
	
	//자가점검표 상세페이지
	@RequestMapping(value = "rest/SelfCheckDetail.do", method = RequestMethod.GET)
	public String sc_detail(HttpServletRequest request, HttpServletResponse reponse, ModelMap model,HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "SCDetail start!");
		
		String safety_restrnt_no = request.getParameter("no");
		
		RestDTO pDTO = new RestDTO();
		pDTO.setSafety_restrnt_no(safety_restrnt_no);
		
		RestDTO rDTO = RestService.getRestInfoDetail(pDTO);
		
		//rList = null;
		//pSet = null;
		//r2List = null;
		
		model.addAttribute("rDTO", rDTO);
		
		log.info("SCDetail.do end!!");
		
		return "/rest/SelfCheckDetail";
	
	}
	
	@RequestMapping(value="rest/about.do")
	public String about(){
		return "rest/about2";
	}

}
