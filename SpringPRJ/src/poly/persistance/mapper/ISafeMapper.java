package poly.persistance.mapper;

import config.Mapper;
import poly.dto.NUserInfoDTO;
import poly.dto.SafeDTO;


@Mapper("SafeMapper")
public interface ISafeMapper {

	int insertSafeUserInfo(SafeDTO pDTO) throws Exception;
	
	SafeDTO getSafeUserExists(SafeDTO pDTO) throws Exception;

	
	SafeDTO getUserSafeLoginCheck(SafeDTO pDTO) throws Exception;

	
	//개인회원
	

	int insertNUserInfo(NUserInfoDTO pDTO) throws Exception;

	
	NUserInfoDTO getNUserExists(NUserInfoDTO pDTO) throws Exception;
	
	
	NUserInfoDTO getNUserLoginCheck(NUserInfoDTO pDTO) throws Exception;

	







}
