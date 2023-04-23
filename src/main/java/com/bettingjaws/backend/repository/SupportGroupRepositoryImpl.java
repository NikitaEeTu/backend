package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.api.dtos.SupportGroup;
import com.bettingjaws.backend.api.dtos.SupportGroupType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SupportGroupRepositoryImpl implements SupportGroupRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<SupportGroup> supportGroupRowMapper = createSupportGroupRowMapper();
    private final static Integer MAX_LIMIT = 3;

    public SupportGroupRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SupportGroup getSupportGroupById(String id) {
        List<SupportGroup> supportGroupList = jdbcTemplate.query("SELECT * FROM support_group_table WHERE id = :supportGroupId", Map.of("supportGroupId", id), supportGroupRowMapper);
        return supportGroupList.get(0);
    }

    @Override
    public List<SupportGroup> getAllSupportGroups(Integer limit, Integer offset, String country) {
        if(limit > MAX_LIMIT){
            limit = MAX_LIMIT;
        }
        List<SupportGroup> supportGroupList = jdbcTemplate.query("select support_group_table.group_name, support_group_table.city, support_group_table.group_type, support_group_table.website, specialist_table.first_name, specialist_table.last_name " +
                        "from support_group_table " +
                        "left join specialist_table on specialist_table.id = support_group_table.creator_id " +
                        "WHERE support_group_table.country = :country LIMIT :limit OFFSET :offset",
                Map.of(
                        "limit", limit,
                        "offset", offset,
                        "country", country
                ),
                supportGroupRowMapper);
        return supportGroupList;
    }

    @Override
    public Integer getSupportGroupNumber(String country) {
        int numberOfSpecialists = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM support_group_table WHERE country= :country",
                Map.of("country", country),
                Integer.class);
        return numberOfSpecialists;
    }

    private RowMapper<SupportGroup> createSupportGroupRowMapper(){
        return ((rs, rowNum) -> {
            String groupName = rs.getString("group_name");
            String city = rs.getString("city");
            String type = rs.getString("group_type");
            String first_name = rs.getString("first_name");
            String last_name =  rs.getString("last_name");
            String website = rs.getString("website");

            return new SupportGroup(
                    groupName,
                    city,
                    SupportGroupType.valueOf(type),
                    website,
                    first_name,
                    last_name
            );
        });
    }
}
