package net.moonj.admgc.auth.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import net.moonj.admgc.auth.entity.Member;
import net.moonj.admgc.auth.mapper.MemberMapper;

@Component
public class MyShiroRealm extends AuthorizingRealm{

	
	@Autowired
	private MemberMapper memberMapper;
	

	@Override
	public String getName() {
		return "MyShiroRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}
/**
 * 授权
 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal() ;	// 取得用户登录名
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo() ;	// 定义授权信息的返回数据
		
		Member vo = memberMapper.selectOne(new QueryWrapper<Member>().eq("username", username));
		auth.setRoles(memberMapper.listRoles(vo.getMid()));
		auth.setStringPermissions(memberMapper.listPermission(vo.getMid()));
		
		
		return auth;
	}
/**
 * 认证
 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		List<Member> rs = memberMapper.selectList(new QueryWrapper<Member>().eq("username", username));
		if(rs.size()==0){
			throw new UnknownAccountException();//用户名错误
		}
		Member vo = rs.get(0);
		if(!vo.getPassword().equals(password)){
			throw new IncorrectCredentialsException();//密码错误
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,this.getName());
		return info;
	}



}
