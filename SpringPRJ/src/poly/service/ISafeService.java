package poly.service;

import poly.dto.SafeDTO;

public interface ISafeService {

	int insertSafeUserInfo(SafeDTO pDTO) throws Exception;

	int getUserSafeLoginCheck(SafeDTO pDTO) throws Exception;

}
