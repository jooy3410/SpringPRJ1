package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

	
	@Service("UserService")
	public class UserService implements IUserService {
		
		@Resource(name = "UserMapper")
		IUserMapper userMapper;


		@Override
		public UserDTO getUserInfo(UserDTO uDTO) {
			// TODO Auto-generated method stub
			return userMapper.getUserInfo(uDTO);
		}

		
		
		
		
		

}
