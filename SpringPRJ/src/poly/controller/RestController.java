package poly.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import poly.dto.RestDTO;
import poly.service.IRestService;
import test.Test;

@Controller
public class RestController {
	
	@Resource(name = "RestService")
	IRestService RestService;

	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "rest/rest.do", method = RequestMethod.GET)
	public String RestService(HttpServletRequest request, HttpServletResponse reponse, ModelMap model,HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "rest start!");

		
		ArrayList<RestDTO> rList= new ArrayList<RestDTO>();
		ArrayList<RestDTO> r2List = null;
		
		int page = 1;   // 페이지 초기값 
	      try{
	         while(true){
	            // parsing할 url 지정(API 키 포함해서)
	            String url = "https://openapi.gg.go.kr/SafetyRestrntInfo?KEY=12200392d4a94e059fec5a6fa315518c&pIndex="+page+"&pSize=1000";
	            
	            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	            Document doc = dBuilder.parse(url);
	            
	            
	            // root tag 
	            doc.getDocumentElement().normalize();
	            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	            
	            // 파싱할 tag
	            NodeList nList = doc.getElementsByTagName("row");
	            
	            System.out.println("파싱할 리스트 수 : "+ nList.getLength());
	            
	            for(int temp = 0; temp < nList.getLength(); temp++){
	               Node nNode = nList.item(temp);
	               if(nNode.getNodeType() == Node.ELEMENT_NODE){
	                  
	                  Element eElement = (Element) nNode;
	                  System.out.println("######################");
	                  //System.out.println(eElement.getTextContent());
	                  System.out.println("식당번호  : " + Test.getTagValue("SAFETY_RESTRNT_NO", eElement));
	                  System.out.println("상호명  : " + Test.getTagValue("BIZPLC_NM", eElement));
	                 // System.out.println("도로명주소  : " + Test.getTagValue("REFINE_ROADNM_ADDR", eElement));
	                  System.out.println("구주소  : " + Test.getTagValue("REFINE_LOTNO_ADDR", eElement));
	                  
	                  RestDTO pDTO = new RestDTO();
	                 
	                  pDTO.setSafety_restrnt_no(Test.getTagValue("SAFETY_RESTRNT_NO", eElement));//식당번호
	                  pDTO.setBizplc_nm(Test.getTagValue("BIZPLC_NM", eElement)); //상호명
	                  pDTO.setRefine_lotno_addr(Test.getTagValue("REFINE_LOTNO_ADDR", eElement)); //구주소
	          		  //pDTO.setRefine_roadnm_addr(Test.getTagValue("REFINE_ROADNM_ADDR", eElement)); //도로명주소
	          		  //pDTO.setRefine_wgs84_lat(Test.getTagValue("REFINE_WGS84_LAT", eElement)); //위도
	          		  //pDTO.setRefine_wgs84_logt(Test.getTagValue("REFINE_WGS84_LOGT", eElement)); //경도
	          		
	          		  rList.add(pDTO);
	          		  HashSet<RestDTO> pSet = new HashSet<>(rList);
	          		  r2List = new ArrayList<>(pSet);
	          		 
	               }   // for end
	            }   // if end
	            
	            page += 1;
	            System.out.println("page number : "+page);
	            if(page > 12){   
	               break;
	            }
	         }   // while end
	         
	      } catch (Exception e){   
	         e.printStackTrace();
	      }   // try~catch end

	
		model.addAttribute("rList",r2List);
		log.info("rest.do end!!");
		
		return "/rest/rest";
	
	}

	
	@RequestMapping(value = "map/map.do", method = RequestMethod.GET)
	public String Map2Service(HttpServletRequest request, HttpServletResponse reponse, ModelMap model,HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "MAP start!");
		
		//List<RestDTO> rList= new ArrayList<>();

		ArrayList<RestDTO> rList= new ArrayList<RestDTO>();
		//ArrayList<RestDTO> r2List = null;
		
		int page = 1;   // 페이지 초기값 
	      try{
	         while(true){
	            // parsing할 url 지정(API 키 포함해서)
	            String url = "https://openapi.gg.go.kr/SafetyRestrntInfo?KEY=12200392d4a94e059fec5a6fa315518c&pIndex="+page+"&pSize=10";
	            
	            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	            Document doc = dBuilder.parse(url);
	            
	            
	            // root tag 
	            doc.getDocumentElement().normalize();
	            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	            
	            // 파싱할 tag
	            NodeList nList = doc.getElementsByTagName("row");
	            
	            System.out.println("파싱할 리스트 수 : "+ nList.getLength());
	            
	            for(int temp = 0; temp < nList.getLength(); temp++){
	               Node nNode = nList.item(temp);
	               if(nNode.getNodeType() == Node.ELEMENT_NODE){
	                  
	                  Element eElement = (Element) nNode;
	                  System.out.println("######################");
	                  //System.out.println(eElement.getTextContent());
	                  
	                  System.out.println("상호명  : " + Test.getTagValue("BIZPLC_NM", eElement));
	                  System.out.println("구주소  : " + Test.getTagValue("REFINE_LOTNO_ADDR", eElement));
	                  System.out.println("도로명주소  : " + Test.getTagValue("REFINE_ROADNM_ADDR", eElement));
	                  
	                  RestDTO pDTO = new RestDTO();
	                  
	                  pDTO.setBizplc_nm(Test.getTagValue("BIZPLC_NM", eElement)); //상호명
	          		  pDTO.setRefine_lotno_addr(Test.getTagValue("REFINE_LOTNO_ADDR", eElement)); //구주소
	          		  //pDTO.setRefine_roadnm_addr(Test.getTagValue("REFINE_ROADNM_ADDR", eElement)); //도로명주소
	          		  //pDTO.setRefine_wgs84_lat(Test.getTagValue("REFINE_WGS84_LAT", eElement)); //위도
	          		  //pDTO.setRefine_wgs84_logt(Test.getTagValue("REFINE_WGS84_LOGT", eElement)); //경도
	          		
	          		  rList.add(pDTO);
	          		 //HashSet<RestDTO> pSet = new HashSet<>(rList);
	          		 //r2List = new ArrayList<>(pSet);
	          		
	               }   // for end
	            }   // if end
	            
	            page += 1;
	            System.out.println("page number : "+page);
	            if(page > 5){   
	               break;
	            }
	         }   // while end
	         
	      } catch (Exception e){   
	         e.printStackTrace();
	      }   // try~catch end

	
		model.addAttribute("rList",rList);
		log.info("map.do end!!");
		
		return "/map/map";
	
	}
	
	// 현재위치 찍기 https로 접속시 사용가능
	@RequestMapping(value="map/mapForGeolocation.do") 
	public String map3() { 
		
		log.info("mapForGeolocation 시작!!");
		
		return "/map/mapForGeolocation";}
		
}
