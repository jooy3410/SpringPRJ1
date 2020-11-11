package poly.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import poly.dto.PagingDTO;
import poly.dto.RestDTO;
import poly.dto.SelfCheckDTO;
import poly.persistance.mapper.IRestMapper;
import poly.persistance.mapper.ISelfCheckMapper;
import poly.service.IRestService;
import test.Test;

@Configuration
@EnableScheduling
@Service("RestService")
public class RestService implements IRestService{
	
	@Resource(name="RestMapper")
	private IRestMapper restMapper;
	
	@Resource(name="SelfCheckMapper")
	private ISelfCheckMapper selfCheckMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	
	@Override
	@Scheduled(cron="0 0 5 ? * MON")
	public void InsertRestInfo() throws Exception{
		
		log.info(this.getClass().getName() + "start!");
		
		ArrayList<RestDTO> rList= new ArrayList<RestDTO>();
		ArrayList<RestDTO> r2List = null;
		
		int page = 1;   // 페이지 초기값 
	      try{
	    	  
	    	 int i = 1;
	    	 
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
	                  
	                  pDTO.setSeq(Integer.toString(i));
	                  pDTO.setBizplc_nm(Test.getTagValue("BIZPLC_NM", eElement)); //상호명
	                  pDTO.setRefine_lotno_addr(Test.getTagValue("REFINE_LOTNO_ADDR", eElement)); //구주소
	                  pDTO.setSafety_restrnt_no(Test.getTagValue("SAFETY_RESTRNT_NO", eElement));//식당번호             
	          		  pDTO.setRefine_roadnm_addr(Test.getTagValue("REFINE_ROADNM_ADDR", eElement)); //도로명주소
	          		  pDTO.setDetail_addr(Test.getTagValue("DETAIL_ADDR", eElement));
	          		  pDTO.setSigngu_nm(Test.getTagValue("SIGNGU_NM", eElement));
	          		  pDTO.setSido_nm(Test.getTagValue("SIDO_NM", eElement));
	          		  pDTO.setIndutype_nm(Test.getTagValue("INDUTYPE_NM", eElement));
	          		  pDTO.setIndutype_detail_nm(Test.getTagValue("INDUTYPE_DETAIL_NM", eElement));
	          		  
	          		  //pDTO.setRefine_wgs84_lat(Test.getTagValue("REFINE_WGS84_LAT", eElement)); //위도
	          		  //pDTO.setRefine_wgs84_logt(Test.getTagValue("REFINE_WGS84_LOGT", eElement)); //경도
	          		  		                  
	          		  rList.add(pDTO);
	          		  HashSet<RestDTO> pSet = new HashSet<>(rList);
	          		  r2List = new ArrayList<>(pSet);
	          			
	          		  i++;
	          		  	   	          		  
	          		  restMapper.InsertRestInfo(pDTO);
	          		           		  
	          		
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
		
		
		
	}
	
	@Override
	public List<String> getRestInfo() throws Exception{
			
		return restMapper.getRestInfo();
	}
	
	@Override
	public int selfCheck(SelfCheckDTO pDTO) throws Exception{
		
		int res = 0;
		
		if(pDTO == null) {
			pDTO = new SelfCheckDTO();
		}
		
		int success = selfCheckMapper.insertSCInfo(pDTO);
		
		log.info(this.getClass().getName() + "호이호이");
		
		if(success > 0) {
			res = 1;
		}
		
		log.info(res);
		
		return res;
	}
	
	@Override
	public int countBoard() throws Exception{
		
		return restMapper.countBoard();
	}

	@Override
	public List<RestDTO> selectBoard(PagingDTO pDTO) throws Exception{
		
		return restMapper.selectBoard(pDTO);
	}

	@Override
	public List<RestDTO> getRestSearchList(RestDTO eDTO) throws Exception{
		
		return restMapper.getRestSearchList(eDTO);
	}

	
	@Override
	public RestDTO getRestInfoDetail(RestDTO pDTO) throws Exception{
			
		return restMapper.getRestInfoDetail(pDTO);
	}
}
