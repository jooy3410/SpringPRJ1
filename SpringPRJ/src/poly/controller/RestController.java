package poly.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	@RequestMapping(value = "rest.do", method = RequestMethod.GET)
	public String senMail(HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + "rest start!");

		
		List<RestDTO> rList= new ArrayList<>();
		
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
	                  System.out.println("상호명  : " + Test.getTagValue("BIZPLC_NM", eElement));
	                  System.out.println("주소  : " + Test.getTagValue("ADDR", eElement));
	                  RestDTO pDTO = new RestDTO();
	                  pDTO.setAddr(Test.getTagValue("BIZPLC_NM", eElement));
	          		  pDTO.setBizplc_nm(Test.getTagValue("ADDR", eElement));
	          		  rList.add(pDTO);
	                  
							/*
							 * System.out.println("금융사  : " + getTagValue("kor_co_nm", eElement));
							 * System.out.println("상품 코드  : " + getTagValue("fin_prdt_cd", eElement));
							 * System.out.println("상품명 : " + getTagValue("fin_prdt_nm", eElement));
							 * System.out.println("연평균 수익률  : " + getTagValue("avg_prft_rate", eElement));
							 * System.out.println("공시 이율  : " + getTagValue("dcls_rate", eElement));
							 */
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

		

		model.addAttribute("rList",rList);
		
		return "/rest";
	}
}
