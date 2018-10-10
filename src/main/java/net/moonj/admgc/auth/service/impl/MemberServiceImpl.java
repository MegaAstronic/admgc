package net.moonj.admgc.auth.service.impl;

import net.moonj.admgc.auth.entity.Member;
import net.moonj.admgc.auth.mapper.MemberMapper;
import net.moonj.admgc.auth.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author varukiri
 * @since 2018-10-10
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
