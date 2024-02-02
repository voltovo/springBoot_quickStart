package com.study.datajpa.repository;

import com.study.datajpa.entity.Member;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "select m from Member m left join m.team t",
        countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, PageRequest pageRequest);
}
