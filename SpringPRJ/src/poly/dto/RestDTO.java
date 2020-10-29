package poly.dto;
public class RestDTO {

	private String bizplc_nm;
	private String refine_roadnm_addr;
	private String refine_lotno_addr; 
	private String refine_wgs84_lat;
	private String refine_wgs84_logt;
	public String safety_restrnt_no;
	
	@Override
	public int hashCode(){
	return (this.safety_restrnt_no.hashCode());
	}

	@Override
	public boolean equals(Object obj){
	if(obj instanceof RestDTO){
		RestDTO temp = (RestDTO) obj;
	if(this.safety_restrnt_no.equals(temp.safety_restrnt_no)){
	return true;
	  }
	 }
	return false;
	}
	
	public String getRefine_lotno_addr() {
		return refine_lotno_addr;
	}

	public void setRefine_lotno_addr(String refine_lotno_addr) {
		this.refine_lotno_addr = refine_lotno_addr;
	}
	
	public String getSafety_restrnt_no() {
		return safety_restrnt_no;
	}
	public void setSafety_restrnt_no(String safety_restrnt_no) {
		this.safety_restrnt_no = safety_restrnt_no;
	}
	
	public String getBizplc_nm()
	{
		return bizplc_nm;
	}
	public void setBizplc_nm(String bizplc_nm) {
		this.bizplc_nm = bizplc_nm;
	}
	public String getRefine_roadnm_addr() {
		return refine_roadnm_addr;
	}
	public void setRefine_roadnm_addr(String refine_roadnm_addr) {
		this.refine_roadnm_addr = refine_roadnm_addr;
	}
	public String getRefine_wgs84_lat() {
		return refine_wgs84_lat;
	}
	public void setRefine_wgs84_lat(String refine_wgs84_lat) {
		this.refine_wgs84_lat = refine_wgs84_lat;
	}
	public String getRefine_wgs84_logt() {
		return refine_wgs84_logt;
	}
	public void setRefine_wgs84_logt(String refine_wgs84_logt) {
		this.refine_wgs84_logt = refine_wgs84_logt;
	}

}
