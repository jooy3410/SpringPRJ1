package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.PagingDTO;
import poly.dto.RestDTO;

@Mapper("RestMapper")
public interface IRestMapper {

	int InsertRestInfo(RestDTO pDTO) throws Exception;
	
	List<String> getRestInfo() throws Exception;
	
	RestDTO getRestInfoDetail(RestDTO pDTO) throws Exception;
	
	List<RestDTO> selectBoard(PagingDTO pDTO) throws Exception;

	int countBoard() throws Exception;

	public List<RestDTO> getRestSearchList(RestDTO eDTO) throws Exception;
}
