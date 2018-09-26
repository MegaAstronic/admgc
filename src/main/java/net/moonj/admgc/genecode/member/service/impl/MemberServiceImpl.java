package net.moonj.admgc.genecode.member.service.impl;

import net.moonj.admgc.genecode.member.entity.Member;
import net.moonj.admgc.genecode.member.mapper.MemberMapper;
import net.moonj.admgc.genecode.member.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admgc
 * @since 2018-09-26
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
