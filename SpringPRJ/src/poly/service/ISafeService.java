package poly.service;

import poly.dto.NUserInfoDTO;
import poly.dto.SafeDTO;

public interface ISafeService {

	int insertSafeUserInfo(SafeDTO pDTO) throws Exception;

	int getUserSafeLoginCheck(SafeDTO pDTO) throws Exception;

	
	//개인회원
	int insertNUserInfo(NUserInfoDTO pDTO) throws Exception;

	int getNUserLoginCheck(NUserInfoDTO pDTO) throws Exception;

}
