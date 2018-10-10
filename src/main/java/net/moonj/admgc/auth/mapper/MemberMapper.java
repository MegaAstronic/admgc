package net.moonj.admgc.auth.mapper;

import net.moonj.admgc.auth.entity.Member;

import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author varukiri
 * @since 2018-10-10
 */
public interface MemberMapper extends BaseMapper<Member> {

	public Set<String> listRoles(Integer mid);
	
	public Set<String> listPermission(Integer mid);
}
