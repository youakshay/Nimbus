package com.service.Nimbus.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("pool_members")
public record PoolMember(
        @Id  Long id,
        Long pool_id,
        Long trip_id
){}
