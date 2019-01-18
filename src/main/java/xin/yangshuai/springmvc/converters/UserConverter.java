package xin.yangshuai.springmvc.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xin.yangshuai.springmvc.entities.User;

/**
 * UserConverter
 *
 * @author shuai
 * @date 2019/1/18
 */
@Component
public class UserConverter implements Converter<String,User> {
	@Override
	public User convert(String source) {
		//username-password-age-email
		if(StringUtils.isNotBlank(source)){
			String[] split = source.split("-");
			if (split.length == 4){
				User user = new User(split[0], split[1], Integer.parseInt(split[2]), split[3]);
				System.out.println("UserConverter-source:"+source);
				System.out.println("UserConverter-user:"+user);
				return user;
			}
		}
		return null;
	}
}
