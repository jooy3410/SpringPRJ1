package poly.persistance.mapper;

import config.Mapper;
import poly.dto.SafeDTO;


@Mapper("SafeMapper")
public interface ISafeMapper {

	int insertSafeUserInfo(SafeDTO pDTO) throws Exception;
	
	SafeDTO getSafeUserExists(SafeDTO pDTO) throws Exception;

	
	SafeDTO getUserSafeLoginCheck(SafeDTO pDTO) throws Exception;

	







}
