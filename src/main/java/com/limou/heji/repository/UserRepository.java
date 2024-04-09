package com.limou.heji.repository;

import com.limou.heji.model.dto.HejiUserDto;
import com.limou.heji.model.po.HejiUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author limoum0u
 * @date 24/3/30 21:34
 */
public interface UserRepository extends BaseRepository<HejiUser, Long> {
    Optional<HejiUser> findByUsername(String username);


    @Query("""
            select new com.limou.heji.model.dto.HejiUserDto(
            h.userId, h.username, h.avatar
            ) from HejiUser h where h.username like %?1%
            """)
    List<HejiUserDto> findByUsernameLike(String username);


}
