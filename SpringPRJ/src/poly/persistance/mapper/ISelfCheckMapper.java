package poly.persistance.mapper;

import config.Mapper;
import poly.dto.SelfCheckDTO;

@Mapper("SelfCheckMapper")
public interface ISelfCheckMapper {

	int insertSCInfo(SelfCheckDTO pDTO) throws Exception;
}
